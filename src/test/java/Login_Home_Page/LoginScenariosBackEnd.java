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

public class LoginScenariosBackEnd {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject loginhomepagelocators;
	JSONArray AlertErrorTextsArr, loginhomepagelocatorsArr, loginscenariosArr, dashboardpageArr;

	public LoginScenariosBackEnd(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Login_Home_Page\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			loginhomepagelocators = (JSONObject) obj;
			AlertErrorTextsArr = (JSONArray) loginhomepagelocators.get("Alert_Error_Text");
			loginhomepagelocatorsArr = (JSONArray) loginhomepagelocators.get("loginHomePage");
			loginscenariosArr = (JSONArray) loginhomepagelocators.get("login_scenarios");
			dashboardpageArr = (JSONArray) loginhomepagelocators.get("DashboardPage"); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// ***** REUSABLE METHODS ***** //

	// click button / Link Text
	public void button_linktext(String locator) {
		WebElement buttonlinkText = driver.findElement(By.cssSelector(locator));
		buttonlinkText.click();
	}

	// check text / label / Name
	public void getLabelText(String locator, String expectedText, String whatisthebug) {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == true) {
			// test if correct title
			WebElement TLN = driver.findElement(By.cssSelector(locator));
			Assert.assertTrue(TLN.getText().contains(expectedText), whatisthebug+ TLN.getText());
		} else {
			Assert.fail(" Text_Label_Name Is Not Present ");
		}
	}

	// enter data in input field
	public void enterdataInputfield(String locator, String datatext) {
		WebElement enterdata = driver.findElement(By.cssSelector(locator));
		enterdata.sendKeys(datatext);
	}

	// clear the input field of data
	public void clearinputfield(String locator) {
		WebElement enterdata = driver.findElement(By.cssSelector(locator));
		enterdata.clear();
	}

	// ***** REUSABLE METHODS ***** //

	// user login without username and password entered
	public void checkloginOne() throws InterruptedException {
		// alert error objct
		JSONObject alerterr = (JSONObject) AlertErrorTextsArr.get(0);
		String errorText = (String) alerterr.get("Error1");
		String csslocatorAlertErrorText = (String) alerterr.get("csslocator");

		// sign in button objt
		JSONObject loginhp = (JSONObject) loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) loginhp.get("csslocator");

		// click sign in button and verify result
		this.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);

		// check if alert error text appear
		this.getLabelText(csslocatorAlertErrorText, errorText, " Incorrect Error Text As It Should Be - ' Enter any username and password. ' ");
	}

	// Test if login using Username only
	public void CheckTwo() throws InterruptedException {
		// alert error objct
		JSONObject alerterr = (JSONObject) AlertErrorTextsArr.get(0);
		String errorText = (String) alerterr.get("Error1");
		String csslocatorAlertErrorText = (String) alerterr.get("csslocator");

		// sign in button objct
		JSONObject signinButtonObjct = (JSONObject) loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) signinButtonObjct.get("csslocator");

		// username input field objct
		JSONObject usernameinputifeldObjct = (JSONObject) loginhomepagelocatorsArr.get(1);
		String usernameinputifieldlocator = (String) usernameinputifeldObjct.get("csslocator");
				
		// user input data objct
		JSONObject userinputdata = (JSONObject) loginscenariosArr.get(0);
		String enterusername = (String) userinputdata.get("username");

		// enter username
		this.enterdataInputfield(usernameinputifieldlocator, enterusername);

		// click SIGN IN button
		this.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
	
		// check if alert error text appear
		this.getLabelText(csslocatorAlertErrorText, errorText, " Incorrect Error Text As It Should Be - ' Enter any username and password. '");
	}

	// Test if login using password only
	public void CheckThree() throws InterruptedException {
		// alert error objct
		JSONObject alerterr = (JSONObject) AlertErrorTextsArr.get(0);
		String errorText = (String) alerterr.get("Error1");
		String csslocatorAlertErrorText = (String) alerterr.get("csslocator");

		// password input field objct
		JSONObject passwordinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(2);
		
		
		// user input data objct
		JSONObject passworduserinputobjct = (JSONObject) loginscenariosArr.get(0);
		String enterpassword = (String) passworduserinputobjct.get("password");
		String passwordInputfieldlocator = (String) passwordinputfieldobjct.get("csslocator");
				
		// sign button objct
		JSONObject signinbuttonobjct = (JSONObject) loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) signinbuttonobjct.get("csslocator");
				
		// clear input field objct
		JSONObject clearUsernameinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(1);
		String usernamelocator = (String) clearUsernameinputfieldobjct.get("csslocator");

		// clear username input field
		this.clearinputfield(usernamelocator);
		// Enter password
		this.enterdataInputfield(passwordInputfieldlocator, enterpassword);
		// click SIGN IN button
		this.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if alert error text appear
		this.getLabelText(csslocatorAlertErrorText, errorText, " Incorrect Alert Error Text As It Should Be - ' Enter any username and password. ' ");
	}

	// Test if incorrect username and password
	public void CheckFour() throws InterruptedException {
		// alert error object
		JSONObject alerterr = (JSONObject) AlertErrorTextsArr.get(1);
		String errorText = (String) alerterr.get("Error2");
		String csslocatorAlertErrorText = (String) alerterr.get("csslocator");

		// username input field object
		JSONObject usernameinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(1);
		String usernameinputfieldlocator = (String) usernameinputfieldobjct.get("csslocator");
		
		// password input field object
		JSONObject passwordinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(2);
		String passwordinputfieldlocator = (String) passwordinputfieldobjct.get("csslocator");
						
		// user data input object
		JSONObject userinputdata = (JSONObject) loginscenariosArr.get(1);
		String enterusername = (String) userinputdata.get("incorrect_username");
		String enterpassword = (String) userinputdata.get("incorrect_password");

		// clear username input field object
		JSONObject clearusernameinputfield = (JSONObject) loginhomepagelocatorsArr.get(1);
		String clear_username_inputfieldlocator = (String) clearusernameinputfield.get("csslocator");
				
		// clear password input field object
		JSONObject clearpasswordinputfield = (JSONObject) loginhomepagelocatorsArr.get(2);
		String clear_password_inputfieldlocator = (String) clearpasswordinputfield.get("csslocator");
	
		// sign button objct
		JSONObject signinbuttonobjct = (JSONObject) loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) signinbuttonobjct.get("csslocator");

		// clear username input field
		this.clearinputfield(clear_username_inputfieldlocator);
		// enter incorrect username
		this.enterdataInputfield(usernameinputfieldlocator, enterusername);
		// clear password input field
		this.clearinputfield(clear_password_inputfieldlocator);
		// enter incorrect password
		this.enterdataInputfield(passwordinputfieldlocator, enterpassword);
		// click SIGN IN button
		this.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if alert error text appear
		this.getLabelText(csslocatorAlertErrorText, errorText, " Incorrect Alert Error Text As It Should Be - ' Please provide a correct username and password. ' ");
	}

	// test if correct username but incorrect password
	public void CheckFive() throws InterruptedException {
		// alert error object
		JSONObject alerterr = (JSONObject) AlertErrorTextsArr.get(1);
		String errorText = (String) alerterr.get("Error2");
		String csslocatorAlertErrorText = (String) alerterr.get("csslocator");

		// username input field object
		JSONObject usernameinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(1);
		String usernameinputfieldlocator = (String) usernameinputfieldobjct.get("csslocator");
		
		// password input field object
		JSONObject passwordinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(2);
		String passwordinputfieldlocator = (String) passwordinputfieldobjct.get("csslocator");
				
		// user data username input object
		JSONObject usernameinputdata = (JSONObject) loginscenariosArr.get(0);
		String enterusername = (String) usernameinputdata.get("username");
		
		// user data password input object
		JSONObject userpasswordinputdata = (JSONObject) loginscenariosArr.get(1);
		String enterpassword = (String) userpasswordinputdata.get("incorrect_password");
		
		// clear username input field object
		JSONObject clearusernameinputfield = (JSONObject) loginhomepagelocatorsArr.get(1);
		String clear_username_inputfieldlocator = (String) clearusernameinputfield.get("csslocator");
				
		JSONObject clearpasswordinputfield = (JSONObject) loginhomepagelocatorsArr.get(2);
		String clear_password_inputfieldlocator = (String) clearpasswordinputfield.get("csslocator");

		// Sign in button objct
		JSONObject signinobjct = (JSONObject) loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) signinobjct.get("csslocator");
		
		// clear username input field
		this.clearinputfield(clear_username_inputfieldlocator);
		// enter incorrect username
		this.enterdataInputfield(usernameinputfieldlocator, enterusername);
		// clear password input field
		this.clearinputfield(clear_password_inputfieldlocator);
		// enter incorrect password
		this.enterdataInputfield(passwordinputfieldlocator, enterpassword);
		// click sign in button
		this.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if alert error text appear
		this.getLabelText(csslocatorAlertErrorText, errorText, " Incorrect Alert Eror Text As It Should Be - ' Please provide a correct username and password. ' ");
	}

	// Test login all valid account
	public void CheckSix() throws InterruptedException {
		// username input field object
		JSONObject usernameinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(1);
		String usernameinputfieldlocator = (String) usernameinputfieldobjct.get("csslocator");
				
		// password input field object
		JSONObject passwordinputfieldobjct = (JSONObject) loginhomepagelocatorsArr.get(2);
		String passwordinputfieldlocator = (String) passwordinputfieldobjct.get("csslocator");
				
		// user data username input object
		JSONObject usernameinputdata = (JSONObject) loginscenariosArr.get(0);
		String enterusername = (String) usernameinputdata.get("username");
		
		// user data password input object
		JSONObject userpasswordinputdata = (JSONObject) loginscenariosArr.get(0);
		String enterpassword = (String) userpasswordinputdata.get("password");
		
		// clear username input field object
		JSONObject clearusernameinputfield = (JSONObject) loginhomepagelocatorsArr.get(1);
		String clear_username_inputfieldlocator = (String) clearusernameinputfield.get("csslocator");
				
		// clear password input field object
		JSONObject clearpasswordinputfield = (JSONObject) loginhomepagelocatorsArr.get(2);
		String clear_password_inputfieldlocator = (String) clearpasswordinputfield.get("csslocator");

		// Sign in button objct
		JSONObject signinobjct = (JSONObject) loginhomepagelocatorsArr.get(5);
		String csslocatorSignInButton = (String) signinobjct.get("csslocator");
		
		// clear username input field
		this.clearinputfield(clear_username_inputfieldlocator);
		// enter incorrect username
		this.enterdataInputfield(usernameinputfieldlocator, enterusername);
		// clear password input field
		this.clearinputfield(clear_password_inputfieldlocator);
		// enter incorrect password
		this.enterdataInputfield(passwordinputfieldlocator, enterpassword);
		// click sign in button
		this.button_linktext(csslocatorSignInButton);
		Thread.sleep(3000);
		// check if it successfully login
		//expected URL destination object
		JSONObject expectedURL = (JSONObject) dashboardpageArr.get(0);
		String getexpectedurl = (String) expectedURL.get("expectedURLpage");
		
		//dashboard title panel object
		JSONObject dashboardpanelTitle = (JSONObject) dashboardpageArr.get(1);
		String dashboardPanel = (String) dashboardpanelTitle.get("DashboardPanel_Title");
		String dashboardtitlepanelLocator = (String) dashboardpanelTitle.get("csslocator");
		
		//test if correct destination url
		Assert.assertEquals(driver.getCurrentUrl(), getexpectedurl, " It Navigate to Different URL "+driver.getCurrentUrl());
		
		//test if there is dashboard panel title
		this.getLabelText(dashboardtitlepanelLocator, dashboardPanel, " Incorrect Title As It Should Be - ' Dashboard ' ");
	}

}
