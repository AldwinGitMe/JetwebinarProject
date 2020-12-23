package Webinars;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Library.TakeShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class aaa0047AddNewWebinarChicagoTemplate_SimulatedLiveWebinar_RunRecurring {
	public WebDriver driver;
	// public String thisbrowser = "Chrome";

	@AfterTest
	public void aftertest() throws IOException {
		driver.quit();
		// Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakeShots.captureScreenshotOnFail(driver, result.getName());
		}
	}

	@BeforeTest
	@Parameters("browser")
	public void launchbrowser(String browser) throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(4);
		String testURL = (String) dothis.dashboardpanel.get("Test_URL");
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			// headless
			ChromeOptions chromeoption = new ChromeOptions();
			// chromeoption.setHeadless(true);

			driver = new ChromeDriver(chromeoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();

			// headless
			FirefoxOptions foxoption = new FirefoxOptions();
			// foxoption.setHeadless(true);

			driver = new FirefoxDriver(foxoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();

			// headless
			EdgeOptions edgeOptions = new EdgeOptions();
			driver = new EdgeDriver(edgeOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		} else {
			System.out.println("ONLY THREE BROWSERS ARE AVAILABLE. PICK ONE ");
		}
	}

	// user host login
	public void userhostlogin() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// username and password input data
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(0);
		String username = (String) dothis.dashboardpanel.get("username");
		String password = (String) dothis.dashboardpanel.get("password");

		// username input field
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(1);
		String unamecsslocator = (String) dothis.dashboardpanel.get("csslocator");
		// password input field
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(2);
		String pwcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		// sign in button
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(3);
		String signincsslocator = (String) dothis.dashboardpanel.get("csslocator");

		// enter username
		dothis.enterdataInputfield(unamecsslocator, username);
		// enter password
		dothis.enterdataInputfield(pwcsslocator, password);
		// click sign button
		dothis.click_button_linktext(signincsslocator);
		Thread.sleep(3000);
	}

	@Test(priority = 1)
	public void Test_If_AddNew_SimulatedLiveWebinar_When_Selecting_RunHourly_SetWeekdayTime_and_AddAnotherTime_Elements_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// login
		this.userhostlogin();

		// hover and then click Webinars
		dothis.dashboardpanel = (JSONObject) dothis.WebinarsMainMenuArr.get(0);
		String Webinarcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(Webinarcsslocator);
		dothis.click_button_linktext(Webinarcsslocator);

		// hover then click Add Webinar sub menu
		dothis.dashboardpanel = (JSONObject) dothis.WebinarsMainMenuArr.get(2);
		String AddWebinarcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(AddWebinarcsslocator);
		dothis.click_button_linktext(AddWebinarcsslocator);
		Thread.sleep(3000);

		// scroll down first
		dothis.scrollDown(230);

		// hover and then click USER CHICAGO button
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(3);
		String useadelinebuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(useadelinebuttoncsslocator);
		dothis.click_button_linktext(useadelinebuttoncsslocator);

		// enter valid url name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String urlinputlabelcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.enterrandomStrings(urlinputlabelcsslocator);

		// enter webinar title
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String titleinputlabelcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.enterrandomStrings(titleinputlabelcsslocator);

		// select timezone
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(3);
		String eventimezonecsslocator = (String) dothis.dashboardpanel.get("selectionmenucsslocator");
		// hover and then click first
		dothis.hoverpointer(eventimezonecsslocator);
		dothis.click_button_linktext(eventimezonecsslocator);
		// then select Asia Manila
		dothis.selectOptionvalue(eventimezonecsslocator, "Asia/Manila");

		// select event type but use default which is Simulate Live Broadcast
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String eventtypeselectioncsslocator = (String) dothis.dashboardpanel.get("eventtypeselectioncsslocator");
		// hover and then click first
		dothis.hoverpointer(eventtypeselectioncsslocator);
		dothis.click_button_linktext(eventtypeselectioncsslocator);
		// set event type and choose Simulate Live
		// then select simulated
		dothis.selectOptionvalue(eventtypeselectioncsslocator, "simulated");

		// first hover and then click Simulated Live Timing selection field
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String simulatedlivetiming_selectionmenu = (String) dothis.dashboardpanel
				.get("simulatedlivetiming_selectionmenu");
		dothis.hoverpointer(simulatedlivetiming_selectionmenu);
		dothis.click_button_linktext(simulatedlivetiming_selectionmenu);
		Thread.sleep(2000);

		// select Run Recurring
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String runrecurringcsslocator = (String) dothis.dashboardpanel.get("runrecurringcsslocator");
		dothis.click_button_linktext(runrecurringcsslocator);

		Thread.sleep(2000);
		// when run recurring is selected there is another element emerge only for run
		// recurring
		// check if there is Webinar Time - Label, check if correct label
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String webinartimelabelcsslocator = (String) dothis.dashboardpanel.get("webinartimelabelcsslocator");
		String Webinar_Time_Label = (String) dothis.dashboardpanel.get("Webinar_Time_Label");
		dothis.isElement_Present(webinartimelabelcsslocator);
		dothis.isText_Label_Name_correct(webinartimelabelcsslocator, Webinar_Time_Label);

		// check if there is Choose a weekday selection input field
		// check if it is enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.isElement_Present(chooseaweekdayselectionmenucsslocator);
		dothis.islinktext_or_button_Enabled(chooseaweekdayselectionmenucsslocator);

		// check if there is Add Another Weekday button
		// check if it is enabled
		// check if correct button name
		// check if there is icon and correct icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanotherweekdaybuttoncsslocator = (String) dothis.dashboardpanel
				.get("addanotherweekdaybuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Weekday_Button_Name");
		dothis.isElement_Present(addanotherweekdaybuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanotherweekdaybuttoncsslocator);
		dothis.isText_Label_Name_correct(addanotherweekdaybuttoncsslocator, expectedbuttonName);

		// icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanotherweekdaybuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanotherweekdaybuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("addanotherweekdaybuttonIconName");
		dothis.getAttribute_and_Compare(addanotherweekdaybuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 2)
	public void Test_ChooseaWeekDay_SelectionMenu_Has_Submenus()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);
		Thread.sleep(2000);

		// check if there is Sunday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String sundaycsslocator = (String) dothis.dashboardpanel.get("sundaycsslocator");
		String Sundaysubmenu = (String) dothis.dashboardpanel.get("Sundaysubmenu");
		dothis.isElement_Present(sundaycsslocator);
		dothis.islinktext_or_button_Enabled(sundaycsslocator);
		dothis.isText_Label_Name_correct(sundaycsslocator, Sundaysubmenu);

		// check if there is Monday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String mondaycsslocator = (String) dothis.dashboardpanel.get("mondaycsslocator");
		String Mondaysubmenu = (String) dothis.dashboardpanel.get("Mondaysubmenu");
		dothis.isElement_Present(mondaycsslocator);
		dothis.islinktext_or_button_Enabled(mondaycsslocator);
		dothis.isText_Label_Name_correct(mondaycsslocator, Mondaysubmenu);

		// check if there is Tuesday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String tuesdaycsslocator = (String) dothis.dashboardpanel.get("tuesdaycsslocator");
		String Tuesdaysubmenu = (String) dothis.dashboardpanel.get("Tuesdaysubmenu");
		dothis.isElement_Present(tuesdaycsslocator);
		dothis.islinktext_or_button_Enabled(tuesdaycsslocator);
		dothis.isText_Label_Name_correct(tuesdaycsslocator, Tuesdaysubmenu);

		// check if there is Wednesday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String wednesdaycsslocator = (String) dothis.dashboardpanel.get("wednesdaycsslocator");
		String Wednesdaysubmenu = (String) dothis.dashboardpanel.get("Wednesdaysubmenu");
		dothis.isElement_Present(wednesdaycsslocator);
		dothis.islinktext_or_button_Enabled(wednesdaycsslocator);
		dothis.isText_Label_Name_correct(wednesdaycsslocator, Wednesdaysubmenu);

		// check if there is Thursday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String thursdaycsslocator = (String) dothis.dashboardpanel.get("thursdaycsslocator");
		String Thursdaysubmenu = (String) dothis.dashboardpanel.get("Thursdaysubmenu");
		dothis.isElement_Present(thursdaycsslocator);
		dothis.islinktext_or_button_Enabled(thursdaycsslocator);
		dothis.isText_Label_Name_correct(thursdaycsslocator, Thursdaysubmenu);

		// check if there is Friday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String fridaycsslocator = (String) dothis.dashboardpanel.get("fridaycsslocator");
		String Fridaysubmenu = (String) dothis.dashboardpanel.get("Fridaysubmenu");
		dothis.isElement_Present(fridaycsslocator);
		dothis.islinktext_or_button_Enabled(fridaycsslocator);
		dothis.isText_Label_Name_correct(fridaycsslocator, Fridaysubmenu);

		// check if there is Saturday sub menu - enabled and correct text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String saturdaycsslocator = (String) dothis.dashboardpanel.get("saturdaycsslocator");
		String Saturdaycsslocator = (String) dothis.dashboardpanel.get("Saturdaycsslocator");
		dothis.isElement_Present(fridaycsslocator);
		dothis.islinktext_or_button_Enabled(saturdaycsslocator);
		dothis.isText_Label_Name_correct(saturdaycsslocator, Saturdaycsslocator);
	}

	@Test(priority = 3)
	public void Test_When_host_SelectDay_Sunday_SetTimeInputfield_With_Closebutton_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Sunday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String sundaycsslocator = (String) dothis.dashboardpanel.get("sundaycsslocator");
		dothis.click_button_linktext(sundaycsslocator);
		Thread.sleep(2000);

		// check if Sunday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Sunday_value = (String) dothis.dashboardpanel.get("Sunday_value");
		dothis.getAttribute_and_Compare(sundaycsslocator, "value", Sunday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 4)
	public void Test_When_host_SelectDay_Monday_SetTimeInputfield_With_Closebutton_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Monday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String mondaycsslocator = (String) dothis.dashboardpanel.get("mondaycsslocator");
		dothis.click_button_linktext(mondaycsslocator);
		Thread.sleep(2000);

		// check if Monday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Monday_value = (String) dothis.dashboardpanel.get("Monday_value");
		dothis.getAttribute_and_Compare(mondaycsslocator, "value", Monday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 5)
	public void Test_When_host_SelectDay_Tuesday_SetTimeInputfield_With_Closebutton_Emerges()
			throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Tuesday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String tuesdaycsslocator = (String) dothis.dashboardpanel.get("tuesdaycsslocator");
		dothis.click_button_linktext(tuesdaycsslocator);
		Thread.sleep(2000);

		// check if Tuesday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Tuesday_value = (String) dothis.dashboardpanel.get("Tuesday_value");
		dothis.getAttribute_and_Compare(tuesdaycsslocator, "value", Tuesday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 6)
	public void Test_When_host_SelectDay_Wednesday_SetTimeInputfield_With_Closebutton_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Wednesday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String wednesdaycsslocator = (String) dothis.dashboardpanel.get("wednesdaycsslocator");
		dothis.click_button_linktext(wednesdaycsslocator);
		Thread.sleep(2000);

		// check if Wednesday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Wednesday_value = (String) dothis.dashboardpanel.get("Wednesday_value");
		dothis.getAttribute_and_Compare(wednesdaycsslocator, "value", Wednesday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 7)
	public void Test_When_host_SelectDay_Thursday_SetTimeInputfield_With_Closebutton_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Thursday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String thursdaycsslocator = (String) dothis.dashboardpanel.get("thursdaycsslocator");
		dothis.click_button_linktext(thursdaycsslocator);
		Thread.sleep(2000);

		// check if Thursday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Thursday_value = (String) dothis.dashboardpanel.get("Thursday_value");
		dothis.getAttribute_and_Compare(thursdaycsslocator, "value", Thursday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 8)
	public void Test_When_host_SelectDay_Friday_SetTimeInputfield_With_Closebutton_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Friday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String fridaycsslocator = (String) dothis.dashboardpanel.get("fridaycsslocator");
		dothis.click_button_linktext(fridaycsslocator);
		Thread.sleep(2000);

		// check if Friday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Friday_value = (String) dothis.dashboardpanel.get("Friday_value");
		dothis.getAttribute_and_Compare(fridaycsslocator, "value", Friday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 9)
	public void Test_When_host_SelectDay_Saturday_SetTimeInputfield_With_Closebutton_Emerges()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Saturday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String saturdaycsslocator = (String) dothis.dashboardpanel.get("saturdaycsslocator");
		dothis.click_button_linktext(saturdaycsslocator);
		Thread.sleep(2000);

		// check if Saturday was really selected
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String Saturday_value = (String) dothis.dashboardpanel.get("Saturday_value");
		dothis.getAttribute_and_Compare(saturdaycsslocator, "value", Saturday_value);

		// check if Set time input field emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.isElement_Present(settimedayinputfield1csslocator);
		dothis.islinktext_or_button_Enabled(settimedayinputfield1csslocator);

		// check if set time close button emerge and enabled
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.isElement_Present(settimedayclosebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(settimedayclosebuttoncsslocator);

		// check if add another time button emerge - enabled and correct button name
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Add_Another_Time_button");
		dothis.isElement_Present(addanothertimebuttoncsslocator);
		dothis.islinktext_or_button_Enabled(addanothertimebuttoncsslocator);
		dothis.isText_Label_Name_correct(addanothertimebuttoncsslocator, expectedbuttonName);

		// check if add another time button has icon
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoniconcsslocator = (String) dothis.dashboardpanel
				.get("addanothertimebuttoniconcsslocator");
		String expectedbuttonIconName = (String) dothis.dashboardpanel.get("AddAnotherTimeButtonIconName");
		dothis.isElement_Present(addanothertimebuttoniconcsslocator);
		dothis.getAttribute_and_Compare(addanothertimebuttoniconcsslocator, "class", expectedbuttonIconName);
	}

	@Test(priority = 10)
	public void Test_Adding_Multiple_SeTimerPerDay_OnSaturday()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}

	@Test(priority = 11)
	public void Test_Adding_Multiple_SeTimerPerDay_OnSunday() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Sunday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String sundaycsslocator = (String) dothis.dashboardpanel.get("sundaycsslocator");
		dothis.click_button_linktext(sundaycsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}

	@Test(priority = 12)
	public void Test_Adding_Multiple_SeTimerPerDay_OnMonday() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Monday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String mondaycsslocator = (String) dothis.dashboardpanel.get("mondaycsslocator");
		dothis.click_button_linktext(mondaycsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}

	@Test(priority = 13)
	public void Test_Adding_Multiple_SeTimerPerDay_OnTuesday()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Tuesday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String tuesdaycsslocator = (String) dothis.dashboardpanel.get("tuesdaycsslocator");
		dothis.click_button_linktext(tuesdaycsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}

	@Test(priority = 14)
	public void Test_Adding_Multiple_SeTimerPerDay_OnWednesday()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Wednesday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String wednesdaycsslocator = (String) dothis.dashboardpanel.get("wednesdaycsslocator");
		dothis.click_button_linktext(wednesdaycsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}

	@Test(priority = 15)
	public void Test_Adding_Multiple_SeTimerPerDay_OnThrusday()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Thursday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String thursdaycsslocator = (String) dothis.dashboardpanel.get("thursdaycsslocator");
		dothis.click_button_linktext(thursdaycsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}

	@Test(priority = 16)
	public void Test_Adding_Multiple_SeTimerPerDay_OnFriday() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click Choose a weekday selection menu to reveal sub menus
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String chooseaweekdayselectionmenucsslocator = (String) dothis.dashboardpanel
				.get("chooseaweekdayselectionmenucsslocator");
		dothis.click_button_linktext(chooseaweekdayselectionmenucsslocator);

		// select Friday
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String fridaycsslocator = (String) dothis.dashboardpanel.get("fridaycsslocator");
		dothis.click_button_linktext(fridaycsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String addanothertimebuttoncsslocator = (String) dothis.dashboardpanel.get("addanothertimebuttoncsslocator");

		for (int i = 1; i < 5; i++) {
			int currentcountclosebutton = 0;
			int currentcountsettimer = 1;
			dothis.click_button_linktext(addanothertimebuttoncsslocator);
			System.out.println(" number of times Add another time button is clicked = " + i);
			Thread.sleep(1000);
			currentcountsettimer = dothis.countSettimersAdded();
			Assert.assertEquals(currentcountsettimer, i + 1);
			currentcountclosebutton = dothis.countSettimersClosebuttonAdded();
			Assert.assertEquals(currentcountclosebutton, i + 1);
		}

		WebElement d5 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[5]"));
		d5.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 4);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 4);

		WebElement d4 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[4]"));
		d4.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 3);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 3);

		WebElement d3 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[3]"));
		d3.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 2);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 2);

		WebElement d2 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[2]"));
		d2.click();
		Thread.sleep(1000);
		Assert.assertEquals(dothis.countSettimersAdded(), 1);
		Assert.assertEquals(dothis.countSettimersClosebuttonAdded(), 1);

		WebElement d1 = driver
				.findElement(By.xpath("(//div[@class='input-group']/span[@class='input-group-btn']/button)[1]"));
		d1.click();
		Thread.sleep(1000);

		// check if there is still set timers elements as it should not be visible
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayinputfield1csslocator = (String) dothis.dashboardpanel.get("settimedayinputfield1csslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayinputfield1csslocator);

		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String settimedayclosebuttoncsslocator = (String) dothis.dashboardpanel.get("settimedayclosebuttoncsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(settimedayclosebuttoncsslocator);
	}
}
