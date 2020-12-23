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

public class aaa0002JetWebinarLogo_ClosedDashboardPanel_HideMenus_UITest {
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
	public void Test_if_JetWebinar_Logo_is_Present() throws IOException, ParseException, InterruptedException {
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

		// check if JetWebinar Logo is Present - on top left
		dothis.dashboardpanel = (JSONObject) dothis.jetwebinarLogoArr.get(0);
		String jwLogocsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String jwLogoimagesrc = (String) dothis.dashboardpanel.get("JetWebinarLogo");

		dothis.islinktext_or_button_is_Present(jwLogocsslocator);

		// check if correct image src
		dothis.getAttribute_and_Compare(jwLogocsslocator, "src", jwLogoimagesrc);
	}

	@Test(priority = 2)
	public void Test_if_Hamburgerbutton_is_Present() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardPanelOpenClosedArr.get(0);
		String hbuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.islinktext_or_button_is_Present(hbuttoncsslocator);
	}

	@Test(priority = 3)
	public void Test_if_Hamburgerbutton_is_Enabled() throws IOException, ParseException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardPanelOpenClosedArr.get(0);
		String hbuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");

		dothis.islinktext_or_button_Enabled(hbuttoncsslocator);
	}

	@Test(priority = 4)
	public void Test_if_Only_Home_Icon_is_Visible()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardPanelOpenClosedArr.get(0);
		String hbuttoncsslocator = (String) dothis.dashboardpanel.get("csslocator");

		// click hamburger button
		dothis.click_button_linktext(hbuttoncsslocator);
		Thread.sleep(3000);

		// test if in the dashboard panel is closed only icons will be visible
		// dashboard main title should be visible and only its home icon is visible
		
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(1);
		String dashboardtitlepanelIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("Dashboard_Title_icon");

		dothis.whenhidden_icon_is_visible(dashboardtitlepanelIconcsslocator, "class",
				expectedIconName);
	}

	@Test(priority = 5)
	public void Test_if_Only_Webinars_Icon_is_Visible_While__Webinarsmenu_is_Hidden()
			throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		
		// webinar icon
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(4);
		String WebinarsMenuIconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String expectedIconName = (String) dothis.dashboardpanel.get("Webinars_icon");
		dothis.whenhidden_icon_is_visible(WebinarsMenuIconcsslocator, "class",
				expectedIconName);
	}
	
	@Test(priority=6)
	public void Test_if_whenMouseisHover_to_Webinars_submenu_shows_checkAllwebinars() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(4);
		// webinars menu icon 
		String WebinarsMenucsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the webinars icon
		dothis.hoverpointer(WebinarsMenucsslocator);
		Thread.sleep(2000);
		
		//hover and check all webinars
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(5);
		// webinars menu link text button
		String AllWebinarscsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(AllWebinarscsslocator);
		Thread.sleep(2000);
		//check if All Webinars sub menu is present
		dothis.getAttribute_and_Compare(AllWebinarscsslocator, "aria-describedby", expectedvalue); 
	}
	
	@Test(priority=7)
	public void Test_if_whenMouseisHover_to_Webinars_submenu_shows_checkAddwebinars() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(4);
		// webinars menu icon 
		String WebinarsMenucsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the webinars icon
		dothis.hoverpointer(WebinarsMenucsslocator);
		Thread.sleep(2000);
		
		//hover and check all webinars
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(6);
		// webinars menu link text button
		String AddWebinarscsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(AddWebinarscsslocator);
		Thread.sleep(2000);
		//check if Add Webinars sub menu is present
		dothis.getAttribute_and_Compare(AddWebinarscsslocator, "aria-describedby", expectedvalue); 
	}
	
	@Test(priority=8)
	public void Test_if_MessageCentericon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(8);
		// message center icon 
		String messageCentericoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Message_Center_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(messageCentericoncsslocator, "class", iconName);
		
	}
	
	@Test(priority=9)
	public void Test_whenMouseisHover_to_MessageCentericon_menu_shows_checkMessageCenter() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(8);
		// message center icon 
		String WebinarsMessagecentericoncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the message center icon
		dothis.hoverpointer(WebinarsMessagecentericoncsslocator);
		Thread.sleep(2000);
		
		//hover and check if message center menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(7);
		// message center menu link text button
		String messageCentercsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(messageCentercsslocator);
		Thread.sleep(2000);
		//check if message center sub menu is present
		dothis.getAttribute_and_Compare(messageCentercsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	@Test(priority=10)
	public void Test_if_Attendeesicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(10);
		// Attendees icon 
		String Attendessiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Attendees_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Attendessiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=11)
	public void Test_whenMouseisHover_to_Attendeesicon_menu_shows_checkAttendees() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(10);
		// Attendees icon 
		String Attendeesiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Attendees icon
		dothis.hoverpointer(Attendeesiconcsslocator);
		Thread.sleep(2000);
		
		//hover and check if Attendees menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(9);
		// message center menu link text button
		String Attendeescsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Attendeescsslocator);
		Thread.sleep(2000);
		//check if Attendees menu is present
		dothis.getAttribute_and_Compare(Attendeescsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	
	@Test(priority=12)
	public void Test_if_Affiliatesicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(12);
		// Affiliates icon 
		String Affiliatesiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Affiliatesicon_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Affiliatesiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=13)
	public void Test_whenMouseisHover_to_Affiliatesicon_menu_shows_checkAffiliates() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(12);
		// Affiliates icon 
		String Affiliatesiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Affiliates icon
		dothis.hoverpointer(Affiliatesiconcsslocator);
		Thread.sleep(2000);
		
		//hover and check if Affiliates menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(11);
		// Affiliates menu link text button
		String Affiliatescsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Affiliatescsslocator);
		Thread.sleep(2000);
		//check if Affiliates menu is present
		dothis.getAttribute_and_Compare(Affiliatescsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	@Test(priority=14)
	public void Test_if_MeetingRoomsicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(15);
		// MeetingRooms icon 
		String MeetingRoomsiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("MeetingRoomsicon_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(MeetingRoomsiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=15)
	public void Test_whenMouseisHover_to_MeetingRoomssicon_menu_shows_checkMeetingRooms() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(15);
		// MeetingRooms icon 
		String MeetingRoomsiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the MeetingRooms icon
		dothis.hoverpointer(MeetingRoomsiconcsslocator);
		Thread.sleep(2000);
		
		//hover and check if MeetingRooms menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(14);
		// MeetingRooms menu link text button
		String MeetingRoomscsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(MeetingRoomscsslocator);
		Thread.sleep(2000);
		//check if MeetingRooms menu is present
		dothis.getAttribute_and_Compare(MeetingRoomscsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	@Test(priority=16)
	public void Test_if_Mediaicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(18);
		// Media icon 
		String Mediaiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Media_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Mediaiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=17)
	public void Test_whenMouseisHover_to_Mediaicon_menu_shows_checkMedia() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(18);
		// Media icon 
		String Mediacsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Media icon
		dothis.hoverpointer(Mediacsslocator);
		Thread.sleep(2000);
		
		//hover and check if Media menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(17);
		// Media menu link text button
		String Medialicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Mediacsslocator);
		Thread.sleep(2000);
		//check if Media menu is present
		dothis.getAttribute_and_Compare(Medialicsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	
	@Test(priority=18)
	public void Test_if_Usersicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(21);
		// Users icon 
		String Usersiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Users_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Usersiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=19)
	public void Test_whenMouseisHover_to_Usersicon_menu_shows_checkUsers() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(21);
		// Users icon 
		String Userscsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Users icon
		dothis.hoverpointer(Userscsslocator);
		Thread.sleep(2000);
		
		//hover and check if Users menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(20);
		// Users menu link text button
		String Userslicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Userscsslocator);
		Thread.sleep(2000);
		//check if Users menu is present
		dothis.getAttribute_and_Compare(Userslicsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	
	@Test(priority=20)
	public void Test_if_Settingsicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(23);
		// Settings icon 
		String Settingsiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Settings_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Settingsiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=21)
	public void Test_whenMouseisHover_to_Settingsicon_menu_shows_checkSettings() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(23);
		// Settings icon 
		String Settingscsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Settings icon
		dothis.hoverpointer(Settingscsslocator);
		Thread.sleep(2000);
		
		//hover and check if Settings menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(22);
		// Settings menu link text button
		String Settingslicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Settingslicsslocator);
		Thread.sleep(2000);
		//check if Settings menu is present
		dothis.getAttribute_and_Compare(Settingslicsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	@Test(priority=22)
	public void Test_if_Subscriptionicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(25);
		// Subscription icon 
		String Subscriptioniconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Subscription_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Subscriptioniconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=23)
	public void Test_whenMouseisHover_to_Subscriptionicon_menu_shows_checkSubscription() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(25);
		// Subscription icon 
		String Subscriptioncsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Subscription icon
		dothis.hoverpointer(Subscriptioncsslocator);
		Thread.sleep(2000);
		
		//hover and check if Subscription menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(24);
		// Subscription menu link text button
		String Subscriptionlicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Subscriptionlicsslocator);
		Thread.sleep(2000);
		//check if Subscription menu is present
		dothis.getAttribute_and_Compare(Subscriptionlicsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	
	@Test(priority=24)
	public void Test_if_VideoTutorialsicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(28);
		//scroll down first
		dothis.scrollDown(80);
		// VideoTutorials icon 
		String VideoTutorialsiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("VideoTutorials_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(VideoTutorialsiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=25)
	public void Test_whenMouseisHover_to_VideoTutorialsicon_menu_shows_checkVideoTutorials() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(28);
		// VideoTutorials icon 
		String VideoTutorialscsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the VideoTutorials icon
		dothis.hoverpointer(VideoTutorialscsslocator);
		Thread.sleep(2000);
		
		//hover and check if VideoTutorials menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(27);
		// VideoTutorials menu link text button
		String VideoTutorialslicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(VideoTutorialslicsslocator);
		Thread.sleep(2000);
		//check if VideoTutorials menu is present
		dothis.getAttribute_and_Compare(VideoTutorialslicsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	@Test(priority=26)
	public void Test_if_Documentationicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(30);
		// Documentation icon 
		String Documentationiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Documentation_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(Documentationiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=27)
	public void Test_whenMouseisHover_to_Documentationicon_menu_shows_checkDocumentation() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(30);
		// Documentation icon 
		String Documentationcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the Documentation icon
		dothis.hoverpointer(Documentationcsslocator);
		Thread.sleep(2000);
		
		//hover and check if Documentation menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(29);
		// Documentation menu link text button
		String Documentationlicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(Documentationlicsslocator);
		Thread.sleep(2000);
		//check if Documentation menu is present
		dothis.getAttribute_and_Compare(Documentationlicsslocator, "aria-describedby", expectedvalue);  
		
	}
	
	
	@Test(priority=28)
	public void Test_if_SubmitSupportTicketicon_is_visible() throws IOException, ParseException, InterruptedException{
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(32);
		// SubmitSupportTicket icon 
		String SubmitSupportTicketiconcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		String iconName = (String) dothis.dashboardpanel.get("Submit_Support_Ticket_icon_icon");
		//check if icon is visible
		dothis.whenhidden_icon_is_visible(SubmitSupportTicketiconcsslocator, "class", iconName);
		
	}
	
	
	@Test(priority=27)
	public void Test_whenMouseisHover_to_SubmitSupportTicketicon_menu_shows_checkSubmitSupportTicket() throws IOException, ParseException, InterruptedException {
		reusableTestMethods dothis = new reusableTestMethods(driver);
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(32);
		// SubmitSupportTicket icon 
		String SubmitSupportTicketcsslocator = (String) dothis.dashboardpanel.get("csslocator");
		
		//hover mouse to the SubmitSupportTicket icon
		dothis.hoverpointer(SubmitSupportTicketcsslocator);
		Thread.sleep(2000);
		
		//hover and check if SubmitSupportTicket menu is present
		dothis.dashboardpanel = (JSONObject) dothis.dashboardmenusArr.get(31);
		// SubmitSupportTicket menu link text button
		String SubmitSupportTicketlicsslocator = (String) dothis.dashboardpanel.get("listcsslocator");
		String expectedvalue = (String) dothis.dashboardpanel.get("aria-describedby");
		dothis.hoverpointer(SubmitSupportTicketlicsslocator);
		Thread.sleep(2000);
		//check if SubmitSupportTicket menu is present
		dothis.getAttribute_and_Compare(SubmitSupportTicketlicsslocator, "aria-describedby", expectedvalue);  
	}
}
