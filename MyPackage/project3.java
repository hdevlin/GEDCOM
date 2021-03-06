/*
 * Nick Cacchione
 * Hunter Devlin
 * Leo Ouyang
 * I pledge my honor that I have abided by the Stevens Honor System
*/

package MyPackage;

import java.io.*;
import java.math.BigInteger;
import java.util.Date;
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
    
    public static int convertMilliToYears(long milli)
    {
    	return ((int)(milli/1000/60/60/24/365));
    }
    
    public static boolean sameBirthday(person person1, person person2) {
    	if (person1.getBirthday() == person2.getBirthday()) {
    		return true;
    	}
    	return false;
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
                    }
                    
                } else if(lastDate.equals("DEAT")){
                    if(currPersonIndex != -1)
                    {
                    	person current = people.get(currPersonIndex);
                        current.setDeathdate(third.concat(s));
                    }
                } else if(lastDate.equals("MARR")){
                    if(currFamilyIndex != -1)
                    {
                    	family current = families.get(currFamilyIndex);
                    	current.setMarrdate(third.concat(s));
                    }
                } else if(lastDate.equals("DIV")){
                    if(currFamilyIndex != -1)
                    {
                    	family current = families.get(currFamilyIndex);
                    	current.setDivdate(third.concat(s));

                    }
                }
            }
           
        }
        
        Collections.sort(people);
        Collections.sort(families);
        
        int sameBirthdayChildren = 1;
        
		for (int i = 0; i< people.size()-1; i ++)
        String surname = people.get(0).getSurname();
		for (int i = 0; i< people.size(); i ++)
        {
        	person current = people.get(i);
        	person next = people.get(i+1);
        	if (current.getBirthday() != "")
        	{
	        	date birthday = new date(current.getBirthday());
	        	Date b = birthday.convert();
	        	if (current.getDeathdate() != "NA")
	        	{
	        		current.setAge(convertMilliToYears(new date(current.getDeathdate()).convert().getTime() - b.getTime()));
	        	}
	        	else
	        	{
	        		current.setAge(convertMilliToYears(System.currentTimeMillis()-b.getTime()));
	        	}
        	}
            if (!current.birthBeforeDeath())
            {
            	System.out.println("US03: ERROR: Birth occured after Death");
            }
			if(!current.birthBeforeCurrentDate()){
				System.out.println("US01: Error: Birth occured after the current date");
			}
			if(!current.deathBeforeCurrentDate()){
				System.out.println("US01: Error: Death occured after the current date");
			}
			
			
			if (sameBirthday(current, next)) {
				sameBirthdayChildren += 1;
			}
			
			if(sameBirthdayChildren > 5) {
				System.out.println("US18: Error: Too many children with same birthday");
			}
			
			
			
			date currentBirthday = new date(current.getBirthday());
			date nextBirthday = new date(next.getBirthday());
			String differenceString = String.valueOf(currentBirthday.difference(nextBirthday));
			BigInteger eightmonths = new BigInteger("21020000000");
			BigInteger twodays = new BigInteger("172800000");
			BigInteger difference = new BigInteger(differenceString);
			
			if (difference.compareTo(eightmonths)!=1 && difference.compareTo(eightmonths)!=0) {
				System.out.println("US17: Error: Siblings born less than 8 months apart");
			}
			else if(difference.compareTo(twodays)==1) {
				System.out.println("US17: Error: Siblings born greater than 2 days apart");
			}
			
			if(current.getGender()=="M" && current.getSurname()!= surname) {
				System.out.println("US20: Error: Males do not have the same last name");
			}
			if(current.getSpouse() == current.getChildren()) {
				System.out.println("US21: Error: Parents cannot marry descendants");
			}
			

        }
		
		
		
		for(int i=0; i<families.size(); i++){
			family current = families.get(i);
            person dad = current.locationInArrList(people, current.husbandid);
            person mom = current.locationInArrList(people, current.wifeid);
            for (int j = 0; j < families.size(); j ++)
            {
            	family find = families.get(j); //we're going to search through every family
            	boolean foundDad = false;
            	boolean foundMom = false;
            	for (int k = 0; k < find.children.size(); k ++)
            	{
            		foundDad = foundDad || find.children.get(k).equals(current.husbandid);
            		foundMom = foundMom || find.children.get(k).equals(current.wifeid);
            		if (foundDad && foundMom)
            		{
            			System.out.println("US22: Error: Siblings married");
            			break;
            		}
            	}
            	if (foundDad && foundMom)
            	{
            		break;
            	}
            }
            if (dad.getBirthday() != "" && mom.getBirthday() != "")
            {
	            date d = new date(dad.getBirthday());
	            date m = new date(mom.getBirthday());
	            date marriage = new date(current.getMarrdate());
	            if (marriage.difference(d) < 14 || marriage.difference(m) < 14) //if either parent was younger than 14 when married
	            {
	            	System.out.println("US10: ERROR: One parent was younger than 14 when married");
	            }
            }
            if (current.incest(people))
            {
            	System.out.println("US21: ERROR: Parent married descendent");
            }
            if (current.dadOld(dad, people) == true) {
                System.out.println("US12: ERROR: Dad is 80+ years older than a child");
            }
            if (current.momOld(mom, people) == true) {
                System.out.println("US12: ERROR: Mom is 60+ years older than a child");
            }
			if (current.birthBeforeMarriage(people))
			{
				System.out.println("US08: ERROR: Birthday of child is before Wedding Date of parents");
			}
        	if (!current.marryBeforeDivorce())
        	{
        		System.out.println("US04: ERROR: Marriage after Divorce");
        	}
			if(!current.marriageBeforeCurrentDate()){
				System.out.println("US01: Error: Marriage occured after the current date");
			}
			if(!current.divorceBeforeCurrentDate()){
				System.out.println("US01: Error: Divorce occured after the current date");
			}
        	person birth = current.birthBeforeDeath(people);
        	if (birth != null)
        	{
        		System.out.println("US09: ERROR: Birthday of :"  + birth.getName() + " is after death of either parent");
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