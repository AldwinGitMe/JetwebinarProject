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

public class aaa0007HostWebinarRoomCurrentAttendeeslist_AND_GlobalChatTestUI {
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
	public void Test_If_CurrentAttendeesPortlet_is_Present() throws IOException, ParseException, InterruptedException {
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
		// switch tab
		dothis.checkNewWindowOpen();
		Thread.sleep(5000);
		// hover and then click Join room now button
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(20);
		String joinroomnowbuttoncsslocator = (String) dothis.dashboardpanel.get("joinroomnowbuttoncsslocator");
		dothis.hoverpointer(joinroomnowbuttoncsslocator);
		dothis.click_button_linktext(joinroomnowbuttoncsslocator);

		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String portlet_AttendeesLISTcsslocator = (String) dothis.dashboardpanel.get("portlet_AttendeesLISTcsslocator");
		dothis.islinktext_or_button_is_Present(portlet_AttendeesLISTcsslocator);
	}
	
	@Test(priority = 2)
	public void Test_If_CurrentAttendeesPortlet_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String currentattendeestitlecsslocator = (String) dothis.dashboardpanel.get("currentattendeestitlecsslocator");
		dothis.isLabel_Text_Name_Present(currentattendeestitlecsslocator);
	}
	
	@Test(priority = 3)
	public void Test_If_CurrentAttendeesPortlet_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String currentattendeestitlecsslocator = (String) dothis.dashboardpanel.get("currentattendeestitlecsslocator");
		String expectedTitle = (String) dothis.dashboardpanel.get("currentAttendeesTitle");
		dothis.isText_Label_Name_correct(currentattendeestitlecsslocator, expectedTitle);
	}
	
	@Test(priority = 4)
	public void Test_If_CurrentAttendeesPortlet_Title_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String currentattendeestitleiconcsslocator = (String) dothis.dashboardpanel
				.get("currentattendeestitleiconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("iconName");
		dothis.getAttribute_and_Compare(currentattendeestitleiconcsslocator, "class", expectedIconName);
	}
	
	@Test(priority = 5)
	public void Test_If_CurrentAttendeesPortlet_HostName_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(18);
		String hostnameupperrightcornerxpath = (String) dothis.dashboardpanel.get("hostnameupperrightcornerxpath");
		String hostname = dothis.getTextXPATH(hostnameupperrightcornerxpath);
		dothis.checkIfTheNameIsInTheAttendeesList(hostname);
	}
	
	@Test(priority = 6)
	public void Test_If_CurrentAttendeesPortlet_HostName_Has_Tagwith_Host_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String hostnamecsslocator = (String) dothis.dashboardpanel.get("hostnamecsslocator");
		String HostName_Tag = (String) dothis.dashboardpanel.get("HostName_Tag");
		dothis.isText_Label_Name_correct(hostnamecsslocator, HostName_Tag);
	}
	
	@Test(priority = 7)
	public void Test_If_CurrentAttendeesPortlet_HostName_is_Correct_and_Match_in_HostName_AboveUpperRightCorner()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String hostnamexpath = (String) dothis.dashboardpanel.get("hostnamexpath");
		// this is from the host name that appear at upper right corner
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(18);
		String hostnameupperrightcornerxpath = (String) dothis.dashboardpanel.get("hostnameupperrightcornerxpath");
		Thread.sleep(5000);
		dothis.isThisgetTextEle_Matchwith_otherGetTextEle_XPATH(hostnamexpath, hostnameupperrightcornerxpath);
	}
	
	@Test(priority = 8)
	public void Test_If_CurrentAttendeesPortlet_Title_TotalCount_IfHostOnly_is_PresentanCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String totalcountcsslocator = (String) dothis.dashboardpanel.get("totalcountcsslocator");
		String expectedCount = (String) dothis.dashboardpanel.get("attendeestotalcount_ifHostonly");
		Thread.sleep(2000);
		dothis.isText_Label_Name_correct(totalcountcsslocator, expectedCount);
	}
	
	@Test(priority = 9)
	public void Test_If_FindAttendee_InputField_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String findattendeecsslocator = (String) dothis.dashboardpanel.get("findattendeecsslocator");
		dothis.islinktext_or_button_is_Present(findattendeecsslocator);
	}
	
	@Test(priority = 10)
	public void Test_If_FindAttendee_InputField_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String findattendeecsslocator = (String) dothis.dashboardpanel.get("findattendeecsslocator");
		dothis.islinktext_or_button_Enabled(findattendeecsslocator);
	}
	
	@Test(priority = 11)
	public void Test_If_FindAttendee_InputField_label_is_PresentandCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String findattendeecsslocator = (String) dothis.dashboardpanel.get("findattendeecsslocator");
		String expectedlabel = (String) dothis.dashboardpanel.get("Find_Attendee");
		dothis.getAttribute_and_Compare(findattendeecsslocator, "placeholder", expectedlabel);
	}
	
	@Test(priority = 12)
	public void Test_If_Attendeeslist_FullscreenIcon_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		dothis.islinktext_or_button_is_Present(fullscreeniconcsslocator);
	}
	
	@Test(priority = 13)
	public void Test_If_Attendeeslist_FullscreenIcon_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		dothis.islinktext_or_button_Enabled(fullscreeniconcsslocator);
	}
	
	@Test(priority = 14)
	public void Test_If_When_FullscreenIcon_isClicked_AttendeeslistPortlet_willExpand()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		// hover and then click
		dothis.hoverpointer(fullscreeniconcsslocator);
		dothis.click_button_linktext(fullscreeniconcsslocator);
		Thread.sleep(3000);
		// check if it becomes full screen
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String fullscreenAttendeelistcsslocator = (String) dothis.dashboardpanel
				.get("fullscreenAttendeelistcsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("fullscreenclass");

		dothis.islinktext_or_button_is_Present(fullscreenAttendeelistcsslocator);
		dothis.getAttribute_and_Compare(fullscreenAttendeelistcsslocator, "class", expectedText);
	}
	
	@Test(priority = 15)
	public void Test_If_When_Click_Again_FullscreenIcon_Itwillgoback_ToNormalsize()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		// hover and then click
		dothis.hoverpointer(fullscreeniconcsslocator);
		dothis.click_button_linktext(fullscreeniconcsslocator);
		Thread.sleep(3000);
		// check if it goes back to normal size
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(17);
		String fullscreenAttendeelistcsslocator = (String) dothis.dashboardpanel
				.get("fullscreenAttendeelistcsslocator");
		dothis.islinktext_or_button_is_hidden(fullscreenAttendeelistcsslocator);
	}
	
	@Test(priority = 16)
	public void Test_If_portletGlobalChat_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String portletglobalchatcsslocator = (String) dothis.dashboardpanel.get("portletglobalchatcsslocator");
		Thread.sleep(3000);
		dothis.islinktext_or_button_is_Present(portletglobalchatcsslocator);
	}
	
	@Test(priority = 17)
	public void Test_If_portletGlobalChat_Title_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String titlecsslocator = (String) dothis.dashboardpanel.get("titlecsslocator");
		dothis.isLabel_Text_Name_Present(titlecsslocator);
	}
	
	@Test(priority = 18)
	public void Test_If_portletGlobalChat_Title_is_textCorrect() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String titlecsslocator = (String) dothis.dashboardpanel.get("titlecsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("portletglobalchat_Title");
		dothis.isText_Label_Name_correct(titlecsslocator, expectedText);
	}
	
	@Test(priority = 19)
	public void Test_If_portletGlobalChat_Title_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String iconcsslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectediconName = (String) dothis.dashboardpanel.get("iconName");
		dothis.getAttribute_and_Compare(iconcsslocator, "class", expectediconName);
	}
	
	@Test(priority = 20)
	public void Test_If_portletGlobalChat_NoNewMessageInfo_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String nonewmessageDefaultxpath = (String) dothis.dashboardpanel.get("nonewmessageDefaultxpath");
		dothis.isElement_presentXPATH(nonewmessageDefaultxpath);
	}
	
	@Test(priority = 21)
	public void Test_If_portletGlobalChat_NoNewMessageInfo_is_textCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String nonewmessageDefaultxpath = (String) dothis.dashboardpanel.get("nonewmessageDefaultxpath");
		String NoNewMessageDefault = (String) dothis.dashboardpanel.get("NoNewMessageDefault");
		dothis.istextCorrect(nonewmessageDefaultxpath, NoNewMessageDefault);
	}
	
	@Test(priority = 22)
	public void Test_If_SendaMessageInputfield_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String SendaMessagecsslocator = (String) dothis.dashboardpanel.get("SendaMessagecsslocator");
		dothis.islinktext_or_button_is_Present(SendaMessagecsslocator);
	}
	
	@Test(priority = 23)
	public void Test_If_SendaMessageInputfield_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String SendaMessagecsslocator = (String) dothis.dashboardpanel.get("SendaMessagecsslocator");
		dothis.islinktext_or_button_Enabled(SendaMessagecsslocator);
	}
	
	@Test(priority = 24)
	public void Test_If_SendaMessageInputfield_is_textCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String SendaMessagecsslocator = (String) dothis.dashboardpanel.get("SendaMessagecsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("SendaMessage");
		dothis.getAttribute_and_Compare(SendaMessagecsslocator, "placeholder", expectedName);
	}
	
	@Test(priority = 25)
	public void Test_If_SendButton_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String sendbuttoncsslocator = (String) dothis.dashboardpanel.get("sendbuttoncsslocator");
		dothis.islinktext_or_button_is_Present(sendbuttoncsslocator);
	}
	
	@Test(priority = 26)
	public void Test_If_SendButton_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String sendbuttoncsslocator = (String) dothis.dashboardpanel.get("sendbuttoncsslocator");
		dothis.islinktext_or_button_Enabled(sendbuttoncsslocator);
	}
	
	@Test(priority = 27)
	public void Test_If_SendButton_Name_is_PresentandCorrect()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String sendbuttoncsslocator = (String) dothis.dashboardpanel.get("sendbuttoncsslocator");
		String Send_button = (String) dothis.dashboardpanel.get("Send_button");
		dothis.isText_Label_Name_correct(sendbuttoncsslocator, Send_button);
	}
	
	@Test(priority = 28)
	public void Test_If_FullscreenIcon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		dothis.islinktext_or_button_is_Present(fullscreeniconcsslocator);
	}

	@Test(priority = 29)
	public void Test_If_FullscreenIcon_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		dothis.islinktext_or_button_Enabled(fullscreeniconcsslocator);
	}
	
	@Test(priority = 30)
	public void Test_If_When_FullscreenIcon_isClicked_portletGlobalChat_willExpand()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		// hover and then click
		dothis.hoverpointer(fullscreeniconcsslocator);
		dothis.click_button_linktext(fullscreeniconcsslocator);
		Thread.sleep(3000);
		// check if it becomes full screen
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String fullscreencsslocator = (String) dothis.dashboardpanel.get("fullscreencsslocator");
		String expectedText = (String) dothis.dashboardpanel.get("fullscreenclass");

		dothis.islinktext_or_button_is_Present(fullscreencsslocator);
		dothis.getAttribute_and_Compare(fullscreencsslocator, "class", expectedText);
	}

	@Test(priority = 31)
	public void Test_If_When_Click_Again_FullscreenIcon_Itwillgoback_ToNormalsizePortletGlobalChat()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String fullscreeniconcsslocator = (String) dothis.dashboardpanel.get("fullscreeniconcsslocator");
		// hover and then click
		dothis.hoverpointer(fullscreeniconcsslocator);
		dothis.click_button_linktext(fullscreeniconcsslocator);
		Thread.sleep(3000);
		// check if it goes back to normal size
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(19);
		String fullscreencsslocator = (String) dothis.dashboardpanel.get("fullscreencsslocator");
		dothis.islinktext_or_button_is_hidden(fullscreencsslocator);
	}
/*
	@Test(priority = 32)
	public void Test_If_ToggleGallerybutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String togglegallerycsslocator = (String) dothis.dashboardpanel.get("togglegallerycsslocator");
		dothis.islinktext_or_button_is_Present(togglegallerycsslocator);
	}

	@Test(priority = 33)
	public void Test_If_ToggleGallerybutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String togglegallerycsslocator = (String) dothis.dashboardpanel.get("togglegallerycsslocator");
		dothis.islinktext_or_button_Enabled(togglegallerycsslocator);
	}

	@Test(priority = 34)
	public void Test_If_ToggleGallerybutton_Icon_is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String togglegalleryOFFiconcsslocator = (String) dothis.dashboardpanel.get("togglegalleryOFFiconcsslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("toggleGallery_icon");
		dothis.getAttribute_and_Compare(togglegalleryOFFiconcsslocator, "class", expectedIconName);
	}

	@Test(priority = 35)
	public void Test_If_ToggleGallerySwitch_ON() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String togglegallerycsslocator = (String) dothis.dashboardpanel.get("togglegallerycsslocator");
		// hover and then click
		dothis.hoverpointer(togglegallerycsslocator);
		dothis.click_button_linktext(togglegallerycsslocator);
		Thread.sleep(2000);
		// check if it switch to ON
		String ToggleGallery_button_ON_style = (String) dothis.dashboardpanel.get("ToggleGallery_button_ON_style");
		dothis.getAttribute_and_Compare(togglegallerycsslocator, "style", ToggleGallery_button_ON_style);
		// check also if there is still icon
		String togglegalleryONiconcsslocator = (String) dothis.dashboardpanel.get("togglegalleryONiconcsslocator");
		String toggleGallery_icon = (String) dothis.dashboardpanel.get("toggleGallery_icon");
		dothis.getAttribute_and_Compare(togglegalleryONiconcsslocator, "class", toggleGallery_icon);
	}

	@Test(priority =36)
	public void Test_If_ToggleGallerySwitch_OFF() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.HostMeetingRoomArr.get(3);
		String togglegallerycsslocator = (String) dothis.dashboardpanel.get("togglegallerycsslocator");
		// hover and then click
		dothis.hoverpointer(togglegallerycsslocator);
		dothis.click_button_linktext(togglegallerycsslocator);
		Thread.sleep(2000);
		// check if it switch back to OFF
		String ToggleGallery_button_OFF_style = (String) dothis.dashboardpanel.get("ToggleGallery_button_OFF_style");
		dothis.getAttribute_and_Compare(togglegallerycsslocator, "style", ToggleGallery_button_OFF_style);
		// check also if there is still icon
		String togglegalleryOFFiconcsslocator = (String) dothis.dashboardpanel.get("togglegalleryOFFiconcsslocator");
		String toggleGallery_icon = (String) dothis.dashboardpanel.get("toggleGallery_icon");
		dothis.getAttribute_and_Compare(togglegalleryOFFiconcsslocator, "class", toggleGallery_icon);
	}  */
}
