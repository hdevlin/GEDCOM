/*
 * Hunter Devlin
 * I pledge my honor that I have abided by the Stevens Honor System
*/

import java.io.*;

public class project2{
    
    //given array of strings and a string, returns location of that string in input array
    public static int helper(String[] arr, String s, int curr){
        if(arr.length <= curr)
            return -1;
        if(arr[curr].equals(s))
            return curr;
        return helper(arr, s, ++curr);
        
    }
    
    
    // The main function takes in 1 argument, which is the path to a GEDCOM file (if GEDCOM file is in the same folder as the executable, then just the file name).
    
    public static void main(String[] args)throws Exception{
        
        String[] acceptedTags = {"NAME","SEX","BIRT","DEAT","FAMC","FAMS","MARR","HUSB","WIFE","CHIL","DIV","DATE","HEAD","TRLR","NOTE"};
        String[] acceptedTagLevel = {"1","1","1","1","1","1","1","1","1","1","1","2","0","0","0"};
        
        if(args.length != 1){
            System.out.println("1 argument expected, got " + args.length + ". The argument should be the path of the GEDCOM file");
            System.exit(1);
        }
        
        File f = new File(args[0]);
        if(!f.isFile()){
            System.out.println("Argument given is not a valid file. Please specify the path of a GEDCOM file");
            System.exit(1);
        }
        
        
        
        BufferedReader br = new BufferedReader(new FileReader(f));
        
        int x = -1;
        String s = "";
        while((s = br.readLine()) != null){
            System.out.println("--> " + s);
            System.out.print("<-- ");
            if(s.isEmpty()){
                System.out.println();
                continue;
            }
            
            String first;
            String second;
            String third;
            
            x = s.indexOf(" ");
            if(x == -1){
                if(helper(acceptedTags, s, 0) == -1){
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
                if(helper(acceptedTags, s, 0) == -1){
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
            
            int y = helper(acceptedTags, second, 0);
            if((third.equals("INDI") || third.equals("FAM")) && first.equals("0")){
                System.out.println("0|" + third + "|Y|" + second);
            } else if((y != -1) && (acceptedTagLevel[y].equals(first))){
                System.out.println(first + "|" + second + "|Y|" + third +  s);
            } else{
                System.out.println(first + "|" + second + "|N|" + third +  s);
            }
            
            
            
        }
    }
}