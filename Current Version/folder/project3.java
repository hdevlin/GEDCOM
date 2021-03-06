/*
 * Nick Cacchione
 * Hunter Devlin
 * Leo Ouyang
 * I pledge my honor that I have abided by the Stevens Honor System
*/

package MyPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class project3{
    
    /*public static int famIndex(String input, ArrayList<family> arr){
        for(int i = 0; i < arr; i+){
            if(input.equals(arr.get(i).getID))
                return i;
        }
        return -1;
            
    }*/
    
    //given array of strings and a string, returns location of that string in input array
    public static int locationInArr(String[] arr, String s, int curr){
        if(arr.length <= curr)
            return -1;
        if(arr[curr].equals(s))
            return curr;
        return locationInArr(arr, s, ++curr);
    }
	
	public person locationInArrList(ArrayList<person> arrList, String id){
		int size = arrList.size();
		for (int i = 0; i < size; i ++)
		{
			if (arrList.get(i).getID().equals(id))
			{
				return arrList.get(i);
			}
		}
		return null;
	}
    
    public static void main(String[] args)throws Exception{
        
        String[] acceptedTags = {"NAME","SEX","BIRT","DEAT","FAMC","FAMS","MARR","HUSB","WIFE","CHIL","DIV","DATE","HEAD","TRLR","NOTE"};
        
        ArrayList<person> people = new ArrayList<person>();
        ArrayList<family> families = new ArrayList<family>();
        int currPersonIndex = -1;//keeps track of who the current person is for tags that are specific to a person (such as NAME)
        int currFamilyIndex = -1;//keeps track of who the current family is for tags that are specific to a family (such as HUSB)
        String lastDate = "";//used to determine whether DATE applies to BIRT, DEAT, MARR, or DIV
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the file name.");
        File f = new File(sc.nextLine());
        if(!f.isFile()){
            System.out.println("Argument given is not a valid file. Please specify the path of a GEDCOM file");
            System.exit(1);
        }
        
        
        
        BufferedReader br = new BufferedReader(new FileReader(f));
        
        int x = -1;
        String s = "";
        while((s = br.readLine()) != null){
            
            if(s.isEmpty()){
                System.out.println();
                continue;
            }
            
            String first;
            String second;
            String third;
            
            x = s.indexOf(" ");
            if(x == -1){
                if(locationInArr(acceptedTags, s, 0) == -1){
                    first = "";
                } else{
                    first = s;
                    s = "";
                }
            } else{
                first = s.substring(0,x);
                s = s.substring(x+1);
            }
            
            x = s.indexOf(" ");
            if(x == -1){
                if(locationInArr(acceptedTags, s, 0) == -1){
                    second = "";
                } else{
                    second = s;
                    s = "";
                }
            } else{
                second = s.substring(0,x);
                s = s.substring(x+1);
            }
            
            x = s.indexOf(" ");
            if(x == -1){
                if(!(s.equals("INDI") || s.equals("FAM"))){
                    third = "";
                } else{
                    third = s;
                    s = "";
                }
            } else{
                third = s.substring(0,x);
                s = s.substring(x);
            }
            
            if(third.equals("INDI") && first.equals("0")){
                people.ensureCapacity(people.size()+10);//increase size of people if necessary
                person newPerson = new person();
                newPerson.setID(second);
                people.add(newPerson);
                currPersonIndex++;
            } else if(third.equals("FAM") && first.equals("0")){
                families.ensureCapacity(families.size()+10);//increase size of families if necessary
                family newFamily = new family();
                newFamily.setID(second);
                families.add(newFamily);
                currFamilyIndex++;
            } else if(second.equals("NAME") && first.equals("1")){
                if(currPersonIndex != -1)
                    people.get(currPersonIndex).setName(third.concat(s));//set name of current person to arguments from line
            }else if(second.equals("SEX") && first.equals("1")){
                if(currPersonIndex != -1)
                    people.get(currPersonIndex).setGender(third.concat(s));
            } else if(second.equals("BIRT") && first.equals("1")){
                lastDate = "BIRT";
            } else if(second.equals("DEAT") && first.equals("1")){
                if(currPersonIndex != -1){
                    lastDate = "DEAT";
                    people.get(currPersonIndex).setAlive(false);
                }
            } else if(second.equals("FAMC") && first.equals("1")){
                if(currPersonIndex != -1){
                    people.get(currPersonIndex).setChildren(third+s);
                }
            } else if(second.equals("FAMS") && first.equals("1")){
                if(currPersonIndex != -1){
                    people.get(currPersonIndex).setSpouse(third+s);
                }
            } else if(second.equals("MARR") && first.equals("1")){
                lastDate = "MARR";
            } else if(second.equals("HUSB") && first.equals("1")){
                if(currFamilyIndex != -1){
                    families.get(currFamilyIndex).setHusbandid(third+s);
                }
            } else if(second.equals("WIFE") && first.equals("1")){
                if(currFamilyIndex != -1)
                    families.get(currFamilyIndex).setWifeid(third+s);
            } else if(second.equals("CHIL") && first.equals("1")){
                if(currFamilyIndex != -1)
                    families.get(currFamilyIndex).setChildren(third+s);
            } else if(second.equals("DIV") && first.equals("1")){
                lastDate = "MARR";
            } else if(second.equals("DATE") && first.equals("2")){
                if(lastDate.equals("BIRT")){
                    if(currPersonIndex != -1)
                    {
                    	person current = people.get(currPersonIndex);
                        current.setBirthday(third.concat(s));
                        if (!current.birthBeforeDeath())
                        {
                        	System.out.println("ERROR: Birth occured after Death");
                        }
                    }
                    
                } else if(lastDate.equals("DEAT")){
                    if(currPersonIndex != -1)
                    {
                    	person current = people.get(currPersonIndex);
                        current.setDeathdate(third.concat(s));
                        if (!current.birthBeforeDeath())
                        {
                        	System.out.println("ERROR: Birth occured after Death");
                        }
                    }
                } else if(lastDate.equals("MARR")){
                    if(currFamilyIndex != -1)
                    {
                    	family current = families.get(currFamilyIndex);
                    	current.setMarrdate(third.concat(s));
                    	if (!current.marryBeforeDivorce())
                    	{
                    		System.out.println("ERROR: Marriage after Divorce");
                    	}
                    }
                } else if(lastDate.equals("DIV")){
                    if(currFamilyIndex != -1)
                    {
                    	family current = families.get(currFamilyIndex);
                    	current.setDivdate(third.concat(s));
                    	if (!current.marryBeforeDivorce())
                    	{
                    		System.out.println("ERROR: Marriage after Divorce");
                    	}

                    }
                }
            }
           
        }
        
        Collections.sort(people);
        Collections.sort(families);
		
		for(int i=0; i<families.size(); i++){
			if (families.get(i).birthBeforeMarriage(people))
			{
				System.out.println("ERROR: Birthday of child is before Wedding Date of parents");
			}
			
		}
		
        
        for(int i=0; i<people.size(); i++){
            System.out.println(people.get(i).toString());
        }
        System.out.println();
        for(int i=0; i<families.size(); i++){
            System.out.println(families.get(i));
        }
    }
}