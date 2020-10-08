package Webinars;

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

public class AddNewWebinarBackEnd {
	WebDriver driver;
	JSONParser jsonParser;
	JSONObject loginhomepagelocators;
	JSONArray loginAccountsArr, WebinarsMenuPanelArr, AddWebinarPageArr, AdelineTemplateArr, MesaTemplateArr,
			BelmontTemplateArr, OakTemplateArr, NexgenTemplateArr, ChicagoTemplateArr,ClarkTemplateArr, LagunaTemplateArr;

	public AddNewWebinarBackEnd(WebDriver driver) throws IOException, ParseException {
		this.driver = driver;
		try {
			jsonParser = new JSONParser();
			FileReader reader = new FileReader(".\\src\\test\\java\\Webinars\\elementlocators.json");
			Object obj = jsonParser.parse(reader);

			loginhomepagelocators = (JSONObject) obj;
			loginAccountsArr = (JSONArray) loginhomepagelocators.get("login_credentials");
			WebinarsMenuPanelArr = (JSONArray) loginhomepagelocators.get("Webinars_Main_menu");
			AddWebinarPageArr = (JSONArray) loginhomepagelocators.get("Add_New_Webinar_Page");
			AdelineTemplateArr = (JSONArray) loginhomepagelocators.get("Adeline_Template");
			MesaTemplateArr = (JSONArray) loginhomepagelocators.get("Mesa_Template");
			BelmontTemplateArr = (JSONArray) loginhomepagelocators.get("Belmont_Template");
			OakTemplateArr = (JSONArray) loginhomepagelocators.get("Oak_Template");
			NexgenTemplateArr = (JSONArray) loginhomepagelocators.get("Nexgen_Template");
			ChicagoTemplateArr = (JSONArray) loginhomepagelocators.get("Chicago_Template");
			ClarkTemplateArr = (JSONArray) loginhomepagelocators.get("Clark_Template");
			LagunaTemplateArr = (JSONArray) loginhomepagelocators.get("Laguna_Template");
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
		 js.executeScript("window.scrollBy(0,"+num+")");
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

	// Test if there is Webinars Main Menu
	public void CheckWebinarMainMenu() throws InterruptedException, IOException {
		// login first
		this.loginaccount();
		Thread.sleep(3000);

		// check if there is webinar main menu
		JSONObject webinarsmainmenu = (JSONObject) WebinarsMenuPanelArr.get(0);
		String webinarslocator = (String) webinarsmainmenu.get("csslocator");

		// test if there is Webinars Main menu
		this.verifybutton_linktext(webinarslocator);
	}

	// Test if there is sub menu under Webinars when click it
	// will reveal namely: All Webinars and Add Webinar
	public void checkWebinarsSubMenus() throws InterruptedException {
		JSONObject webinarsmainmenu = (JSONObject) WebinarsMenuPanelArr.get(0);
		String webinarslocator = (String) webinarsmainmenu.get("csslocator");
		// click the Webinars main menu
		this.clickButton_linktext(webinarslocator);
		Thread.sleep(3000);

		// test if there is All Webinars
		JSONObject AllWebinars = (JSONObject) WebinarsMenuPanelArr.get(1);
		String AllWebinars_csslocator = (String) AllWebinars.get("csslocator");

		this.verifybutton_linktext(AllWebinars_csslocator);

		// test if there is Add Webinar
		JSONObject AddWebinars = (JSONObject) WebinarsMenuPanelArr.get(2);
		String AddWebinars_csslocator = (String) AddWebinars.get("csslocator");

		this.verifybutton_linktext(AddWebinars_csslocator);
	}

	// test Add New Webinar Page
	public void checkAddNewWebinarPage() throws InterruptedException, IOException {
		JSONObject AddWebinars = (JSONObject) WebinarsMenuPanelArr.get(2);
		String AddWebinars_csslocator = (String) AddWebinars.get("csslocator");

		// click Add Webinar sub menu
		this.clickButton_linktext(AddWebinars_csslocator);
		Thread.sleep(2000);

		JSONObject AddNewWebinar_url = (JSONObject) AddWebinarPageArr.get(0);
		String expectedurl = (String) AddNewWebinar_url.get("URL");

		// Test expected url
		Assert.assertEquals(driver.getCurrentUrl(), expectedurl,
				" The URL Is Different From What I Expected - " + driver.getCurrentUrl());

		JSONObject AddNewWebinar_title = (JSONObject) AddWebinarPageArr.get(1);
		String title = (String) AddNewWebinar_title.get("Title");
		String title_locator = (String) AddNewWebinar_title.get("csslocator");

		// Test title
		this.getLabelText(title_locator, title, " Incorrect Title As It Should Be - ' Add New Webinar - Select Template ' ");

		JSONObject Accordion_Title = (JSONObject) AddWebinarPageArr.get(2);
		String selectaTemplate = (String) Accordion_Title.get("Accordion_Template_Title");
		String selectaTemplate_csslocator = (String) Accordion_Title.get("csslocator");

		// test select a template accordion title
		this.getLabelText(selectaTemplate_csslocator, selectaTemplate, " Incorrect Text As It Should Be - ' Select A Template ' ");
	}

	// check if Adeline template is present
	public void checkAdelineTemplate() throws IOException, InterruptedException {
		// check if Adeline template is present
		JSONObject adelineTemp = (JSONObject) AdelineTemplateArr.get(0);
		String adeline_card_template = (String) adelineTemp.get("csslocator");

		// test if there is adeline card
		this.checkElement(adeline_card_template);

		JSONObject adelineTempName = (JSONObject) AdelineTemplateArr.get(1);
		String name = (String) adelineTempName.get("Template_Title");
		String namelocator = (String) adelineTempName.get("csslocator");

		// test adeline template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Adeline ' ");

		JSONObject adelineTempSubtitle = (JSONObject) AdelineTemplateArr.get(2);
		String subtitle = (String) adelineTempSubtitle.get("Adeline_subTitle");
		String subtitlelocator = (String) adelineTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Blue theme with waves ' ");

		JSONObject usebutton = (JSONObject) AdelineTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Adeline_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use adeline button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use adeline button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE ADELINE ' ");
	}

	// check if Mesa Template Exist
	public void checkMesaTemplate() throws IOException, InterruptedException {
		// check if Mesa Template is Present
		JSONObject MesaTemplate = (JSONObject) MesaTemplateArr.get(0);
		String mesa_card_template = (String) MesaTemplate.get("csslocator");

		// test if there is mesa card
		this.checkElement(mesa_card_template);

		JSONObject mesaTempName = (JSONObject) MesaTemplateArr.get(1);
		String name = (String) mesaTempName.get("Template_Title");
		String namelocator = (String) mesaTempName.get("csslocator");

		// test mesa template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Mesa ' ");

		JSONObject mesaTempSubtitle = (JSONObject) MesaTemplateArr.get(2);
		String subtitle = (String) mesaTempSubtitle.get("Mesa_subTitle");
		String subtitlelocator = (String) mesaTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Smoky grey theme ' ");

		JSONObject usebutton = (JSONObject) MesaTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Mesa_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use mesa button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use adeline button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE MESA ' ");
	}

	// check if Belmont template is present
	public void checkBelmontTemplate() throws IOException, InterruptedException {
		// check if Belmont Template is Present
		JSONObject BelmontTemplate = (JSONObject) BelmontTemplateArr.get(0);
		String belmont_card_template = (String) BelmontTemplate.get("csslocator");

		// test if there is belmont card
		this.checkElement(belmont_card_template);

		JSONObject belmontTempName = (JSONObject) BelmontTemplateArr.get(1);
		String name = (String) belmontTempName.get("Template_Title");
		String namelocator = (String) belmontTempName.get("csslocator");

		// test belmont template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Belmont ' ");

		JSONObject belmontTempSubtitle = (JSONObject) BelmontTemplateArr.get(2);
		String subtitle = (String) belmontTempSubtitle.get("Belmont_subTitle");
		String subtitlelocator = (String) belmontTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Digital blue theme ' ");

		JSONObject usebutton = (JSONObject) BelmontTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Belmont_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use belmont button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use belmont button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE BELMONT ' ");
	}
	
	// check if Oak template is present
	public void checkOakTemplate() throws IOException, InterruptedException {
		// check if Oak Template is Present
		JSONObject OakTemplate = (JSONObject) OakTemplateArr.get(0);
		String oak_card_template = (String) OakTemplate.get("csslocator");

		// test if there is oak card
		this.checkElement(oak_card_template);

		JSONObject oakTempName = (JSONObject) OakTemplateArr.get(1);
		String name = (String) oakTempName.get("Template_Title");
		String namelocator = (String) oakTempName.get("csslocator");

		// test oak template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Oak ' ");

		JSONObject oakTempSubtitle = (JSONObject) OakTemplateArr.get(2);
		String subtitle = (String) oakTempSubtitle.get("Oak_subTitle");
		String subtitlelocator = (String) oakTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Silver and clean theme ' ");

		JSONObject usebutton = (JSONObject) OakTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Oak_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use oak button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use oak button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE OAK ' ");
	}
	
	// check if nexgen template is present
	public void checkNexgenTemplate() throws IOException, InterruptedException {
		//first scroll down
		this.scrollDown(300);
		
		// check if Nexgen Template is Present
		JSONObject NexgenTemplate = (JSONObject) NexgenTemplateArr.get(0);
		String nexgen_card_template = (String) NexgenTemplate.get("csslocator");

		// test if there is nexgen card
		this.checkElement(nexgen_card_template);

		JSONObject nexgenTempName = (JSONObject) NexgenTemplateArr.get(1);
		String name = (String) nexgenTempName.get("Template_Title");
		String namelocator = (String) nexgenTempName.get("csslocator");

		// test nexgen template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Nexgen ' ");

		JSONObject nexgenTempSubtitle = (JSONObject) NexgenTemplateArr.get(2);
		String subtitle = (String) nexgenTempSubtitle.get("Nexgen_subTitle");
		String subtitlelocator = (String) nexgenTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' White minimalist theme ' ");

		JSONObject usebutton = (JSONObject) NexgenTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Nexgen_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use nexgen button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use nexgen button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE NEXGEN ' ");
	}
	
	
	//check if Chicago is Present
	public void checkChicagoTemplate() throws IOException, InterruptedException {
		// check if chicago Template is Present
		JSONObject ChicagoTemplate = (JSONObject) ChicagoTemplateArr.get(0);
		String chicago_card_template = (String) ChicagoTemplate.get("csslocator");

		// test if there is chicago card
		this.checkElement(chicago_card_template);

		JSONObject chicagoTempName = (JSONObject) ChicagoTemplateArr.get(1);
		String name = (String) chicagoTempName.get("Template_Title");
		String namelocator = (String) chicagoTempName.get("csslocator");

		// test chicago template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Chicago ' ");

		JSONObject chicagoTempSubtitle = (JSONObject) ChicagoTemplateArr.get(2);
		String subtitle = (String) chicagoTempSubtitle.get("Chicago_subTitle");
		String subtitlelocator = (String) chicagoTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Blue minimalist theme ' ");

		JSONObject usebutton = (JSONObject) ChicagoTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Chicago_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use chicago button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use chicago button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorect Button Name As It Should Be - ' USE CHICAGO ' ");
	}
	
	
	// check if Clark template is present
	public void checkClarkTemplate() throws IOException, InterruptedException {
		// check if clark Template is Present
		JSONObject ClarkTemplate = (JSONObject) ClarkTemplateArr.get(0);
		String clark_card_template = (String) ClarkTemplate.get("csslocator");

		// test if there is clark card
		this.checkElement(clark_card_template);

		JSONObject clarkTempName = (JSONObject) ClarkTemplateArr.get(1);
		String name = (String) clarkTempName.get("Template_Title");
		String namelocator = (String) clarkTempName.get("csslocator");

		// test clark template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Clark ' ");

		JSONObject clarkTempSubtitle = (JSONObject) ClarkTemplateArr.get(2);
		String subtitle = (String) clarkTempSubtitle.get("Clark_subTitle");
		String subtitlelocator = (String) clarkTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Silver classic theme ' ");

		JSONObject usebutton = (JSONObject) ClarkTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Clark_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use clark button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use clark button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE CLARK ' ");
	}
	
	//check Laguna template
	public void checkLagunaTemplate() throws IOException, InterruptedException {
		// check if laguna Template is Present
		JSONObject LagunaTemplate = (JSONObject) LagunaTemplateArr.get(0);
		String laguna_card_template = (String) LagunaTemplate.get("csslocator");

		// test if there is laguna card
		this.checkElement(laguna_card_template);

		JSONObject lagunaTempName = (JSONObject) LagunaTemplateArr.get(1);
		String name = (String) lagunaTempName.get("Template_Title");
		String namelocator = (String) lagunaTempName.get("csslocator");

		// test laguna template name card
		this.getLabelText(namelocator, name, " Incorrect Title As It Should Be - ' Laguna ' ");

		JSONObject lagunaTempSubtitle = (JSONObject) LagunaTemplateArr.get(2);
		String subtitle = (String) lagunaTempSubtitle.get("Laguna_subTitle");
		String subtitlelocator = (String) lagunaTempSubtitle.get("csslocator");

		// test sub title
		this.getLabelText(subtitlelocator, subtitle, " Incorrect Sub Title As It Should Be - ' Dark digital theme ' ");

		JSONObject usebutton = (JSONObject) LagunaTemplateArr.get(3);
		String usebuttonInside = (String) usebutton.get("Use_Laguna_Button");
		String usebuttonlocator = (String) usebutton.get("csslocator");

		this.hoverpointer(usebuttonlocator);
		// test if use laguna button exist
		Thread.sleep(3000);
		this.verifybutton_linktext(usebuttonlocator);

		// test use laguna button name
		this.getLabelText(usebuttonlocator, usebuttonInside, " Incorrect Button Name As It Should Be - ' USE LAGUNA ' ");
	}
	
}
