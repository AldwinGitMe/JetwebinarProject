package Dashboard;

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

public class aaa0001dashboardMenuUITest {
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
		dothis.dashboardpanel = (JSONObject) dothis.userprofiledropDownMenuArr.get(4);
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
	public void Test_if_DashboardTitlePanel_is_Present() throws IOException, ParseException, InterruptedException {
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
		Thread.sleep(5000);

		// check if there is Dashboard title panel
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(0);
		String dashboardtitlepanelcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(dashboardtitlepanelcsslocator);
	}

	@Test(priority = 2)
	public void Test_if_DashboardTitlePanel_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(0);
		String dashboardtitlepanelcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(dashboardtitlepanelcsslocator);
	}

	@Test(priority = 3)
	public void Test_if_DashboardTitlePanel_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(0);
		String dashboardtitlepanelcsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectedtitle = (String) dothis.dashboardpanel.get("Dashboard_Title_Panel");

		dothis.isText_Label_Name_correct(dashboardtitlepanelcsslocator, expectedtitle);
	}
	
	@Test(priority = 4)
	public void Test_if_DashboardTitlePanel_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(1);
		String dashboardtitlepanelIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("Dashboard_Title_icon");

		dothis.getAttribute_and_Compare(dashboardtitlepanelIconcsslocator, "class", expectedIconName);
	}

	@Test(priority = 5)
	public void Test_if_DashboardPanel_subTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(2);
		String dashboardpanelSubtitlecsslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.islinktext_or_button_is_Present(dashboardpanelSubtitlecsslocator);
	}

	@Test(priority = 6)
	public void Test_if_DashboardPanel_subTitle_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(2);
		String dashboardpanelSubtitlecsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedsubTitle = (String) dothis.dashboardpanel.get("Dashboard_Panel_SubTitle");

		dothis.isText_Label_Name_correct(dashboardpanelSubtitlecsslocator, expectedsubTitle);
	}

	@Test(priority = 7)
	public void Test_if_DashboardPanel_Webinars_Menu_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(3);
		String WebinarsMenucsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.isLabel_Text_Name_Present(WebinarsMenucsslocator);
	}

	@Test(priority = 8)
	public void Test_if_DashboardPanel_Webinars_Menu_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(3);
		String WebinarsMenucsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(WebinarsMenucsslocator);
	}

	@Test(priority = 9)
	public void Test_if_DashboardPanel_Webinars_Menu_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(3);
		String WebinarsMenucsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectedMenutitle = (String) dothis.dashboardpanel.get("Webinars");

		dothis.isText_Label_Name_correct(WebinarsMenucsslocator, expectedMenutitle);
	}
	
	@Test(priority = 10)
	public void Test_if_DashboardPanel_Webinars_Menu_has_icon()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(4);
		String WebinarsMenuIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("Webinars_icon");
		dothis.getAttribute_and_Compare(WebinarsMenuIconcsslocator, "class", expectedIconName);
	}

	@Test(priority = 11)
	public void Test_if_DashboardPanel_Webinars_subMenus_are_Present_and_Hidden_WhenClicked_Webinars()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(3);
		String WebinarsMenucsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		Thread.sleep(3000);
		// click Webinars
		dothis.click_button_linktext(WebinarsMenucsslocator);
		Thread.sleep(3000);

		// All Webinars
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(5);
		String AllWebinarscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedAllWebinarsText = (String) dothis.dashboardpanel.get("All_Webinars");

		// check if All Webinars is present
		dothis.islinktext_or_button_is_Present(AllWebinarscsslocator);
		// check if All Webinars is Enabled
		dothis.islinktext_or_button_Enabled(AllWebinarscsslocator);
		// check if All Webinars has correct text
		dothis.isText_Label_Name_correct(AllWebinarscsslocator, expectedAllWebinarsText);

		// Add Webinar
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(6);
		String AddWebinarcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");
		String expectedAddWebinarText = (String) dothis.dashboardpanel.get("Add_Webinar");

		// check if Add Webinars is present
		dothis.islinktext_or_button_is_Present(AddWebinarcsslocator);
		// check if Add Webinar is Enabled
		dothis.islinktext_or_button_Enabled(AddWebinarcsslocator);
		// check if Add Webinar has correct text
		dothis.isText_Label_Name_correct(AddWebinarcsslocator, expectedAddWebinarText);

		// click back Webinars
		dothis.click_button_linktext(WebinarsMenucsslocator);

		// check if All Webinars is hidden
		dothis.islinktext_or_button_is_hidden(AllWebinarscsslocator);
		// check if Add Webinar is hidden
		dothis.islinktext_or_button_is_hidden(AddWebinarcsslocator);
	}
	
	@Test(priority = 12)
	public void Test_if_MessageCenter_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(7);
		String messageCentercsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(messageCentercsslocator);
	}

	@Test(priority = 13)
	public void Test_if_MessageCenter_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(7);
		String messageCentercsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(messageCentercsslocator);
	}

	@Test(priority = 14)
	public void Test_if_MessageCenter_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(7);
		String messageCentercsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectedmessageCentertext = (String) dothis.dashboardpanel.get("Message_Center");

		dothis.isText_Label_Name_correct(messageCentercsslocator, expectedmessageCentertext);
	}

	@Test(priority = 15)
	public void Test_if_MessageCenter_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(8);
		String messageCenterIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedmessageCenterIcontext = (String) dothis.dashboardpanel.get("Message_Center_icon");

		dothis.getAttribute_and_Compare(messageCenterIconcsslocator, "class", expectedmessageCenterIcontext);
	}

	@Test(priority = 16)
	public void Test_if_Attendees_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(9);
		String Attendeescsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Attendeescsslocator);
	}

	@Test(priority = 17)
	public void Test_if_Attendees_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(9);
		String Attendeescsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Attendeescsslocator);
	}

	@Test(priority = 18)
	public void Test_if_Attendees_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(9);
		String Attendeescsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectAttendeestext = (String) dothis.dashboardpanel.get("Attendees");

		dothis.isText_Label_Name_correct(Attendeescsslocator, expectAttendeestext);
	}

	@Test(priority = 19)
	public void Test_if_Attendees_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(10);
		String AttendeesIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedAttendeesIcontext = (String) dothis.dashboardpanel.get("Attendees_icon");

		dothis.getAttribute_and_Compare(AttendeesIconcsslocator, "class", expectedAttendeesIcontext);
	}

	@Test(priority = 20)
	public void Test_if_Affiliates_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(11);
		String Affiliatescsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Affiliatescsslocator);
	}

	@Test(priority = 21)
	public void Test_if_Affiliates_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(11);
		String Affiliatescsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Affiliatescsslocator);
	}

	@Test(priority = 22)
	public void Test_if_Affiliates_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(11);
		String Affiliatescsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectAffiliatestext = (String) dothis.dashboardpanel.get("Affiliates");

		dothis.isText_Label_Name_correct(Affiliatescsslocator, expectAffiliatestext);
	}

	@Test(priority = 23)
	public void Test_if_Affiliates_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(12);
		String AffiliatesIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedAffiliatesIcontext = (String) dothis.dashboardpanel.get("Affiliates_icon");

		dothis.getAttribute_and_Compare(AffiliatesIconcsslocator, "class", expectedAffiliatesIcontext);
	}

	@Test(priority = 24)
	public void Test_if_Meetings_CategoryTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(13);
		String Meetingscsslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.isLabel_Text_Name_Present(Meetingscsslocator);
	}

	@Test(priority = 25)
	public void Test_if_Meetings_CategoryTitle_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(13);
		String Meetingscsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String Meetingstext = (String) dothis.dashboardpanel.get("Meetings");

		dothis.isText_Label_Name_correct(Meetingscsslocator, Meetingstext);
	}

	@Test(priority = 26)
	public void Test_if_MeetingRooms_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(14);
		String meetingRoomscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(meetingRoomscsslocator);
	}

	@Test(priority = 27)
	public void Test_if_MeetingRooms_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(14);
		String meetingRoomscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(meetingRoomscsslocator);
	}

	@Test(priority = 28)
	public void Test_if_MeetingRooms_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(14);
		String meetingRoomscsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectmeetingRoomstext = (String) dothis.dashboardpanel.get("Meeting_Rooms");

		dothis.isText_Label_Name_correct(meetingRoomscsslocator, expectmeetingRoomstext);
	}

	@Test(priority = 29)
	public void Test_if_MeetingRooms_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(15);
		String meetingRoomsIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedmeetingRoomsIcontext = (String) dothis.dashboardpanel.get("Meeting_Rooms_icon");

		dothis.getAttribute_and_Compare(meetingRoomsIconcsslocator, "class", expectedmeetingRoomsIcontext);
	}

	@Test(priority = 30)
	public void Test_if_VideoAssets_CategoryTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(16);
		String VideoAssetscsslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.isLabel_Text_Name_Present(VideoAssetscsslocator);
	}

	@Test(priority = 31)
	public void Test_if_VideoAssets_CategoryTitle_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(16);
		String VideoAssetscsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String VideoAssetstext = (String) dothis.dashboardpanel.get("Video_Assets");

		dothis.isText_Label_Name_correct(VideoAssetscsslocator, VideoAssetstext);
	}
	
	@Test(priority = 32)
	public void Test_if_Media_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(17);
		String mediacsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(mediacsslocator);
	}

	@Test(priority = 33)
	public void Test_if_Media_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(17);
		String mediacsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(mediacsslocator);
	}

	@Test(priority = 34)
	public void Test_if_Media_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(17);
		String mediacsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectmediatext = (String) dothis.dashboardpanel.get("Media");

		dothis.isText_Label_Name_correct(mediacsslocator, expectmediatext);
	}

	@Test(priority = 35)
	public void Test_if_Media_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(18);
		String mediaIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedmediaIcontext = (String) dothis.dashboardpanel.get("Media_icon");

		dothis.getAttribute_and_Compare(mediaIconcsslocator, "class", expectedmediaIcontext);
	}
	
	@Test(priority = 36)
	public void Test_if_MyAccounts_CategoryTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(19);
		String MyAccountscsslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.isLabel_Text_Name_Present(MyAccountscsslocator);
	}

	@Test(priority = 37)
	public void Test_if_MyAccounts_CategoryTitle_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(19);
		String MyAccountscsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String MyAccountstext = (String) dothis.dashboardpanel.get("My_Account");

		dothis.isText_Label_Name_correct(MyAccountscsslocator, MyAccountstext);
	}
	
	
	@Test(priority = 38)
	public void Test_if_Users_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(20);
		String Userscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Userscsslocator);
	}

	@Test(priority = 39)
	public void Test_if_Users_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(20);
		String Userscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Userscsslocator);
	}

	@Test(priority = 40)
	public void Test_if_Users_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(20);
		String Userscsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectUserstext = (String) dothis.dashboardpanel.get("Users");

		dothis.isText_Label_Name_correct(Userscsslocator, expectUserstext);
	}

	@Test(priority = 41)
	public void Test_if_Users_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(21);
		String UsersIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedUsersIcontext = (String) dothis.dashboardpanel.get("Users_icon");

		dothis.getAttribute_and_Compare(UsersIconcsslocator, "class", expectedUsersIcontext);
	}
	
	
	@Test(priority = 42)
	public void Test_if_Settings_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(22);
		String Settingscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Settingscsslocator);
	}

	@Test(priority = 43)
	public void Test_if_Settings_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(22);
		String Settingscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Settingscsslocator);
	}

	@Test(priority = 44)
	public void Test_if_Settings_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(22);
		String Settingscsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectSettingstext = (String) dothis.dashboardpanel.get("Settings");

		dothis.isText_Label_Name_correct(Settingscsslocator, expectSettingstext);
	}

	@Test(priority = 45)
	public void Test_if_Settings_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(23);
		String SettingsIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedSettingsIcontext = (String) dothis.dashboardpanel.get("Settings_icon");

		dothis.getAttribute_and_Compare(SettingsIconcsslocator, "class", expectedSettingsIcontext);
	}
	
	@Test(priority = 46)
	public void Test_if_Subscription_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(24);
		String Subscriptioncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Subscriptioncsslocator);
	}

	@Test(priority = 47)
	public void Test_if_Subscription_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(24);
		String Subscriptioncsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Subscriptioncsslocator);
	}

	@Test(priority = 48)
	public void Test_if_Subscription_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(24);
		String Subscriptioncsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectSubscriptiontext = (String) dothis.dashboardpanel.get("Subscription");

		dothis.isText_Label_Name_correct(Subscriptioncsslocator, expectSubscriptiontext);
	}

	@Test(priority = 49)
	public void Test_if_Subscription_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(25);
		String SubscriptionIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedSubscriptionIcontext = (String) dothis.dashboardpanel.get("Subscription_icon");

		dothis.getAttribute_and_Compare(SubscriptionIconcsslocator, "class", expectedSubscriptionIcontext);
	}
	
	@Test(priority = 50)
	public void Test_if_Support_CategoryTitle_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(26);
		String Supportcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		//scroll down a bit first
		dothis.scrollDown(30);
		
		dothis.isLabel_Text_Name_Present(Supportcsslocator);
	}

	@Test(priority = 51)
	public void Test_if_Support_CategoryTitle_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(26);
		String Supportcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String Supporttext = (String) dothis.dashboardpanel.get("Support");

		dothis.isText_Label_Name_correct(Supportcsslocator, Supporttext);
	}
	
	@Test(priority = 52)
	public void Test_if_VideoTutorials_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(27);
		String VideoTutorialscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(VideoTutorialscsslocator);
	}

	@Test(priority = 53)
	public void Test_if_VideoTutorials_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(27);
		String VideoTutorialscsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(VideoTutorialscsslocator);
	}

	@Test(priority = 54)
	public void Test_if_VideoTutorials_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(27);
		String VideoTutorialscsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectVideoTutorialstext = (String) dothis.dashboardpanel.get("Video_Tutorials");

		dothis.isText_Label_Name_correct(VideoTutorialscsslocator, expectVideoTutorialstext);
	}

	@Test(priority = 55)
	public void Test_if_VideoTutorials_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(28);
		String VideoTutorialsIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedVideoTutorialsIcontext = (String) dothis.dashboardpanel.get("Video_Tutorials_icon");

		dothis.getAttribute_and_Compare(VideoTutorialsIconcsslocator, "class", expectedVideoTutorialsIcontext);
	}
	
	@Test(priority = 56)
	public void Test_if_Documentation_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(29);
		String Documentationcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Documentationcsslocator);
	}

	@Test(priority = 57)
	public void Test_if_Documentation_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(29);
		String Documentationcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Documentationcsslocator);
	}

	@Test(priority = 58)
	public void Test_if_Documentation_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(29);
		String Documentationcsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectDocumentationtext = (String) dothis.dashboardpanel.get("Documentation");

		dothis.isText_Label_Name_correct(Documentationcsslocator, expectDocumentationtext);
	}

	@Test(priority = 59)
	public void Test_if_Documentation_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(30);
		String DocumentationIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedDocumentationIcontext = (String) dothis.dashboardpanel.get("Documentation_icon");

		dothis.getAttribute_and_Compare(DocumentationIconcsslocator, "class", expectedDocumentationIcontext);
	}
	
	@Test(priority = 60)
	public void Test_if_Submit_Support_Ticket_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(31);
		String Submit_Support_Ticketcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_is_Present(Submit_Support_Ticketcsslocator);
	}

	@Test(priority = 61)
	public void Test_if_Submit_Support_Ticket_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(31);
		String Submit_Support_Ticketcsslocator = (String) dothis.dashboardpanel.get("linkcsslocator");

		dothis.islinktext_or_button_Enabled(Submit_Support_Ticketcsslocator);
	}

	@Test(priority = 62)
	public void Test_if_Submit_Support_Ticket_is_Correct() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(31);
		String Submit_Support_Ticketcsslocator = (String) dothis.dashboardpanel.get("textcsslocator");
		String expectSubmit_Support_Tickettext = (String) dothis.dashboardpanel.get("Submit_Support_Ticket");

		dothis.isText_Label_Name_correct(Submit_Support_Ticketcsslocator, expectSubmit_Support_Tickettext);
	}

	@Test(priority = 63)
	public void Test_if_Submit_Support_Ticket_has_icon() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(32);
		String Submit_Support_TicketIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedSubmit_Support_TicketIcontext = (String) dothis.dashboardpanel.get("Submit_Support_Ticket_icon");

		dothis.getAttribute_and_Compare(Submit_Support_TicketIconcsslocator, "class", expectedSubmit_Support_TicketIcontext);
	}
	
}
