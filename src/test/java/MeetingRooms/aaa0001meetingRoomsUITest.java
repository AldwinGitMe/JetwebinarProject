package MeetingRooms;

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

public class aaa0001meetingRoomsUITest {
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
	public void Test_if_when_MeetingRoomsMenu_is_Clicked_Correct_URL_Destination()
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
		Thread.sleep(3000);

		// click Meeting Rooms in the Dashboard panel
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(0);
		String meetingroomscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedURL = (String) dothis.dashboardpanel.get("url_destination");
		dothis.click_button_linktext(meetingroomscsslocator);
		Thread.sleep(2000);

		// check url destination
		dothis.getCurrentURL(expectedURL);
	}

	@Test(priority = 2)
	public void Test_if_PageTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Page Title
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 3)
	public void Test_if_PageTitle_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Page Title
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedPagetitle = (String) dothis.dashboardpanel.get("page_title");
		dothis.isText_Label_Name_correct(csslocator, expectedPagetitle);
	}

	@Test(priority = 4)
	public void Test_if_PageTitle_icon_is_present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Page Title
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedPageicon = (String) dothis.dashboardpanel.get("pageTitle_icon");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedPageicon);
	}

	@Test(priority = 5)
	public void Test_if_WebinarLength_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms length label
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 6)
	public void Test_if_WebinarLength_Label_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms length label
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedLabel1 = (String) dothis.dashboardpanel.get("webinarLength_label1");
		String expectedLabel2 = (String) dothis.dashboardpanel.get("webinarLength_label2");
		dothis.isText_Label_Name_correct(csslocator, expectedLabel1);
		dothis.isText_Label_Name_correct(csslocator, expectedLabel2);
	}

	@Test(priority = 7)
	public void Test_if_ListLengthSelector_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("selectorcsslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 8)
	public void Test_if_ListLengthSelector_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("selectorcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 9)
	public void Test_if_ListOption5_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("selectorcsslocator");
		dothis.click_button_linktext(csslocator);
		String csslocator5 = (String) dothis.dashboardpanel.get("csslocator5");
		dothis.isLabel_Text_Name_Present(csslocator5);
	}

	@Test(priority = 10)
	public void Test_if_ListOption10_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(3);
		String csslocator10 = (String) dothis.dashboardpanel.get("csslocator10");
		dothis.isLabel_Text_Name_Present(csslocator10);
	}

	@Test(priority = 11)
	public void Test_if_ListOptionA11_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(3);
		String csslocatorall = (String) dothis.dashboardpanel.get("csslocatorall");
		dothis.isLabel_Text_Name_Present(csslocatorall);
	}

	@Test(priority = 12)
	public void Test_if_SearchLabel_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(4);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 13)
	public void Test_if_SearchLabel_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(4);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("Search");
		dothis.isText_Label_Name_correct(csslocator, expectedLabel);
	}

	@Test(priority = 14)
	public void Test_if_SearchInputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(4);
		String csslocator = (String) dothis.dashboardpanel.get("searchinputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 15)
	public void Test_if_SearchInputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms List Length Selector Option 5
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(4);
		String csslocator = (String) dothis.dashboardpanel.get("searchinputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 16)
	public void Test_if_AddNewButton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Add New button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}
	
	@Test(priority = 17)
	public void Test_if_AddNewButton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Add New button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}
	
	@Test(priority = 18)
	public void Test_if_AddNewButton_name_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Add New button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedName = (String) dothis.dashboardpanel.get("AddNewButton");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}
	
	@Test(priority = 19)
	public void Test_if_AddNewButton_icon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Meeting Rooms Add New button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("addnewbutton_iconcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Addnewbutton_icon");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedName);
	}
	
}
