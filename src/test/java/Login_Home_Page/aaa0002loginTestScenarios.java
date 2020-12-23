package Login_Home_Page;

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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Library.TakeShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class aaa0002loginTestScenarios {
	public WebDriver driver;
	//public String thisbrowser = "Firefox";

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
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(7);
		String testURL = (String) dothis.loginhp.get("Test_URL");
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			// headless
			ChromeOptions chromeoption = new ChromeOptions();
			//chromeoption.setHeadless(true);

			driver = new ChromeDriver(chromeoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();

			// headless
			FirefoxOptions foxoption = new FirefoxOptions();
			//foxoption.setHeadless(true);

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

	@Test(priority = 1)
	public void Test_login_without_uname_and_password() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.alerterr = (JSONObject) dothis.AlertErrorTextsArr.get(0);
		String errorText = (String) dothis.alerterr.get("Error1");
		String csslocatorAlertErrorText = (String) dothis.alerterr.get("csslocator");

		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) dothis.loginhp.get("csslocator");

		// click sign in button and verify result
		dothis.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if alert error text appear
		dothis.isLabel_Text_Name_Present(csslocatorAlertErrorText);
		// check if correct alert error text
		dothis.isText_Label_Name_correct(csslocatorAlertErrorText, errorText);
	}

	@Test(priority = 2)
	public void Test_login_Enter_Username_Only() throws InterruptedException, IOException, ParseException {

		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.alerterr = (JSONObject) dothis.AlertErrorTextsArr.get(0);
		String errorText = (String) dothis.alerterr.get("Error1");
		String csslocatorAlertErrorText = (String) dothis.alerterr.get("csslocator");

		// sign in button object
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) dothis.loginhp.get("csslocator");

		// username input field objct
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String csslocator = (String) dothis.loginhp.get("csslocator");

		// user input data objct
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(0);
		String enterusername = (String) dothis.loginhp.get("username");

		// enter username
		dothis.enterdataInputfield(csslocator, enterusername);
		// click sign in button and verify result
		dothis.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if alert error text appear
		dothis.isLabel_Text_Name_Present(csslocatorAlertErrorText);
		// check if correct alert error text
		dothis.isText_Label_Name_correct(csslocatorAlertErrorText, errorText);
	}

	@Test(priority = 3)
	public void Test_login_Enter_Password_Only() throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.alerterr = (JSONObject) dothis.AlertErrorTextsArr.get(0);
		String errorText = (String) dothis.alerterr.get("Error1");
		String csslocatorAlertErrorText = (String) dothis.alerterr.get("csslocator");

		// password input field
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String pwcsslocator = (String) dothis.loginhp.get("csslocator");

		// user input data
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(0);
		String enterpassword = (String) dothis.loginhp.get("password");

		// sign in button object
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) dothis.loginhp.get("csslocator");

		// username input field objct
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String unamecsslocator = (String) dothis.loginhp.get("csslocator");

		// clear username input field
		dothis.clearinputfield(unamecsslocator);

		// Enter password
		dothis.enterdataInputfield(pwcsslocator, enterpassword);

		// click sign in button and verify result
		dothis.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if alert error text appear
		dothis.isLabel_Text_Name_Present(csslocatorAlertErrorText);
		// check if correct alert error text
		dothis.isText_Label_Name_correct(csslocatorAlertErrorText, errorText);
	}

	@Test(priority = 4)
	public void Test_login_Enter_Incorrect_Username_and_Password()
			throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.alerterr = (JSONObject) dothis.AlertErrorTextsArr.get(1);
		String errorText = (String) dothis.alerterr.get("Error2");
		String csslocatorAlertErrorText = (String) dothis.alerterr.get("csslocator");

		// username input field objct
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String unamecsslocator = (String) dothis.loginhp.get("csslocator");

		// password input field
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String pwcsslocator = (String) dothis.loginhp.get("csslocator");

		// user data input object
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(1);
		String enterusername = (String) dothis.loginhp.get("incorrect_username");
		String enterpassword = (String) dothis.loginhp.get("incorrect_password");

		// clear username and password input field
		dothis.clearinputfield(unamecsslocator);
		dothis.clearinputfield(pwcsslocator);

		// sign in button object
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) dothis.loginhp.get("csslocator");

		// enter incorrect username
		dothis.enterdataInputfield(unamecsslocator, enterusername);
		dothis.enterdataInputfield(pwcsslocator, enterpassword);

		// click sign in button and verify result
		dothis.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);

		// check if alert error text appear
		dothis.isLabel_Text_Name_Present(csslocatorAlertErrorText);
		// check if correct alert error text
		dothis.isText_Label_Name_correct(csslocatorAlertErrorText, errorText);
	}

	@Test(priority = 5)
	public void Test_login_Enter_Correct_Username_Incorrect_Password()
			throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.alerterr = (JSONObject) dothis.AlertErrorTextsArr.get(1);
		String errorText = (String) dothis.alerterr.get("Error2");
		String csslocatorAlertErrorText = (String) dothis.alerterr.get("csslocator");

		// username input field objct
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String unamecsslocator = (String) dothis.loginhp.get("csslocator");

		// password input field
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String pwcsslocator = (String) dothis.loginhp.get("csslocator");

		// username data input object
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(0);
		String enterusername = (String) dothis.loginhp.get("username");
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(1);
		String enterpassword = (String) dothis.loginhp.get("incorrect_password");

		// clear username and password input field
		dothis.clearinputfield(unamecsslocator);
		dothis.clearinputfield(pwcsslocator);

		// sign in button object
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) dothis.loginhp.get("csslocator");

		// enter correct username
		dothis.enterdataInputfield(unamecsslocator, enterusername);
		// enter incorrect password
		dothis.enterdataInputfield(pwcsslocator, enterpassword);
		// click sign in button and verify result
		dothis.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);

		// check if alert error text appear
		dothis.isLabel_Text_Name_Present(csslocatorAlertErrorText);
		// check if correct alert error text
		dothis.isText_Label_Name_correct(csslocatorAlertErrorText, errorText);
	}

	@Test(priority = 6)
	public void Test_login_Enter_Correct_Username_and_Password()
			throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// username input field objct
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String unamecsslocator = (String) dothis.loginhp.get("csslocator");

		// password input field
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String pwcsslocator = (String) dothis.loginhp.get("csslocator");

		// username data input object
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(0);
		String enterusername = (String) dothis.loginhp.get("username");
		dothis.loginhp = (JSONObject) dothis.loginscenariosArr.get(0);
		String enterpassword = (String) dothis.loginhp.get("password");

		// clear username and password input field
		dothis.clearinputfield(unamecsslocator);
		dothis.clearinputfield(pwcsslocator);

		// sign in button object
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) dothis.loginhp.get("csslocator");

		// enter correct username
		dothis.enterdataInputfield(unamecsslocator, enterusername);
		// enter correct password
		dothis.enterdataInputfield(pwcsslocator, enterpassword);
		// click sign in button and verify result
		dothis.button_linktext(csslocatorSignInButton);
		Thread.sleep(5000);
		// check if it successfully login
		// expected URL destination object
		dothis.loginhp = (JSONObject) dothis.dashboardpageArr.get(0);
		String expectedURL = (String) dothis.loginhp.get("expectedURLpage");

		// test if correct destination url
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedURL));
	}

}
