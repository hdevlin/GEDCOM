package MyPackage;

public class person implements Comparable<person>{
    private String id;
    private String name;
    private String gender;
    private String birthday;
    private String age;
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
        age = "";
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
    
    public String getAge(){
        return this.age;
    }
    public String setAge(String input){
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
    	if (deathdate != "NA")
    	{
        	Date birth = new Date(birthday);
        	Date death = new Date(deathdate);
        	error = Date.before(birth, death);
        	
    	}
    	return error;
    }
    
    public boolean marriageBeforeDeath() {
    	//returns true if marriage is before death
    	
    	boolean error = true;
    	if (weddingdate != "NA") {
    		Date wedding = new Date(weddingdate);
    		Date death = new Date(deathdate);
    		error = Date.before(wedding, death);
    	}
    	return error;
    }
    public boolean divorceBeforeDeath() {
    	//returns true if divorce is before death
    	
    	boolean error = true;
    	if (divorcedate != "NA") {
    		Date divorce = new Date(divorcedate);
    		Date death = new Date(deathdate);
    		error = Date.before(divorce, death);
    	}
    	return error;
    }
    
    @Override
    public int compareTo(person p){
        return this.getID().compareTo(p.getID());
    }
    
    
}