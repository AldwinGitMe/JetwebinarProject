package Webinars;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

public class AddNewWebinarSettingsBackEnd {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject webinarsettingslocators;
	JSONArray userLogArr, webinarMainMenuArr, buttonTemplateArr, webinarsettingsTemplateArr;

	public AddNewWebinarSettingsBackEnd(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Webinars\\AddWebinarSettings.json");
			Object obj = jsonParser.parse(reader);

			webinarsettingslocators = (JSONObject) obj;
			userLogArr = (JSONArray) webinarsettingslocators.get("userlogin");
			webinarMainMenuArr = (JSONArray) webinarsettingslocators.get("Webinars_Main_menu");
			buttonTemplateArr = (JSONArray) webinarsettingslocators.get("Button_Access_Template");
			webinarsettingsTemplateArr = (JSONArray) webinarsettingslocators.get("Webinar_Settings");
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// ***** REUSABLE METHODS ***** //

	// check text / label / Name
	public void getLabelText(String locator, String expectedText, String whatisthebug) throws IOException, InterruptedException {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == true) {
			// test if correct title
			WebElement TLN = driver.findElement(By.cssSelector(locator));
			Assert.assertTrue(TLN.getText().contains(expectedText), whatisthebug+ TLN.getText());
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
	
	//enter data string in inputfield / text area field
	public void enteruserData(String locator, String dataText) {
		WebElement enterUserData = driver.findElement(By.cssSelector(locator));
		enterUserData.clear();
		enterUserData.sendKeys(dataText);
	}
	
	//click Webinars Main Menu
	public void clickWebinarsMainMenubutton() {
		JSONObject webinarsMmenu = (JSONObject) webinarMainMenuArr.get(0);
		String webinars_main_menu_locator = (String) webinarsMmenu.get("csslocator");
		this.clickButton_linktext(webinars_main_menu_locator);
	}
	
	//click Add Webinar sub menu
	public void clickAddWebinarSubMenubutton() {
		JSONObject Addwebinarsubmenu = (JSONObject) webinarMainMenuArr.get(2);
		String Addwebinar_sub_menu_locator = (String) Addwebinarsubmenu.get("csslocator");
		this.clickButton_linktext(Addwebinar_sub_menu_locator);
	}
	
	//click USE ADELINE button
	public void clickUSEADELINEbutton() throws InterruptedException {
		JSONObject adeline = (JSONObject) buttonTemplateArr.get(0);
		String adelinelocator = (String) adeline.get("csslocator");
		//hover to the button first
		this.hoverpointer(adelinelocator);
		Thread.sleep(3000);
		
		//click the USE ADELINE button
		this.clickButton_linktext(adelinelocator);
	}
	
	// check select drop down list menu if it is not empty
	public void checkdropDownlist_Not_Empty(String locator) throws InterruptedException {
		List<WebElement> dDropList = driver.findElements(By.cssSelector(locator));
		WebElement dDropListM = driver.findElement(By.cssSelector(locator));
		dDropListM.click();
		Thread.sleep(2000);
		
		int totalDroplist = dDropList.size();
		if((totalDroplist == 0)) {
			System.out.println(" The Drop Down List Menu Is Empty ");
		}
		dDropListM.click();
	}
	
	//this will only click the GO BACK button
	public void clickGOBACK_button() throws InterruptedException {
		JSONObject goBackbutton = (JSONObject) webinarsettingsTemplateArr.get(14);
		String GoBack_button_locator = (String) goBackbutton.get("csslocator");
		this.hoverpointer(GoBack_button_locator);
		WebElement goback = driver.findElement(By.cssSelector(GoBack_button_locator));
		goback.click();
		Thread.sleep(2000);
	}
	
	//click USE MESA
	public void clickUSE_MESA() throws InterruptedException {
		JSONObject mesa = (JSONObject) buttonTemplateArr.get(1);
		String mesalocator = (String) mesa.get("csslocator");
		//hover to the button first
		this.hoverpointer(mesalocator);
		Thread.sleep(3000);
		
		//click the USE MESA button
		this.clickButton_linktext(mesalocator);
	}
	
	//click USE BELMONT
	public void clickUSE_BELMONT() throws InterruptedException {
		JSONObject belmont = (JSONObject) buttonTemplateArr.get(2);
		String belmontlocator = (String) belmont.get("csslocator");
		//hover to the button first
		this.hoverpointer(belmontlocator);
		Thread.sleep(3000);
		
		//click the USE BELMONT button
		this.clickButton_linktext(belmontlocator);
	}
	
	//click USE OAK
	public void clickUSE_OAK() throws InterruptedException {
		JSONObject oak = (JSONObject) buttonTemplateArr.get(3);
		String oaklocator = (String) oak.get("csslocator");
		//hover to the button first
		this.hoverpointer(oaklocator);
		Thread.sleep(3000);
		
		//click the USE OAK button
		this.clickButton_linktext(oaklocator);
	}
	
	//click USE NEXGEN
	public void clickUSE_NEXGEN() throws InterruptedException {
		JSONObject nexgen = (JSONObject) buttonTemplateArr.get(4);
		String nexgenlocator = (String) nexgen.get("csslocator");
		//hover to the button first
		this.hoverpointer(nexgenlocator);
		Thread.sleep(3000);
		
		//click the USE NEXGEN button
		this.clickButton_linktext(nexgenlocator);
	}
	
	//click USE CHICAGO
	public void clickUSE_CHICAGO() throws InterruptedException {
		JSONObject chicago = (JSONObject) buttonTemplateArr.get(5);
		String chicagolocator = (String) chicago.get("csslocator");
		//hover to the button first
		this.hoverpointer(chicagolocator);
		Thread.sleep(3000);
		
		//click the USE CHICAGO button
		this.clickButton_linktext(chicagolocator);
	}
	
	//click USE CLARK
	public void clickUSE_CLARK() throws InterruptedException {
		JSONObject clark = (JSONObject) buttonTemplateArr.get(6);
		String clarklocator = (String) clark.get("csslocator");
		//hover to the button first
		this.hoverpointer(clarklocator);
		Thread.sleep(3000);
		
		//click the USE CLARK button
		this.clickButton_linktext(clarklocator);
	}
	
	//click USE LAGUNA
	public void clickUSE_LAGUNA() throws InterruptedException {
		JSONObject laguna = (JSONObject) buttonTemplateArr.get(7);
		String lagunalocator = (String) laguna.get("csslocator");
		//hover to the button first
		this.hoverpointer(lagunalocator);
		Thread.sleep(3000);
		
		//click the USE LAGUNA button
		this.clickButton_linktext(lagunalocator);
	}

	// ***** REUSABLE METHODS ***** //
	
	//user login
	public void userLogin() {
		JSONObject usernameaccount = (JSONObject) userLogArr.get(0);
		String username = (String) usernameaccount.get("username");
		String usernamelocator = (String) usernameaccount.get("csslocator");
		
		JSONObject passwordaccount = (JSONObject) userLogArr.get(1);
		String password = (String) passwordaccount.get("password");
		String passwordlocator = (String) passwordaccount.get("csslocator");
		
		JSONObject signinB = (JSONObject) userLogArr.get(2);
		String signinbuttonlocator = (String) signinB.get("csslocator");
		
		//enter username
		this.enteruserData(usernamelocator, username);
		//enter password
		this.enteruserData(passwordlocator, password);
		//click SIGN IN button
		this.clickButton_linktext(signinbuttonlocator);
		
	}
	
	// Test Webinar settings template
	public void checkWebinarSettingsTemplate() throws IOException, InterruptedException {
		//Webinar if Settings Title is present
		JSONObject title = (JSONObject) webinarsettingsTemplateArr.get(0);
		String accordion_title_name = (String) title.get("Webinar_Settings_Accordion_Title");
		String accordion_title_name_locator = (String) title.get("csslocator");
		
		//check title accordion
		this.getLabelText(accordion_title_name_locator, accordion_title_name, " Incorrect Title As It Should Be - ' Webinar Settings ' ");
		
		//check if webinar url input field, its label, placeholder text, and predefined url
		//its label
		JSONObject labelwebinarURL = (JSONObject) webinarsettingsTemplateArr.get(1);
		String labelURL = (String) labelwebinarURL.get("Webinar_URL");
		String labelURL_locator = (String) labelwebinarURL.get("csslocator");
		Thread.sleep(3000);
		this.getLabelText(labelURL_locator, labelURL, " Incorrect Label As It Should Be - ' Webinar URL ' ");
		
		//its input field
		JSONObject webinarURL_inputfield = (JSONObject) webinarsettingsTemplateArr.get(2);
		String URL_inputfield = (String) webinarURL_inputfield.get("WebinarURL_inputField");
		String URL_inputfield_locator = (String) webinarURL_inputfield.get("csslocator");
		this.verifyInputfield_checkbox(URL_inputfield_locator);
		
		//its placeholder text
		this.getplaceholder_text(URL_inputfield_locator, URL_inputfield);
		
		JSONObject webinarURL_inputfield_pre = (JSONObject) webinarsettingsTemplateArr.get(3);
		String URL_inputfield_pre = (String) webinarURL_inputfield_pre.get("WebinarURL_inputfield_predefined_URL");
		String URL_inputfield_pre_locator = (String) webinarURL_inputfield_pre.get("csslocator");
		
		//its predefined URL
		this.getLabelText(URL_inputfield_pre_locator, URL_inputfield_pre, " Incorrect URL Text As It Should Be - ' http://aldwinj.qa.jetwebinar.com/ ' ");
		
		JSONObject webinartitle_inputfield = (JSONObject) webinarsettingsTemplateArr.get(4);
		String webinar_title_inputfield = (String) webinartitle_inputfield.get("Webinar_Title");
		String webinar_title_inputfield_locator = (String) webinartitle_inputfield.get("csslocator");
		
		//check Webinar title and label
		this.getLabelText(webinar_title_inputfield_locator, webinar_title_inputfield, " Incorrect Label As It Should Be - ' Webinar Title ' ");
		
		
		JSONObject webinartitle_inputfield_Title = (JSONObject) webinarsettingsTemplateArr.get(5);
		String webinar_title_inputfield__label = (String) webinartitle_inputfield_Title.get("WebinarTitle_inputfield_label");
		String webinar_title_inputfield__label_locator = (String) webinartitle_inputfield_Title.get("csslocator");
		
		//check its input field
		this.verifyInputfield_checkbox(webinar_title_inputfield__label_locator);
		
		//check its input field label
		this.getplaceholder_text(webinar_title_inputfield__label_locator, webinar_title_inputfield__label);
		
		JSONObject eventTimeZone = (JSONObject) webinarsettingsTemplateArr.get(6);
		String eventTZ_title = (String) eventTimeZone.get("EvemtTimeZone_Title");
		String eventTZ_csslocator = (String) eventTimeZone.get("csslocator");
		
		//check its title in event time zone
		this.getLabelText(eventTZ_csslocator, eventTZ_title, " Incorrect Title As It Should Be - ' Event Timezone '");
		
		JSONObject chooseEventTZ = (JSONObject) webinarsettingsTemplateArr.get(7);
		String chooseeventTimeZone_locator = (String) chooseEventTZ.get("csslocator");
		
		//check choose event time zone drop down list menu is present
		this.checkElement(chooseeventTimeZone_locator);
		
		//check if choose event time zone drop down list menu is not empty
		this.checkdropDownlist_Not_Empty(chooseeventTimeZone_locator);
		
		JSONObject eventType = (JSONObject) webinarsettingsTemplateArr.get(8);
		String eventType_label = (String) eventType.get("eventType_Label");
		String eventType_locator = (String) eventType.get("csslocator");
		
		//check event type label is present
		this.getLabelText(eventType_locator, eventType_label, " Incorrect Label As It Should Be - ' Event Type '");
		
		JSONObject eventyTyoeMenu = (JSONObject) webinarsettingsTemplateArr.get(9);
		String eventTypeMenu_locator = (String) eventyTyoeMenu.get("csslocator");
		
		//check if event type menu is present
		this.checkElement(eventTypeMenu_locator);
		
		//check if event type drop down list menu is not empty
		this.checkElement(eventTypeMenu_locator);
		
		JSONObject eventTimeNote = (JSONObject) webinarsettingsTemplateArr.get(10);
		String eventTNote = (String) eventTimeNote.get("eventTime_note");
		String eventTNote_locator = (String) eventTimeNote.get("csslocator");
		
		//check event time note text
		this.getLabelText(eventTNote_locator, eventTNote, " Incorrect Text As It Should Be - ' Note: Please make sure your event time is 5 minutes advance from the current time. ' ");
		
		JSONObject eventTimeLabel = (JSONObject) webinarsettingsTemplateArr.get(11);
		String eventTime_label = (String) eventTimeLabel.get("Event_Time_Label");
		String eventTime_label_locator = (String) eventTimeLabel.get("csslocator");
		
		//check event time label
		this.getLabelText(eventTime_label_locator, eventTime_label, " Incorrect Label As It Should Be - ' Event Time ' ");
		
		JSONObject eventimeInputfield = (JSONObject) webinarsettingsTemplateArr.get(12);
		String eventTime_inputfield_locator = (String) eventimeInputfield.get("csslocator");
		
		//check if event time input field is present
		this.verifyInputfield_checkbox(eventTime_inputfield_locator);
		
		JSONObject eventimeDateWidgetbutton = (JSONObject) webinarsettingsTemplateArr.get(13);
		String eventime_dateWidget_button_locator = (String) eventimeDateWidgetbutton.get("csslocator");
		
		//check if event time date widget button is present
		this.verifybutton_linktext(eventime_dateWidget_button_locator);
		
		JSONObject goBackbutton = (JSONObject) webinarsettingsTemplateArr.get(14);
		String goBack_button_Name = (String) goBackbutton.get("GoBack_button");
		String GoBack_button_locator = (String) goBackbutton.get("csslocator");
		
		//check if go back button is present
		this.verifybutton_linktext(GoBack_button_locator);

		//check if correct button name og Go Back
		this.getLabelText(GoBack_button_locator, goBack_button_Name, " Incorrect Button Name As It Should Be - ' GO BACK ' ");
		
		JSONObject AddWebinarButton = (JSONObject) webinarsettingsTemplateArr.get(15);
		String AddWebinarButton_Name = (String) AddWebinarButton.get("AddWebinar_button");
		String AddWebinarButton_locator = (String) AddWebinarButton.get("csslocator");
		Thread.sleep(2000);
		
		//check if Add Webinar Button Is Present
		this.verifybutton_linktext(AddWebinarButton_locator);
		
		//check if Add Webinar button has correct name
		this.getLabelText(AddWebinarButton_locator, AddWebinarButton_Name, " Incorrect Button Name As It Should Be - ' ADD WEBINAR ' ");
 	}	
	
	
}
