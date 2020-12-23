package Webinars;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Library.TakeShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class aaa0028AddNewBelmontTemplate_SimulatedLiveWebinar_RunOnce_GoesTo_AllWebinarsPage {
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
	public void Test_If_WhenSelecting_SimulateLive_SimulatedLiveTiming_Menu_Will_Emerge()
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

		// hover and then click USER BELMONT button
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(3);
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

		// check if Simulated Live Timing Selection menu will emerge
		// check simulated live timing label
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String simulatedlivetiminglabelcsslocator = (String) dothis.dashboardpanel
				.get("simulatedlivetiminglablecsslocator");
		String expectedlabel = (String) dothis.dashboardpanel.get("Simulated_Live_Timing_Label");
		dothis.isElement_Present(simulatedlivetiminglabelcsslocator);

		// check label is correct
		dothis.isText_Label_Name_correct(simulatedlivetiminglabelcsslocator, expectedlabel);

		// when click the simulate live timing menu it has 3 selections
		// Run Once, Run Hourly, Run Recurring
		// first hover and then click Simulated Live Timing selection field
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String simulatedlivetiming_selectionmenu = (String) dothis.dashboardpanel
				.get("simulatedlivetiming_selectionmenu");
		dothis.hoverpointer(simulatedlivetiming_selectionmenu);
		dothis.click_button_linktext(simulatedlivetiming_selectionmenu);
		Thread.sleep(2000);

		// check if Run Once is Present, Enabled, and Correct Text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String runoncecsslocator = (String) dothis.dashboardpanel.get("runoncecsslocator");
		String Run_Once = (String) dothis.dashboardpanel.get("Run_Once");
		dothis.isElement_Present(runoncecsslocator);
		dothis.islinktext_or_button_Enabled(runoncecsslocator);
		dothis.isText_Label_Name_correct(runoncecsslocator, Run_Once);

		// check if Run Hourly is Present, Enabled, and Correct Text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String runhourlycsslocator = (String) dothis.dashboardpanel.get("runhourlycsslocator");
		String Run_Hourly = (String) dothis.dashboardpanel.get("Run_Hourly");
		dothis.isElement_Present(runhourlycsslocator);
		dothis.islinktext_or_button_Enabled(runhourlycsslocator);
		dothis.isText_Label_Name_correct(runhourlycsslocator, Run_Hourly);

		// check if Run Recurring is Present, Enabled, and Correct Text
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String runrecurringcsslocator = (String) dothis.dashboardpanel.get("runrecurringcsslocator");
		String Run_Recurring = (String) dothis.dashboardpanel.get("Run_Recurring");
		dothis.isElement_Present(runrecurringcsslocator);
		dothis.islinktext_or_button_Enabled(runrecurringcsslocator);
		dothis.isText_Label_Name_correct(runrecurringcsslocator, Run_Recurring);
	}

	@Test(priority = 2)
	public void Test_If_AddNew_SimulatedLiveWebinar_RunOnce_IT_GoesToCorrectpath()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// select run once but since as default it is already selected so no need
		// just proceed to select event time
		// set event time present plus 4minutes of the time
		String thistime = dothis.getTimeandDateThenPlus4mins();
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(8);
		String simulatedtimeinputcsslocator = (String) dothis.dashboardpanel.get("simulatedtimeinputcsslocator");
		dothis.enterdataInputfield(simulatedtimeinputcsslocator, thistime);

		// hover and then click Add webinar button
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(7);
		String addwebinarbuttoncsslocator = (String) dothis.dashboardpanel.get("addwebinarbuttoncsslocator");
		dothis.hoverpointer(addwebinarbuttoncsslocator);
		dothis.click_button_linktext(addwebinarbuttoncsslocator);
		Thread.sleep(2000);

		// check url as it should expect to have = /admin/webinars/edit/id
		dothis.getCurrentURL("/admin/webinars/edit/");
	}

	@Test(priority = 3)
	public void Test_If_The_RecentCreated_SimulatedLiveWebinar_RunOnce_Appear_At_AllWebinarsPage()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// hover and then click All Webinars submenu
		dothis.dashboardpanel = (JSONObject) dothis.AllWebinarsPageArr.get(0);
		String allwebinarbuttoncsslocator = (String) dothis.dashboardpanel.get("allwebinarscsslocator");
		dothis.hoverpointer(allwebinarbuttoncsslocator);
		dothis.click_button_linktext(allwebinarbuttoncsslocator);

		Thread.sleep(2000);
		// check if there is a newly added webinar with webinar title starting "webinar"
		dothis.checkAllWebinarsTableList("webinar");
	}

	@Test(priority = 4)
	public void Test_If_The_RecentCreated_SimulatedLiveWebinar_RunOnce_Has_ManageSimulatedEvent_button_Enabled_CorrectButtonName_ButtonHasIconandWithCorrectIconName()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AllWebinarsPageArr.get(0);
		String expectediconName = (String) dothis.dashboardpanel.get("buttonIconName");
		dothis.checkifitHasStartLiveBroadcastbutton_Enabled_and_CorrectName("MANAGE SIMULATED EVENT", "class",
				expectediconName);
	}

	@Test(priority = 5)
	public void Test_If_The_RecentCreated_SimulatedLiveWebinar_RunOnce_Has_WebinarLink_Enabled_CorrectLinkText()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AllWebinarsPageArr.get(0);
		String expectedLinkText = (String) dothis.dashboardpanel.get("expectedLinkText");
		dothis.checkwebinarlinkIfPresent_Enabled_CorrectLinktext(expectedLinkText);
	}

	@Test(priority = 6)
	public void Test_If_The_RecentCreated_SimulatedLiveWebinar_RunOnce_Has_EditLink_Enabled_Correctlinktext()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.checkifcreatedwebinarhasEditlink_enabled_correctlinktext();
	}

	@Test(priority = 7)
	public void Test_If_The_RecentCreated_SimulatedLiveWebinar_RunOnce_Has_CopyLink_Enabled_Correctlinktext()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.checkifcreatedwebinarhasCopylink_enabled_correctlinktext();
	}

	@Test(priority = 8)
	public void Test_If_The_RecentCreated_SimulatedLiveWebinar_RunOnce_Has_DeleteLink_Enabled_Correctlinktext()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.checkifcreatedwebinarhasDeletelink_enabled_correctlinktext();
	}
}