package Dashboard;

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

public class reusableTestMethods {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject dashboardPagelocators;
	JSONArray userloginArr, dashboardmenusArr, dashboardPanelOpenClosedArr, jetwebinarLogoArr, userprofiledropDownMenuArr;
	JSONObject dashboardpanel;

	public reusableTestMethods(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Dashboard\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			dashboardPagelocators = (JSONObject) obj;
			userloginArr = (JSONArray) dashboardPagelocators.get("userLogin");
			dashboardmenusArr = (JSONArray) dashboardPagelocators.get("Menus");
			dashboardPanelOpenClosedArr = (JSONArray) dashboardPagelocators.get("DashboardPanel_Closed_Open");
			jetwebinarLogoArr = (JSONArray) dashboardPagelocators.get("JetWebinar_Logo");
			userprofiledropDownMenuArr = (JSONArray) dashboardPagelocators.get("User_Login_Profile");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// enter data in input field
	public void enterdataInputfield(String locator, String datatext) {
		WebElement enterdata = driver.findElement(By.cssSelector(locator));
		enterdata.sendKeys(datatext);
	}

	// click button / link text
	public void click_button_linktext(String locator) {
		WebElement button_linktext = driver.findElement(By.cssSelector(locator));
		button_linktext.click();
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

	// check text / label / Name is correct
	public void isText_Label_Name_correct(String locator, String expectedText) throws IOException {
		WebElement TLN = driver.findElement(By.cssSelector(locator));
		Assert.assertTrue(TLN.getText().contains(expectedText));
	}

	// get attribute name and compare
	public void getAttribute_and_Compare(String locator, String value, String expected) throws InterruptedException {
		WebElement att = driver.findElement(By.cssSelector(locator));
		String as = att.getAttribute(value);
		Assert.assertTrue(as.contains(expected));
	}

	// check if text / label / name is present
	public void isLabel_Text_Name_Present(String locator) {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == false) {
			Assert.fail(" Text_Label_Name Is Not Present");
		}
	}

	// check button or link text is hidden
	public void islinktext_or_button_is_hidden(String locator) {
		int buttonlinktext = driver.findElements(By.cssSelector(locator)).size();
		if (buttonlinktext != 0) {
			Assert.fail(" Element Is Still Present ");
		}
	}

	// scroll down the page
	public void scrollDown(int num) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + num + ")");
	}

	// check if hidden then check icon as it should be visible
	public void whenhidden_icon_is_visible(String locator1, String value, String expected)
			throws InterruptedException {
		boolean buttonlinktext = driver.findElement(By.cssSelector(locator1)).isDisplayed();
		if (buttonlinktext == false) {
			// check for icon
			this.getAttribute_and_Compare(locator1, value, expected);
		}
	}

	// check if hidden the text / label
	public void whenhidden_is_text(String locator) throws InterruptedException {
		boolean buttonlinktext = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (buttonlinktext == true) {
			// check for icon
			Assert.fail(" Element Is Still Present ");
		}
	}

	// mouser hover in element - button / link text / image
	public void hoverpointer(String locator) {
		Actions actions = new Actions(driver);
		WebElement thiselement = driver.findElement(By.cssSelector(locator));
		actions.moveToElement(thiselement).perform();
	}
	
	//get current URL
	public void getCurrentURL(String expectedurl) {
		String currentURL = driver.getCurrentUrl();
		//compare if it is what is expected destination url
		Assert.assertTrue(currentURL.contains(expectedurl));
	}

}
