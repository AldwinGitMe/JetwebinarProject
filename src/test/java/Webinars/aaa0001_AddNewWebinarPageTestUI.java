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

public class aaa0001_AddNewWebinarPageTestUI {
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
	public void Test_If_AddWebinar_Is_Click_It_WillGo_To_AddWebinarPage()
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
		// check if correct url goto
		dothis.dashboardpanel = (JSONObject) dothis.AddNewWebinarPageArr.get(0);
		String expectedURL = (String) dothis.dashboardpanel.get("URL");
		dothis.getCurrentURL(expectedURL);
	}

	@Test(priority = 2)
	public void Test_If_PageTitle_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AddNewWebinarPageArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}

	@Test(priority = 3)
	public void Test_If_PageTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AddNewWebinarPageArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String Title = (String) dothis.dashboardpanel.get("Title");
		dothis.isText_Label_Name_correct(csslocator, Title);
	}

	@Test(priority = 4)
	public void Test_If_AccordionTitle_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AddNewWebinarPageArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}

	@Test(priority = 5)
	public void Test_If_AccordionTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AddNewWebinarPageArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Accordion_Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}

	@Test(priority = 6)
	public void Test_If_AdelineTemplate_IsPresent() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}

	@Test(priority = 7)
	public void Test_If_AdelineTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 8)
	public void Test_If_AdelineTemplate_TemplatTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTemplateTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTemplateTitle);
	}
	
	@Test(priority = 9)
	public void Test_If_AdelineTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 10)
	public void Test_If_AdelineTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Adeline_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 11)
	public void Test_If_USEADELINE_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//first hover on the adeline template
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(0);
		String adelineTemplatecsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(adelineTemplatecsslocator);
		Thread.sleep(2000);
		//check if USE ADELINE button emerge or is visible
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 12)
	public void Test_If_USEADELINE_button_Has_Name() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//first hover on the adeline template
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Use_Adeline_Button");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}
	
	@Test(priority = 13)
	public void Test_If_USEADELINE_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//first hover on the adeline template
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}
	
	@Test(priority = 14)
	public void Test_If_USEADELINE_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.AdelineTemplateArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUseAdelineButton");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedIconName);
	}
	
	@Test(priority = 15)
	public void Test_If_MesaTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 16)
	public void Test_If_MesaTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 17)
	public void Test_If_MesaTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 18)
	public void Test_If_MesaTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 19)
	public void Test_If_MesaTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Mesa_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 20)
	public void Test_If_USEMESA_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to mesa template
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USE MESA is visible ro emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(3);
		String usemesabuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(usemesabuttoncsslocator);
	}
	
	@Test(priority = 21)
	public void Test_If_USEMESA_button_Has_Name() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(3);
		String usemesabuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Mesa_Button");
		dothis.isText_Label_Name_correct(usemesabuttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 22)
	public void Test_If_USEMESA_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(3);
		String usemesabuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(usemesabuttoncsslocator);
	}
	
	@Test(priority = 23)
	public void Test_If_USEMESA_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MesaTemplateArr.get(3);
		String usemesabuttoncsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSEMESAbutton");
		dothis.getAttribute_and_Compare(usemesabuttoncsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 24)
	public void Test_If_BelmontTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 25)
	public void Test_If_BelmontTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 26)
	public void Test_If_BelmontTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 27)
	public void Test_If_BelmontTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 28)
	public void Test_If_BelmontTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Belmont_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 29)
	public void Test_If_USEBELMONT_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to belmont template
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USEBELMMONT is visible or emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(buttoncsslocator);
	}
	
	@Test(priority = 30)
	public void Test_If_USEBELMONT_button_Has_Name() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Belmont_Button");
		dothis.isText_Label_Name_correct(buttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 31)
	public void Test_If_USEBELMONT_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(buttoncsslocator);
	}
	
	@Test(priority = 32)
	public void Test_If_USEBELMONT_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.BelmontTemplateArr.get(3);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSEBELMONTbutton");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 33)
	public void Test_If_OakTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 34)
	public void Test_If_OakTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 35)
	public void Test_If_OakTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 36)
	public void Test_If_OakTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 37)
	public void Test_If_OakTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Oak_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 38)
	public void Test_If_USEOAK_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to belmont template
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USEOAK is visible or emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(buttoncsslocator);
	}
	
	@Test(priority = 39)
	public void Test_If_USEOAK_button_Has_Name() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Oak_Button");
		dothis.isText_Label_Name_correct(buttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 40)
	public void Test_If_USEOAK_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(buttoncsslocator);
	}
	
	@Test(priority = 41)
	public void Test_If_USEOAK_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.OakTemplateArr.get(3);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSEOAKbutton");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 42)
	public void Test_If_NexGenTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		//scroll down first
		dothis.scrollDown(230);
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 43)
	public void Test_If_NexGenTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 44)
	public void Test_If_NexGenTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 45)
	public void Test_If_NexGenTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 46)
	public void Test_If_NexGenTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Nexgen_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 47)
	public void Test_If_USENEXGEN_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to belmont template
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USEOAK is visible or emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(buttoncsslocator);
	}
	
	@Test(priority = 48)
	public void Test_If_USENEXGEN_button_Has_Name() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Nexgen_Button");
		dothis.isText_Label_Name_correct(buttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 49)
	public void Test_If_USENEXGEN_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(buttoncsslocator);
	}
	
	@Test(priority = 50)
	public void Test_If_USENEXGEN_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.NexgenTemplateArr.get(3);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSENEXGENbutton");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 51)
	public void Test_If_ChicagoTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 52)
	public void Test_If_ChicagoTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 53)
	public void Test_If_ChicagoTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 54)
	public void Test_If_ChicagoTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 55)
	public void Test_If_ChicagoTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Chicago_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 56)
	public void Test_If_USECHICAGO_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to belmont template
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USEOAK is visible or emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(buttoncsslocator);
	}
	
	@Test(priority = 57)
	public void Test_If_USECHICAGO_button_Has_Name() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Chicago_Button");
		dothis.isText_Label_Name_correct(buttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 58)
	public void Test_If_USECHICAGO_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(buttoncsslocator);
	}
	
	@Test(priority = 59)
	public void Test_If_USECHICAGO_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ChicagoTemplateArr.get(3);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSECHICAGObutton");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 60)
	public void Test_If_ClarkTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 61)
	public void Test_If_ClarkTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 62)
	public void Test_If_ClarkTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 63)
	public void Test_If_ClarkTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 64)
	public void Test_If_ClarkTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Clark_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 65)
	public void Test_If_USECLARK_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to belmont template
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USEOAK is visible or emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(buttoncsslocator);
	}
	
	@Test(priority = 66)
	public void Test_If_USECLARK_button_Has_Name() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Clark_Button");
		dothis.isText_Label_Name_correct(buttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 67)
	public void Test_If_USECLARK_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(buttoncsslocator);
	}
	
	@Test(priority = 68)
	public void Test_If_USECLARK_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.ClarkTemplateArr.get(3);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSECLARKbutton");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 69)
	public void Test_If_LagunaTemplate_Is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 70)
	public void Test_If_LagunaTemplate_Has_TemplateTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 71)
	public void Test_If_LagunaTemplate_TemplateTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Template_Title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 72)
	public void Test_If_LagunaTemplate_Has_subTitle() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(csslocator);
	}
	
	@Test(priority = 73)
	public void Test_If_LagunaTemplate_subTitle_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Laguna_subTitle");
		dothis.isText_Label_Name_correct(csslocator, expectedsubTitle);
	}
	
	@Test(priority = 74)
	public void Test_If_USELAGUNA_button_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		//hover first to belmont template
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(0);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(csslocator);
		//check if USEOAK is visible or emerge
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isElement_Present(buttoncsslocator);
	}
	
	@Test(priority = 75)
	public void Test_If_USELAGUNA_button_Has_Name() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Use_Laguna_Button");
		dothis.isText_Label_Name_correct(buttoncsslocator, expectedbuttonName);
	}
	
	@Test(priority = 76)
	public void Test_If_USELAGUNA_button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(3);
		String buttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(buttoncsslocator);
	}
	
	@Test(priority = 77)
	public void Test_If_USELAGUNA_button_Has_IconBeside() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.LagunaTemplateArr.get(3);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("IconUSELAGUNAbutton");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectedIconName);
	}
}
