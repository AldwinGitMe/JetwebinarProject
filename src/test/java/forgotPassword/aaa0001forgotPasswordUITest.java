package forgotPassword;

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

public class aaa0001forgotPasswordUITest {
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
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(7);
		String testURL = (String) dothis.forgotPass.get("Test_URL");
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
	public void Test_Main_Title_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(1);
		String csslocator = (String) dothis.forgotPass.get("csslocator");

		// click forgot Password link text
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);

		// main title object
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(0);
		String titlecsslocator = (String) dothis.forgotPass.get("csslocator");

		// test if main title is present
		dothis.isLabel_Text_Name_Present(titlecsslocator);
	}

	@Test(priority = 2)
	public void Test_is_Main_Title_correct() throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// main title object
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(0);
		String titlecsslocator = (String) dothis.forgotPass.get("csslocator");
		String expectedTitle = (String) dothis.forgotPass.get("title");

		// test if correct text
		dothis.isText_Label_Name_correct(titlecsslocator, expectedTitle);
	}

	@Test(priority = 3)
	public void Test_SubTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// check sub title
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(2);
		String subtitlecsslocator = (String) dothis.forgotPass.get("csslocator");

		// test if main title is present
		dothis.isLabel_Text_Name_Present(subtitlecsslocator);
	}

	@Test(priority = 4)
	public void Test_SubTitle_is_correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// check sub title
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(2);
		String subtitlecsslocator = (String) dothis.forgotPass.get("csslocator");
		String expectedSubTitle = (String) dothis.forgotPass.get("Forgot_Password_sub_Title");

		// test if sub title is correct
		dothis.isText_Label_Name_correct(subtitlecsslocator, expectedSubTitle);
	}

	@Test(priority = 5)
	public void Test_if_InstructionalText_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// check sub title
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(3);
		String insrtuctioncsslocator = (String) dothis.forgotPass.get("csslocator");

		// test if instruction text is present
		dothis.isLabel_Text_Name_Present(insrtuctioncsslocator);
	}

	@Test(priority = 6)
	public void Test_if_InstructionalText_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// check sub title
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(3);
		String insrtuctioncsslocator = (String) dothis.forgotPass.get("csslocator");
		String expectedinsrtuction = (String) dothis.forgotPass.get("Instruction_Text");

		// test if sub title is correct
		dothis.isText_Label_Name_correct(insrtuctioncsslocator, expectedinsrtuction);
	}
	
	@Test(priority=7)
	public void Test_if_EmailInputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(4);
		String emailinputfieldlocator = (String) dothis.forgotPass.get("csslocator");
		
		//check if it is present
		dothis.isInputfieldPresent(emailinputfieldlocator);
	}
	
	@Test(priority=8)
	public void Test_if_EmailInputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(4);
		String emailinputfieldlocator = (String) dothis.forgotPass.get("csslocator");
		
		//check if it is enabled
		dothis.isInputfieldEnabled(emailinputfieldlocator);
	}
	
	@Test(priority=9)
	public void Test_if_EmailInputfield_InsideLabel_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(4);
		String emailinputfieldlocator = (String) dothis.forgotPass.get("csslocator");
		String placeholdertext = (String) dothis.forgotPass.get("Email_inputfield");
		
		//check if the placeholder text is correct
		dothis.getplaceholder_text(emailinputfieldlocator, placeholdertext);
	}
	
	@Test(priority=10)
	public void Test_if_BackButton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(5);
		String backbuttonlocator = (String) dothis.forgotPass.get("csslocator");
		
		//check if back button is present
		dothis.islinktext_or_button_is_Present(backbuttonlocator);
	}
	
	
	@Test(priority=11)
	public void Test_if_BackButton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(5);
		String backbuttonlocator = (String) dothis.forgotPass.get("csslocator");
		
		//check if back button is enabled
		dothis.islinktext_or_button_Enabled(backbuttonlocator);
	}
	
	@Test(priority=12)
	public void Test_if_BackButton_Name_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(5);
		String backbuttonlocator = (String) dothis.forgotPass.get("csslocator");
		String expectedbuttonName = (String) dothis.forgotPass.get("Back_button");
		
		//check if back button is correct
		dothis.isText_Label_Name_correct(backbuttonlocator, expectedbuttonName);
	}
	
	@Test(priority=13)
	public void Test_if_SubmitButton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(6);
		String submitbuttonlocator = (String) dothis.forgotPass.get("csslocator");
		
		//check if submit button is present
		dothis.islinktext_or_button_is_Present(submitbuttonlocator);
	}
	
	
	@Test(priority=14)
	public void Test_if_SubmitButton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(6);
		String submitbuttonlocator = (String) dothis.forgotPass.get("csslocator");
		
		//check if submit button is enabled
		dothis.islinktext_or_button_Enabled(submitbuttonlocator);
	}
	
	@Test(priority=15)
	public void Test_if_SubmitButton_Name_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.forgotPass = (JSONObject) dothis.forgotpasswordPagelocatorsArr.get(6);
		String submitbuttonlocator = (String) dothis.forgotPass.get("csslocator");
		String expectedbuttonName = (String) dothis.forgotPass.get("Submit_Button");
		
		//check if submit button is correct
		dothis.isText_Label_Name_correct(submitbuttonlocator, expectedbuttonName);
	}
}
