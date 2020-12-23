package MeetingRooms;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
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

public class aaa0003AddNewMeetingRoomTest {
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
	public void Test_if_When_click_addNewbutton_Correct_URLdestination()
			throws InterruptedException, IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		this.userhostlogin();
		// click Meeting Rooms in the Dashboard panel
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(0);
		String meetingroomscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover to Meetings menu
		dothis.hoverpointer(meetingroomscsslocator);
		dothis.click_button_linktext(meetingroomscsslocator);
		Thread.sleep(2000);
		// click Add New button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(5);
		String Addnewbuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.hoverpointer(Addnewbuttoncsslocator);
		dothis.click_button_linktext(Addnewbuttoncsslocator);
		Thread.sleep(2000);
		// check if correct url destination after clicking the add new button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(6);
		String expectedURL = (String) dothis.dashboardpanel.get("url_destination");
		dothis.getCurrentURL(expectedURL);
	}

	@Test(priority = 2)
	public void Test_if_AddMeetingRoomPageTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room Page Title
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(6);
		String csslocator = (String) dothis.dashboardpanel.get("pagetitlecsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 3)
	public void Test_if_AddMeetingRoomPageTitle_Text_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room Page Title text
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(6);
		String csslocator = (String) dothis.dashboardpanel.get("pagetitlecsslocator");
		String expectedtitle = (String) dothis.dashboardpanel.get("page_title");
		dothis.isText_Label_Name_correct(csslocator, expectedtitle);
	}

	@Test(priority = 4)
	public void Test_if_AddMeetingRoomPageTitle_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room Page Title Icon
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(6);
		String csslocator = (String) dothis.dashboardpanel.get("pagetitle_iconcsslocator");
		String expectedtitle = (String) dothis.dashboardpanel.get("pagetitle_icon");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedtitle);
	}

	@Test(priority = 5)
	public void Test_if_AddMeetingRoom_InputField_Label_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room input field label
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String csslocator = (String) dothis.dashboardpanel.get("labelcsslocator");
		dothis.isLabel_Text_Name_Present(csslocator);
	}

	@Test(priority = 6)
	public void Test_if_AddMeetingRoom_InputField_Label_name_is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room input field label
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String csslocator = (String) dothis.dashboardpanel.get("labelcsslocator");
		String expectedlabel = (String) dothis.dashboardpanel.get("Meeting_Room_Handle_Label");
		dothis.isText_Label_Name_correct(csslocator, expectedlabel);
	}

	@Test(priority = 7)
	public void Test_if_AddMeetingRoom_InputField__is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room input field
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String csslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 8)
	public void Test_if_AddMeetingRoom_InputField__is_Enabled()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room input field
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String csslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 9)
	public void Test_if_AddMeetingRoom_InputField__name_is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room input field
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String csslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		String expectedname = (String) dothis.dashboardpanel.get("Input_field");
		dothis.dashboardpanel = (JSONObject) dothis.userloginArr.get(4);
		String testURL = (String) dothis.dashboardpanel.get("Test_URL");
		dothis.getAttribute_and_Compare(csslocator, "placeholder", testURL + expectedname);
	}

	@Test(priority = 10)
	public void Test_if_AddMeetingRoom_button__is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(8);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 11)
	public void Test_if_AddMeetingRoom_button__is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(8);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 12)
	public void Test_if_AddMeetingRoom_button__name_is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(8);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("AddMeetingRoom_button");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 12)
	public void Test_if_AddMeetingRoom_button__icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Add Meeting Room button icon
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(8);
		String csslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("AddMeetingRoomButton_icon");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedName);
	}

	@Test(priority = 13)
	public void Test_if_ReturnToMeetings_button__is_Present() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Return To Meetings button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(9);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_is_Present(csslocator);
	}

	@Test(priority = 14)
	public void Test_if_ReturnToMeetings_button_is_Enabled() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Return To Meetings button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(9);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.islinktext_or_button_Enabled(csslocator);
	}

	@Test(priority = 15)
	public void Test_if_ReturnToMeetings_button_name_is_Correct()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Return To Meetings button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(9);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Return_To_Meetings_button");
		dothis.isText_Label_Name_correct(csslocator, expectedName);
	}

	@Test(priority = 16)
	public void Test_if_ReturnToMeetings_button_Icon_is_Present()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// Return To Meetings button icon
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(9);
		String csslocator = (String) dothis.dashboardpanel.get("iconcsslocator");
		String expectedName = (String) dothis.dashboardpanel.get("Return_To_Meetings_button_icon");
		dothis.getAttribute_and_Compare(csslocator, "class", expectedName);
	}

	@Test(priority = 17)
	public void Test_if_addNewMeeting_NoMeetingName_is_entered()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click Add meeting button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(8);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);
		Thread.sleep(2000);
		// check url as it should remain on add url
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(6);
		String expectedurl = (String) dothis.dashboardpanel.get("url_destination");
		dothis.getCurrentURL(expectedurl);
		Thread.sleep(2000);
		// check alert error text
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(11);
		String errorcsslocator = (String) dothis.dashboardpanel.get("error1csslocator");
		String expectederrotext = (String) dothis.dashboardpanel.get("ThisFieldIsRequired");
		dothis.isLabel_Text_Name_Present(errorcsslocator);
		dothis.isText_Label_Name_correct(errorcsslocator, expectederrotext);
		Thread.sleep(2000);
		// check instruction text
		String instructcsslocator = (String) dothis.dashboardpanel.get("instructioncsslocator");
		String expectedinstructtext = (String) dothis.dashboardpanel.get("EnterMeetingURLhandlehere");
		dothis.isLabel_Text_Name_Present(instructcsslocator);
		dothis.isText_Label_Name_correct(instructcsslocator, expectedinstructtext);
	}

	@Test(priority = 18)
	public void Test_if_AddnewMeeting_enterAName() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String inputfieldccslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");

		// enter meeting name
		dothis.enterdataInputfield(inputfieldccslocator, "thisaddmeeting");

		// click Add meeting button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(8);
		String csslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		// hover first
		dothis.hoverpointer(csslocator);
		dothis.click_button_linktext(csslocator);

		// check url destination as it should go back to all meetings room
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(12);
		String expectedurl = (String) dothis.dashboardpanel.get("after_a_successful_addNewmeeting");
		dothis.getCurrentURL(expectedurl);

		// check if it is added in the meeting name column in the table last row
		String thisnewlyaddedmeetingroomName = dothis.getmeetingName();
		Assert.assertTrue(thisnewlyaddedmeetingroomName.contains("thisaddmeeting"));
		Thread.sleep(3000);

		// check if there is Start Meeting Now button in next column same row
		dothis.StartMeetingNowButtonisPresent();
		// check if enabled
		dothis.StartMeetingNowButtonisEnabled();

		// check if correct button name
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(10);
		String expectedbuttonName = (String) dothis.dashboardpanel.get("Start_Meeting_Now_button");
		dothis.StartMeetingNowButtonNameIsCorrect(expectedbuttonName);
		
		// check icon is present
		String StartMeetingNow_button_icon = (String) dothis.dashboardpanel.get("StartMeetingNow_button_icon");
		dothis.StartMeetingNowButtonNameIconCorrect("class", StartMeetingNow_button_icon);
		Thread.sleep(2000);

		// check if meeting join link is present on next column same row
		dothis.checkExpectedJoinMeetinglinkisPresent();
		
		// check if enabled
		dothis.checkExpectedJoinMeetinglinkisEnabled();

		// check if correct text
		String expectedlinktext = (String) dothis.dashboardpanel.get("Meeting_Join_Link");
		dothis.checkcorrectJoinmeetinglinktext(expectedlinktext);
		Thread.sleep(2000);
	
		// check if delete link is present
		dothis.checkDeletelinktext_is_Present();

		// check if enabled
		dothis.checkDeletelinktext_is_Enabled();

		// check if correct link text
		String expectedDeletelinktext = (String) dothis.dashboardpanel.get("Delete_Link");
		dothis.checkDeletelinktext_is_correct(expectedDeletelinktext);
	}
	
	@Test(priority = 19)
	public void Test_Delete_the_justAddedMeeting() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click delete link text
		dothis.clickthisDeletelinktext();
		Thread.sleep(2000);

		// check if it is totally deleted
		dothis.check_if_thisword_is_present_inTables_shouldNOTExist("thisaddmeeting");
	}
	
	@Test(priority = 20)
	public void Test_AddNewMeeting_AddName_click_ReturMeetingbutton() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		// click Add new button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(5);
		String addnewcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		dothis.click_button_linktext(addnewcsslocator);

		// add meeting room name
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(7);
		String inputfieldcsslocator = (String) dothis.dashboardpanel.get("inputfieldcsslocator");
		dothis.enterdataInputfield(inputfieldcsslocator, "roomname");

		// click Return Meeting button
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(9);
		String returnmeetingcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		dothis.hoverpointer(returnmeetingcsslocator);
		dothis.click_button_linktext(returnmeetingcsslocator);

		// check url destination
		dothis.dashboardpanel = (JSONObject) dothis.MeetingRoomsArr.get(12);
		String expectedurl = (String) dothis.dashboardpanel.get("after_a_successful_addNewmeeting");
		dothis.getCurrentURL(expectedurl);

		// check if the name is not appear on the meeting room name table column as it
		// should not be
		String thisnameshouldnotExist = dothis.getmeetingName();
		Assert.assertFalse(thisnameshouldnotExist.contains("roomname"));
	} 
}
