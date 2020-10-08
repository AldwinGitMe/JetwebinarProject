package forgotPassword;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class forgotPasswordPageBackend {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject forgotpasswordPagelocators;
	JSONArray forgotpasswordPagelocatorsArr;

	public forgotPasswordPageBackend(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\forgotPassword\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			forgotpasswordPagelocators = (JSONObject) obj;
			forgotpasswordPagelocatorsArr = (JSONArray) forgotpasswordPagelocators.get("ForgotPassword");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// ***** REUSABLE METHODS ***** //

	// click button / link text
	public void click_button_linktext(String locator) {
		WebElement button_linktext = driver.findElement(By.cssSelector(locator));
		button_linktext.click();
	}

	// check text / label / Name
	public void getLabelText(String locator, String expectedText, String whatisthebug) {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == true) {
			// test if correct title
			WebElement TLN = driver.findElement(By.cssSelector(locator));
			Assert.assertTrue(TLN.getText().contains(expectedText), whatisthebug + TLN.getText());
		} else {
			Assert.fail(" Text_Label_Name Is Not Present ");
		}
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
	public void verifybutton_linktext(String locator) {
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

	// ***** REUSABLE METHODS ***** //

	// Test when Forgot Password is Click - it reveals the expected UI elements
	public void ClickForgotPassword_And_Verify() throws InterruptedException {
		// forgot password link text Object
		JSONObject forgotPlink = (JSONObject) forgotpasswordPagelocatorsArr.get(0);
		String getforgotpasswordlinktext_csslocator = (String) forgotPlink.get("csslocator");

		// click Forgot Password link text
		this.click_button_linktext(getforgotpasswordlinktext_csslocator);
		Thread.sleep(5000);

		// forgot password sub title Object
		JSONObject forgotPsubT = (JSONObject) forgotpasswordPagelocatorsArr.get(1);
		String getforgotPassword_subtitle = (String) forgotPsubT.get("Forgot_Password_sub_Title");
		String getforgotPassword_subtitle_Locator = (String) forgotPsubT.get("csslocator");

		// verify if sub title is Exist
		this.getLabelText(getforgotPassword_subtitle_Locator, getforgotPassword_subtitle, " Incorrect Sub Title As It Should Be - ' Forgot Password ? '");

		// instructional text object
		JSONObject instructionT = (JSONObject) forgotpasswordPagelocatorsArr.get(2);
		String getinstructional_text = (String) instructionT.get("Instruction_Text");
		String getinstructional_text_csslocator = (String) instructionT.get("csslocator");

		// verify instructional text
		this.getLabelText(getinstructional_text_csslocator, getinstructional_text, " Incorrect Text As It Should Be - ' Enter your e-mail address below to reset your password. ' ");
		
		//Email input field object
		JSONObject emailinputf = (JSONObject) forgotpasswordPagelocatorsArr.get(3);
		String getEmailinputfield_name = (String) emailinputf.get("Email_inputfield");
		String getEmailinputfield_cssselector = (String) emailinputf.get("csslocator");
		
		//verify email input field
		this.verifyInputfield_checkbox(getEmailinputfield_cssselector);
		
		//verify if there is label in the form of placeholder text
		this.getplaceholder_text(getEmailinputfield_cssselector, getEmailinputfield_name);
		
		//back button Object
		JSONObject backbutton = (JSONObject) forgotpasswordPagelocatorsArr.get(4);
		String getbackbuttonName = (String) backbutton.get("Back_button");
		String getBackbuttonselector = (String) backbutton.get("csslocator");
		
		//verify if back button is present
		this.verifyInputfield_checkbox(getBackbuttonselector);
		
		//verify back button Name
		this.getLabelText(getBackbuttonselector, getbackbuttonName, " Incorrect Button Name As It Should Be - ' BACK '");
		
		//Submit button object
		JSONObject submitbutton = (JSONObject) forgotpasswordPagelocatorsArr.get(5);
		String getsubmitbuttonName = (String) submitbutton.get("Submit_Button");
		String getsubmit_button_locator = (String) submitbutton.get("csslocator");
		
		//verify if submit button exist
		this.verifybutton_linktext(getsubmit_button_locator);
		
		//verify button name
		this.getLabelText(getsubmit_button_locator, getsubmitbuttonName, " Incorrect Button Name As It Should Be - ' SUBMIT '");
	}
}
