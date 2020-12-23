package MeetingRooms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
	JSONArray userloginArr, MeetingRoomsArr, StartMeetingRoomModalDialogArr, HostMeetingRoomArr, JoinMeetingRoomArr,
			AttendeeMeetingRoomArr;
	JSONObject dashboardpanel;

	public reusableTestMethods(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\MeetingRooms\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			dashboardPagelocators = (JSONObject) obj;
			userloginArr = (JSONArray) dashboardPagelocators.get("user_account");
			MeetingRoomsArr = (JSONArray) dashboardPagelocators.get("Meeting_Rooms");
			StartMeetingRoomModalDialogArr = (JSONArray) dashboardPagelocators.get("Start_Meeting_Room_Modal_Dialog");
			HostMeetingRoomArr = (JSONArray) dashboardPagelocators.get("Host_Meeting_Room");
			JoinMeetingRoomArr = (JSONArray) dashboardPagelocators.get("Join_Meeting_Room");
			AttendeeMeetingRoomArr = (JSONArray) dashboardPagelocators.get("Attendee_Meeting_Room");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	// scroll down the page
	public void scrollDown(int num) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + num + ")");
	}

	// clear input field
	public void clearInputfield(String locator) {
		WebElement inputfield = driver.findElement(By.cssSelector(locator));
		inputfield.clear();
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

	// get current URL
	public void getCurrentURL(String expectedurl) {
		String currentURL = driver.getCurrentUrl();
		// compare if it is what is expected destination url
		Assert.assertTrue(currentURL.contains(expectedurl));
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
		Assert.assertTrue(TLN.getText().contains(expectedText), TLN.getText());
	}

	// compare get text from an element to another element that should be match
	public void isThisgetTextEle_Matchwith_otherGetTextEle(String locator1, String locator2) {
		WebElement ele1 = driver.findElement(By.cssSelector(locator1));
		WebElement ele2 = driver.findElement(By.cssSelector(locator2));
		Assert.assertTrue(ele1.getText().contains(ele2.getText()));
	}

	// XPATH = compare get text from an element to another element that should be
	// match
	public void isThisgetTextEle_Matchwith_otherGetTextEle_XPATH(String locator1, String locator2) {
		WebElement ele1 = driver.findElement(By.xpath(locator1));
		WebElement ele2 = driver.findElement(By.xpath(locator2));
		System.out.println(ele1.getText() + " AND " + ele2.getText());
		Assert.assertTrue(ele1.getText().contains(ele2.getText()));

	}

	// get attribute name and compare
	public void getAttribute_and_Compare(String locator, String value, String expected) throws InterruptedException {
		WebElement att = driver.findElement(By.cssSelector(locator));
		String as = att.getAttribute(value);
		Assert.assertTrue(as.contains(expected), as);
	}

	// check if text / label / name is present
	public void isLabel_Text_Name_Present(String locator) {
		boolean TextLabelName = driver.findElement(By.cssSelector(locator)).isDisplayed();
		if (TextLabelName == false) {
			Assert.fail(" Text_Label_Name Is Not Present");
		}
	}

	// get text of an element using xpath
	public String getTextXPATH(String xpath) {
		String thistextname = new String();
		WebElement thistext = driver.findElement(By.xpath(xpath));
		thistextname = thistext.getText();
		return thistextname;
	}

	// get meeting room name at Meeting Room Handle column in All meetings page
	// table
	public String getmeetingName() throws IOException, ParseException {
		String meetingName = new String();
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement thisname = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]"));
		System.out.println(meetingName = thisname.getText());
		return meetingName;
	}

	// click START MEETING NOW button on a recent add meeting room
	public void ClickStartMeetingNowbutton() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement thisStartmeetingNowbutton = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/a"));
		thisStartmeetingNowbutton.click();
	}

	// click join meeting link and click
	public void ClickJoinmeetinglink() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement thisJoinMeetingLinkname = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/a"));
		thisJoinMeetingLinkname.click();
	}

	// check the correct join meeting link is Present
	public void checkExpectedJoinMeetinglinkisPresent() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		boolean thisJoinMeetingLinkname = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/a")).isDisplayed();
		if (thisJoinMeetingLinkname == false) {
			Assert.fail(" NO Join Meeting Link for this newly created Meeting Room ");
		}
	}

	// check the correct join meeting link is enabled
	public void checkExpectedJoinMeetinglinkisEnabled() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		boolean thisJoinMeetingLinkname = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/a")).isEnabled();
		if (thisJoinMeetingLinkname == false) {
			Assert.fail(" The Join Meeting Link for this newly created Meeting Room Is Disabled ");
		}
	}

	// check if correct text link for this join meeting link
	public void checkcorrectJoinmeetinglinktext(String expectedText) {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement thisJoinMeetingLinkname = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[4]/a"));
		Assert.assertTrue(thisJoinMeetingLinkname.getText().contains(expectedText), thisJoinMeetingLinkname.getText());
	}

	// check START MEETING NOW button is Enabled
	public void StartMeetingNowButtonisEnabled() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		boolean startmeetingnowbutton = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/a")).isEnabled();
		if (startmeetingnowbutton == false) {
			Assert.fail(" START MEETING NOW BUTTON Is Not Present");
		}
	}

	// check START MEETING NOW button is Present
	public void StartMeetingNowButtonisPresent() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		boolean startmeetingnowbutton = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/a")).isDisplayed();
		if (startmeetingnowbutton == false) {
			Assert.fail(" START MEETING NOW BUTTON Is Not Present");
		}
	}

	// check START MEETING NOW button is Name is Correct
	public void StartMeetingNowButtonNameIsCorrect(String expectedText) {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement startmeetingnowbutton = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/a"));
		Assert.assertTrue(startmeetingnowbutton.getText().contains(expectedText), startmeetingnowbutton.getText());
	}

	// check if that start meeting room now button has correct icon
	public void StartMeetingNowButtonNameIconCorrect(String value, String expected) {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement startmeetingnowbuttonIcon = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[3]/a/i"));
		String as = startmeetingnowbuttonIcon.getAttribute(value);
		Assert.assertTrue(as.contains(expected));
	}

	// check if it has delete link text is present
	public void checkDeletelinktext_is_Present() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		boolean deletelinktext = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[5]/a")).isDisplayed();
		if (deletelinktext == false) {
			Assert.fail(" There is No Delete Link text for this newly added Meeting Room ");
		}
	}

	// check if it has delete link text is enabled
	public void checkDeletelinktext_is_Enabled() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		boolean deletelinktext = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[5]/a")).isEnabled();
		if (deletelinktext == false) {
			Assert.fail(" The Delete Link text is Disabled for this newly added Meeting Room ");
		}
	}

	// check if it has delete link text is correct
	public void checkDeletelinktext_is_correct(String expected) {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement deletelinktext = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[5]/a"));
		Assert.assertTrue(deletelinktext.getText().contains(expected));
	}

	// click this delete link text
	public void clickthisDeletelinktext() {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		int i = colVals.size();
		WebElement deletelinktext = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[5]/a"));
		deletelinktext.click();
	}

	// iterate using xpath on tables - it should not exist
	public void check_if_thisword_is_present_inTables_shouldNOTExist(String expectedName) {
		List<WebElement> colVals = driver.findElements(By.xpath("//tbody//tr"));
		// System.out.println(colVals.size());
		for (int i = 1; i <= colVals.size(); i++) {
			WebElement thisname = driver.findElement(By.xpath("//tbody//tr[" + i + "]/td[2]"));
			Assert.assertFalse(thisname.getText().contains(expectedName));
		}
	}

	// check button or link text is hidden
	public void islinktext_or_button_is_hidden(String locator) {
		int buttonlinktext = driver.findElements(By.cssSelector(locator)).size();
		if (buttonlinktext != 0) {
			Assert.fail(" Element Is Still Present ");
		}
	}

	// mouser hover in element - button / link text / image
	public void hoverpointer(String locator) {
		Actions actions = new Actions(driver);
		WebElement thiselement = driver.findElement(By.cssSelector(locator));
		actions.moveToElement(thiselement).perform();
	}

	// check if element is present using xpath
	public void isElement_presentXPATH(String xpathvalue) {
		boolean ele = driver.findElement(By.xpath(xpathvalue)).isDisplayed();
		if (ele == false) {
			Assert.fail(" Element Is Not Present ");
		}
	}

	// check if element is enabled using xpath
	public void isElement_enabledXPATH(String xpathvalue) {
		boolean ele = driver.findElement(By.xpath(xpathvalue)).isEnabled();
		if (ele == false) {
			Assert.fail(" Element Is Disabled ");
		}
	}

	// check if correct text using xpath
	public void istextCorrect(String xpathvalue, String expectedText) {
		WebElement TLN = driver.findElement(By.xpath(xpathvalue));
		Assert.assertTrue(TLN.getText().contains(expectedText), TLN.getText());
	}

	// get attribute value using xpath
	public void getattributeValueXPATH(String xpathlocator, String value, String expected) {
		WebElement att = driver.findElement(By.xpath(xpathlocator));
		String as = att.getAttribute(value);
		Assert.assertTrue(as.contains(expected));
	}

	// check element is hidden or gone in the DOM using XPATH
	public void checkElement_gone_hiddenXPATH(String xpathvalue) {
		int buttonlinktext = driver.findElements(By.xpath(xpathvalue)).size();
		if (buttonlinktext != 0) {
			Assert.fail(" Element Is Still Present ");
		}
	}

	// check element is hidden or gone in the DOM using csslocator
	public void checkElement_gone_hiddenCSSlocator(String csslocator) {
		int buttonlinktext = driver.findElements(By.cssSelector(csslocator)).size();
		if (buttonlinktext != 0) {
			Assert.fail(" Element Is Still Present ");
		}
	}

	// click button link using xpath
	public void clickbutton_linktextXPATH(String xpathvalue) {
		WebElement button_linktext = driver.findElement(By.xpath(xpathvalue));
		button_linktext.click();
	}

	// hover using xpath
	public void hoverpointerXPATH(String xpathvalue) {
		Actions actions = new Actions(driver);
		WebElement thiselement = driver.findElement(By.xpath(xpathvalue));
		actions.moveToElement(thiselement).perform();
	}

	// handle window popup or check next window open
	public void checkNewWindowOpen() throws InterruptedException {
		// check if Host meeting room opens in new window tab
		String allmeetingsPage = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		if (windows.size() == 2) {
			Iterator iterator = windows.iterator();
			String currentWindowID;
			while (iterator.hasNext()) {
				currentWindowID = iterator.next().toString();
				System.out.println(currentWindowID);

				// check if the current window is focused on the new window tab then bug
				if (!currentWindowID.equals(allmeetingsPage)) {
					driver.switchTo().window(currentWindowID);
					Thread.sleep(2000);
				}
			}
		} else {
			Assert.fail(" It Did Not Open in New Window As There Is => " + windows.size() + " Windows Opened ");
		}
	}

	// switch back to preview window
	public void switchtoattendee() throws InterruptedException {
		String allmeetingsPage = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		if (windows.size() == 2) {
			Iterator iterator = windows.iterator();
			String currentWindowID;
			while (iterator.hasNext()) {
				currentWindowID = iterator.next().toString();
				System.out.println(currentWindowID);
				driver.switchTo().window(currentWindowID);
				Thread.sleep(2000);
			}
		}
	}

	// check get alert error popup on input fields
	public void checkAlertpopup(String inputfield) {
		WebElement nam = driver.findElement(By.cssSelector(inputfield));
		Assert.assertTrue(nam.getAttribute("validationMessage").contains("Please fill out this field."));
	}

	// navigate to
	public void gotothisurl(String url) {
		driver.navigate().to(url);
	}

	// list of all attendees top to bottom XPATH
	// iterate using xpath on the list as it should
	public void checkIfTheNameIsInTheAttendeesList(String expectedN) {
		List<WebElement> alllist = driver.findElements(By.xpath("//ul[@id='presence_box']/li"));
		// System.out.println(colVals.size());
		for (int i = 1; i <= alllist.size(); i++) {
			WebElement thisname = driver
					.findElement(By.xpath("//ul[@id='presence_box']/li[" + i + "]/div/div/div[2]/div"));
			Assert.assertTrue(thisname.getText().contains(expectedN), thisname.getText());
		}
	}

	public String listattend() {
		String lista = new String();
		List<WebElement> alllist = driver.findElements(By.xpath("//ul[@id='presence_box']/li"));
		// System.out.println(colVals.size());
		for (int i = 1; i <= alllist.size(); i++) {
			WebElement thisname = driver
					.findElement(By.xpath("//ul[@id='presence_box']/li[" + i + "]/div/div/div[2]/div"));
			lista = thisname.getText();
		}
		return lista;
	}

	// host chat box
	public void checkchatAtHost(String expectedChat) {
		List<WebElement> alllist = driver.findElements(By.xpath("//ul[@id='chat_box']/li"));
		// System.out.println(colVals.size());
		for (int i = 1; i <= alllist.size(); i++) {
			WebElement thisname = driver.findElement(By.xpath("//ul[@id='chat_box']/li[" + i + "]/div/div/div[2]/div"));
			System.out.println("mao ni sa host = >" + thisname.getText());
			Assert.assertTrue(thisname.getText().contains(expectedChat));
		}
	}

	// Attendee chat box
	public void checkchatAtAttendee(String expectedChat) {
		List<WebElement> alllist = driver.findElements(By.xpath("//ul[@id='attendee_chat_box']/li"));
	
		for (int i = 1; i <= alllist.size(); i++) {
			WebElement thisname = driver
					.findElement(By.xpath("//ul[@id='attendee_chat_box']/li[" + i + "]/div/div/div[2]/div"));
			System.out.println("mao ni sa attendee = >" + thisname.getText());
			Assert.assertTrue(thisname.getText().contains(expectedChat));
		}
	}
}
