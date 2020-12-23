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

public class aaa0004HostWebinarRoomConfirmationDialogTest {
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

	// login
	public void login() throws IOException, ParseException {
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
	}
	
	@Test(priority = 1)
	public void Test_If_ConfirmationDialog_Popup_At_Host_WebinarRoom_When_Launch()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// login
		this.login();
		Thread.sleep(3000);

		// click Meeting Rooms in the Dashboard panel
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(0);
		String meetingroomscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover to Meetings menu
		dothis.hoverpointer(meetingroomscsslocator);
		dothis.click_button_linktext(meetingroomscsslocator);

		// click start meeting now button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(10);
		String startmeetingnowcsslocator = (String) dothis.dashboardpanel.get("startmeetingnowbuttonxpathlocator");
		dothis.hoverpointerXPATH(startmeetingnowcsslocator);
		dothis.clickbutton_linktextXPATH(startmeetingnowcsslocator);

		// check if Start Meeting Room modal dialog will launch
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(0);
		String modalDialogcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		Thread.sleep(2000);
		dothis.islinktext_or_button_is_Present(modalDialogcsslocator);

		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and then click Start meeting now button
		dothis.hoverpointer(linkcsslocator);
		dothis.click_button_linktext(linkcsslocator);
		//switch tab
		dothis.checkNewWindowOpen();
		Thread.sleep(5000);
		// When host webinar room test if there is confirmation dialog popup
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String confirmationdialogcsslocator = (String) dothis.dashboardpanel.get("confirmationdialogcsslocator");
		dothis.islinktext_or_button_is_Present(confirmationdialogcsslocator);
	}

	@Test(priority = 2)
	public void Test_If_ConfirmationDialog_Has_Title() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String confirmationdialogTitlecsslocator = (String) dothis.dashboardpanel.get("confirmationdialogTitlecsslocator");
		dothis.isLabel_Text_Name_Present(confirmationdialogTitlecsslocator);
	}
	
	@Test(priority = 3)
	public void Test_If_ConfirmationDialog_Has_Title_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String confirmationdialogTitlecsslocator = (String) dothis.dashboardpanel.get("confirmationdialogTitlecsslocator");
		String Confirmation_Dialog_Title = (String) dothis.dashboardpanel.get("Confirmation_Dialog_Title");
		dothis.isText_Label_Name_correct(confirmationdialogTitlecsslocator, Confirmation_Dialog_Title);
	}
	
	@Test(priority = 4)
	public void Test_If_ConfirmationDialog_Has_InstructionText() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String instructiontextcsslocator = (String) dothis.dashboardpanel.get("instructiontextcsslocator");
		dothis.isLabel_Text_Name_Present(instructiontextcsslocator);
	}
	
	@Test(priority = 5)
	public void Test_If_ConfirmationDialog_InstructionText_Is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String instructiontextcsslocator = (String) dothis.dashboardpanel.get("instructiontextcsslocator");
		String Instruction_Text = (String) dothis.dashboardpanel.get("Instruction_Text");
		dothis.isText_Label_Name_correct(instructiontextcsslocator, Instruction_Text);
	}
	
	@Test(priority = 6)
	public void Test_If_ConfirmationDialog_Has_JoinRoomNow_Button() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(joinroomnowbuttoncsslocator);
	}
	
	@Test(priority = 6)
	public void Test_If_ConfirmationDialog_JoinRoomNow_Button_Is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(joinroomnowbuttoncsslocator);
	}
	
	@Test(priority = 8)
	public void Test_If_ConfirmationDialog_JoinRoomNow_Button_Has_Name_And_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		String Join_Room_Now_Button = (String) dothis.dashboardpanel.get("Join_Room_Now_Button");
		dothis.isText_Label_Name_correct(joinroomnowbuttoncsslocator, Join_Room_Now_Button);
	}
	
	@Test(priority = 9)
	public void Test_If_When_JoinRoomNow_Button_Is_Clicked_ConfirmationDialog_Will_Be_Closed() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		//hover and then click
		dothis.hoverpointer(joinroomnowbuttoncsslocator);
		dothis.click_button_linktext(joinroomnowbuttoncsslocator);
		//test if confirmation dialog will be closed
		String confirmationdialogcsslocator = (String) dothis.dashboardpanel.get("confirmationdialogcsslocator");
		dothis.checkElement_gone_hiddenCSSlocator(confirmationdialogcsslocator);
	}

}
