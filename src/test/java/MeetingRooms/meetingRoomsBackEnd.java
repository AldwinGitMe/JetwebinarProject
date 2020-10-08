package MeetingRooms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class meetingRoomsBackEnd {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject loginhomepagelocators;
	JSONArray loginAccountsArr, meetingRoomPageArr, addmeetingRoomArr, alerterrorArr;

	public meetingRoomsBackEnd(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\MeetingRooms\\meetingRooms.json");
			Object obj = jsonParser.parse(reader);

			loginhomepagelocators = (JSONObject) obj;
			loginAccountsArr = (JSONArray) loginhomepagelocators.get("Login_Accounts");
			meetingRoomPageArr = (JSONArray) loginhomepagelocators.get("MeetingRooms_Page");
			addmeetingRoomArr = (JSONArray) loginhomepagelocators.get("Add_Meeting_Room");
			alerterrorArr = (JSONArray) loginhomepagelocators.get("Alert_Error_Text");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// ***** REUSABLE METHODS ***** //

	// check text / label / Name
	public void getLabelText(String locator, String expectedText, String whatisthebug)
			throws IOException, InterruptedException {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == true) {
			// test if correct title
			WebElement TLN = driver.findElement(By.cssSelector(locator));
			Assert.assertTrue(TLN.getText().contains(expectedText), whatisthebug + TLN.getText());
		} else {
			Assert.fail(" Text_Label_Name Is Not Present ");
		}
	}

	// this is for xpath check text / label / name
	public void getLabelText_xpath(String xpath, String expectedText) throws IOException {
		boolean TextLabelName = driver.findElement(By.xpath(xpath)).isDisplayed();
		if (TextLabelName == true) {
			// test if correct title
			WebElement TLN = driver.findElement(By.xpath(xpath));
			Assert.assertTrue(TLN.getText().contains(expectedText), " Not What The Expected Text - " + TLN.getText());
		} else {
			Assert.fail(" Text_Label_Name Is Not Present ");
		}
	}

	// click button / link text
	public void clickButton_linktext(String locator) {
		WebElement buttonlinkText = driver.findElement(By.cssSelector(locator));
		buttonlinkText.click();
	}

	// check placeholder text
	public void getplaceholder_text(String locator, String expectedText) {
		WebElement placeholdertext = driver.findElement(By.cssSelector(locator));
		Assert.assertTrue(placeholdertext.getAttribute("placeholder").equalsIgnoreCase(expectedText),
				" No Placeholder / Empty " + placeholdertext.getAttribute("placeholder"));
	}

	// check Input field / checkbox
	public void verifyInputfield_checkbox(String locator) {
		boolean inputfield = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (inputfield == true) {
			// test if not disabled
			boolean inputfield_if_enabled = driver.findElement(By.cssSelector(locator)).isEnabled();
			if (inputfield_if_enabled == false) {
				Assert.fail(" Input field is Disabled ");
			}
		} else {
			Assert.fail(" Input field Is Not Present ");
		}
	}

	// check button / link text
	public void verifybutton_linktext(String locator) throws InterruptedException {
		Thread.sleep(2000);
		boolean button_linktext = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (button_linktext == true) {
			// test if enabled
			boolean button_link_text = driver.findElement(By.cssSelector(locator)).isEnabled();
			if (button_link_text == false) {
				Assert.fail(" Button / Link Text is Disabled ");
			}
		} else {
			Assert.fail(" Button / Link Text Is Not Present ");
		}
	}

	// this is only for xpath
	public void verifybutton_linktext_xpath(String xpath) {
		boolean button_linktext = driver.findElement(By.xpath(xpath)).isDisplayed();
		if (button_linktext == true) {
			// test if enabled
			boolean button_link_text = driver.findElement(By.xpath(xpath)).isEnabled();
			if (button_link_text == false) {
				Assert.fail(" Button / Link Text is Disabled ");
			}
		} else {
			Assert.fail(" Button / Link Text Is Not Present ");
		}
	}

	// if element exist - not button, not link text
	public void checkElement(String locator) {
		boolean element = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (element == false) {
			Assert.fail(" No Such Element Exist ");
		}
	}

	// mouser hover in element - button / link text / image
	public void hoverpointer(String locator) {
		Actions actions = new Actions(driver);
		WebElement thiselement = driver.findElement(By.cssSelector(locator));
		actions.moveToElement(thiselement).perform();
	}

	// scroll down the page
	public void scrollDown(int num) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + num + ")");
	}
	
	//this will only click the Meeting Rooms menu in the dashboard panel
	public void clickMeetingRoomsMenu(String locator) {
		WebElement meetingRooms = driver.findElement(By.cssSelector(locator));
		meetingRooms.click();
	}
	
	//this will only click the Add New button
	public void clickAddNewButton(String locator) {
		WebElement addnewbutton = driver.findElement(By.cssSelector(locator));
		addnewbutton.click();
	}

	// ***** REUSABLE METHODS ***** //

	// this will login using account
	public void loginaccount() {
		JSONObject loginAccount = (JSONObject) loginAccountsArr.get(0);

		// username input field
		JSONObject usernameInputF = (JSONObject) loginAccountsArr.get(1);
		String getusername = (String) loginAccount.get("username");
		String usrnameinputfield_locator = (String) usernameInputF.get("csslocator");

		// password input field
		JSONObject passwordInputF = (JSONObject) loginAccountsArr.get(2);
		String getpassword = (String) loginAccount.get("password");
		String passwordinputfield_locator = (String) passwordInputF.get("csslocator");

		// enter username
		WebElement enterusrname = driver.findElement(By.cssSelector(usrnameinputfield_locator));
		enterusrname.sendKeys(getusername);

		// enter password
		WebElement enterpass = driver.findElement(By.cssSelector(passwordinputfield_locator));
		enterpass.sendKeys(getpassword);

		// click SIGN IN button
		JSONObject signinb = (JSONObject) loginAccountsArr.get(3);
		String signInbutton_locator = (String) signinb.get("csslocator");
		this.clickButton_linktext(signInbutton_locator);
	}

	// check if there is Meeting Rooms Menu in the dashboard Panel
	public void checkMeetingRoomsMainMenu() throws InterruptedException, IOException {
		JSONObject meetingrooms_menu = (JSONObject) meetingRoomPageArr.get(0);
		String getMenubutton_Label = (String) meetingrooms_menu.get("MeetingRooms_Menu");
		String getMenubutton_csslocator = (String) meetingrooms_menu.get("csslocator");
		
		//check if there is Meeting Rooms in the dashboard Panel
		this.verifybutton_linktext(getMenubutton_csslocator);
		
		//check if correct link text name on Meeting Rooms in the Dashboard panel
		this.getLabelText(getMenubutton_csslocator, getMenubutton_Label, " Incorrect Label Link Text As It Should Be - ' Meeting Rooms ' ");
	}
	
	//check if when Meeting Rooms is clicked it will go to correct destination URL
	//and expected elements are presented such as title page, add new button
	public void checkMeetingRoomsURL_PageElements() throws InterruptedException, IOException {
		JSONObject meetingrooms_menu = (JSONObject) meetingRoomPageArr.get(0);
		String getMenubutton_csslocator = (String) meetingrooms_menu.get("csslocator");
		//click Meeting Rooms menu first
		this.clickMeetingRoomsMenu(getMenubutton_csslocator);
		Thread.sleep(2000);
		
		JSONObject MeetingRooms_URL = (JSONObject) meetingRoomPageArr.get(1);
		String expectedURL = (String) MeetingRooms_URL.get("Page_URL");
		
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedURL), " Incorrect URL Page Destination As It Should Be - "+expectedURL+" - But Instead - "+driver.getCurrentUrl());
		
		JSONObject pagetitle = (JSONObject) meetingRoomPageArr.get(2);
		String pagetitle_label = (String) pagetitle.get("Page_Title");
		String pagetitle_csslocator = (String) pagetitle.get("csslocator");
		
		//check if there is page title and correct page title
		this.getLabelText(pagetitle_csslocator, pagetitle_label, " Incorrect Page Title As It Should Be - ' Meeting Rooms ' ");
		
		JSONObject addnewbutton = (JSONObject) meetingRoomPageArr.get(3);
		String addnewbutton_label = (String) addnewbutton.get("Add_New_Button");
		String addnewbutton_csslocator = (String) addnewbutton.get("csslocator");
		
		//check if there is Add New button and If correct button Name
		this.verifybutton_linktext(addnewbutton_csslocator);
		
		//check if correct button name
		this.getLabelText(addnewbutton_csslocator, addnewbutton_label, " Incorrect Button Name As It Should Be - ' ADD NEW ' ");
		
		JSONObject searchinputfieldLabel = (JSONObject) meetingRoomPageArr.get(4);
		String searchinputfield_label = (String) searchinputfieldLabel.get("Search_Inputfield_Label");
		String searchinputfieldLabel_csslocator = (String) searchinputfieldLabel.get("csslocator");
		
		//check if there is Search: input field label exist
		this.getLabelText(searchinputfieldLabel_csslocator, searchinputfield_label, " Incorrect Search Inputfield Label As It Should Be - ' Search: '");
		
		JSONObject searchInputfield = (JSONObject) meetingRoomPageArr.get(5);
		String searchinputfield_csslocator = (String) searchInputfield.get("csslocator");
		
		//check Search input field is exist and not disabled
		this.verifyInputfield_checkbox(searchinputfield_csslocator);
	}
	
	//check if add new button will go to its correct destination URL
	public void checkAddNewDestinationPage() {
		JSONObject addnewbutton = (JSONObject) meetingRoomPageArr.get(3);
		String addnewbutton_csslocator = (String) addnewbutton.get("csslocator");
		
		//click first Add New button
		this.clickAddNewButton(addnewbutton_csslocator);
		
		JSONObject addnewMeetingRoomPage = (JSONObject) addmeetingRoomArr.get(0);
		String expectedURL = (String) addnewMeetingRoomPage.get("Add_Meeting_Room_URL");
		
		//check if correct URL destination after ADD NEW button was clicked
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedURL), " Incorrect URL Destination Adter ADD NEW button Was Clicked As It Should Be - ' admin/meetings/add ' ");
	}
	
	//check Add New Meeting Room Page Elements
	public void checkAddNewMeetingRoomPageElements() throws IOException, InterruptedException {
		JSONObject addmeetingroomtitle = (JSONObject) addmeetingRoomArr.get(1);
		String addmeetingroomtitleText = (String) addmeetingroomtitle.get("Add_Meeting_Room_Title");
		String addmeetingroomtitleText_csslocator = (String) addmeetingroomtitle.get("csslocator");
		
		//check add new meeting room title
		this.getLabelText(addmeetingroomtitleText_csslocator, addmeetingroomtitleText, " Incorrect Add Meeting Room Title As It Should Be - ' Add Meeting Room ' ");
		
		JSONObject meetingroomhandleLabel = (JSONObject) addmeetingRoomArr.get(2);
		String meetingRoomHandleLabel = (String) meetingroomhandleLabel.get("Meeting_Room_Handle_Label");
		String meetingRoomHandleLabel_csslocator = (String) meetingroomhandleLabel.get("csslocator");
		
		//check Meeting Room Handle Input field Label
		this.getLabelText(meetingRoomHandleLabel_csslocator, meetingRoomHandleLabel, " Incorrect Title Inputfield As It Should Be - ' Meeting Room Handle '");
		
		JSONObject meetingroomhandleinputfield = (JSONObject) addmeetingRoomArr.get(3);
		String meetingroomhandleinputfield_csslocator = (String) meetingroomhandleinputfield.get("csslocator");
		String meetingroomhandleinputfield_predefinedURL = (String) meetingroomhandleinputfield.get("Meeting_Room_Handle_Label");
		
		//check if meeting room handle input field is exist
		this.verifyInputfield_checkbox(meetingroomhandleinputfield_csslocator);
		
		//check if there is predefined url in meeting room handle input field and correct
		this.getLabelText(meetingRoomHandleLabel_csslocator, meetingroomhandleinputfield_predefinedURL, " Incorrect Predefined URL As It Shoud Be - ' https://aldwinj.qa.jetwebinar.com/<Room Handle> ' ");
		
		JSONObject returntomeetingsbutton = (JSONObject) addmeetingRoomArr.get(4);
		String returntomeetingsbutton_label = (String) returntomeetingsbutton.get("Return_To_Meetings_Button");
		String returntomeetingsbutton_csslocator = (String) returntomeetingsbutton.get("csslocator");
		
		//check Return To Meetings Button Exist
		this.verifybutton_linktext(returntomeetingsbutton_csslocator);
		
		//check if correct button name in Return To Meetings button
		this.getLabelText(returntomeetingsbutton_csslocator, returntomeetingsbutton_label, " Incorrect Button Name As It Should Be - ' RETURN TO MEETINGS '");
		
		JSONObject addmeetingroombutton = (JSONObject) addmeetingRoomArr.get(5);
		String addomeetingroombutton_label = (String) addmeetingroombutton.get("ADD_MEETING_ROOM_Button");
		String addomeetingroombutton_csslocator = (String) addmeetingroombutton.get("csslocator");
		
		//check ADD MEETING ROOM Button Exist
		this.verifybutton_linktext(addomeetingroombutton_csslocator);
				
		//check if correct button name in ADD MEETING ROOM button
		this.getLabelText(addomeetingroombutton_csslocator, addomeetingroombutton_label , " Incorrect Button Name As It Should Be - ' ADD MEETING ROOM '");
	}
	
	//check if immediately add new meeting room without enter meeting room name
	public void checkAttemptAddNewMeetingRoom_withoutUserDataEntry() throws IOException, InterruptedException {
		JSONObject addmeetingroombutton = (JSONObject) addmeetingRoomArr.get(5);
		String addomeetingroombutton_csslocator = (String) addmeetingroombutton.get("csslocator");
		
		//click Add Meeting Room Button
		this.clickButton_linktext(addomeetingroombutton_csslocator);
		
		JSONObject alertErrorText = (JSONObject) alerterrorArr.get(0);
		String alertTexterror = (String) alertErrorText.get("Error_Text1");
		String alertTexterror_csslocator = (String) alertErrorText.get("csslocator");
		
		//check alert error text is present
		this.getLabelText(alertTexterror_csslocator, alertTexterror, " Incorrect Alert Error Text As It Should Be - ' This field is required. ' ");
	}
}
