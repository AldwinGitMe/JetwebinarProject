package Dashboard;

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

public class aaa0003UserLoginProfile_UITest {
	public WebDriver driver;
	//public String thisbrowser = "Chrome";

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
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(4);
		String testURL = (String) dothis.dashboardpanel.get("Test_URL");
		if(browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			// headless
			ChromeOptions chromeoption = new ChromeOptions();
			//chromeoption.setHeadless(true);

			driver = new ChromeDriver(chromeoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();

			// headless
			FirefoxOptions foxoption = new FirefoxOptions();
			//foxoption.setHeadless(true);

			driver = new FirefoxDriver(foxoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		}
		else if(browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();

			// headless
			EdgeOptions edgeOptions = new EdgeOptions();
            driver = new EdgeDriver(edgeOptions);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		}
		else {
			System.out.println("ONLY THREE BROWSERS ARE AVAILABLE. PICK ONE ");
		}
	}

	@Test(priority = 1)
	public void Test_if_username_is_Present_onToprightcorner()
			throws IOException, ParseException, InterruptedException {
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
		Thread.sleep(5000);

		// check if there is the username on top right corner
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(0);
		String uncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(uncsslocator);
	}

	@Test(priority = 2)
	public void Test_if_username_is_Enabled_onToprightcorner() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(0);
		String uncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// check if link is enabled
		dothis.islinktext_or_button_Enabled(uncsslocator);
	}

	@Test(priority = 3)
	public void Test_if_username_icon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(0);
		String unIconcsslocator = (String) dothis.dashboardpanel.get("Iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("iconName");

		dothis.getAttribute_and_Compare(unIconcsslocator, "class", expectedIconName);
	}

	@Test(priority = 4)
	public void Test_if_Settings_Menu_is_Present_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(0);
		String uncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// click username first
		dothis.click_button_linktext(uncsslocator);
		Thread.sleep(2000);

		// check if Settings is present
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(1);
		String settingscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(settingscsslocator);
	}

	@Test(priority = 5)
	public void Test_if_Settings_Menu_is_Enabled_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// check if Settings is enabled
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(1);
		String settingscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(settingscsslocator);
	}

	@Test(priority = 6)
	public void Test_if_Settings_Menu_is_TextisCorrect_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// check if Settings is enabled
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(1);
		String settingscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedlabel = (String) dothis.dashboardpanel.get("Settings");
		dothis.isText_Label_Name_correct(settingscsslocator, expectedlabel);
	}

	@Test(priority = 7)
	public void Test_if_Settings_icon_is_Present_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// check if Settings is enabled
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(1);
		String settingsIconcsslocator = (String) dothis.dashboardpanel.get("Iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconName");
		dothis.getAttribute_and_Compare(settingsIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 8)
	public void Test_if_Support_Menu_is_Present_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(2);
		String supportcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// check if Support is present
		dothis.islinktext_or_button_is_Present(supportcsslocator);
	}

	@Test(priority = 9)
	public void Test_if_Support_Menu_is_Enabled_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(2);
		String supportcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// check if Support is enabled
		dothis.islinktext_or_button_Enabled(supportcsslocator);
	}

	@Test(priority = 11)
	public void Test_if_Support_Menu_is_CorrectTetxt_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(2);
		String supportcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedlabel = (String) dothis.dashboardpanel.get("Support");

		// check if Support is correct label text
		dothis.isText_Label_Name_correct(supportcsslocator, expectedlabel);
	}

	@Test(priority = 10)
	public void Test_if_Support_Icon_is_Present_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(2);
		String supportIconcsslocator = (String) dothis.dashboardpanel.get("Iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconName");

		// check if Support icon is present and correct name
		dothis.getAttribute_and_Compare(supportIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 11)
	public void Test_if_Logout_Menu_is_Present_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(3);
		String logoutcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// check if Logout menu is present
		dothis.islinktext_or_button_is_Present(logoutcsslocator);
	}

	@Test(priority = 12)
	public void Test_if_Logout_Menu_is_Enabled_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(3);
		String logoutcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// check if Logout menu is enabled
		dothis.islinktext_or_button_Enabled(logoutcsslocator);
	}

	@Test(priority = 13)
	public void Test_if_Logout_Menu_is_CorrectText_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(3);
		String logoutcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Logout");
		Thread.sleep(2000);
		// check if Logout menu has correct text
		dothis.isText_Label_Name_correct(logoutcsslocator, expectedText);
	}

	@Test(priority = 14)
	public void Test_if_Logout_Icon_is_Present_when_username_is_clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(3);
		String logoutIconcsslocator = (String) dothis.dashboardpanel.get("Iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconName");

		// check if Logout icon is present
		dothis.getAttribute_and_Compare(logoutIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 15)
	public void Test_if_When_Settings_is_Clicked_Correct_URLDestination_and_CorrectPage_title()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(1);
		String settingscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// click Settings first
		dothis.click_button_linktext(settingscsslocator);
		Thread.sleep(2000);

		// check if correct url destination
		String settingsPageURLExpected = (String) dothis.dashboardpanel.get("url_destination");
		String settingspagetitlecsslocator = (String) dothis.dashboardpanel.get("page_title");
		dothis.getCurrentURL(settingsPageURLExpected);

		// check if there is a page title and correct page title
		dothis.isLabel_Text_Name_Present(settingspagetitlecsslocator);
		// check if correct page title text
		String expectedPagetitle = (String) dothis.dashboardpanel.get("Settings");
		dothis.isText_Label_Name_correct(settingspagetitlecsslocator, expectedPagetitle);
	}

	@Test(priority = 16)
	public void Test_if_When_Support_is_Clicked_Correct_URLDestination_and_CorrectPage_title()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click username first
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(0);
		String uncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.click_button_linktext(uncsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(2);
		String supportcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// click Support first
		dothis.click_button_linktext(supportcsslocator);
		Thread.sleep(2000);

		// check if correct url destination
		String supportPageURLExpected = (String) dothis.dashboardpanel.get("url_destination");
		String supportpagetitlecsslocator = (String) dothis.dashboardpanel.get("page_title");
		dothis.getCurrentURL(supportPageURLExpected);

		// check if there is a page title and correct page title
		dothis.isLabel_Text_Name_Present(supportpagetitlecsslocator);
		// check if correct page title text
		String expectedPagetitle = (String) dothis.dashboardpanel.get("Support");
		dothis.isText_Label_Name_correct(supportpagetitlecsslocator, expectedPagetitle);
	}

	@Test(priority = 17)
	public void Test_if_When_LogOut_is_Clicked_Correct_URLDestination()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click username first
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(0);
		String uncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		//hover and then click
		dothis.hoverpointer(uncsslocator);
		dothis.click_button_linktext(uncsslocator);
		Thread.sleep(2000);

		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(3);
		String logoutcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		// click Logout first
		//hover and then click
		dothis.hoverpointer(uncsslocator);
		dothis.click_button_linktext(logoutcsslocator);
		Thread.sleep(2000);

		// check if correct url destination
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(4);
		String expectedURL = (String) dothis.dashboardpanel.get("Test_URL");
		dothis.getCurrentURL(expectedURL);
	}

}
