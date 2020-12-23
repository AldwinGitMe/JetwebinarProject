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

public class aaa0004AddNewWebinarBelmontTemplateWebinarSettingsUI {
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
	public void Test_Webinar_Settings_Title_Is_Present() throws IOException, ParseException, InterruptedException {
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
		// hover and then click USE BELMONT button
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(3);
		String usebelmontbuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(usebelmontbuttoncsslocator);
		dothis.click_button_linktext(usebelmontbuttoncsslocator);
		Thread.sleep(2000);

		// check if webinar settings acordion title emerge
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(0);
		String Titlecsslocator = (String) dothis.dashboardpanel.get("titleaccordioncsslocator");
		dothis.isElement_Present(Titlecsslocator);
	}

	@Test(priority = 2)
	public void Test_If_WebinarSettings_Title_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(0);
		String Titlecsslocator = (String) dothis.dashboardpanel.get("titleaccordioncsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("accordion_title");
		dothis.isText_Label_Name_correct(Titlecsslocator, expectedTitle);
	}

	@Test(priority = 3)
	public void Test_If_WebinarURL_Title_Input() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String webinarurllabelcsslocator = (String) dothis.dashboardpanel.get("webinarurllabelcsslocator");
		dothis.isElement_Present(webinarurllabelcsslocator);
	}

	@Test(priority = 4)
	public void Test_If_WebinarURL_Title_Input_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String webinarurllabelcsslocator = (String) dothis.dashboardpanel.get("webinarurllabelcsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("webinar_url");
		dothis.isText_Label_Name_correct(webinarurllabelcsslocator, expectedLabel);
	}

	@Test(priority = 5)
	public void Test_If_WebinarURL_InputLabel_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String inputlabelcsslocator = (String) dothis.dashboardpanel.get("inputlabelcsslocator");
		dothis.isElement_Present(inputlabelcsslocator);
	}

	@Test(priority = 6)
	public void Test_If_WebinarURL_InputLabel_Is_Correct() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String inputlabelcsslocator = (String) dothis.dashboardpanel.get("inputlabelcsslocator");
		dothis.isText_Label_Name_correct(inputlabelcsslocator, "aldwinjuma.qa.jetwebinar.com");
	}

	@Test(priority = 7)
	public void Test_If_WebinarURL_InputField_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.isElement_Present(inputfieldcsslocator);
	}

	@Test(priority = 8)
	public void Test_If_WebinarURL_InputField_Is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(inputfieldcsslocator);
	}

	@Test(priority = 9)
	public void Test_If_WebinarURL_InputField_PlaceholderText_IsPresentandCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("placeholderText");
		dothis.getAttribute_and_Compare(inputfieldcsslocator, "placeholder", expectedText);
	}

	@Test(priority = 10)
	public void Test_If_WebinarTitle_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String webinartitlecsslocator = (String) dothis.dashboardpanel.get("webinartitlecsslocator");
		dothis.isElement_Present(webinartitlecsslocator);
	}

	@Test(priority = 11)
	public void Test_If_WebinarTitle_Is_Correct() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String webinartitlecsslocator = (String) dothis.dashboardpanel.get("webinartitlecsslocator");
		String webinar_title = (String) dothis.dashboardpanel.get("webinar_title");
		dothis.isText_Label_Name_correct(webinartitlecsslocator, webinar_title);
	}

	@Test(priority = 12)
	public void Test_If_WebinarTitle_InputField_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.isElement_Present(inputfieldcsslocator);
	}

	@Test(priority = 13)
	public void Test_If_WebinarTitle_InputField_Is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(inputfieldcsslocator);
	}

	@Test(priority = 14)
	public void Test_If_WebinarTitle_InputField_PlaceholderText_IsPresentandCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("placeholderText");
		dothis.getAttribute_and_Compare(inputfieldcsslocator, "placeholder", expectedText);
	}

	@Test(priority = 15)
	public void Test_If_EventTimezone_Label_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(3);
		String eventimezonecsslocator = (String) dothis.dashboardpanel.get("eventimezonecsslocator");
		dothis.isElement_Present(eventimezonecsslocator);
	}

	@Test(priority = 16)
	public void Test_If_EventTimezone_Label_Is_Correct() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(3);
		String eventimezonecsslocator = (String) dothis.dashboardpanel.get("eventimezonecsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("event_timezone_label");
		dothis.isText_Label_Name_correct(eventimezonecsslocator, expectedLabel);
	}

	@Test(priority = 17)
	public void Test_If_EventTimezone_ChooseEventTimezone_Is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(3);
		String selectionmenucsslocator = (String) dothis.dashboardpanel.get("selectionmenucsslocator");
		dothis.isElement_Present(selectionmenucsslocator);
	}

	@Test(priority = 18)
	public void Test_If_EventTimezone_ChooseEventTimezone_Is_Enabled()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(3);
		String selectionmenucsslocator = (String) dothis.dashboardpanel.get("selectionmenucsslocator");
		dothis.islinktext_or_button_Enabled(selectionmenucsslocator);
	}

	@Test(priority = 19)
	public void Test_If_EventType_Label_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String eventtypecsslocator = (String) dothis.dashboardpanel.get("eventtypecsslocator");
		dothis.isElement_Present(eventtypecsslocator);
	}

	@Test(priority = 20)
	public void Test_If_EventType_Label_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String eventtypecsslocator = (String) dothis.dashboardpanel.get("eventtypecsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("EventTypeLabel");
		dothis.isText_Label_Name_correct(eventtypecsslocator, expectedLabel);
	}

	@Test(priority = 21)
	public void Test_If_EventType_EventSelection_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String eventtypeselectioncsslocator = (String) dothis.dashboardpanel.get("eventtypeselectioncsslocator");
		dothis.isElement_Present(eventtypeselectioncsslocator);
	}

	@Test(priority = 22)
	public void Test_If_EventType_EventSelection_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String eventtypeselectioncsslocator = (String) dothis.dashboardpanel.get("eventtypeselectioncsslocator");
		dothis.islinktext_or_button_Enabled(eventtypeselectioncsslocator);
	}

	@Test(priority = 23)
	public void Test_If_When_EventType_EventSelection_Is_Click_ItWill_ShowAvailableSeletions_LiveBroadcast_Is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String eventtypeselectioncsslocator = (String) dothis.dashboardpanel.get("eventtypeselectioncsslocator");
		// hover and then click
		dothis.hoverpointer(eventtypeselectioncsslocator);
		dothis.click_button_linktext(eventtypeselectioncsslocator);
		Thread.sleep(2000);
		// check if in the available selections there is Live Broadcast
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String livebroadcastcsslocator = (String) dothis.dashboardpanel.get("livebroadcastcsslocator");
		dothis.isElement_Present(livebroadcastcsslocator);
	}

	@Test(priority = 24)
	public void Test_If_LiveBroadcast_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String livebroadcastcsslocator = (String) dothis.dashboardpanel.get("livebroadcastcsslocator");
		dothis.islinktext_or_button_Enabled(livebroadcastcsslocator);
	}

	@Test(priority = 25)
	public void Test_If_LiveBroadcast_Label_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String livebroadcastcsslocator = (String) dothis.dashboardpanel.get("livebroadcastcsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("LiveBroadcast");
		dothis.isText_Label_Name_correct(livebroadcastcsslocator, expectedLabel);
	}

	@Test(priority = 26)
	public void Test_If_SimulatedLive_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String simulatedlivecsslocator = (String) dothis.dashboardpanel.get("simulatedlivecsslocator");
		dothis.isElement_Present(simulatedlivecsslocator);
	}

	@Test(priority = 27)
	public void Test_If_SimulatedLive_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String simulatedlivecsslocator = (String) dothis.dashboardpanel.get("simulatedlivecsslocator");
		dothis.islinktext_or_button_Enabled(simulatedlivecsslocator);
	}

	@Test(priority = 28)
	public void Test_If_SimulatedLive_Label_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String simulatedlivecsslocator = (String) dothis.dashboardpanel.get("simulatedlivecsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("Simulated_Live");
		dothis.isText_Label_Name_correct(simulatedlivecsslocator, expectedLabel);
	}

	@Test(priority = 29)
	public void Test_If_InstantReplay_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String instantreplaycsslocator = (String) dothis.dashboardpanel.get("instantreplaycsslocator");
		dothis.isElement_Present(instantreplaycsslocator);
	}

	@Test(priority = 30)
	public void Test_If_InstantReplay_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String instantreplaycsslocator = (String) dothis.dashboardpanel.get("instantreplaycsslocator");
		dothis.islinktext_or_button_Enabled(instantreplaycsslocator);
	}

	@Test(priority = 31)
	public void Test_If_InstantReplay_Label_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(4);
		String instantreplaycsslocator = (String) dothis.dashboardpanel.get("instantreplaycsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("Instant_Replay");
		dothis.isText_Label_Name_correct(instantreplaycsslocator, expectedLabel);
	}

	@Test(priority = 32)
	public void Test_If_EventTime_Label_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String eventtimecsslocator = (String) dothis.dashboardpanel.get("eventtimecsslocator");
		dothis.isElement_Present(eventtimecsslocator);
	}

	@Test(priority = 33)
	public void Test_If_EventTime_Label_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String eventtimecsslocator = (String) dothis.dashboardpanel.get("eventtimecsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("EventTime");
		dothis.isText_Label_Name_correct(eventtimecsslocator, expectedLabel);
	}

	@Test(priority = 34)
	public void Test_If_dateInputfield_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String inputdatefieldcsslocator = (String) dothis.dashboardpanel.get("inputdatefieldcsslocator");
		dothis.isElement_Present(inputdatefieldcsslocator);
	}

	@Test(priority = 35)
	public void Test_If_dateInputfield_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String inputdatefieldcsslocator = (String) dothis.dashboardpanel.get("inputdatefieldcsslocator");
		dothis.islinktext_or_button_Enabled(inputdatefieldcsslocator);
	}

	@Test(priority = 36)
	public void Test_If_dateInputIcon_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String dateinputiconcsslocator = (String) dothis.dashboardpanel.get("dateinputiconcsslocator");
		dothis.isElement_Present(dateinputiconcsslocator);
	}

	@Test(priority = 37)
	public void Test_If_dateInputIcon_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String dateinputiconcsslocator = (String) dothis.dashboardpanel.get("dateinputiconcsslocator");
		dothis.islinktext_or_button_Enabled(dateinputiconcsslocator);
	}

	@Test(priority = 38)
	public void Test_If_dateInputIcon_When_Click_ItOpenUp_TheDatePicker()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String dateinputiconcsslocator = (String) dothis.dashboardpanel.get("dateinputiconcsslocator");
		// hover and then click
		dothis.hoverpointer(dateinputiconcsslocator);
		dothis.click_button_linktext(dateinputiconcsslocator);
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(5);
		String datepickercsslocator = (String) dothis.dashboardpanel.get("datepickercsslocator");
		dothis.isElement_Present(datepickercsslocator);
	}

	@Test(priority = 39)
	public void Test_If_GoBackButton_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(6);
		String gobackbuttoncsslocator = (String) dothis.dashboardpanel.get("gobackbuttoncsslocator");
		dothis.isElement_Present(gobackbuttoncsslocator);
	}

	@Test(priority = 40)
	public void Test_If_GoBackButton_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(6);
		String gobackbuttoncsslocator = (String) dothis.dashboardpanel.get("gobackbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(gobackbuttoncsslocator);
	}

	@Test(priority = 41)
	public void Test_If_GoBackButton_Name_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(6);
		String gobackbuttoncsslocator = (String) dothis.dashboardpanel.get("gobackbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("GoBackButton");
		dothis.isText_Label_Name_correct(gobackbuttoncsslocator, expectedName);
	}

	@Test(priority = 42)
	public void Test_If_GoBackButton_Icon_Is_PresentandCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(6);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("Goback_Icon");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}

	@Test(priority = 43)
	public void Test_If_AddWebinarButton_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(7);
		String addwebinarbuttoncsslocator = (String) dothis.dashboardpanel.get("addwebinarbuttoncsslocator");
		dothis.isElement_Present(addwebinarbuttoncsslocator);
	}

	@Test(priority = 44)
	public void Test_If_AddWebinarButton_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(7);
		String addwebinarbuttoncsslocator = (String) dothis.dashboardpanel.get("addwebinarbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(addwebinarbuttoncsslocator);
	}

	@Test(priority = 45)
	public void Test_If_AddWebinarButton_Name_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(7);
		String addwebinarbuttoncsslocator = (String) dothis.dashboardpanel.get("addwebinarbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("AddWebinarButton");
		dothis.isText_Label_Name_correct(addwebinarbuttoncsslocator, expectedName);
	}

	@Test(priority = 46)
	public void Test_If_AddWebinarButton_Icon_Is_PresentandCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(7);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("AddWebinar_Icon");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
}
