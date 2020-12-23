package Webinars;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class reusableTestMethods {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject dashboardPagelocators;
	JSONArray userloginArr, WebinarsMainMenuArr, AddNewWebinarPageArr, AdelineTemplateArr, MesaTemplateArr,
			BelmontTemplateArr, OakTemplateArr, NexgenTemplateArr, ChicagoTemplateArr, ClarkTemplateArr,
			LagunaTemplateArr, WebinarSettingsArr, ErrorMessagesArr, AllWebinarsPageArr;
	JSONObject dashboardpanel;

	public reusableTestMethods(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Webinars\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			dashboardPagelocators = (JSONObject) obj;
			userloginArr = (JSONArray) dashboardPagelocators.get("user_account");
			WebinarsMainMenuArr = (JSONArray) dashboardPagelocators.get("Webinars_Main_menu");
			AddNewWebinarPageArr = (JSONArray) dashboardPagelocators.get("Add_New_Webinar_Page");
			AdelineTemplateArr = (JSONArray) dashboardPagelocators.get("Adeline_Template");
			MesaTemplateArr = (JSONArray) dashboardPagelocators.get("Mesa_Template");
			BelmontTemplateArr = (JSONArray) dashboardPagelocators.get("Belmont_Template");
			OakTemplateArr = (JSONArray) dashboardPagelocators.get("Oak_Template");
			NexgenTemplateArr = (JSONArray) dashboardPagelocators.get("Nexgen_Template");
			ChicagoTemplateArr = (JSONArray) dashboardPagelocators.get("Chicago_Template");
			ClarkTemplateArr = (JSONArray) dashboardPagelocators.get("Clark_Template");
			LagunaTemplateArr = (JSONArray) dashboardPagelocators.get("Laguna_Template");
			WebinarSettingsArr = (JSONArray) dashboardPagelocators.get("Webinar_Settings");
			ErrorMessagesArr = (JSONArray) dashboardPagelocators.get("ErrorMessages");
			AllWebinarsPageArr = (JSONArray) dashboardPagelocators.get("All_Webinars_Page");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// scroll down the page
	public void scrollDown(int num) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + num + ")");
	}

	// clear input field
	public void clearInputfield(String locator) {
		WebElement inputfield = driver.findElement(By.cssSelector(locator));
		inputfield.clear();
	}

	// enter data in input field
	public void enterdataInputfield(String locator, String datatext) {
		WebElement enterdata = driver.findElement(By.cssSelector(locator));
		enterdata.sendKeys(datatext);
	}

	// click button / link text
	public void click_button_linktext(String locator) {
		WebElement button_linktext = driver.findElement(By.cssSelector(locator));
		button_linktext.click();
	}

	// check element is present using xpath
	public void isElement_PresentXPATH(String locator) {
		boolean buttonlinktext = driver.findElement(By.xpath(locator)).isDisplayed();
		if (buttonlinktext == false) {
			Assert.fail(" Element Is Not Present ");
		}
	}

	// check element is present
	public void isElement_Present(String locator) {
		boolean buttonlinktext = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (buttonlinktext == false) {
			Assert.fail(" Element Is Not Present ");
		}
	}

	// check element is should not be present
	public void isElement_NotbePresent(String locator) {
		boolean buttonlinktext = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (buttonlinktext == true) {
			Assert.fail(" Element Is Still Present ");
		}
	}

	// check element is should not be present XPATH
	public void isElement_NotbePresentXPATH(String locator) {
		boolean buttonlinktext = driver.findElement(By.xpath(locator)).isDisplayed();
		if (buttonlinktext == true) {
			Assert.fail(" Element Is Still Present ");
		}
	}

	// check button or link text is enabled
	public void islinktext_or_button_Enabled(String locator) {
		boolean buttonlink_text = driver.findElement(By.cssSelector(locator)).isEnabled();
		if (buttonlink_text == false) {
			Assert.fail(" Button / Link Text is Disabled ");
		}
	}

	// get current URL
	public void getCurrentURL(String expectedurl) {
		String currentURL = driver.getCurrentUrl();
		// compare if it is what is expected destination url
		Assert.assertTrue(currentURL.contains(expectedurl));
	}

	// mouser hover in element - button / link text / image
	public void hoverpointer(String locator) {
		Actions actions = new Actions(driver);
		WebElement thiselement = driver.findElement(By.cssSelector(locator));
		actions.moveToElement(thiselement).perform();
	}

	// check text / label / Name is correct
	public void isText_Label_Name_correct(String locator, String expectedText) throws IOException {
		WebElement TLN = driver.findElement(By.cssSelector(locator));
		Assert.assertTrue(TLN.getText().contains(expectedText), TLN.getText());
	}

	// check if element is present using xpath
	public void isElement_presentXPATH(String xpathvalue) {
		boolean ele = driver.findElement(By.xpath(xpathvalue)).isDisplayed();
		if (ele == false) {
			Assert.fail(" Element Is Not Present ");
		}
	}

	// get attribute name and compare
	public void getAttribute_and_Compare(String locator, String value, String expected) throws InterruptedException {
		WebElement att = driver.findElement(By.cssSelector(locator));
		String as = att.getAttribute(value);
		Assert.assertTrue(as.contains(expected), as);
	}

	// get color
	public void getcolorAttribute_and_Compare(String locator, String value, String expected) {
		WebElement colorattribute = driver.findElement(By.cssSelector(locator));
		String color = colorattribute.getCssValue(value);
		Assert.assertTrue(color.contains(expected), color);
	}

	// select an option value
	public void selectOptionvalue(String locator, String value) {
		Select dropdownmenu = new Select(driver.findElement(By.cssSelector(locator)));
		dropdownmenu.selectByValue(value);
	}

	// get current time and date in 12hr format and MM/dd/YYYY
	// plus 4 minutes
	public String getTimeandDateThenPlus4mins() {
		new String();
		long ONE_MINUTE_IN_MILLIS = 60000;// millisecs
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		// Displaying current date and time in 12 hour format with AM/PM
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
		String dateString = dateFormat.format(new Date(t + (4 * ONE_MINUTE_IN_MILLIS))).toString();
		return dateString;
	}

	// this will enter unique random strings
	public void enterrandomStrings(String locator) {
		String stringNames = "webinar" + new Random().nextInt(1000);
		WebElement enterdata = driver.findElement(By.cssSelector(locator));
		enterdata.sendKeys(stringNames);
	}

	// check the tables lists if the added webinar is present in the last row
	public void checkAllWebinarsTableList(String expectedName) {
		List<WebElement> allname = driver.findElements(By.xpath("//table[@id='webinarlist']/tbody/tr"));
		int i = allname.size();
		WebElement webinarname = driver.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[2]"));
		Assert.assertTrue(webinarname.getText().contains(expectedName));
	}

	// check if the recently added webinar has Startlive webinar has live broadcast
	// button
	public void checkifitHasStartLiveBroadcastbutton_Enabled_and_CorrectName(String expectedbuttonName, String value,
			String expectedIconName) {
		List<WebElement> livewebinar = driver.findElements(By.xpath("//table[@id='webinarlist']/tbody/tr"));
		int i = livewebinar.size();
		boolean startlivebroadcastbutton = driver
				.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[3]/a")).isDisplayed();
		if (startlivebroadcastbutton == true) {
			// check if the button is Enabled
			boolean buttonisEnabled = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[3]/a")).isEnabled();
			if (buttonisEnabled == false) {
				Assert.fail(i + "  This Particular Live Webinar, Its START LIVE BROADCAST BUTTON IS DISABLED ");
			}

			// check if the Name is Correct as it should be START LIVE BROADCAST
			WebElement buttonName = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[3]/a"));
			Assert.assertTrue(buttonName.getText().contains(expectedbuttonName),
					i + " This Live Webinar Has Different Button Name = " + buttonName.getText());

			// check if the button has icon and with correct icon name
			boolean buttonIcon = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[3]/a/i")).isDisplayed();
			if (buttonIcon == true) {
				// check if the name of the icon is correct
				WebElement buttonIconName = driver
						.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[3]/a/i"));
				String iconName = buttonIconName.getAttribute(value);
				Assert.assertTrue(iconName.contains(expectedIconName), iconName);
			} else {
				Assert.fail(i + " This Particular Live Webinar, Its START LIVE BROADCAST button has no Icon ");
			}
		} else {
			Assert.fail(i + " The Recently Created Live Webinar Does Not Have A Start Live Broadcast Button ");
		}

	}

	// check if the recently added live webinar has webinar link of the 5th column
	// of the all webinars table and
	// it is not disabled and correct link text
	public void checkwebinarlinkIfPresent_Enabled_CorrectLinktext(String expectedLinkText) {
		List<WebElement> livewebinar = driver.findElements(By.xpath("//table[@id='webinarlist']/tbody/tr"));
		int i = livewebinar.size();
		boolean thisweblink = driver.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[5]/a"))
				.isDisplayed();
		if (thisweblink == true) {
			// check if the link is enabled
			boolean thisweblinkEn = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[5]/a")).isEnabled();
			if (thisweblinkEn == false) {
				Assert.fail(i + " This Particular Webinar, Its Web link on the 5th column, it is a disabled Link ");
			}

			// check if it has a correct link text [ Webinar Link ]
			WebElement weblink = driver.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[5]/a"));
			Assert.assertTrue(weblink.getText().contains(expectedLinkText),
					i + " This Particular Webinar that has weblink on the 5th column has a different link text ");
		}
	}

	// check if the created webinar has delete link
	public void checkifcreatedwebinarhasEditlink_enabled_correctlinktext() {
		List<WebElement> livewebinar = driver.findElements(By.xpath("//table[@id='webinarlist']/tbody/tr"));
		int i = livewebinar.size();
		boolean editlink = driver.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[1]"))
				.isDisplayed();

		if (editlink == true) {
			// check if it is enabled
			boolean editlinktext = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[1]")).isEnabled();
			if (editlinktext == false) {
				Assert.fail(" This Particular Webinar in the Last Row Has a Edit Link but Disabled ");
			}
			// check if correct link text
			WebElement editlinktxt = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[1]"));
			Assert.assertTrue(editlinktxt.getText().contains("Edit"),
					" The Edit Link Text for This Particular Live Webinar Has a Different Text = "
							+ editlinktxt.getText());
		}
	}

	// check if the recently created live webinar has copy link text
	public void checkifcreatedwebinarhasCopylink_enabled_correctlinktext() {
		List<WebElement> livewebinar = driver.findElements(By.xpath("//table[@id='webinarlist']/tbody/tr"));
		int i = livewebinar.size();
		boolean copylink = driver.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[2]"))
				.isDisplayed();

		if (copylink == true) {
			// check if it is enabled
			boolean copylinktxt = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[2]")).isEnabled();
			if (copylinktxt == false) {
				Assert.fail(" This Particular Webinar in the Last Row Has a Copy Link but Disabled ");
			}
			// check if correct link text
			WebElement copylinktext = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[2]"));
			Assert.assertTrue(copylinktext.getText().contains("Copy"),
					" The Copy Link Text for This Particular Live Webinar Has a Different Text = "
							+ copylinktext.getText());
		}
	}

	// check if the recently created live webinar has delete link text
	public void checkifcreatedwebinarhasDeletelink_enabled_correctlinktext() {
		List<WebElement> livewebinar = driver.findElements(By.xpath("//table[@id='webinarlist']/tbody/tr"));
		int i = livewebinar.size();
		boolean dellink = driver.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[3]"))
				.isDisplayed();

		if (dellink == true) {
			// check if it is enabled
			boolean dellinktxt = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[3]")).isEnabled();
			if (dellinktxt == false) {
				Assert.fail(" This Particular Webinar in the Last Row Has a Delete Link but Disabled ");
			}
			// check if correct link text
			WebElement deletelinktext = driver
					.findElement(By.xpath("//table[@id='webinarlist']/tbody/tr[" + i + "]/td[6]/a[3]"));
			Assert.assertTrue(deletelinktext.getText().contains("Delete"),
					" The Delete Link Text for This Particular Live Webinar Has a Different Text = "
							+ deletelinktext.getText());
		}
	}

	// this will check how many set timers are added under Simulated
	// Live Webinar Run Recurring > Choose a Weekday
	public int countSettimersAdded() {
		List<WebElement> settimers = driver.findElements(
				By.xpath("(//input[@class='form-control timepicker timepicker-no-seconds timepicker-1'])"));
		int i = settimers.size();
		return i;
	}

	// this will check how many set timers close button are added under Simulated
	// Live Webinar Run Recurring > Choose a Weekday
	public int countSettimersClosebuttonAdded() {
		List<WebElement> settimersclosebutton = driver
				.findElements(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)"));
		int i = settimersclosebutton.size();
		return i;
	}

	// check element is hidden or gone in the DOM using csslocator
	public void checkElement_gone_hiddenCSSlocator(String csslocator) {
		int buttonlinktext = driver.findElements(By.cssSelector(csslocator)).size();
		if (buttonlinktext != 0) {
			Assert.fail(" Element Is Still Present ");
		}
	}
}
