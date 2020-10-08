package Login_Home_Page;

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



public class LoginHomepageBackEnd {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject loginhomepagelocators;
	JSONArray loginhomepagelocatorsArr;
	JSONObject loginhp;

	public LoginHomepageBackEnd(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Login_Home_Page\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			loginhomepagelocators = (JSONObject) obj;
			loginhomepagelocatorsArr = (JSONArray) loginhomepagelocators.get("loginHomePage");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// ***** REUSABLE METHODS ***** //

	// check text / label / Name
	public void getLabelText(String locator, String expectedText, String whatisthebug) throws IOException {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == true) {
			// test if correct title
			WebElement TLN = driver.findElement(By.cssSelector(locator));
			Assert.assertTrue(TLN.getText().contains(expectedText) , whatisthebug+TLN.getText());
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
	
	//check button / link text
	public void verifybutton_linktext(String locator) {
		boolean button_linktext = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if(button_linktext == true) {
			//test if enabled
			boolean button_link_text = driver.findElement(By.cssSelector(locator)).isEnabled();
			if (button_link_text == false) {
				Assert.fail(" Button / Link Text is Disabled ");
			}
		}else {
			Assert.fail(" Button / Link Text Is Not Present ");
		}
	}

	// ***** REUSABLE METHODS ***** //

	// Test if Title Exist
	public void checkTitle() throws IOException {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(0);
		String title = (String) loginhp.get("title");
		String csslocator = (String) loginhp.get("csslocator");
		this.getLabelText(csslocator, title, " Incorrect Title As It Should Be - ' Login To JetWebinar ' ");
	}
	
	// Test if Username input field Exist, Not Disabled, and Has Place holder text
	// as label called Username
	public void checkusernameInputfield() {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(1);
		String username_placeholdertext = (String) loginhp.get("usernameInputfield_placeholder_text");
		String csslocator = (String) loginhp.get("csslocator");

		this.verifyInputfield_checkbox(csslocator);
		this.getplaceholder_text(csslocator, username_placeholdertext);
	}

	// Test if Password input field Exist, Not Disabled, and Has Place holder text
	// as label called Password
	public void checkpasswordInputfield() {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(2);
		String password_placeholdertext = (String) loginhp.get("passwordInputfield_placeholder_text");
		String csslocator = (String) loginhp.get("csslocator");

		this.verifyInputfield_checkbox(csslocator);
		this.getplaceholder_text(csslocator, password_placeholdertext);
	}
	
	//Test if Remember me Exist and its checkbox and if not disabled
	public void checkRememberme_and_checkbox() throws IOException {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(3);
		String remembermetext = (String) loginhp.get("remember_me_label_text");
		String csslocator = (String) loginhp.get("csslocator");
	
		this.getLabelText(csslocator, remembermetext, " Incorrect Label Text As It Should Be - ' Remember me ' ");
		
		this.verifyInputfield_checkbox(csslocator);
	}
	
	//Test if Forgot Password Link text Exist
	public void checkForgotpasswordlinktext() throws IOException {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(4);
		String forgotpassword_link_text = (String) loginhp.get("forgotPassword_text");
		String csslocator = (String) loginhp.get("csslocator");
		
		this.verifybutton_linktext(csslocator);
		this.getLabelText(csslocator, forgotpassword_link_text, " Incorrect Text As It Should Be - ' Forgot Password? ' ");
	}
	
	//Test Sign In button
	public void checkSignInbutton() throws IOException {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(5);
		String signinbuttonName = (String) loginhp.get("SignInButton_name");
		String csslocator = (String) loginhp.get("csslocator");
		
		this.verifybutton_linktext(csslocator);
		this.getLabelText(csslocator, signinbuttonName, " Incorrect Button Name As It Should Be - ' SIGN IN ' ");
	}
	
	//Test copy right text in footer
	public void checkfootertext() throws IOException {
		loginhp = (JSONObject) loginhomepagelocatorsArr.get(6);
		String footertext = (String) loginhp.get("footer_text");
		String csslocator = (String) loginhp.get("csslocator");
		
		this.getLabelText(csslocator, footertext, " Incorrect Text As It Should Be - ' JetWebinar 2020 ' ");
	}  
}
