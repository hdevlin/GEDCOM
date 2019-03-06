package MyPackage;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class family implements Comparable<family>{
    
    String id;
    String marrdate;
    String divdate;//NA if family was not divorced
    String husbandid;
    //String husbandname;
    String wifeid;
    //String wifename;
    ArrayList<String> children;
    
    public family(){
        id = "";
        marrdate = "";
        divdate = "NA";
        husbandid = "";
        //husbandname = "";
        wifeid = "";
        //wifename = "";
        
        children = new ArrayList<String>(8);
        for(int i = 0; i < children.size(); i++){
            children.set(i, "");
        }
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
	
    public String getID(){
        return this.id;
    }
    public String setID(String input){
        this.id = input;
        return input;
    }
    
    public String getMarrdate(){
        return this.marrdate;
    }
    public String setMarrdate(String input){
        this.marrdate = input;
        return input;
    }
    
    public String getDivdate(){
        return this.divdate;
    }
    public String setDivdate(String input){
        this.divdate = input;
        return input;
    }
    
    public String getHusbandid(){
        return this.husbandid;
    }
    public String setHusbandid(String input){
        this.husbandid = input;
        return input;
    }
    public boolean marryBeforeDivorce() //returns true if the marriage happened before the divorce (US04)
    {
    	boolean value = true;
    	if (divdate != "NA")
    	{
        	date marry = new date(marrdate);
        	date divorce = new date(divdate);
        	value = date.before(marry, divorce);
    	}
    	return value;

    }
	
	public boolean marriageBeforeCurrentDate(){ //returns true if wedding date is before the current date (US01)
		if(marrdate != ""){
			Date curr = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
			try{
			return curr.compareTo(sdf.parse(marrdate)) >= 0;
			} catch(Exception e){
				System.out.println("failed parse with exception" + e.toString());
			}
		}
		return true;
	}
	
	public boolean divorceBeforeCurrentDate(){ //returns true if divorce date is before the current date (US01)
		if(divdate != "NA"){
			Date curr = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
			try{
			return curr.compareTo(sdf.parse(divdate)) >= 0;
			} catch(Exception e){
				System.out.println("failed parse with exception" + e.toString());
			}
		}
		return true;
	}
    
    /*public String getHusbandname(){
        return this.husbandname;
    }
    public String setHusbandname(String input){
        this.husbandname = input;
        return input;
    }*/
    
    public String getWifeid(){
        return this.wifeid;
    }
    public String setWifeid(String input){
        this.wifeid = input;
        return input;
    }
    
    public ArrayList<String> getChildren(){
        return this.children;
    }
    public String setChildren(String input){
        this.children.ensureCapacity(this.children.size()+1);
        this.children.add(input);
        return input;
    }
	public boolean birthBeforeMarriage(ArrayList<person> arrList)
	{					
		date weddingDate = new date(marrdate);
		for(int j=0; j<children.size(); j++){
			String child = children.get(j);
			person person1 = locationInArrList(arrList, child);
			if (person1 != null)
			{
				String birthday = person1.getBirthday();
				if (birthday != "")
				{
					date birthdayDate = new date(birthday);
					
					if(!weddingDate.before(weddingDate, birthdayDate)){
						return true;
					}
				}
			}
			else
			{
				throw new NullPointerException("Person is  null");
			}
		}
		return false;
	}
    
    @Override
    public String toString(){
        String x = "";
        for(int i = 0; i < this.children.size(); i++){
            x = x.concat(this.children.get(i) + ", ");
        }
        return "ID:"+this.id+" Married:"+this.marrdate+" Divorced:"+this.divdate+" HusbandID:"+this.husbandid+" WifeID:"+this.wifeid+" Children:"+x;
    }
    
    @Override
    public int compareTo(family f){
        return this.getID().compareTo(f.getID());
    }
}