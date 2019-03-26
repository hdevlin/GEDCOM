package MyPackage;

import java.util.Date;
import java.text.SimpleDateFormat;

public class person implements Comparable<person>{
    private String id;
    private String name;
    private String gender;
    private String birthday;
    private int age;
    private boolean alive;
    private String deathdate;//NA if perosn is alive
    private String children;
    private String spouse;
    private String weddingdate;
    private String divorcedate;
    
    public person(){
        id = "";
        name = "";
        gender = "";
        birthday = "";
        age = 0;
        alive = true;
        deathdate = "NA";
        children = "NA";
        spouse = "NA";
        weddingdate = "NA";
        divorcedate = "NA";
    }
    
    public String getID(){
        return this.id;
    }
    public String setID(String input){
        this.id = input;
        return input;
    }
    
    public String getName(){
        return this.name;
    }
    public String setName(String input){
        this.name = input;
        return input;
    }
    
    public String getGender(){
        return this.gender;
    }
    public String setGender(String input){
        this.gender = input;
        return input;
    }
    
    public String getBirthday(){
        return this.birthday;
    }
    public String setBirthday(String input){
        this.birthday = input;
        return input;
    }
    
    public int getAge(){
        return this.age;
    }
    public int setAge(int input){
        this.age = input;
        return input;
    }
    
    public boolean getAlive(){
        return this.alive;
    }
    public boolean setAlive(boolean input){
        this.alive = input;
        return input;
    }
    
    public String getDeathdate(){
        return this.deathdate;
    }
    public String setDeathdate(String input){
        this.deathdate = input;
        return input;
    }
    
    public String getChildren(){
        return this.children;
    }
    public String setChildren(String input){
        this.children = input;
        return input;
    }
    
    public String getSpouse(){
        return this.spouse;
    }
    public String setSpouse(String input){
        this.spouse = input;
        return input;
    }
    public String setWeddingdate(String input) {
        if(this.weddingdate != null && this.divorcedate == null) {
            System.out.println("US11: Cannot be married again without being divorced");
        }
    	this.weddingdate = input;
    	return input;
    }
    
    public String setDivorcedate(String input) {
    	this.divorcedate = input;
    	return input;
    }
    
    @Override
    public String toString(){
        return "ID:"+this.id+" Name:"+this.name+" Gender:"+this.gender+" Birthday:"+this.birthday+" Alive:"+this.alive+" Death:"+this.deathdate+" Child:"+this.children+" Spouse:"+this.spouse;
        //TODO: add age calculation
    }
    
    public boolean birthBeforeDeath() //returns true if birth is before death (US03)
    {
    	boolean error = true;
    	if (deathdate != "NA" && birthday != "")
    	{
        	date birth = new date(birthday);
        	date death = new date(deathdate);
        	error = date.before(birth, death);
        	
    	}
    	return error;
    }
    
    public boolean marriageBeforeDeath() {
    	//returns true if marriage is before death
    	
    	boolean error = true;
    	if (weddingdate != "NA" && deathdate != "NA") {
    		date wedding = new date(weddingdate);
    		date death = new date(deathdate);
    		error = date.before(wedding, death);
    	}
    	return error;
    }
    public boolean divorceBeforeDeath() {
    	//returns true if divorce is before death
    	
    	boolean error = true;
    	if (divorcedate != "NA" && deathdate != "NA") {
    		date divorce = new date(divorcedate);
    		date death = new date(deathdate);
    		error = date.before(divorce, death);
    	}
    	return error;
    }
	
	public boolean birthBeforeCurrentDate(){ //returns true if birthday is before the current date (US01)
		if(birthday != ""){
			Date curr = new Date();
			try{
			return curr.compareTo(new date(birthday).convert()) >= 0;
			} catch(Exception e){
				System.out.println("failed parse with exception" + e.toString());
			}
		}
		return true;
	}
	
	public boolean deathBeforeCurrentDate(){ //returns true if death date is before the current date (US01)
		if(deathdate != "NA"){
			Date curr = new Date();
			try{
			return curr.compareTo(new date(deathdate).convert()) >= 0;
			} catch(Exception e){
				System.out.println("failed parse with exception" + e.toString());
			}
		}
		return true;
	}
	
	public boolean marriageBeforeCurrentDate(){ //returns true if wedding date is before the current date (US01)
		if(weddingdate != "NA"){
			Date curr = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
			try{
			return curr.compareTo(sdf.parse(weddingdate)) >= 0;
			} catch(Exception e){
				System.out.println("failed parse with exception" + e.toString());
			}
		}
		return true;
	}
	
	public boolean divorceBeforeCurrentDate(){ //returns true if divorce date is before the current date (US01)
		if(divorcedate != "NA"){
			Date curr = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
			try{
			return curr.compareTo(sdf.parse(divorcedate)) >= 0;
			} catch(Exception e){
				System.out.println("failed parse with exception" + e.toString());
			}
		}
		return true;
	}
	
	public boolean birthBeforeMarriage() //returns true if birth is before marriage (US02)
    {
    	boolean error = true;
    	if (deathdate != "NA" && weddingdate != "NA")
    	{
        	date birth = new date(birthday);
        	date wedding = new date(weddingdate);
        	error = date.before(birth, wedding);
        	
    	}
    	return error;
    }
	
    
    @Override
    public int compareTo(person p){
        return this.getID().compareTo(p.getID());
    }
    
    
}