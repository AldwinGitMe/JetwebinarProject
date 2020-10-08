package Login_Home_Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import org.testng.annotations.Test;

import Library.TakeShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class runTestLoginScenarios {
	public WebDriver driver;
	public String testURL = "https://aldwinj.qa.jetwebinar.com/";
	public String thisbrowser = "Chrome";

	@AfterTest
	public void aftertest() throws IOException {
		driver.quit();
		//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakeShots.captureScreenshotOnFail(driver, result.getName());
		}
	}

	@BeforeTest
	public void launchbrowser() throws FileNotFoundException {

		switch (thisbrowser) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();

			// headless
			ChromeOptions chromeoption = new ChromeOptions();
			chromeoption.addArguments("window-size=1536,864");
			chromeoption.setHeadless(true);

			driver = new ChromeDriver(chromeoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(testURL);
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();

			// headless
			FirefoxOptions foxoption = new FirefoxOptions();
			foxoption.addArguments("window-size=1536,864");
			foxoption.setHeadless(true);

			driver = new FirefoxDriver(foxoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			// driver.manage().window().maximize();
			driver.get(testURL);
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();

			// headless
			EdgeOptions edgeoption = new EdgeOptions();
			//edgeoption.addArguments("window-size=1536,864");
			//edgeoption.setHeadless(true);

			driver = new EdgeDriver(edgeoption);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// driver.manage().window().maximize();
			driver.get(testURL);
			break;
		default:
			System.out.println("ONLY THREE BROWSERS ARE AVAILABLE. PICK ONE ");
		}
	}

	@Test(priority = 1)
	public void Test_If_Login_Without_Credentials() throws IOException, ParseException, InterruptedException {
		LoginScenariosBackEnd dothis = new LoginScenariosBackEnd(driver);
		dothis.checkloginOne();
	}

	@Test(priority = 2)
	public void Test_If_Login_Username_Only_Credentials() throws IOException, ParseException, InterruptedException {
		LoginScenariosBackEnd dothis = new LoginScenariosBackEnd(driver);
		dothis.CheckTwo();
	}

	@Test(priority = 3)
	public void Test_If_Login_Password_Only_Credentials() throws IOException, ParseException, InterruptedException {
		LoginScenariosBackEnd dothis = new LoginScenariosBackEnd(driver);
		dothis.CheckThree();
	}

	@Test(priority = 4)
	public void Test_If_Login_Incorrect_Username_Incorrect_Password_Credentials()
			throws IOException, ParseException, InterruptedException {
		LoginScenariosBackEnd dothis = new LoginScenariosBackEnd(driver);
		dothis.CheckFour();
	}

	@Test(priority = 5)
	public void Test_If_Login_Correct_Username_Incorrect_Password_Credentials()
			throws IOException, ParseException, InterruptedException {
		LoginScenariosBackEnd dothis = new LoginScenariosBackEnd(driver);
		dothis.CheckFive();
	}

	@Test(priority = 6)
	public void Test_If_Login_Correct_Username_And_Password_Credentials()
			throws IOException, ParseException, InterruptedException {
		LoginScenariosBackEnd dothis = new LoginScenariosBackEnd(driver);
		dothis.CheckSix();
	}

}
