package MyPackage;
import java.util.Date;

public class date {
	//holds operations for working with dates
	public int day = -1, month = -1, year = -1;
	public static int getDaysForMonth(int month, int year) //given a month and a year, returns the number of days in that month
	{
		int value = -1;
		switch (month)
		{
		case 1:
			value = 31;
			break;
		case 2:
			value = 28 + (year%4 == 0 ? 1 : 0 );
			break;
		case 3:
			value = 31;
			break;
		case 4:
			value = 30;
			break;
		case 5:
			value = 31;
			break;
		case 6:
			value = 30;
			break;
		case 7:
			value = 31;
			break;
		case 8:
			value = 31;
			break;
		case 9:
			value = 30;
			break;
		case 10:
			value = 31;
			break;
		case 11:
			value = 30;
			break;
		case 12:
			value = 31;
			break;
		}
		return value;
	}
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
	private date()
	{
		
	}
	public date(String str) //expects a string of form 'day month year'"
	{
		if (str == "")
		{
			return;
		}
		try {
			int firstSpace = str.indexOf(" ");
			if (firstSpace == -1) //this means the date only has a year
			{
				year = Integer.parseInt(str);
			}
			else
			{
				day = Integer.parseInt(str.substring(0,firstSpace));
				int secondSpace = str.substring(firstSpace+1).indexOf(" ");
				if (secondSpace == -1) //this means the date doesn't have a day
				{
					month = day;
					day = -1; 
					year = Integer.parseInt(str.substring(firstSpace+1));
				}
				else
				{
					secondSpace += firstSpace + 1;
					month = convertMonth(str.substring(firstSpace+1,secondSpace));
					year = Integer.parseInt(str.substring(secondSpace+1));
				}
			}

		}
		catch (StringIndexOutOfBoundsException e)
		{
			System.out.println("unable to create date with value of " + str);
		}
	}
	public String toString()
	{
		String answer = "";
		if (day != -1)
		{
			answer += Integer.toString(day) + " ";
		}
		if (month != -1)
		{
			answer += Integer.toString(month) + " ";
		}
		answer += Integer.toString(year);
		return answer;
	}
	public static boolean before(date d1, date d2) //returns whether or not d1 comes before d2,
	{
		if (d1.isIncomplete() )
		{
			d1 = d1.complete();
		}
		if (d2.isIncomplete())
		{
			d2 = d2.complete();
		}
		return (d1.year < d2.year) || (d1.year == d2.year && d1.month < d2.month)|| (d1.year == d2.year && d1.month == d2.month && d1.day < d2.day);
	}
	public Date convert()
	{
		return new Date(year-1900,month-1,day);
	}
	public date complete() //if this date is invalid, return it as a valid date with default month and date 1/1
	{
		date d = new date();
		d.year = this.year;
		if (day == -1)
		{
			d.day = 1;
		}
		else
		{
			d.day = this.day;
		}
		if (month == -1)
		{
			d.month = 1;
		}
		else
		{
			d.month = this.month;
		}
		return d;
	}
	public boolean isIncomplete()
	{
		return (day == -1 || month == -1);
	}

	public boolean isValid() //returns whether or not a date is valid
	{
		return day >= 1 && day <= getDaysForMonth(month,year) && month >= 1 && month <= 12;
	}
	public long difference(date other) //finds the distance between this date and other
	{
		if (other.isIncomplete())
		{
			other = other.complete();
		}
		date d = this;
		if (d.isIncomplete())
		{
			d = d.complete();
		}
		Date thisDate = new Date(d.year, d.month, d.day);
		Date otherDate = new Date(other.year, other.month, other.day);
		return thisDate.getTime() - otherDate.getTime();
	}
	public int differenceInYears(date other)
	{
		return project3.convertMilliToYears(difference(other));
	}
}
