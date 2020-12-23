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

public class aaa0005HostWebinarRoom_Logo_BroadcastPortlet_ConferenceInfoTestUI {
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
	public void Test_If_There_Is_JetWebinar_Logo_Inside_The_Host_WebinarRoom() throws IOException, ParseException, InterruptedException {
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
		
		dothis.dashboardpanel = (JSONObject) dothis.StartMeetingRoomModalDialogArr.get(3);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and then click Start meeting now button
		dothis.hoverpointer(linkcsslocator);
		dothis.click_button_linktext(linkcsslocator);
		//switch tab
		dothis.checkNewWindowOpen();
		Thread.sleep(5000);
		//hover and then click Join room now button
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		dothis.hoverpointer(joinroomnowbuttoncsslocator);
		dothis.click_button_linktext(joinroomnowbuttoncsslocator);
		//test if there is JetWebinar Logo
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(1);
		String imgcsslocator = (String) dothis.dashboardpanel.get("imgcsslocator");
		dothis.islinktext_or_button_is_Present(imgcsslocator);
	}
	
	@Test(priority = 2)
	public void Test_if_Correct_JetWebinarLogo_in_HostMeetingRoom()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(1);
		String imgcsslocator = (String) dothis.dashboardpanel.get("imgcsslocator");
		String expectedImg = (String) dothis.dashboardpanel.get("JetWebinar_logo");
		dothis.getAttribute_and_Compare(imgcsslocator, "src", expectedImg);
	}
	
	@Test(priority = 3)
	public void Test_if_BROACASTROOM_videoScreen_Title_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(2);
		String xpathlocator = (String) dothis.dashboardpanel.get("xpathcsslocator");
		dothis.isElement_presentXPATH(xpathlocator);
	}
	
	@Test(priority = 4)
	public void Test_if_BROACASTROOM_videoScreen_Title_is_textCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(2);
		String xpathlocator = (String) dothis.dashboardpanel.get("xpathcsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("BROADCAST_ROOM");
		dothis.istextCorrect(xpathlocator, expectedTitle);
	}
	
	@Test(priority = 5)
	public void Test_if_BROACASTROOM_videoScreen_Title_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(2);
		String xpathiconcsslocator = (String) dothis.dashboardpanel.get("xpathiconcsslocator");
		String expectedIcon = (String) dothis.dashboardpanel.get("BROADCAST_ROOM_icon");
		Thread.sleep(2000);
		dothis.getattributeValueXPATH(xpathiconcsslocator, "class", expectedIcon);
	}
	
	@Test(priority = 6)
	public void Test_if_FullScreen_ButtonIcon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		Thread.sleep(2000);
		dothis.islinktext_or_button_is_Present(fullscreeniconcsslocator);
	}
	
	@Test(priority = 7)
	public void Test_if_FullScreen_ButtonIcon_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		dothis.islinktext_or_button_Enabled(fullscreeniconcsslocator);
	}
	
	@Test(priority = 8)
	public void Test_if_Conference_Info_button_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(4);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(linkcsslocator);
	}
	
	@Test(priority = 9)
	public void Test_if_Conference_Info_button_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(4);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(linkcsslocator);
	}
	
	@Test(priority = 10)
	public void Test_if_Conference_Info_button_name_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(4);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("ConferenceInfo_button");
		dothis.isText_Label_Name_correct(linkcsslocator, expectedName);
	}
	
	@Test(priority = 11)
	public void Test_if_Conference_Info_button_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(4);
		String linkcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("ConferenceInfo_icon");
		Thread.sleep(2000);
		dothis.getAttribute_and_Compare(linkcsslocator, "class", expectedName);
	}
	
	@Test(priority = 12)
	public void Test_if_Conference_Info_ModalDialog_willLaunch_When_its_button_isClicked()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(4);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click conference info button
		dothis.hoverpointer(linkcsslocator);
		dothis.click_button_linktext(linkcsslocator);
		Thread.sleep(2000);
		// check if modal dialog is launched
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}
	
	@Test(priority = 13)
	public void Test_if_Conference_Info_ModalDialog_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("titlecsslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}
	
	@Test(priority = 14)
	public void Test_if_Conference_Info_ModalDialog_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("titlecsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("Conference Information_Modal_Dialog");
		dothis.isText_Label_Name_correct(csslocator, expectedTitle);
	}
	
	@Test(priority = 15)
	public void Test_if_Conference_Info_ModalDialog_LabelLink_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("labelcsslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}
	
	@Test(priority = 16)
	public void Test_if_Conference_Info_ModalDialog_LabelLink_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("labelcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("ConferenceLink_label");
		dothis.isText_Label_Name_correct(csslocator, expectedText);
	}
	
	@Test(priority = 17)
	public void Test_if_Conference_Info_ModalDialog_ConferenceLink_Inputfield_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String xpathcsslocator = (String) dothis.dashboardpanel.get("ConferenceLinkxpathcsslocator");
		dothis.isElement_presentXPATH(xpathcsslocator);
	}
	
	@Test(priority = 18)
	public void Test_if_Conference_Info_ModalDialog_ConferenceLink_Inputfield_is_NotEmpty_and_Correct_Link()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String xpathcsslocator = (String) dothis.dashboardpanel.get("ConferenceLinkxpathcsslocator");
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(4);
		String testURL = (String) dothis.dashboardpanel.get("Test_URL");
		dothis.getattributeValueXPATH(xpathcsslocator, "value", testURL);
	}
	
	@Test(priority = 19)
	public void Test_if_Conference_Info_ModalDialog_CloseButton_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("closelinkcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}
	
	@Test(priority = 20)
	public void Test_if_Conference_Info_ModalDialog_CloseButton_is_Enabled()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("closelinkcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}
	
	@Test(priority = 21)
	public void Test_if_Conference_Info_ModalDialog_CloseButton_name_is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("closelinkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("close_button");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}
	
	@Test(priority = 22)
	public void Test_if_Conference_Info_ModalDialog_CloseButton_is_Clicked_Modal_willClosed()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("closelinkcsslocator");
		// hovert to close button then click
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check if modal dialog is closed
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String modalDialogcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_is_hidden(modalDialogcsslocator);
	}
	
	@Test(priority = 23)
	public void Test_if_Conference_Info_ModalDialog_xButton_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// open back first the conference modal dialog
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(4);
		String linkcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover and click conference info button
		dothis.hoverpointer(linkcsslocator);
		dothis.click_button_linktext(linkcsslocator);
		Thread.sleep(2000);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("xlinkcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}
	
	@Test(priority = 24)
	public void Test_if_Conference_Info_ModalDialog_xButton_is_Enabled()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("xlinkcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}
	
	@Test(priority = 25)
	public void Test_if_Conference_Info_ModalDialog_xButton_is_Clicked_Modal_willClosed()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String csslocator = (String) dothis.dashboardpanel.get("xlinkcsslocator");
		// hover then click
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check if modal dialog is closed
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(5);
		String modalDialogcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.islinktext_or_button_is_hidden(modalDialogcsslocator);
	}
	
	@Test(priority = 26)
	public void Test_If_BroadcastRoom_Screen_Is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(2);
		String broadcastscreencsslocator = (String) dothis.dashboardpanel.get("broadcastscreencsslocator");
		dothis.islinktext_or_button_is_Present(broadcastscreencsslocator);
	}
	
	@Test(priority = 27)
	public void Test_If_When_FullscreenIcon_Is_Click_BroadcastRoom_Will_GoFullScreen() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		//hover and then click
		dothis.hoverpointer(fullscreeniconcsslocator);
		dothis.click_button_linktext(fullscreeniconcsslocator);
		//test if the broadcasting room expand
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(2);
		String broadcastscreencsslocator = (String) dothis.dashboardpanel.get("broadcastscreencsslocator");
		String fullscreensize = (String) dothis.dashboardpanel.get("fullscreensize");
		Thread.sleep(3000);
		dothis.getAttribute_and_Compare(broadcastscreencsslocator, "style", fullscreensize);
	}
	
	@Test(priority = 28)
	public void Test_If_When_FullscreenIcon_Is_Click_BackAgain_BroadcastRoom_Will_GoBackToNormalScreenSize() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		//hover and then click
		dothis.hoverpointer(fullscreeniconcsslocator);
		dothis.click_button_linktext(fullscreeniconcsslocator);
		//test if the broadcasting room expand
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(2);
		String broadcastscreencsslocator = (String) dothis.dashboardpanel.get("broadcastscreencsslocator");
		String normalscreensize = (String) dothis.dashboardpanel.get("normalscreensize");
		dothis.getAttribute_and_Compare(broadcastscreencsslocator, "style", normalscreensize);
	}
}
