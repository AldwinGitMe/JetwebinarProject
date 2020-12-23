package Webinars;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

public class time {
	static final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
	static WebDriver driver;
	public static void main(String[] args) throws IOException, ParseException {
		//Displaying current time in 12 hour format with AM/PM + 10 minutes
		Calendar date = Calendar.getInstance();
    	DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
    	long t = date.getTimeInMillis();
    	String dateString = dateFormat.format(new Date(t + (4 * ONE_MINUTE_IN_MILLIS))).toString();
    	System.out.println("Current time in AM/PM: "+dateString);
    	
    	//Displaying current date and time in 12 hour format with AM/PM
    	DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    	String dateString2 = dateFormat2.format(new Date(t + (4 * ONE_MINUTE_IN_MILLIS))).toString();
    	System.out.println("Current date and time in AM/PM: "+dateString2);
	
		Date afterAddingTenMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));
		System.out.println(" OKAY "+afterAddingTenMins);
		reusableTestMethods dothis = new reusableTestMethods(driver);
		System.out.println(" dsadadasd "+dothis.getTimeandDateThenPlus4mins());
	}
}
