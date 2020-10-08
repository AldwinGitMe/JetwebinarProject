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

public class AddNewWebinarPageAlertErrorsBackEnd {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject webinarsettingslocators;
	JSONArray userLogArr, webinarMainMenuArr, buttonTemplateArr, webinarsettingsTemplateArr, alertErrotTextArr;

	public AddNewWebinarPageAlertErrorsBackEnd(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Webinars\\AddNewWebinarAlertErrors.json");
			Object obj = jsonParser.parse(reader);

			webinarsettingslocators = (JSONObject) obj;
			userLogArr = (JSONArray) webinarsettingslocators.get("userlogin");
			webinarMainMenuArr = (JSONArray) webinarsettingslocators.get("Webinars_Main_menu");
			buttonTemplateArr = (JSONArray) webinarsettingslocators.get("Button_Access_Template");
			webinarsettingsTemplateArr = (JSONArray) webinarsettingslocators.get("Webinar_Settings");
			alertErrotTextArr = (JSONArray) webinarsettingslocators.get("Alert_Error_Text");
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
	public void scrollDown(int num) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + num + ")");
		Thread.sleep(2000);
	}

	// scroll down the page
	public void scrollUp(int num) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, -"+num+");");
		Thread.sleep(2000);
	}

	// enter data string in inputfield / text area field
	public void enteruserData(String locator, String dataText) {
		WebElement enterUserData = driver.findElement(By.cssSelector(locator));
		enterUserData.clear();
		enterUserData.sendKeys(dataText);
	}

	// click Webinars Main Menu
	public void clickWebinarsMainMenubutton() {
		JSONObject webinarsMmenu = (JSONObject) webinarMainMenuArr.get(0);
		String webinars_main_menu_locator = (String) webinarsMmenu.get("csslocator");
		this.clickButton_linktext(webinars_main_menu_locator);
	}

	// click Add Webinar sub menu
	public void clickAddWebinarSubMenubutton() {
		JSONObject Addwebinarsubmenu = (JSONObject) webinarMainMenuArr.get(2);
		String Addwebinar_sub_menu_locator = (String) Addwebinarsubmenu.get("csslocator");
		this.clickButton_linktext(Addwebinar_sub_menu_locator);
	}

	// click USE ADELINE button
	public void clickUSEADELINEbutton() throws InterruptedException {
		JSONObject adeline = (JSONObject) buttonTemplateArr.get(0);
		String adelinelocator = (String) adeline.get("csslocator");
		// hover to the button first
		this.hoverpointer(adelinelocator);
		Thread.sleep(3000);

		// click the USE ADELINE button
		this.clickButton_linktext(adelinelocator);
	}

	// check select drop down list menu if it is not empty
	public void checkdropDownlist_Not_Empty(String locator) throws InterruptedException {
		List<WebElement> dDropList = driver.findElements(By.cssSelector(locator));
		WebElement dDropListM = driver.findElement(By.cssSelector(locator));
		dDropListM.click();
		Thread.sleep(2000);

		int totalDroplist = dDropList.size();
		if ((totalDroplist == 0)) {
			System.out.println(" The Drop Down List Menu Is Empty ");
		}
		dDropListM.click();
	}

	// this will only click the GO BACK button
	public void clickGOBACK_button() throws InterruptedException {
		JSONObject goBackbutton = (JSONObject) webinarsettingsTemplateArr.get(14);
		String GoBack_button_locator = (String) goBackbutton.get("csslocator");
		this.hoverpointer(GoBack_button_locator);
		WebElement goback = driver.findElement(By.cssSelector(GoBack_button_locator));
		goback.click();
		Thread.sleep(2000);
	}

	// click USE MESA
	public void clickUSE_MESA() throws InterruptedException {
		JSONObject mesa = (JSONObject) buttonTemplateArr.get(1);
		String mesalocator = (String) mesa.get("csslocator");
		// hover to the button first
		this.hoverpointer(mesalocator);
		Thread.sleep(3000);

		// click the USE MESA button
		this.clickButton_linktext(mesalocator);
	}

	// click USE BELMONT
	public void clickUSE_BELMONT() throws InterruptedException {
		JSONObject belmont = (JSONObject) buttonTemplateArr.get(2);
		String belmontlocator = (String) belmont.get("csslocator");
		// hover to the button first
		this.hoverpointer(belmontlocator);
		Thread.sleep(3000);

		// click the USE BELMONT button
		this.clickButton_linktext(belmontlocator);
	}

	// click USE OAK
	public void clickUSE_OAK() throws InterruptedException {
		JSONObject oak = (JSONObject) buttonTemplateArr.get(3);
		String oaklocator = (String) oak.get("csslocator");
		// hover to the button first
		this.hoverpointer(oaklocator);
		Thread.sleep(3000);

		// click the USE OAK button
		this.clickButton_linktext(oaklocator);
	}

	// click USE NEXGEN
	public void clickUSE_NEXGEN() throws InterruptedException {
		JSONObject nexgen = (JSONObject) buttonTemplateArr.get(4);
		String nexgenlocator = (String) nexgen.get("csslocator");
		// hover to the button first
		this.hoverpointer(nexgenlocator);
		Thread.sleep(3000);

		// click the USE NEXGEN button
		this.clickButton_linktext(nexgenlocator);
	}

	// click USE CHICAGO
	public void clickUSE_CHICAGO() throws InterruptedException {
		JSONObject chicago = (JSONObject) buttonTemplateArr.get(5);
		String chicagolocator = (String) chicago.get("csslocator");
		// hover to the button first
		this.hoverpointer(chicagolocator);
		Thread.sleep(3000);

		// click the USE CHICAGO button
		this.clickButton_linktext(chicagolocator);
	}

	// click USE CLARK
	public void clickUSE_CLARK() throws InterruptedException {
		JSONObject clark = (JSONObject) buttonTemplateArr.get(6);
		String clarklocator = (String) clark.get("csslocator");
		// hover to the button first
		this.hoverpointer(clarklocator);
		Thread.sleep(3000);

		// click the USE CLARK button
		this.clickButton_linktext(clarklocator);
	}

	// click USE LAGUNA
	public void clickUSE_LAGUNA() throws InterruptedException {
		JSONObject laguna = (JSONObject) buttonTemplateArr.get(7);
		String lagunalocator = (String) laguna.get("csslocator");
		// hover to the button first
		this.hoverpointer(lagunalocator);
		Thread.sleep(3000);

		// click the USE LAGUNA button
		this.clickButton_linktext(lagunalocator);
	}

	// click ADD WEBINAR button
	public void clickAddWebinar_button() throws InterruptedException {
		JSONObject AddWebinarButton = (JSONObject) webinarsettingsTemplateArr.get(15);
		String AddWebinar_button_csslocator = (String) AddWebinarButton.get("csslocator");
		//hover first into that button
		this.hoverpointer(AddWebinar_button_csslocator);
		//then click that same button
		this.clickButton_linktext(AddWebinar_button_csslocator);
	}
	
	//get the value css style
	public void getCssStyles(String locator, String css_value, String expectedColor, String errorMessage) {
		WebElement getthisvalue = driver.findElement(By.cssSelector(locator));
		//Get the color property value and store in colorValue variable
		String color = getthisvalue.getCssValue(css_value);
		Assert.assertEquals(color, expectedColor, errorMessage);
	}

	// ***** REUSABLE METHODS ***** //

	// user login
	public void userLogin() {
		JSONObject usernameaccount = (JSONObject) userLogArr.get(0);
		String username = (String) usernameaccount.get("username");
		String usernamelocator = (String) usernameaccount.get("csslocator");

		JSONObject passwordaccount = (JSONObject) userLogArr.get(1);
		String password = (String) passwordaccount.get("password");
		String passwordlocator = (String) passwordaccount.get("csslocator");

		JSONObject signinB = (JSONObject) userLogArr.get(2);
		String signinbuttonlocator = (String) signinB.get("csslocator");

		// enter username
		this.enteruserData(usernamelocator, username);
		// enter password
		this.enteruserData(passwordlocator, password);
		// click SIGN IN button
		this.clickButton_linktext(signinbuttonlocator);
	}
		
	//check if alert error text will occur
	public void checkAlertErrorTexts() throws IOException, InterruptedException {
		JSONObject expectURL = (JSONObject) alertErrotTextArr.get(0);
		String expectedURL = (String) expectURL.get("expected_URL"); 
		//first check URL if it is still at the Add New Webinars page
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(expectedURL), " Incorrect Page URL As It Should be - ' https://aldwinj.qa.jetwebinar.com/add ' - "+driver.getCurrentUrl());
		
		JSONObject blockalert = (JSONObject) alertErrotTextArr.get(1);
		String blockbackground_alert_locator = (String) blockalert.get("csslocator");
		String expectedtextError = (String) blockalert.get("Text_Error");
		String gettextColor_inRGB = (String) blockalert.get("textColor_inRGB");
		String getblockbackground_color_inRGB = (String) blockalert.get("Block_Background_Color_inRGB");
		
		
		//check if there alert block background
		this.checkElement(blockbackground_alert_locator);
		
		//check if there is this expected color of the block
		this.getCssStyles(blockbackground_alert_locator, "background-color", getblockbackground_color_inRGB, " Incorrect RGB Color Expectation As It Should Be - ' rgba(251, 225, 227, 1) '");
		
		//check if correct text error
		this.getLabelText(blockbackground_alert_locator, expectedtextError, " Incorrect Alert Error Text As It Should Be - ' Please check to make sure the fields are filled out properly ' ");
		
		//check if correct text error color
		this.getCssStyles(blockbackground_alert_locator, "color", gettextColor_inRGB, " Incorrect Alert Text RGB Color As It Should Be - ' rgb(231, 61, 74, 1) ' ");
		
		JSONObject webinarURLinputfield_label = (JSONObject) webinarsettingsTemplateArr.get(1);
		String webinarURL_label_csslocator = (String) webinarURLinputfield_label.get("csslocator");
		String expectedWebinarURL_label_color_inRGB = (String) webinarURLinputfield_label.get("errorColor_inRGB");
		
		//check if correct text error color in Webinar URL input field LABEL
		this.getCssStyles(webinarURL_label_csslocator, "color", expectedWebinarURL_label_color_inRGB, " Incorrect Alert Text RGB Color As It Should Be - ' rgb(231, 61, 74, 1) ' ");
		
		JSONObject webinarURLinputfield = (JSONObject) webinarsettingsTemplateArr.get(2);
		String webinarURLinputfield_csslocator = (String) webinarURLinputfield.get("csslocator");
		String expectedborderBottom_colorError_inRGB = (String) webinarURLinputfield.get("errorColor_inRGB");
		
		//check if border bottom turned red color 
		this.getCssStyles(webinarURLinputfield_csslocator, "border-bottom", expectedborderBottom_colorError_inRGB, " Incorrect Alert Error Border-Bottom Color As It Should Be - ' 1px solid rgb(231, 61, 74) ' ");
		
		JSONObject webinarURLinputfield_predefinedURL = (JSONObject) webinarsettingsTemplateArr.get(3);
		String webinarURLinputfield_predefinedURL_csslocator = (String) webinarURLinputfield_predefinedURL.get("csslocator");
		String expectedBorderBottom_colorError_inRGB = (String) webinarURLinputfield_predefinedURL.get("errorColor_inRGB");
		
		//check if border-bottom color is correct in the Webinar URL input field pre-defined URL
		this.getCssStyles(webinarURLinputfield_predefinedURL_csslocator, "border-bottom", expectedBorderBottom_colorError_inRGB, " Incorrect Border Bottom Color As It Should Be - ' 1px solid rgb(231, 61, 74) ' ");
		
		JSONObject webinartitleinputfield_label = (JSONObject) webinarsettingsTemplateArr.get(4);
		String webinartitle_csslocator = (String) webinartitleinputfield_label.get("csslocator");
		String expectedColor_inRGB = (String) webinartitleinputfield_label.get("errorColor_inRGB");
		
		//check if webinar title input field label has correct error color
		this.getCssStyles(webinartitle_csslocator, "color", expectedColor_inRGB, " Incorrect Alert Text RGB Color As It Should Be - ' rgb(231, 61, 74, 1) ' ");
		
		JSONObject webinartitleInputfield = (JSONObject) webinarsettingsTemplateArr.get(5);
		String webinartitleinputfield_csslocator = (String) webinartitleInputfield.get("csslocator");
		String expectedborderbottomColor_inRGB = (String) webinartitleInputfield.get("errorColor_inRGB");
		
		//check webinar title input field if it has correct error color in border-bottom
		this.getCssStyles(webinartitleinputfield_csslocator, "border-bottom", expectedborderbottomColor_inRGB, " Incorrect Border Bottom Color As It Should Be - ' 1px solid rgb(231, 61, 74) ' ");
		
		JSONObject eventTimezoneinputfield_label = (JSONObject) webinarsettingsTemplateArr.get(6);
		String eventTZinputfield_label_csslocator = (String) eventTimezoneinputfield_label.get("csslocator");
		String expectedtextcolor_inRGB = (String) eventTimezoneinputfield_label.get("errorColor_inRGB");
		
		//check event time zone input field has correct alert error text color
		this.getCssStyles(eventTZinputfield_label_csslocator, "color", expectedtextcolor_inRGB, " Incorrect Alert Text RGB Color As It Should Be - ' rgb(231, 61, 74, 1) ' ");
		
		JSONObject eventimezoneinputfield = (JSONObject) webinarsettingsTemplateArr.get(7);
		String eventTZinputfield_csslocator = (String) eventimezoneinputfield.get("csslocator");
		String expectedBBColor_inRGB = (String) eventimezoneinputfield.get("errorColor_inRGB");
		
		//check event time zone input field border-bottom color 
		this.getCssStyles(eventTZinputfield_csslocator, "border-bottom", expectedBBColor_inRGB, " Incorrect Border Bottom Color As It Should Be - ' 1px solid rgb(231, 61, 74) ' ");
		
		JSONObject eventTimeinputfield_label = (JSONObject) webinarsettingsTemplateArr.get(11);
		String eventTimezoneinputfield_csslocator = (String) eventTimeinputfield_label.get("csslocator");
		String expectedTextcolor_inRGB = (String) eventTimeinputfield_label.get("errorColor_inRGB");
		
		//check even time input field label if it has correct error text color
		this.getCssStyles(eventTimezoneinputfield_csslocator, "color", expectedTextcolor_inRGB, " Incorrect Alert Text RGB Color As It Should Be - ' rgb(231, 61, 74, 1) ' ");
		
		
		JSONObject eventTimeinputfield = (JSONObject) webinarsettingsTemplateArr.get(12);
		String eventTimeinputfield_csslocator = (String) eventTimeinputfield.get("csslocator");
		String expectedBbottomcolor_inRGB = (String) eventTimeinputfield.get("errorColor_inRGB");
		
		//check if even time input field has correct error border bottom color
		this.getCssStyles(eventTimeinputfield_csslocator, "border-bottom", expectedBbottomcolor_inRGB, " Incorrect Border Bottom Color As It Should Be - ' 1px solid rgb(231, 61, 74) ' ");
	}
}
