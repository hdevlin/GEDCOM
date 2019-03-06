package MyPackage;

public class date {
	//holds operations for working with dates
	public int day, month, year;
	static int convertMonth(String str) //converts a month to number
	{
		switch(str)
		{
		case "JAN":
			return 1;
		case "FEB":
			return 2;
		case "MAR":
			return 3;
		case "APR":
			return 4;
		case "MAY":
			return 5;
		case "JUN":
			return 6;
		case "JUL":
			return 7;
		case "AUG":
			return 8;
		case "SEP":
			return 9;
		case "OCT":
			return 10;
		case "NOV":
			return 11;
		case "DEC":
			return 12;
		default:
			return -1;
		}
	}
	public date(String str) //expects a string of form 'day month year'"
	{
		int firstSpace = str.indexOf(" ");
		day = Integer.parseInt(str.substring(0,firstSpace));
		int secondSpace = str.substring(firstSpace+1).indexOf(" ")+firstSpace+1;
		month = convertMonth(str.substring(firstSpace+1,secondSpace));
		year = Integer.parseInt(str.substring(secondSpace+1));
	}
	public String toString()
	{
		return Integer.toString(day) + " " + Integer.toString(month) + " " + Integer.toString(year);
	}
	public static boolean before(date d1, date d2) //returns whether or not d1 comes before d2
	{
		
		return (d1.year < d2.year) || (d1.year == d2.year && d1.month < d2.month)|| (d1.year == d2.year && d1.month == d2.month && d1.day < d2.day);
	}
}
