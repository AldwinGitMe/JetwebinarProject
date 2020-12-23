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

public class aaa0002StartMeetingRoomConfirmationDialogTest {
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
	public void Test_If_StartMeetingRoom_ConfirmmationDialog_Will_Popup_When_StartMeetingNow_Button_Is_Clicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// login
		this.login();
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
	}

	@Test(priority = 2)
	public void Test_Start_Meeting_Room_modalDialog_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// title
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(0);
		String titlecsslocator = (String) dothis.dashboardpanel.get("titlecsslocator");
		dothis.isLabel_Text_Name_Present(titlecsslocator);
	}

	@Test(priority = 3)
	public void Test_Start_Meeting_Room_modalDialog_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// title
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(0);
		String titlecsslocator = (String) dothis.dashboardpanel.get("titlecsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Modal_dialog");
		dothis.isText_Label_Name_correct(titlecsslocator, expectedTitle);
	}

	@Test(priority = 4)
	public void Test_Start_Meeting_Room_modalDialog_questionText_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Question text
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 5)
	public void Test_Start_Meeting_Room_modalDialog_questionText_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Question text
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(1);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedText = (String) dothis.dashboardpanel.get("question_text");
		dothis.isText_Label_Name_correct(csslocator, expectedText);
	}

	@Test(priority = 6)
	public void Test_Start_Meeting_Room_modalDialog_CancelButton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// cancel button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 7)
	public void Test_Start_Meeting_Room_modalDialog_CancelButton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// cancel button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 8)
	public void Test_Start_Meeting_Room_modalDialog_CancelButton_name_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// cancel button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("cancel_button");
		dothis.isLabel_Text_Name_Present(csslocator);
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 9)

	public void Test_Start_Meeting_Room_modalDialog_STARTMEETINGButton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// START MEETING button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 10)
	public void Test_Start_Meeting_Room_modalDialog_STARTMEETINGButton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// START MEETING button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 11)
	public void Test_Start_Meeting_Room_modalDialog_STARTMEETINGButton_name_is_Correct()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// START MEETING button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("START_MEETING_button");
		dothis.isLabel_Text_Name_Present(csslocator);
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 12)
	public void Test_if_Click_cancelButton_will_close_StartMeetingRoomModalDialog()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// cancel button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(2);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);

		// check if modal is closed as it should be
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(0);
		String modalDialogcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		Thread.sleep(2000);
		dothis.islinktext_or_button_is_hidden(modalDialogcsslocator);
	}

	@Test(priority = 13)
	public void Test_if_STARTMEETINGbutton_is_click_HostMeetingRoom_will_launch_next_windowTab()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);

		// click start meeting now button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(10);
		String startmeetingnowcsslocator = (String) dothis.dashboardpanel.get("startmeetingnowbuttonxpathlocator");
		Thread.sleep(2000);
		dothis.hoverpointerXPATH(startmeetingnowcsslocator);
		dothis.clickbutton_linktextXPATH(startmeetingnowcsslocator);

		// click START MEETING button
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check if it opens in new window the host meeting room and then check url
		dothis.checkNewWindowOpen();
	}

	@Test(priority = 14)
	public void Test_if_Correct_URL_in_HostMeetingRoom() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(0);
		String expectedURL = (String) dothis.dashboardpanel.get("Window_URL");
		dothis.getCurrentURL(expectedURL);
	}
}
