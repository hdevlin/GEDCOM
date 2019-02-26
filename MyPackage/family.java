package MyPackage;

import java.util.ArrayList;

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
        	Date marry = new Date(marrdate);
        	Date divorce = new Date(divdate);
        	value = Date.before(marry, divorce);
    	}
    	return value;

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