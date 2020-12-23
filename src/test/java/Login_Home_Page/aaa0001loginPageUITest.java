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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Library.TakeShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class aaa0001loginPageUITest {
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
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(7);
		String testURL = (String) dothis.loginhp.get("Test_URL");
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
	public void Test_if_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(0);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 2)
	public void Test_if_Title_is_correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(0);
		String expectedText = (String) dothis.loginhp.get("title");
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isText_Label_Name_correct(csslocator, expectedText);
	}

	@Test(priority = 3)
	public void Test_if_UserNameInputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isInputfieldPresent(csslocator);
	}

	@Test(priority = 4)
	public void Test_if_UserNameInputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isInputfieldEnabled(csslocator);
	}

	@Test(priority = 5)
	public void Test_if_UserNameInputfield_InsideLabel_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(1);
		String placeholderText = (String) dothis.loginhp.get("usernameInputfield_placeholder_text");
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.getplaceholder_text(csslocator, placeholderText);
	}

	@Test(priority = 6)
	public void Test_if_PasswordInputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isInputfieldPresent(csslocator);
	}

	@Test(priority = 7)
	public void Test_if_PasswordInputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isInputfieldEnabled(csslocator);
	}

	@Test(priority = 8)
	public void Test_if_PasswordInputfield_InsideLabel_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(2);
		String placeholderText = (String) dothis.loginhp.get("passwordInputfield_placeholder_text");
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.getplaceholder_text(csslocator, placeholderText);
	}

	@Test(priority = 9)
	public void Test_if_Rememberme_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(3);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 10)
	public void Test_if_Rememberme_Label_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(3);
		String expectedText = (String) dothis.loginhp.get("remember_me_label_text");
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isText_Label_Name_correct(csslocator, expectedText);
	}

	@Test(priority = 11)
	public void Test_if_CheckBox_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(3);
		String csslocator = (String) dothis.loginhp.get("checkbox_cssLocator");
		dothis.ischeckboxPresent(csslocator);
	}

	@Test(priority = 12)
	public void Test_if_CheckBox_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(3);
		String csslocator = (String) dothis.loginhp.get("checkbox_cssLocator");
		dothis.ischeckboxEnabled(csslocator);
	}

	@Test(priority = 13)
	public void Test_if_ForgotPasswordLinktext_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(4);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 14)
	public void Test_if_ForgotPasswordLinktext_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(4);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 15)
	public void Test_if_ForgotPasswordLinktext_is_correctText() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(4);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		String expectedText = (String) dothis.loginhp.get("forgotPassword_text");
		dothis.isText_Label_Name_correct(csslocator, expectedText);
	}

	@Test(priority = 16)
	public void Test_if_SIGNINButton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 17)
	public void Test_if_SIGNINButton_is_nameIscorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		String expectedName = (String) dothis.loginhp.get("SignInButton_name");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 18)
	public void Test_if_SIGNINButton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(5);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 19)
	public void Test_if_footer_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(6);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 20)
	public void Test_if_footer_is_correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.loginhp = (JSONObject) dothis.loginhomepagelocatorsArr.get(6);
		String csslocator = (String) dothis.loginhp.get("csslocator");
		String expectedName = (String) dothis.loginhp.get("footer_text");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

}
