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

public class aaa0013AddNewWebinarOakTemplateInputErrorScenarios {
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
	public void Test_If_Attempt_ToAdd_NewWebinar_Without_UserInputData_AlertErrorMessage_Popup()
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
		Thread.sleep(2000);
		// hover and then click USER OAK button
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(3);
		String useadelinebuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(useadelinebuttoncsslocator);
		dothis.click_button_linktext(useadelinebuttoncsslocator);
		// immediately click the Add Webinar button below
		// hover and then click
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(7);
		String addwebinarbuttoncsslocator = (String) dothis.dashboardpanel.get("addwebinarbuttoncsslocator");
		dothis.hoverpointer(addwebinarbuttoncsslocator);
		dothis.click_button_linktext(addwebinarbuttoncsslocator);
		Thread.sleep(2000);
		// check if error message popup
		dothis.dashboardpanel = (JSONObject) dothis.ErrorMessagesArr.get(0);
		String pleasecheckcsslocator = (String) dothis.dashboardpanel.get("pleasecheckcsslocator");
		dothis.isElement_Present(pleasecheckcsslocator);
	}

	@Test(priority = 2)
	public void Test_If_Pleaseheck_ErrormessageText_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ErrorMessagesArr.get(0);
		String pleasecheckcsslocator = (String) dothis.dashboardpanel.get("pleasecheckcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Please_check_Error");
		dothis.isText_Label_Name_correct(pleasecheckcsslocator, expectedText);
	}

	@Test(priority = 3)
	public void Test_If_Pleaseheck_ErrormessageText_Color_Is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ErrorMessagesArr.get(0);
		String pleasecheckcsslocator = (String) dothis.dashboardpanel.get("pleasecheckcsslocator");
		String expectedtextColor = (String) dothis.dashboardpanel.get("textcolor_RGB");
		dothis.getcolorAttribute_and_Compare(pleasecheckcsslocator, "color", expectedtextColor);
	}

	@Test(priority = 4)
	public void Test_If_Pleasecheck_ErrormessageText_BackgroundColor_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ErrorMessagesArr.get(0);
		String pleasecheckcsslocator = (String) dothis.dashboardpanel.get("pleasecheckcsslocator");
		String expectedtextBackgroundColor = (String) dothis.dashboardpanel.get("text_backgroundcolor_inRGB");
		dothis.getcolorAttribute_and_Compare(pleasecheckcsslocator, "background-color", expectedtextBackgroundColor);
	}

	@Test(priority = 5)
	public void Test_If_Webinar_URL_TextColor_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String webinarurllabelcsslocator = (String) dothis.dashboardpanel.get("webinarurllabelcsslocator");
		String expectedtextColor = (String) dothis.dashboardpanel.get("webinar_url_titleColor_inRGB");
		dothis.getcolorAttribute_and_Compare(webinarurllabelcsslocator, "color", expectedtextColor);
	}

	@Test(priority = 6)
	public void Test_If_Webinar_URL_InputfieldLabel_TextColor_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(1);
		String inputlabelcsslocator = (String) dothis.dashboardpanel.get("inputlabelcsslocator");
		String expectedtextColor = (String) dothis.dashboardpanel.get("webinar_url_titleColor_inRGB");
		dothis.getcolorAttribute_and_Compare(inputlabelcsslocator, "color", expectedtextColor);
	}

	@Test(priority = 7)
	public void Test_If_Webinar_Title_InputfieldLabel_TextColor_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(2);
		String inputlabelcsslocator = (String) dothis.dashboardpanel.get("webinartitlecsslocator");
		String expectedtextColor = (String) dothis.dashboardpanel.get("webinar_titleColor_inRGB");
		dothis.getcolorAttribute_and_Compare(inputlabelcsslocator, "color", expectedtextColor);
	}

	@Test(priority = 8)
	public void Test_If_EventTimezone_InputfieldLabel_TextColor_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.WebinarSettingsArr.get(3);
		String eventimezonecsslocator = (String) dothis.dashboardpanel.get("eventimezonecsslocator");
		String expectedtextColor = (String) dothis.dashboardpanel.get("event_timezone_titleColor_inRGB");
		dothis.getcolorAttribute_and_Compare(eventimezonecsslocator, "color", expectedtextColor);
	}
}
