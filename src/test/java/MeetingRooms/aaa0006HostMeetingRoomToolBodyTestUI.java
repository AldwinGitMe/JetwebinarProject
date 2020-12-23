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

public class aaa0006HostMeetingRoomToolBodyTestUI {
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
	public void Test_if_ToggleswitchCamera_is_Present() throws IOException, ParseException, InterruptedException {
		// first is login
		this.login();
		Thread.sleep(3000);

		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click Meeting Rooms in the Dashboard panel
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(0);
		String meetingroomscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover to Meetings menu
		dothis.hoverpointer(meetingroomscsslocator);
		dothis.click_button_linktext(meetingroomscsslocator);

		// hover and click START MEETING NOW button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(10);
		String startmeetingnowcsslocator = (String) dothis.dashboardpanel.get("startmeetingnowbuttonxpathlocator");
		dothis.hoverpointerXPATH(startmeetingnowcsslocator);
		dothis.clickbutton_linktextXPATH(startmeetingnowcsslocator);

		// hover and then click Start Meeting button under Start Meeting Room modal
		// dialog
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check if it opens in new window the host meeting room and then check url
		dothis.checkNewWindowOpen();
		
		//hover and then click join room now button at webinar room confirmation dialog
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		dothis.hoverpointer(joinroomnowbuttoncsslocator);
		dothis.click_button_linktext(joinroomnowbuttoncsslocator);

		// check if Toggle switch camera is present
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(6);
		String toggleswitchCameracsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_is_Present(toggleswitchCameracsslocator);
	}

	@Test(priority = 2)
	public void Test_if_ToggleswitchCamera_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(6);
		String toggleswitchCameracsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_Enabled(toggleswitchCameracsslocator);
	}

	@Test(priority = 3)
	public void Test_if_ToggleswitchCamera_Icon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(6);
		String toggleswitchCameraIconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconCamera");
		dothis.getAttribute_and_Compare(toggleswitchCameraIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 4)
	public void Test_if_ToggleswitchCamera_is_ByDefault_Off() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(6);
		String toggleswitchCameracsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Toggle_Camera_Switch_button");
		dothis.getAttribute_and_Compare(toggleswitchCameracsslocator, "class", expectedclass);
	}

	@Test(priority = 5)
	public void Test_if_ToggleswitchCamera_is_ToggleON() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(6);
		String toggleswitchCameracsslocator = (String) dothis.dashboardpanel.get("csslocator");
		// hover and then click
		dothis.hoverpointer(toggleswitchCameracsslocator);
		dothis.click_button_linktext(toggleswitchCameracsslocator);
		Thread.sleep(2000);
		// check if it was turned ON
		String toggledCameraONcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_On");
		dothis.getAttribute_and_Compare(toggledCameraONcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleswitchCameraIconcsslocator = (String) dothis.dashboardpanel.get("iconCameraONcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconCamera");
		dothis.getAttribute_and_Compare(toggleswitchCameraIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 6)
	public void Test_if_ToggleswitchCamera_is_ToggleOFF() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(6);
		String toggledCameraONcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		// hover and then click
		dothis.hoverpointer(toggledCameraONcsslocator);
		dothis.click_button_linktext(toggledCameraONcsslocator);
		Thread.sleep(2000);
		// check if it was turned OFF
		String toggledCameraOFFcsslocator = (String) dothis.dashboardpanel.get("switchOffcsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_Off");
		dothis.getAttribute_and_Compare(toggledCameraOFFcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleswitchCameraIconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconCamera");
		dothis.getAttribute_and_Compare(toggleswitchCameraIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 7)
	public void Test_if_ToggleScreenSharing_is_ByDefault_Off()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(7);
		String togglescreensharingcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Toggle_ScreenShare_Switch_button");
		dothis.getAttribute_and_Compare(togglescreensharingcsslocator, "class", expectedclass);
	}

	@Test(priority = 8)
	public void Test_if_ToggleScreenSharing_is_ToggleON() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(7);
		String togglescreensharingcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		// hover and then click
		dothis.hoverpointer(togglescreensharingcsslocator);
		dothis.click_button_linktext(togglescreensharingcsslocator);
		Thread.sleep(2000);
		// check if it was turned ON
		String toggledScreenShareONcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_On");
		dothis.getAttribute_and_Compare(toggledScreenShareONcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleScreenShareIconcsslocator = (String) dothis.dashboardpanel.get("iconScreenShareONcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("screenshare_icon");
		dothis.getAttribute_and_Compare(toggleScreenShareIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 9)
	public void Test_if_ToggleScreenSharing_is_ToggleOFF() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(7);
		String togglescreensharingcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		// hover and then click
		dothis.hoverpointer(togglescreensharingcsslocator);
		dothis.click_button_linktext(togglescreensharingcsslocator);
		Thread.sleep(2000);
		// check if it was turned OFF
		String togglescreensharingOFFcsslocator = (String) dothis.dashboardpanel.get("switchOffcsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_Off");
		dothis.getAttribute_and_Compare(togglescreensharingOFFcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String togglescreensharingIconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("screenshare_icon");
		dothis.getAttribute_and_Compare(togglescreensharingIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 10)
	public void Test_if_ToggleRecording_is_ByDefault_Off() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(8);
		String togglerecordingcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Toggle_Recording_Switch_button");
		dothis.getAttribute_and_Compare(togglerecordingcsslocator, "class", expectedclass);
	}

	@Test(priority = 11)
	public void Test_if_ToggleRecording_is_ToggleON() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(8);
		String togglerecordingcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		Thread.sleep(2000);
		// hover and then click
		dothis.hoverpointer(togglerecordingcsslocator);
		dothis.click_button_linktext(togglerecordingcsslocator);
		Thread.sleep(2000);
		// check if it was turned ON
		String toggledRecordingONcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_On");
		dothis.getAttribute_and_Compare(toggledRecordingONcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleRecoringIconcsslocator = (String) dothis.dashboardpanel.get("iconRecordingONcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("recording_icon");
		dothis.getAttribute_and_Compare(toggleRecoringIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 12)
	public void Test_if_ToggleRecording_is_ToggleOFF() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(8);
		String togglerecordingcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		// hover and then click
		dothis.hoverpointer(togglerecordingcsslocator);
		dothis.click_button_linktext(togglerecordingcsslocator);
		Thread.sleep(2000);
		// check if it was turned OFF
		String toggleRecordingOFFcsslocator = (String) dothis.dashboardpanel.get("switchOffcsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_Off");
		dothis.getAttribute_and_Compare(toggleRecordingOFFcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleRecordingIconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("recording_icon");
		dothis.getAttribute_and_Compare(toggleRecordingIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 13)
	public void Test_if_ToggleSpeaker_is_ByDefault_Off() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(9);
		String toggleSpeakercsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Toggle_Speaker_Switch_button");
		dothis.getAttribute_and_Compare(toggleSpeakercsslocator, "class", expectedclass);
	}

	@Test(priority = 14)
	public void Test_if_ToggleSpeaker_is_ToggleON() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(9);
		String toggleSpeakercsslocator = (String) dothis.dashboardpanel.get("csslocator");
		Thread.sleep(2000);
		// hover and then click
		dothis.hoverpointer(toggleSpeakercsslocator);
		dothis.click_button_linktext(toggleSpeakercsslocator);
		Thread.sleep(2000);
		// check if it was turned ON
		String toggledSpeakerONcsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_On");
		dothis.getAttribute_and_Compare(toggledSpeakerONcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleSpeakerIconcsslocator = (String) dothis.dashboardpanel.get("iconSpeakerONcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("Speaker_icon");
		dothis.getAttribute_and_Compare(toggleSpeakerIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 15)
	public void Test_if_ToggleSpeaker_is_ToggleOFF() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(9);
		String toggleSpeakercsslocator = (String) dothis.dashboardpanel.get("switchOncsslocator");
		Thread.sleep(2000);
		// hover and then click
		dothis.hoverpointer(toggleSpeakercsslocator);
		dothis.click_button_linktext(toggleSpeakercsslocator);
		Thread.sleep(2000);
		// check if it was turned OFF
		String toggleSpeakerOFFcsslocator = (String) dothis.dashboardpanel.get("switchOffcsslocator");
		String expectedclass = (String) dothis.dashboardpanel.get("Switch_Off");
		dothis.getAttribute_and_Compare(toggleSpeakerOFFcsslocator, "class", expectedclass);
		// then check if still it has camera icon
		String toggleSpeakerIconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("Speaker_icon");
		dothis.getAttribute_and_Compare(toggleSpeakerIconcsslocator, "class", expectediconName);
	}

	@Test(priority = 16)
	public void Test_if_TOOLSbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(toolscsslocator);
	}

	@Test(priority = 17)
	public void Test_if_TOOLSbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(toolscsslocator);
	}

	@Test(priority = 18)
	public void Test_if_TOOLSbutton_Name_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Tools_DropdownMenu");
		dothis.isText_Label_Name_correct(toolscsslocator, expectedName);
	}

	@Test(priority = 19)
	public void Test_if_TOOLSbutton_Icon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolsiconcsslocator = (String) dothis.dashboardpanel.get("Toolsiconcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("ToolsiconName");
		dothis.getAttribute_and_Compare(toolsiconcsslocator, "class", expectedName);
	}

	@Test(priority = 20)
	public void Test_If_When_Toolsbutton_is_Clicked_It_Shows_Its_SubmeButtons()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click
		dothis.hoverpointer(toolscsslocator);
		dothis.click_button_linktext(toolscsslocator);
		Thread.sleep(2000);
		// check if it shows the sub menus
		String toolssubmenucsslocator = (String) dothis.dashboardpanel.get("listsubmenuscsslocator");
		dothis.islinktext_or_button_is_Present(toolssubmenucsslocator);
	}

	@Test(priority = 21)
	public void Test_If_CreatePoll_submenu_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("createpollcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 22)
	public void Test_If_CreatePoll_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("createpollcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 23)
	public void Test_If_CreatePoll_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("createpollcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Share_Poll");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 24)
	public void Test_If_CreatePoll_is_clicked_itsModalDialog_willLaunch()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("createpollcsslocator");
		// hover and click
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check if create poll modal dialog will launch
		String modaldialogcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_Present(modaldialogcsslocator);
	}

	@Test(priority = 25)
	public void Test_If_CreatePoll_ModalDialog_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("modalTitlecsslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 26)
	public void Test_If_CreatePoll_ModalDialog_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("modalTitlecsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("modal_title");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}

	@Test(priority = 27)
	public void Test_If_CreatePoll_ModalDialog_QuestionLabel_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("questioncsslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 28)
	public void Test_If_CreatePoll_ModalDialog_QuestionLabel_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("questioncsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("QuestionLabel");
		dothis.isText_Label_Name_correct(csslocator, expectedText);
	}

	@Test(priority = 29)
	public void Test_If_CreatePoll_ModalDialog_Question_InputField_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("textareacsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 30)
	public void Test_If_CreatePoll_ModalDialog_Question_InputField_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("textareacsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 31)
	public void Test_If_CreatePoll_ModalDialog_Answer_InputField_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("answercsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 32)
	public void Test_If_CreatePoll_ModalDialog_Answer_InputField_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("answercsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 33)
	public void Test_If_CreatePoll_ModalDialog_Answer_InputField_Name_is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("answercsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Answer_inputfield");
		dothis.getAttribute_and_Compare(csslocator, "placeholder", expectedText);
	}

	@Test(priority = 34)
	public void Test_If_CreatePoll_ModalDialog_Answer_InputField_Plusbutton_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("answerpluscsslocator");
		Thread.sleep(2000);
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 35)
	public void Test_If_CreatePoll_ModalDialog_Answer_InputField_Plusbutton_is_Enabled()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("answerpluscsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 36)
	public void Test_If_CreatePoll_ModalDialog_Answer_InputField_Plusbutton_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("answerplusIconcsslocator");
		String expectedIcon = (String) dothis.dashboardpanel.get("answerPlusbutton");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedIcon);
	}

	@Test(priority = 37)
	public void Test_If_CreatePoll_ModalDialog_Save_button_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("savebuttoncsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 38)
	public void Test_If_CreatePoll_ModalDialog_Save_button_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("savebuttoncsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 39)
	public void Test_If_CreatePoll_ModalDialog_Save_button_Name_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("savebuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Save_button");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 40)
	public void Test_If_CreatePoll_ModalDialog_Close_button_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 41)
	public void Test_If_CreatePoll_ModalDialog_Close_button_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 42)
	public void Test_If_CreatePoll_ModalDialog_Close_button_Name_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Close_button");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 43)
	public void Test_If_CreatePoll_ModalDialog_Close_button_is_Clicked_Modal_willClosed()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(11);
		String csslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		// hover and click
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check if modal is closed
		String modalclosedcsslocator = (String) dothis.dashboardpanel.get("modalclosedcsslocator");
		String expectedStyle = (String) dothis.dashboardpanel.get("modalclose_style");
		dothis.getAttribute_and_Compare(modalclosedcsslocator, "style", expectedStyle);
	}

	@Test(priority = 44)
	public void Test_If_CameraMicSettings_submenu_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click
		dothis.hoverpointer(toolscsslocator);
		dothis.click_button_linktext(toolscsslocator);
		Thread.sleep(2000);
		// check if there is Camera/Mic Settings suub menu under Tools
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String cammicsettingscsslocator = (String) dothis.dashboardpanel.get("cameramiccsslocator");
		dothis.islinktext_or_button_is_Present(cammicsettingscsslocator);
	}

	@Test(priority = 45)
	public void Test_If_CameraMicSettings_submenu_is_Enabled()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String cammicsettingscsslocator = (String) dothis.dashboardpanel.get("cameramiccsslocator");
		dothis.islinktext_or_button_Enabled(cammicsettingscsslocator);
	}

	@Test(priority = 46)
	public void Test_If_CameraMicSettings_submenu_is_textCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String cammicsettingscsslocator = (String) dothis.dashboardpanel.get("cameramiccsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Camera_Mic_Settings");
		dothis.isText_Label_Name_correct(cammicsettingscsslocator, expectedText);
	}

	@Test(priority = 47)
	public void Test_If_CameraMicSettings_is_clicked_itsModalDialog_willLaunch()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String cammicsettingscsslocator = (String) dothis.dashboardpanel.get("cameramiccsslocator");
		// hover and click
		dothis.hoverpointer(cammicsettingscsslocator);
		dothis.click_button_linktext(cammicsettingscsslocator);
		Thread.sleep(2000);
		String modalcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_Present(modalcsslocator);
	}

	@Test(priority = 48)
	public void Test_If_CameraMicSettingsModal_title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		dothis.isLabel_Text_Name_Present(modaltitlecsslocator);
	}

	@Test(priority = 49)
	public void Test_If_CameraMicSettingsModal_title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("modal_title");
		dothis.isText_Label_Name_correct(modaltitlecsslocator, expectedTitle);
	}

	@Test(priority = 50)
	public void Test_If_CameraMicSettingsModal_Camera_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String cameralabelcsslocator = (String) dothis.dashboardpanel.get("cameralabelcsslocator");
		dothis.isLabel_Text_Name_Present(cameralabelcsslocator);
	}

	@Test(priority = 51)
	public void Test_If_CameraMicSettingsModal_Camera_Label_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String cameralabelcsslocator = (String) dothis.dashboardpanel.get("cameralabelcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("camera_label");
		dothis.isText_Label_Name_correct(cameralabelcsslocator, expectedText);
	}

	@Test(priority = 52)
	public void Test_If_CameraMicSettingsModal_Microphone_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String microphonelabelcsslocator = (String) dothis.dashboardpanel.get("microphonelabelcsslocator");
		dothis.isLabel_Text_Name_Present(microphonelabelcsslocator);
	}

	@Test(priority = 53)
	public void Test_If_CameraMicSettingsModal_Microphone_Label_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String microphonelabelcsslocator = (String) dothis.dashboardpanel.get("microphonelabelcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("microphone_label");
		dothis.isText_Label_Name_correct(microphonelabelcsslocator, expectedText);
	}

	@Test(priority = 54)
	public void Test_If_CameraMicSettingsModal_Microphone_Label_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String miclabeliconcsslocator = (String) dothis.dashboardpanel.get("miclabeliconcsslocator");
		String expectedIcon = (String) dothis.dashboardpanel.get("mic_label_icon");
		dothis.getAttribute_and_Compare(miclabeliconcsslocator, "class", expectedIcon);
	}

	@Test(priority = 55)
	public void Test_If_CameraMicSettingsModal_CameraPreviewDisplay_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String camerapreviewxpath = (String) dothis.dashboardpanel.get("camerapreviewxpath");
		Thread.sleep(2000);
		dothis.isElement_presentXPATH(camerapreviewxpath);
	}

	@Test(priority = 56)
	public void Test_If_CameraMicSettingsModal_CameraSelectionInputField_is_Present()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String camera_selection_inputfield_xpath = (String) dothis.dashboardpanel
				.get("camera_selection_inputfield_xpath");
		dothis.isElement_enabledXPATH(camera_selection_inputfield_xpath);
	}

	@Test(priority = 57)
	public void Test_If_CameraMicSettingsModal_CameraSelectionInputField_is_Enabled()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String camera_selection_inputfield_xpath = (String) dothis.dashboardpanel
				.get("camera_selection_inputfield_xpath");
		dothis.isElement_enabledXPATH(camera_selection_inputfield_xpath);
	}

	@Test(priority = 58)
	public void Test_If_CameraMicSettingsModal_MicrophoneSelectionInputField_is_Present()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String microphone_selection_inputfield_xpath = (String) dothis.dashboardpanel
				.get("microphone_selection_inputfield_xpath");
		dothis.isElement_presentXPATH(microphone_selection_inputfield_xpath);
	}

	@Test(priority = 59)
	public void Test_If_CameraMicSettingsModal_MicrophoneSelectionInputField_is_Enabled()
			throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String microphone_selection_inputfield_xpath = (String) dothis.dashboardpanel
				.get("microphone_selection_inputfield_xpath");
		dothis.isElement_enabledXPATH(microphone_selection_inputfield_xpath);
	}

	@Test(priority = 60)
	public void Test_If_CameraMicSettingsModal_SaveSettingsbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String savesettingsbuttoncsslocator = (String) dothis.dashboardpanel.get("savesettingsbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(savesettingsbuttoncsslocator);
	}

	@Test(priority = 61)
	public void Test_If_CameraMicSettingsModal_SaveSettingsbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String savesettingsbuttoncsslocator = (String) dothis.dashboardpanel.get("savesettingsbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(savesettingsbuttoncsslocator);
	}

	@Test(priority = 62)
	public void Test_If_CameraMicSettingsModal_SaveSettingsbutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String savesettingsbuttoncsslocator = (String) dothis.dashboardpanel.get("savesettingsbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Save_Settings_button");
		dothis.isText_Label_Name_correct(savesettingsbuttoncsslocator, expectedName);
	}

	@Test(priority = 63)
	public void Test_If_CameraMicSettingsModal_Closebutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String closebuttoncsslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		dothis.islinktext_or_button_is_Present(closebuttoncsslocator);
	}

	@Test(priority = 64)
	public void Test_If_CameraMicSettingsModal_Closebutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String closebuttoncsslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		dothis.islinktext_or_button_Enabled(closebuttoncsslocator);
	}

	@Test(priority = 65)
	public void Test_If_CameraMicSettingsModal_Closebutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String closebuttoncsslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Close_button");
		dothis.isText_Label_Name_correct(closebuttoncsslocator, expectedName);
	}

	@Test(priority = 66)
	public void Test_If_CameraMicSettings_ModalDialog_Close_button_is_Clicked_Modal_willClosed()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(12);
		String closebuttoncsslocator = (String) dothis.dashboardpanel.get("closebuttoncsslocator");
		// hover and click
		dothis.hoverpointer(closebuttoncsslocator);
		dothis.click_button_linktext(closebuttoncsslocator);
		Thread.sleep(2000);
		// check if modal closed
		String modalDialogcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_hidden(modalDialogcsslocator);
	}

	@Test(priority = 67)
	public void Test_If_ShareOffer_submenu_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click
		dothis.hoverpointer(toolscsslocator);
		dothis.click_button_linktext(toolscsslocator);
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffercsslocator = (String) dothis.dashboardpanel.get("shareoffercsslocator");
		dothis.islinktext_or_button_is_Present(shareoffercsslocator);
	}

	@Test(priority = 68)
	public void Test_If_ShareOffer_submenu_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffercsslocator = (String) dothis.dashboardpanel.get("shareoffercsslocator");
		dothis.islinktext_or_button_Enabled(shareoffercsslocator);
	}

	@Test(priority = 69)
	public void Test_If_ShareOffer_submenu_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffercsslocator = (String) dothis.dashboardpanel.get("shareoffercsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Share_Offer");
		dothis.isText_Label_Name_correct(shareoffercsslocator, expectedText);
	}

	@Test(priority = 70)
	public void Test_If_ShareOffer_is_clicked_itsModalDialog_willLaunch()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffercsslocator = (String) dothis.dashboardpanel.get("shareoffercsslocator");
		// hover and click
		dothis.hoverpointer(shareoffercsslocator);
		dothis.click_button_linktext(shareoffercsslocator);
		Thread.sleep(2000);
		String modalcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_Present(modalcsslocator);
	}

	@Test(priority = 71)
	public void Test_If_ShareOffer_ModalDialog_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		dothis.isLabel_Text_Name_Present(modaltitlecsslocator);
	}

	@Test(priority = 72)
	public void Test_If_ShareOffer_ModalDialog_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("modal_Title");
		dothis.isText_Label_Name_correct(modaltitlecsslocator, expectedText);
	}

	@Test(priority = 73)
	public void Test_If_OfferDescription_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerdescriptioncsslocator = (String) dothis.dashboardpanel.get("offerdescriptioncsslocator");
		dothis.isLabel_Text_Name_Present(offerdescriptioncsslocator);
	}

	@Test(priority = 74)
	public void Test_If_OfferDescription_Label_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerdescriptioncsslocator = (String) dothis.dashboardpanel.get("offerdescriptioncsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Offer_Description_Label");
		dothis.isText_Label_Name_correct(offerdescriptioncsslocator, expectedText);
	}

	@Test(priority = 75)
	public void Test_If_OfferDescription_InputField_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerdescriptionInputfieldcsslocator = (String) dothis.dashboardpanel
				.get("offerdescriptionInputfieldcsslocator");
		dothis.islinktext_or_button_is_Present(offerdescriptionInputfieldcsslocator);
	}

	@Test(priority = 76)
	public void Test_If_OfferDescription_InputField_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerdescriptionInputfieldcsslocator = (String) dothis.dashboardpanel
				.get("offerdescriptionInputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(offerdescriptionInputfieldcsslocator);
	}

	@Test(priority = 77)
	public void Test_If_OfferDescription_InputField_Label_is_textCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerdescriptionInputfieldcsslocator = (String) dothis.dashboardpanel
				.get("offerdescriptionInputfieldcsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("offerdescription_inputfield_label");
		dothis.getAttribute_and_Compare(offerdescriptionInputfieldcsslocator, "placeholder", expectedLabel);
	}

	@Test(priority = 78)
	public void Test_If_OfferURL_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerurlcsslocator = (String) dothis.dashboardpanel.get("offerurlcsslocator");
		dothis.isLabel_Text_Name_Present(offerurlcsslocator);
	}

	@Test(priority = 79)
	public void Test_If_OfferURL_Label_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerurlcsslocator = (String) dothis.dashboardpanel.get("offerurlcsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("Offer_URL_Label");
		dothis.isText_Label_Name_correct(offerurlcsslocator, expectedLabel);
	}

	@Test(priority = 80)
	public void Test_If_OfferUrl_Inputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerurlinputfieldcsslocator = (String) dothis.dashboardpanel.get("offerurlinputfieldcsslocator");
		dothis.islinktext_or_button_is_Present(offerurlinputfieldcsslocator);
	}

	@Test(priority = 81)
	public void Test_If_OfferUrl_Inputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerurlinputfieldcsslocator = (String) dothis.dashboardpanel.get("offerurlinputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(offerurlinputfieldcsslocator);
	}

	@Test(priority = 82)
	public void Test_If_OfferUrl_Inputfield_Label_is_textCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerurlinputfieldcsslocator = (String) dothis.dashboardpanel.get("offerurlinputfieldcsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("offerurl_Inputfield_Label");
		dothis.getAttribute_and_Compare(offerurlinputfieldcsslocator, "placeholder", expectedLabel);
	}

	@Test(priority = 83)
	public void Test_If_OfferButtonCaption_Label_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerbuttoncaptioncsslocator = (String) dothis.dashboardpanel.get("offerbuttoncaptioncsslocator");
		dothis.isLabel_Text_Name_Present(offerbuttoncaptioncsslocator);
	}

	@Test(priority = 84)
	public void Test_If_OfferButtonCaption_Label_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerbuttoncaptioncsslocator = (String) dothis.dashboardpanel.get("offerbuttoncaptioncsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Offer_button_caption_Label");
		dothis.isText_Label_Name_correct(offerbuttoncaptioncsslocator, expectedText);
	}

	@Test(priority = 85)
	public void Test_If_OfferButtonCaption_Inputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerbuttoncationinputfieldcsslocator = (String) dothis.dashboardpanel.get("offerbuttoncationinputfieldcsslocator");
		dothis.islinktext_or_button_is_Present(offerbuttoncationinputfieldcsslocator);
	}
	
	@Test(priority = 86)
	public void Test_If_OfferButtonCaption_Inputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerbuttoncationinputfieldcsslocator = (String) dothis.dashboardpanel.get("offerbuttoncationinputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(offerbuttoncationinputfieldcsslocator);
	}
	
	@Test(priority = 87)
	public void Test_If_OfferButtonCaption_Inputfield_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String offerbuttoncationinputfieldcsslocator = (String) dothis.dashboardpanel.get("offerbuttoncationinputfieldcsslocator");
		String expectedLabel = (String) dothis.dashboardpanel.get("OfferButtonCaption_Inputfield_Label");
		dothis.getAttribute_and_Compare(offerbuttoncationinputfieldcsslocator, "placeholder", expectedLabel);
	}
	
	@Test(priority=88)
	public void Test_If_ShareOffersbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffersbuttoncsslocator = (String) dothis.dashboardpanel.get("shareoffersbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(shareoffersbuttoncsslocator);
	}
	
	@Test(priority=89)
	public void Test_If_ShareOffersbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffersbuttoncsslocator = (String) dothis.dashboardpanel.get("shareoffersbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(shareoffersbuttoncsslocator);
	}
	
	@Test(priority=90)
	public void Test_If_ShareOffersbutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String shareoffersbuttoncsslocator = (String) dothis.dashboardpanel.get("shareoffersbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("ShareOffers_button");
		dothis.isText_Label_Name_correct(shareoffersbuttoncsslocator, expectedName);
	}
	
	@Test(priority=91)
	public void Test_If_ClearOffersbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String clearoffersbuttoncsslocator = (String) dothis.dashboardpanel.get("clearoffersbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(clearoffersbuttoncsslocator);
	}
	
	@Test(priority=92)
	public void Test_If_ClearOffersbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String clearoffersbuttoncsslocator = (String) dothis.dashboardpanel.get("clearoffersbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(clearoffersbuttoncsslocator);
	}
	
	@Test(priority=93)
	public void Test_If_ClearOffersbutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String clearoffersbuttoncsslocator = (String) dothis.dashboardpanel.get("clearoffersbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("ClearOffers_button");
		dothis.isText_Label_Name_correct(clearoffersbuttoncsslocator, expectedName);
	}
	
	@Test(priority=94)
	public void Test_If_ShareOffer_ModalDialog_Cancelbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(cancelbuttoncsslocator);
	}
	
	@Test(priority=95)
	public void Test_If_ShareOffer_ModalDialog_Cancelbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(cancelbuttoncsslocator);
	}
	
	@Test(priority=96)
	public void Test_If_ShareOffer_ModalDialog_Cancelbutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Cancel_button");
		dothis.isText_Label_Name_correct(cancelbuttoncsslocator, expectedName);
	}
	
	@Test(priority=97)
	public void Test_If_ShareOffer_ModalDialog_Cancebutton_is_Clicked_ShareOfferModalialog_willClosed() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(13);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		//hover and click
		dothis.hoverpointer(cancelbuttoncsslocator);
		dothis.click_button_linktext(cancelbuttoncsslocator);
		Thread.sleep(2000);
		String shareoffermodalclosedcsslocator = (String) dothis.dashboardpanel.get("shareoffermodalclosedcsslocator");
		String expectedstyle = (String) dothis.dashboardpanel.get("shareoffer_closed");
		dothis.getAttribute_and_Compare(shareoffermodalclosedcsslocator, "style", expectedstyle);
	}
	
	@Test(priority=98)
	public void Test_If_ShareSlides_submenu_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click
		dothis.hoverpointer(toolscsslocator);
		dothis.click_button_linktext(toolscsslocator);
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String shareslidescsslocator = (String) dothis.dashboardpanel.get("shareslidescsslocator");
		dothis.islinktext_or_button_is_Present(shareslidescsslocator);
	}
	
	@Test(priority=99)
	public void Test_If_ShareSlides_submenu_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String shareslidescsslocator = (String) dothis.dashboardpanel.get("shareslidescsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("Share_Slides");
		dothis.isText_Label_Name_correct(shareslidescsslocator, expectedText);
	}
	
	@Test(priority=100)
	public void Test_If_ShareSlides_is_clicked_itsModalDialog_willLaunch() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String shareslidescsslocator = (String) dothis.dashboardpanel.get("shareslidescsslocator");
		//hover and click
		dothis.hoverpointer(shareslidescsslocator);
		dothis.click_button_linktext(shareslidescsslocator);
		Thread.sleep(2000);
		String modaldialogcsslocator = (String) dothis.dashboardpanel.get("modaldialogcsslocator");
		dothis.islinktext_or_button_is_Present(modaldialogcsslocator);
	}
	
	@Test(priority=101)
	public void Test_If_ShareSlides_ModalDialog_Title_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		dothis.isLabel_Text_Name_Present(modaltitlecsslocator);
	}
	
	@Test(priority=102)
	public void Test_If_ShareSlides_ModalDialog_Title_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("modal_title");
		dothis.isText_Label_Name_correct(modaltitlecsslocator, expectedTitle);
	}
	
	@Test(priority=103)
	public void Test_If_ShareSlides_ModalDialog_Cancelbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(cancelbuttoncsslocator);
	}
	
	@Test(priority=104)
	public void Test_If_ShareSlides_ModalDialog_Cancelbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(cancelbuttoncsslocator);
	}
	
	@Test(priority=105)
	public void Test_If_ShareSlides_ModalDialog_Cancelbutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Cancel_button");
		dothis.isText_Label_Name_correct(cancelbuttoncsslocator, expectedName);
	}
	
	@Test(priority=106)
	public void Test_If_ShareSlides_ModalDialog_OKbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String okbuttoncsslocator = (String) dothis.dashboardpanel.get("okbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(okbuttoncsslocator);
	}
	
	@Test(priority=107)
	public void Test_If_ShareSlides_ModalDialog_OKbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String okbuttoncsslocator = (String) dothis.dashboardpanel.get("okbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(okbuttoncsslocator);
	}
	
	@Test(priority=108)
	public void Test_If_ShareSlides_ModalDialog_OKbutton_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String okbuttoncsslocator = (String) dothis.dashboardpanel.get("okbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Ok_button");
		dothis.isText_Label_Name_correct(okbuttoncsslocator, expectedName);
	}
	
	@Test(priority=109)
	public void Test_If_ShareSlides_ModalDialog_Cancel_button_is_Clicked_Modal_willClosed() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(14);
		String cancelbuttoncsslocator = (String) dothis.dashboardpanel.get("cancelbuttoncsslocator");
		//hover and click
		dothis.hoverpointer(cancelbuttoncsslocator);
		dothis.click_button_linktext(cancelbuttoncsslocator);
		Thread.sleep(2000);
		String modaldialogcsslocator = (String) dothis.dashboardpanel.get("modaldialogcsslocator");
		dothis.islinktext_or_button_is_hidden(modaldialogcsslocator);
	}
	
	@Test(priority=110)
	public void Test_If_ShareViaFbLive_submenu_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click
		dothis.hoverpointer(toolscsslocator);
		dothis.click_button_linktext(toolscsslocator);
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String sharefblivecsslocator = (String) dothis.dashboardpanel.get("sharefblivecsslocator");
		dothis.isLabel_Text_Name_Present(sharefblivecsslocator);
	}
	
	@Test(priority=111)
	public void Test_If_ShareViaFbLive_submenu_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String sharefblivecsslocator = (String) dothis.dashboardpanel.get("sharefblivecsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("ShareViaFBLive");
		dothis.isText_Label_Name_correct(sharefblivecsslocator, expectedText);
	}
	
	@Test(priority=112)
	public void Test_If_ShareViaFbLive_is_clicked_itsModalDialog_willLaunch() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String sharefblivecsslocator = (String) dothis.dashboardpanel.get("sharefblivecsslocator");
		//hover and then click
		dothis.hoverpointer(sharefblivecsslocator);
		dothis.click_button_linktext(sharefblivecsslocator);
		Thread.sleep(2000);
		String modalcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_Present(modalcsslocator);
	}
	
	@Test(priority=113)
	public void Test_If_ShareViaFbLiveModal_title_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		dothis.islinktext_or_button_is_Present(modaltitlecsslocator);
	}
	
	@Test(priority=114)
	public void Test_If_ShareViaFbLiveModal_title_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("modal_title");
		dothis.isText_Label_Name_correct(modaltitlecsslocator, expectedText);
	}
	
	@Test(priority=115)
	public void Test_If_ShareViaFbLiveModal_continuewithfbButton_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String continuefbimgbuttoncsslocator = (String) dothis.dashboardpanel.get("continuefbimgbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(continuefbimgbuttoncsslocator);
	}
	
	@Test(priority=116)
	public void Test_If_ShareViaFbLiveModal_continuewithfbButton_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String continuefbimgbuttoncsslocator = (String) dothis.dashboardpanel.get("continuefbimgbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(continuefbimgbuttoncsslocator);
	}
	
	@Test(priority=117)
	public void Test_If_ShareViaFbLiveModal_continuewithfbButton_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String continuefbimgbuttoncsslocator = (String) dothis.dashboardpanel.get("continuefbimgbuttoncsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("continuefbimgButton");
		dothis.getAttribute_and_Compare(continuefbimgbuttoncsslocator, "src", expectedName);
	}
	
	@Test(priority=118)
	public void Test_If_ShareViaFbLiveModal_SaveSettingsButton_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String savesettingscsslocator = (String) dothis.dashboardpanel.get("savesettingscsslocator");
		dothis.islinktext_or_button_is_Present(savesettingscsslocator);
	}
	
	@Test(priority=119)
	public void Test_If_ShareViaFbLiveModal_SaveSettingsButton_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String savesettingscsslocator = (String) dothis.dashboardpanel.get("savesettingscsslocator");
		dothis.islinktext_or_button_Enabled(savesettingscsslocator);
	}
	
	@Test(priority=120)
	public void Test_If_ShareViaFbLiveModal_SaveSettingsButton_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String savesettingscsslocator = (String) dothis.dashboardpanel.get("savesettingscsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Save_Settings_button");
		dothis.isText_Label_Name_correct(savesettingscsslocator, expectedName);
	}
	
	@Test(priority=121)
	public void Test_If_ShareViaFbLiveModal_CancelButton_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String cancelcsslocator = (String) dothis.dashboardpanel.get("cancelcsslocator");
		dothis.islinktext_or_button_is_Present(cancelcsslocator);
	}
	
	@Test(priority=122)
	public void Test_If_ShareViaFbLiveModal_CancelButton_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String cancelcsslocator = (String) dothis.dashboardpanel.get("cancelcsslocator");
		dothis.islinktext_or_button_Enabled(cancelcsslocator);
	}
	
	@Test(priority=123)
	public void Test_If_ShareViaFbLiveModal_CancelButton_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String cancelcsslocator = (String) dothis.dashboardpanel.get("cancelcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Cancel_button");
		dothis.isText_Label_Name_correct(cancelcsslocator, expectedName);
	}
	
	@Test(priority=124)
	public void Test_If_ShareViaFbLiveModal_Cancel_button_is_Clicked_Modal_willClosed() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(15);
		String cancelcsslocator = (String) dothis.dashboardpanel.get("cancelcsslocator");
		//hover and click
		dothis.hoverpointer(cancelcsslocator);
		dothis.click_button_linktext(cancelcsslocator);
		Thread.sleep(2000);
		String modalcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_hidden(modalcsslocator);
	}
	
	@Test(priority=125)
	public void Test_If_ShareViaRTMP_submenu_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(10);
		String toolscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click
		dothis.hoverpointer(toolscsslocator);
		dothis.click_button_linktext(toolscsslocator);
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(16);
		String shareviartmpcsslocator = (String) dothis.dashboardpanel.get("shareviartmpcsslocator");
		dothis.islinktext_or_button_is_Present(shareviartmpcsslocator);
	}
	
	@Test(priority=126)
	public void Test_If_ShareViaRTMP_submenu_is_textCorrect() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(16);
		String shareviartmpcsslocator = (String) dothis.dashboardpanel.get("shareviartmpcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("ShareViaRTMP");
		dothis.isText_Label_Name_correct(shareviartmpcsslocator, expectedText);
	}
	
	@Test(priority=127)
	public void Test_If_ShareViaRTMP_submenu_is_clicked_itsModalDialog_willLaunch() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(16);
		String shareviartmpcsslocator = (String) dothis.dashboardpanel.get("shareviartmpcsslocator");
		//hover and then click
		dothis.hoverpointer(shareviartmpcsslocator);
		dothis.click_button_linktext(shareviartmpcsslocator);
		Thread.sleep(2000);
		String modalcsslocator = (String) dothis.dashboardpanel.get("modalcsslocator");
		dothis.islinktext_or_button_is_Present(modalcsslocator);
	}
	
	@Test(priority=128)
	public void Test_If_ShareViaRTMP_Modal_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(16);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		dothis.isLabel_Text_Name_Present(modaltitlecsslocator);
	}
	
	@Test(priority=129)
	public void Test_If_ShareViaRTMP_Modal_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(16);
		String modaltitlecsslocator = (String) dothis.dashboardpanel.get("modaltitlecsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("modal_title");
		dothis.isText_Label_Name_correct(modaltitlecsslocator, expectedText);
	}
}
