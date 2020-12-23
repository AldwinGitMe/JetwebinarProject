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

public class reusableTestMethods {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject loginhomepagelocators;
	JSONArray loginhomepagelocatorsArr, AlertErrorTextsArr, loginscenariosArr, dashboardpageArr;
	JSONObject loginhp, alerterr;

	public reusableTestMethods(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Login_Home_Page\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			loginhomepagelocators = (JSONObject) obj;
			loginhomepagelocatorsArr = (JSONArray) loginhomepagelocators.get("loginHomePage");
			AlertErrorTextsArr = (JSONArray) loginhomepagelocators.get("Alert_Error_Text");
			loginscenariosArr = (JSONArray) loginhomepagelocators.get("login_scenarios");
			dashboardpageArr = (JSONArray) loginhomepagelocators.get("DashboardPage");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// check if text / label / name is present
	public void isLabel_Text_Name_Present(String locator) {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == false) {
			Assert.fail(" Text_Label_Name Is Not Present");
		}
	}

	// check text / label / Name is correct
	public void isText_Label_Name_correct(String locator, String expectedText) throws IOException {
		WebElement TLN = driver.findElement(By.cssSelector(locator));
		Assert.assertTrue(TLN.getText().contains(expectedText));
	}

	// check input field if present
	public void isInputfieldPresent(String locator) {
		boolean inputfield = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (inputfield == false) {
			Assert.fail(" Input field Is Not Present ");
		}
	}

	// check input field if enabled
	public void isInputfieldEnabled(String locator) {
		boolean inputfield_if_enabled = driver.findElement(By.cssSelector(locator)).isEnabled();
		if (inputfield_if_enabled == false) {
			Assert.fail(" Input field is Disabled ");
		}
	}

	// check placeholder text
	public void getplaceholder_text(String locator, String expectedText) {
		WebElement placeholdertext = driver.findElement(By.cssSelector(locator));
		Assert.assertTrue(placeholdertext.getAttribute("placeholder").equalsIgnoreCase(expectedText),
				" No Placeholder / Empty " + placeholdertext.getAttribute("placeholder"));
	}

	// check checkbox if present
	public void ischeckboxPresent(String locator) {
		boolean checkbox = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (checkbox == false) {
			Assert.fail(" Checkbox Is Not Present ");
		}
	}

	// check checkbox if enabled
	public void ischeckboxEnabled(String locator) {
		boolean checkbox_if_enabled = driver.findElement(By.cssSelector(locator)).isEnabled();
		if (checkbox_if_enabled == false) {
			Assert.fail(" Check Box is Disabled ");
		}
	}

	// check button or link text is present
	public void islinktext_or_button_is_Present(String locator) {
		boolean buttonlinktext = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (buttonlinktext == false) {
			Assert.fail(" Button / Link Text Is Not Present ");
		}
	}

	// check button or link text is enabled
	public void islinktext_or_button_Enabled(String locator) {
		boolean buttonlink_text = driver.findElement(By.cssSelector(locator)).isEnabled();
		if (buttonlink_text == false) {
			Assert.fail(" Button / Link Text is Disabled ");
		}
	}

	// click button / Link Text
	public void button_linktext(String locator) {
		WebElement buttonlinkText = driver.findElement(By.cssSelector(locator));
		buttonlinkText.click();
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

}
