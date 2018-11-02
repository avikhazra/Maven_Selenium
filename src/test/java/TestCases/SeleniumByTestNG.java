package TestCases;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.http.HttpMessage;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;

import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import CommonLIB.Initial_and_Common;
import CommonLIB.Repository;
import CommonLIB.commonlib;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import okhttp3.Credentials;
//import org.openqa.selenium.security.UserAndPassword;


@SuppressWarnings("unused")
public class SeleniumByTestNG extends Initial_and_Common  {
	
	public int count=0;
	Repository Repo = new Repository();


	/************************************************************************************************** 
	 * Test Case Name:  AbyBTesting
	 * SL No: 1
	 * Objective: Click on A/B testing link , verify the heading and write the page text on  note pad.
	 * Scenario: +Ve
	 * @throws Exception 
	 * @throws SecurityException 
	 **************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void AbyBTesting(String devicename,String Browser) throws SecurityException, Exception {

		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,5);
		Thread.sleep(1000);
		ClickObject(Repo.A_Btesting);
		Thread.sleep(1000);
		System.out.println("*********************WithCookieAfterVisitingPage************************");
		ValidateElementText(Repo.heading,"A/B Test");
		WriteGetElementTextonNotePad(Repo.Description,new Object(){}.getClass().getEnclosingMethod().getName().toString(),"");
		driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
		driver.navigate().refresh();
		ValidateElementText(Repo.heading,"No A/B Test");
		BrowserClosing(devicename);
		System.out.println("***********************************************************************");
		System.out.println("*********************WithCookieBeforeVisitingPage************************");
		BrowserLaunchwithCapabilities(devicename);
		PauseExecution(5000);
		FocusOnUrl(Repo.StrtestUrl,5);
		driver.manage().addCookie(new Cookie("optimizelyOptOut", "true"));
		ClickObject(Repo.A_Btesting);
		ValidateElementText(Repo.heading,"No A/B Test");
		BrowserClosing(devicename);
		System.out.println("***********************************************************************");
		System.out.println("******************************Deleting Cookies*****************************************");
		BrowserLaunchwithCapabilities(devicename);
		PauseExecution(5000);
		FocusOnUrl(Repo.StrtestUrl,5);
		ClickObject(Repo.A_Btesting);
		ValidateElementText(Repo.heading,"A/B Test");
		driver.manage().deleteAllCookies();
		driver.manage().deleteCookieNamed("optimizelyOptOut");
		driver.manage().deleteCookieNamed("optimizelyEndUserId");
		driver.manage().deleteCookieNamed("optimizelyPendingLogEvents");
		driver.manage().deleteCookieNamed("optimizelySegments");
		driver.manage().deleteCookieNamed("rack.session");
		driver.manage().deleteCookieNamed("optimizelyBuckets");
		Set<Cookie> cookiesList1 =  driver.manage().getCookies();
		for(Cookie getcookies :cookiesList1) {
			System.out.println(getcookies.getName());
			System.out.println(cookiesList1.size());
			driver.manage().deleteCookie(getcookies);
			driver.navigate().refresh();
		}
		PauseExecution(3000);

		driver.manage().deleteCookieNamed("optimizelyBuckets");
		driver.navigate().refresh();
		ValidateElementText(Repo.heading,"A/B Test ");
		//WriteGetElementTextonNotePad(Repo.Description,new Object(){}.getClass().getEnclosingMethod().getName().toString());
		PauseExecution(1000);
		BrowserClosing(devicename);


		System.out.println("***********************************************************************");
		System.out.println("******************************WithOptOutUrl*****************************************");
		BrowserLaunchwithCapabilities(devicename);
		PauseExecution(5000);

		FocusOnUrl("http://the-internet.herokuapp.com/abtest?optimizely_opt_out=true",10);
		PauseExecution(3000);


		ValidateElementText(Repo.heading,"No A/B Test");;
		PauseExecution(1000);
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Basic Authentication
	 * SL No: 2.1
	 * Objective: login  through passing user name and password in browser alert(with URL: https://username:password@<testUrl[Firefox and Chrome]) and passing data through Robot class(IE)
	 * Scenario: +Ve
	 * Note:  If browser supports "https" and "http" then try this both for URL approach 
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Basic_Auth(String devicename,String Browser) throws Exception {
		String Url;
		BrowserLaunchwithCapabilities(devicename);
		if (driver.toString().contains("chrome")) {
			Url	= Repo.StrtestUrl.replace("http://", "http://"+Repo.username+":"+Repo.password+"@")+"/basic_auth";
			FocusOnUrl(Repo.StrtestUrl,1);
		}else {
			Url= Repo.StrtestUrl;
			FocusOnUrl(Repo.StrtestUrl,1);
			element =driver.findElement(Repo.Basic_Auth_Link);
			element.click();
			Thread.sleep(1000);
			int[] Uname= {KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_M,KeyEvent.VK_I,KeyEvent.VK_N,KeyEvent.VK_TAB};
			int[] Pname= {KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_M,KeyEvent.VK_I,KeyEvent.VK_N,KeyEvent.VK_ENTER};
			TypeWithRobot(Uname);
			TypeWithRobot(Pname);
			PauseExecution(3000);
		}
		ValidateElementText(Repo.heading,"Basic Auth");
		WriteGetElementTextonNotePad(Repo.Description,new Object(){}.getClass().getEnclosingMethod().getName().toString(),"");
		PauseExecution(1000);
		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Basic Authentication -VE Validation
	 * SL No: 2.2
	 * Objective: login  through passing user name and password in browser alert(with URL: https://username:password@<testUrl[Firefox and Chrome]) and passing data through Robot class(IE)
	 * Scenario: -Ve
	 * Note:  If browser supports "https" and "http" then try this both for URL approach 
	 * Comment: this test case is pending for chrome Browser
	 * @throws Exception 
	 * @throws SecurityException 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Basic_Auth_Nagetive(String devicename,String Browser) throws SecurityException, Exception {
		String Url;
		BrowserLaunchwithCapabilities(devicename);
		if (driver.toString().contains("chrome")) {

			/*			
			Url	= Repo.StrtestUrl+"/basic_auth";

			driver.get(Url);
			PauseExecution(1000);
			int[] Pname= {KeyEvent.VK_TAB,KeyEvent.VK_TAB,KeyEvent.VK_TAB,KeyEvent.VK_CANCEL};
			TypeWithRobot(Pname);
			PauseExecution(3000);
			 */			
		}else {
			Url= Repo.StrtestUrl;
			FocusOnUrl(Repo.StrtestUrl,0);
			element =driver.findElement(Repo.Basic_Auth_Link);
			element.click();
			Thread.sleep(1000);
			int[] Pname= {KeyEvent.VK_CANCEL};
			TypeWithRobot(Pname);
			PauseExecution(3000);
		}
		WriteGetElementTextonNotePad(Repo.BodyDescription,new Object(){}.getClass().getEnclosingMethod().getName().toString(),"");
		PauseExecution(1000);
		BrowserClosing(devicename);
	}

	/**
	 * @throws Exception **********************************************************************************************************************************************************************************
	 * Test Case Name:  Broken Images with naturalWidth
	 * SL No: 3
	 * Objective:Checking Broken links
	 * Scenario: +Ve
	 * Note:   
	 * Comment:  if an image is broken, simply check if the naturalWidth of the element is 0.
	 * @throws 
	 **************************************************************************************************************************************************************************************/

	//@Test(dataProvider="BrowserProvider")
	public void BrokenImagesCheck(String devicename,String Browser) throws Exception {
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Broken_Images_Link);
		PauseExecution(1000);
		List <WebElement> Images = driver.findElements(Repo.Images);
		for(WebElement Image: Images ) {
			if (Image.getAttribute("naturalWidth").equals("0")) {
				System.out.println( "Image: "+Image.getAttribute("outerHTML") + " is broken.");
			}else {
				System.out.println( "Image: "+Image.getAttribute("outerHTML") + " is not broken.");
			}
		}
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Challenging DOM
	 * SL No: 4
	 * Objective:find the best locator, position button text and color
	 * Scenario: +Ve
	 * Note:   Canvas text automation is pending
	 * Comment:  after clicking any button, it is checking  very button position,color and name . Canvas element is also changing but right now I can not.
	 * @throws FindFailed 
	 * 
	 **************************************************************************************************************************************************************************************/

	//@Test(dataProvider="BrowserProvider")
	public void Challenging_DOM(String devicename,String Browser) throws IOException, InterruptedException {
		Point Point;
		String text;
		String color;
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Challenging_DOM_Link);

		HighlisghtThexpath(Repo.Challenging_DOM_button);
		System.out.println("****************************************************************************************************" );
		GetElementColorTextPositions(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_success);
		ClickObjectIfExists(Repo.Challenging_DOM_Link);
		HighlisghtThexpath(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_success);
		System.out.println("****************************************************************************************************" );
		ClickObjectIfExists(Repo.Challenging_DOM_Link);
		HighlisghtThexpath(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_success);
		ClickObject(Repo.Challenging_DOM_button_alert);
		ClickObjectIfExists(Repo.Challenging_DOM_Link);
		HighlisghtThexpath(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_success);
		System.out.println("****************************************************************************************************" );
		ClickObjectIfExists(Repo.Challenging_DOM_Link);
		HighlisghtThexpath(Repo.Challenging_DOM_button_success);
		GetElementColorTextPositions(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_success);
		ClickObject(Repo.Challenging_DOM_button_success);
		ClickObjectIfExists(Repo.Challenging_DOM_Link);
		HighlisghtThexpath(Repo.Challenging_DOM_button_success);
		GetElementColorTextPositions(Repo.Challenging_DOM_button);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_alert);
		GetElementColorTextPositions(Repo.Challenging_DOM_button_success);
		System.out.println("****************************************************************************************************" );
		ClickObjectIfExists(Repo.Challenging_DOM_Link);
		ScrollVieWObject(Repo.ElementalSelenium_Link);
		HighlisghtThexpath(Repo.Challenging_DOM_canvas);	
		SpecificscreenShotoFSpecficRegion(Repo.Challenging_DOM_canvas);
		System.out.println("****************************************************************************************************" );
		BrowserClosing(devicename);
	}


	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Check Boxes
	 * SL No: 5
	 * Objective:Checking check box is checked or not with 'checked' attribute
	 * Scenario: +Ve
	 * Note:   
	 * Comment:  
	 **************************************************************************************************************************************************************************************/
	//	//@Test(dataProvider="BrowserProvider")
	public void Checkboxes(String devicename,String Browser) throws IOException, InterruptedException {
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Checkboxes_Link);
		CheckCheckBoxStatusChecking(Repo.Checkboxes_Checkbox1);
		CheckCheckBox(Repo.Checkboxes_Checkbox1,"check");		
		CheckCheckBoxStatusChecking(Repo.Checkboxes_Checkbox2);
		CheckCheckBox(Repo.Checkboxes_Checkbox2,"uncheck");
		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Disappearing Elements
	 * SL No: 6
	 * Objective:This example demonstrates when elements on a page change by disappearing/reappearing on each page load. Checking ready state of the page and fluent wait
	 * Scenario: +Ve
	 * Note:   
	 * Comment:   all button is showing not found or 404
	 * @throws Exception 
	 * 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Disappearing_Elements(String devicename,String Browser) throws Exception {
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Disappearing_Elements_Link);
		VerifyElementExists(Repo.Disappearing_Elements_Gallery,"");
		ClickObject(Repo.Disappearing_Elements_Home);
		PageReadyStateCheck(2000);
		ClickObject(Repo.Disappearing_Elements_Link);
		PageReadyStateCheck(1000);
		VerifyElementExists(Repo.Disappearing_Elements_Gallery,"");
		ClickObject(Repo.Disappearing_Elements_About);
		driver.navigate().back();
		VerifyElementExists(Repo.Disappearing_Elements_Gallery,"");
		PageReadyStateCheck(1000);
		ClickObject(Repo.Disappearing_Elements_ContactUs);
		driver.navigate().back();
		PageReadyStateCheck(1000);
		VerifyElementExists(Repo.Disappearing_Elements_Gallery,"");
		ClickObject(Repo.Disappearing_Elements_Portfolio);
		driver.navigate().back();
		PageReadyStateCheck(1000);
		VerifyElementExists(Repo.Disappearing_Elements_Gallery,"");
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Drag and Drop
	 * SL No: 6
	 * Objective:Drag and Drop element and find the position.
	 * Scenario: +Ve
	 * Note:   pending
	 * Comment:   

	 * 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void DragandDrop(String devicename,String Browser) throws IOException, InterruptedException{
		System.out.println("Pending ...........................................................");
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Drag_and_Drop_Link);
		BrowserClosing(devicename);

	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:  Dropdown
	 * SL No: 7
	 * Objective:Dropdown.
	 * Scenario: +Ve
	 * Note:   
	 * Comment:   

	 * 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Dropdown(String devicename,String Browser) throws IOException, InterruptedException{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Dropdown_Link);
		ValidateElementText(Repo.heading,"Dropdown List");
		VerifyDropdownContains(Repo.select_dropdown,"Please select an option|Option 1|Option 2");
		VerifySelectedvalueonDropdown(Repo.select_dropdown,"Please select an option");
		SelectDropDown(Repo.select_dropdown,"Option 1");
		VerifySelectedvalueonDropdown(Repo.select_dropdown,"Option 1");
		SelectDropDown(Repo.select_dropdown,"Option 2");
		VerifySelectedvalueonDropdown(Repo.select_dropdown,"Option 2");
		BrowserClosing(devicename);

	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name: Dynamic Content
	 * SL No: 7
	 * Objective:Dynamic Content
	 * Scenario: +Ve
	 * Note:   
	 * Comment:   
	 * @throws Exception 
	 * @throws SecurityException 

	 * 
	 **************************************************************************************************************************************************************************************/
	//	//@Test(dataProvider="BrowserProvider")
	public void DynamicContent(String devicename,String Browser) throws SecurityException, Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.DynamicContent);
		ValidateElementText(Repo.heading,"Dynamic Content");
		WriteGetElementTextonNotePad(Repo.Description,new Object(){}.getClass().getEnclosingMethod().getName().toString(),"");
		System.out.println("**********************************************************************************************************************************************");
		elements= driver.findElements(Repo.DynamicContent_Images);
		for(WebElement ele: elements ) {
			System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src"));
			if (!ele.getAttribute("naturalWidth").equals("0")) {

				// System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src"));
				System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src") + "   is Not Broken.");

			}else {
				System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src") + "   is  Broken."); 
			}
		}

		//DynamicContent_Images_Description		 
		System.out.println("**********************************************************************************************************************************************");
		elements= driver.findElements(Repo.DynamicContent_Images_Description);
		for(WebElement ele: elements ) {
			System.out.println("DynamicContent>>>>>> "+ "Image Description" + ele.getText());
		}


		System.out.println("**********************************************************************************************************************************************");
		ClickObject(Repo.DynamicContent_clickhere);

		elements= driver.findElements(Repo.DynamicContent_Images);
		for(WebElement ele: elements ) {
			System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src"));
			if (!ele.getAttribute("naturalWidth").equals("0")) {

				// System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src"));
				System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src") + "   is Not Broken.");

			}else {
				System.out.println("DynamicContent>>>>>"+ ele.getAttribute("src") + "   is  Broken."); 

			}
		}
		System.out.println("**********************************************************************************************************************************************");
		elements= driver.findElements(Repo.DynamicContent_Images_Description);
		for(WebElement ele: elements ) {
			System.out.println("DynamicContent>>>>>> "+ "Image Description" + ele.getText());
		}

		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:Dynamic Controls
	 * SL No: 7
	 * Objective:Dynamic Content
	 * Scenario: +Ve
	 * Note:   
	 * Comment:   

	 * 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void DynamicControls(String devicename,String Browser) throws IOException, InterruptedException{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		WebDriverWait Wait= new WebDriverWait(driver,1000);
		ClickObject(Repo.Dynamic_Controls_Link);
		ClickObject(Repo.Dynamic_Controls_Remove);
	
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(Repo.Dynamic_loading));
		VerifyVisibilityCheck(Repo.Dynamic_loading,"");
		VerifyElementExists(Repo.Dynamic_Controls_Add,"exists");
		ValidateElementText(Repo.Dynamic_Controls_MSGcheckbox,"It's gone!");
		VerifyElementExists(Repo.Dynamic_Controls_checkbox,"");
		ClickObject(Repo.Dynamic_Controls_Add);
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(Repo.Dynamic_loading));
		VerifyVisibilityCheck(Repo.Dynamic_loading,"");
		VerifyElementExists(Repo.Dynamic_Controls_Remove,"exists");
		ValidateElementText(Repo.Dynamic_Controls_MSGcheckbox,"It's back!");
		VerifyElementExists(Repo.Dynamic_Controls_checkbox,"exists");
		System.out.println("**********************************************************************************************************************************************");
		driver.navigate().refresh();
		VerifyEnabilityCheck(Repo.Dynamic_Controls_textbox,"disable");
		ClickObject(Repo.Dynamic_Controls_Enable);
		WebDriverWait Wait1= new WebDriverWait(driver,200);
		Wait1.until(ExpectedConditions.invisibilityOfElementLocated(Repo.Dynamic_loading));
		ValidateElementText(Repo.Dynamic_Controls_MSGinput,"It's enabled!");
		VerifyEnabilityCheck(Repo.Dynamic_Controls_textbox,"enable");
		BrowserClosing(devicename);
	}


	/************************************************************************************************************************************************************************************
	 * Test Case Name:Dynamic Loading
	 * SL No: 8
	 * Objective:Dynamic Content
	 * Scenario: +Ve
	 * Note:   
	 * Comment:   
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/

	//@Test(dataProvider="BrowserProvider")
	public void DynamicLoading(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		WebDriverWait Wait= new WebDriverWait(driver,3000);
		System.out.println("**********************************************************************************************************************************************");
		System.out.println("**********************************************************************************************************************************************");
		ClickObject(Repo.DynamicLoading_Link);
		PauseExecution(1000);
		ClickObject(Repo.DynamicLoading_Example1_Link);
		ValidateElementText(Repo.DynamicLoading_heading, "Example 1: Element on page that is hidden");
		ClickObject(Repo.DynamicLoading_Examples_button);
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(Repo.Dynamic_loading));
		VerifyVisibilityCheck(Repo.Dynamic_loading,"");			
		ValidateElementText(Repo.DynamicLoading_Example1_Finish, "Hello World!");
		System.out.println("**********************************************************************************************************************************************");
		System.out.println("**********************************************************************************************************************************************");
		driver.navigate().back();
		driver.navigate().refresh();
		PauseExecution(1000);
		ClickObject(Repo.DynamicLoading_Example2_Link);
		ValidateElementText(Repo.DynamicLoading_heading, "Example 2: Element rendered after the fact");
		ClickObject(Repo.DynamicLoading_Examples_button);
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(Repo.Dynamic_loading));
		VerifyVisibilityCheck(Repo.Dynamic_loading,"");			
		ValidateElementText(Repo.DynamicLoading_Example1_Finish, "Hello World!");
		System.out.println("**********************************************************************************************************************************************");
		System.out.println("**********************************************************************************************************************************************");
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Exit Intent>> Modal Window
	 * SL No: 9
	 * Objective:controlling Modal windows with hovering mouse
	 * Scenario: +Ve
	 * Note:    It is not alert. it has xpath
	 * Comment:   
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/

	//@Test(dataProvider="BrowserProvider")
	public void ExitIntent_ModalWindow(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.ExitIntent_Link);
		//PauseExecution(3000);
		PageReadyStateCheck(2000);
		// Thread.sleep(6000);
		element=WaitforElementReturn(Repo.ElementalSelenium_Link, 2000);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		action.release();
		Thread.sleep(2000);
		//element = driver.findElement(Repo.ExitIntent_modalWindow_Title);
		element =WaitforElementReturn(Repo.ExitIntent_modalWindow_Title, 3000);
		Thread.sleep(2000);
		System.out.println("Modal Window Title : ' "+element.getText().toString().toUpperCase()+"'");
		element=WaitforElementReturn(Repo.ExitIntent_modalWindow_Body, 2000);
		System.out.println("Modal Window Body is present with  : ' "+element.getText().toString().toUpperCase()+"'");
		ClickObject(Repo.ExitIntent_modalWindow_close);
		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:FileDownload
	 * SL No: 9
	 * Objective: care that a file can be downloaded 
	 * Scenario: +Ve
	 * Note:    for downloading it needs some special type of capability setting visit >>>>> http://www.seleniumeasy.com/selenium-tutorials/how-to-download-a-file-with-webdriver
	 * Comment:profile.setPreference("browser.download.dir", downloadPath);  
	 **************************************************************************************************************************************************************************************/

	//@Test(dataProvider="BrowserProvider")
	@SuppressWarnings("unused")
	public void FileDownload(String devicename,String Browser) throws IOException, InterruptedException{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.File_Download_Link);
		elements = driver.findElements(Repo.File_Download_DownloadableFiles);
		System.out.println(" Total Files no: " + elements.size() );
		for ( WebElement ele : elements) {
			System.out.println("File Name is : " + ele.getText()); 

			String link = ele.getAttribute("href");
			;
			System.out.println("**********************Cheking File Is downloadable or not !********************************");


			try {
				System.out.println("Creating Http Client >>>>>>  HttpClient httpclient = HttpClientBuilder.create().build() ");
				HttpClient httpclient = HttpClientBuilder.create().build();
				System.out.println("Creating HttpHead request >>>>>> HttpHead request = new HttpHead(link)");
				HttpHead request = new HttpHead(link);
				System.out.println("Creating HttpResponse response >>>>>> org.apache.http.HttpResponse response = httpclient.execute(request)");
				org.apache.http.HttpResponse response = httpclient.execute(request);
				String contentType = response.getFirstHeader("Content-Type").getValue();
				int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());
				System.out.println("contentType :" + contentType);
				System.out.println("contentLength :" + contentLength);
				if ((contentType.equals("application/octet-stream")) && (contentLength>0)) {
					System.out.println (ele.getText() + " Named file is downloadble");
					//ele.click();
					PauseExecution(1000);
					System.out.println("********************************************************************************************************");
				}else {
					System.out.println (ele.getText() + " Named file is not downloadble");
					System.out.println("********************************************************************************************************");
				}
			}catch(Exception e) {
				System.out.println (e.toString());
				BrowserClosing(devicename);
			}

		}
		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:FileUpload
	 * SL No: 9
	 * Objective: Upload file 
	 * Scenario: +Ve
	 * Note: visit >>>>> https://stackoverflow.com/questions/16897253/how-to-upload-file-with-selenium-in-java?noredirect=1&lq=1
	 * Comment:
	 **************************************************************************************************************************************************************************************/

	//@Test(dataProvider="BrowserProvider")
	@SuppressWarnings("unused")
	public void FileUpload(String devicename,String Browser) throws IOException, InterruptedException{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.FileUploadLink);
		System.out.println("**********************************************File Upload**********************************************************");
		try {
			element=driver.findElement(Repo.browseLink);
			element.sendKeys("C:\\Users\\IBM_ADMIN\\Desktop\\Nucleya - Bass Rani\\cover1.jpg");
			ClickObject(Repo.uploadLink);

			ValidateElementText(Repo.heading,"File Uploaded!");

		}catch(Exception ex) {
			System.out.println(ex.toString());

		}
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Floating Menu
	 * SL No: 9
	 * Objective: Checking after scrolling floating objects are present or not.
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void FloatingMenu(String devicename,String Browser) throws IOException, InterruptedException{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.FloatingMenu);
		System.out.println("**********************************************Floating Menu**********************************************************");
		System.out.println("..... Scrolling button of the page......");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println("..... Scrolling end......");
		System.out.println("..... checking Visibilty and existance of floating menus......");
		System.out.println("********************************************************************************************************");
		element = WaitforElementReturn(Repo.FloatingMenu_Home,3000);
		if (element!=null) {
			System.out.println(Repo.FloatingMenu_Home.toString().replace("By.xpath:", "")+ "  is present");
			VerifyVisibilityCheck(Repo.FloatingMenu_Home,"");
		}else {
			System.out.println(Repo.FloatingMenu_Home.toString().replace("By.xpath:", "")+ "  is not present");	
		}

		System.out.println("********************************************************************************************************");

		element = WaitforElementReturn(Repo.FloatingMenu_News,3000);


		if (element!=null) {
			System.out.println(Repo.FloatingMenu_News.toString().replace("By.xpath:", "")+ "  is present");
			VerifyVisibilityCheck(Repo.FloatingMenu_News,"");
		}else {
			System.out.println(Repo.FloatingMenu_News.toString().replace("By.xpath:", "")+ "  is not present");	
		}

		System.out.println("********************************************************************************************************");

		element = WaitforElementReturn(Repo.FloatingMenu_Contact,3000);


		if (element!=null) {
			System.out.println(Repo.FloatingMenu_Contact.toString().replace("By.xpath:", "")+ "  is present");
			VerifyVisibilityCheck(Repo.FloatingMenu_Contact,"");
		}else {
			System.out.println(Repo.FloatingMenu_Contact.toString().replace("By.xpath:", "")+ "  is not present");	
		}
		System.out.println("********************************************************************************************************");	
		element = WaitforElementReturn(Repo.FloatingMenu_About,3000);


		if (element!=null) {
			System.out.println(Repo.FloatingMenu_About.toString().replace("By.xpath:", "")+ "  is present");
			VerifyVisibilityCheck(Repo.FloatingMenu_About,"");
		}else {
			System.out.println(Repo.FloatingMenu_About.toString().replace("By.xpath:", "")+ "  is not present");	
		}
		BrowserClosing(devicename);
	}


	/************************************************************************************************************************************************************************************
	 * Test Case Name:Forgot Password
	 * SL No: 9
	 * Objective: Checking after scrolling floating objects are present or not.
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void ForgotPassword(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.ForgotPassword);
		ValidateElementText(Repo.ForgotPasswordheading, "Forgot Password");
		SetOnparam(Repo.ForgotPasswordEmail,"abC@gmail.com");
		ClickObject(Repo.ForgotPasswordButton);
		PageReadyStateCheck(3000);
		ValidateElementText(Repo.ForgotPasswordContent,"Your e-mail's been sent!");
		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:Form Authentication
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void FormAuthentication(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.FormAuthenticationLink);
		System.out.println("------------------------------------------------Test Case 1--------------------------------------------------");
		ValidateElementText(Repo.FormAuthenticationheading, "Login Page");
		HighlisghtThexpath(Repo.FormAuthentication_username);
		HighlisghtThexpath(Repo.FormAuthentication_password);
		HighlisghtThexpath(Repo.FormAuthentication_Login);
		SetOnparam(Repo.FormAuthentication_username,"tomsmith");
		SetOnparam(Repo.FormAuthentication_password,"SuperSecretPassword!");
		ClickObject(Repo.FormAuthentication_Login);
		PageReadyStateCheck(1000);
		ValidateElementText(Repo.FormAuthenticationheading,"Secure Area");
		ValidateElementText(Repo.FormAuthentication_Msg,"Welcome to the Secure Area. When you are done click logout below.");
		ValidateElementText(Repo.FormAuthentication_MsgFlash,"You logged into a secure area!");
		GetAttributevalue(Repo.FormAuthentication_MsgFlash,"class");
		GetElementColorTextPositions(Repo.FormAuthentication_MsgFlash);
		HighlisghtThexpath(Repo.FormAuthentication_MsgFlash);
		HighlisghtThexpath(Repo.FormAuthentication_LogOut);
		ClickObject(Repo.FormAuthentication_LogOut);
		ValidateElementText(Repo.FormAuthentication_MsgFlash,"You logged out of the secure area!");
		GetElementColorTextPositions(Repo.FormAuthentication_MsgFlash);
		GetAttributevalue(Repo.FormAuthentication_MsgFlash,"class");
		HighlisghtThexpath(Repo.FormAuthentication_MsgFlash);
		System.out.println("------------------------------------------------Test Case 2--------------------------------------------------");
		ValidateElementText(Repo.FormAuthenticationheading, "Login Page");
		SetOnparam(Repo.FormAuthentication_username,"tomsmith");
		SetOnparam(Repo.FormAuthentication_password,"SuperSecretPasswor");
		ClickObject(Repo.FormAuthentication_Login);
		PauseExecution(1000);
		ValidateElementText(Repo.FormAuthentication_MsgFlash,"Your password is invalid!");
		GetAttributevalue(Repo.FormAuthentication_MsgFlash,"class");
		GetElementColorTextPositions(Repo.FormAuthentication_MsgFlash);
		GetElementColorTextPositions(Repo.FormAuthentication_MsgFlash);
		HighlisghtThexpath(Repo.FormAuthentication_MsgFlash);
		System.out.println("------------------------------------------------Test Case 3--------------------------------------------------");
		ValidateElementText(Repo.FormAuthenticationheading, "Login Page");
		SetOnparam(Repo.FormAuthentication_username,"tomsmit");
		SetOnparam(Repo.FormAuthentication_password,"SuperSecretPassword!");
		ClickObject(Repo.FormAuthentication_Login);
		ValidateElementText(Repo.FormAuthentication_MsgFlash,"Your username is invalid!");
		GetAttributevalue(Repo.FormAuthentication_MsgFlash,"class");
		GetElementColorTextPositions(Repo.FormAuthentication_MsgFlash);
		HighlisghtThexpath(Repo.FormAuthentication_MsgFlash);
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Frames
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Frames(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.FramesLink);
		ClickObject(Repo.Nested_FramesLink);
		PauseExecution(1000);
		System.out.println("***************************** Nested Frames ************************************************************");
		elements = driver.findElements(Repo.Nested_Frames_Nested_Frame);
		System.out.println("*****************************Move first Frame************************************************************");
		System.out.println("Move first Frame>>>>>>>>>> '" + elements.get(0).getAttribute("name").toString().toUpperCase()+"'");
		driver.switchTo().defaultContent();
		String Framename = elements.get(0).getAttribute("name");

		driver.switchTo().frame(elements.get(0).getAttribute("name"));
		elements=null;
		elements = driver.findElements(Repo.Nested_Frames_Nested_Frame);
		for(WebElement ele : elements ) {
			System.out.println("Frame Name which is driver is switching >>>>>>>>>> '" + ele.getAttribute("name").toString().toUpperCase()+"'");
			driver.switchTo().frame(ele.getAttribute("name"));
			element = WaitforElementReturn(By.xpath("//body"), 3000);
			if (element!=null) {
				System.out.println(" frame Contains:  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+element.getText());	
			}else {
				element = WaitforElementReturn(Repo.Nested_Frames_Contents, 3000);
				System.out.println(" frame Contains: >>>>>>>>>>>>>>>>>>>>>>>> "+element.getText());	
			}
			driver.switchTo().defaultContent();	
			driver.switchTo().frame(Framename);
		}
		driver.navigate().back();
		ClickObject(Repo.Nested_FramesLink);
		PauseExecution(1000);
		elements = driver.findElements(Repo.Nested_Frames_Nested_Frame);
		System.out.println("*****************************Move Second Frame************************************************************");
		System.out.println("Move first Frame>>>>>>>>>> '" + elements.get(1).getAttribute("name").toString().toUpperCase()+"'");		
		driver.switchTo().defaultContent();		
		driver.switchTo().frame(elements.get(1).getAttribute("name"));
		element = WaitforElementReturn(By.xpath("//body"), 3000);
		if (element!=null) {
			System.out.println(" frame Contains:  "+element.getText());	
		}
		System.out.println("***************************** Nested Frames  END************************************************************");
		driver.navigate().back();

		System.out.println("*****************************frame starts************************************************************");
		ClickObject(Repo.iFramesLink);
		PauseExecution(1000);
		elements = driver.findElements(By.xpath("//iframe"));
		System.out.println("*****************************iframee************************************************************");
		driver.switchTo().defaultContent();
		for(WebElement ele : elements) {
			System.out.println("Move first Frame>>>>>>>>>> '" + ele.getAttribute("frameborder").toString().toUpperCase()+"'");
			driver.switchTo().frame(Integer.parseInt(ele.getAttribute("frameborder")));
		}
		SetOnparam(Repo.iframeTextarea, "Text messaging, or texting, is the act of composing and sending electronic messages, typically consisting of alphabetic and numeric characters, between two or more users of mobile devices, desktops/laptops, or other type of compatible computer. Text messages may be sent over a cellular network, or may also be sent via an Internet connection.");
		BrowserClosing(devicename);

	}	

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Geolocation
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Geolocation(String devicename,String Browser) throws Exception{
		System.out.println("***************************** InterNet Explorer Text Cases>>> It always opens IE************************************************************");
		devicename="IE";
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.GeolocationLink);
		ClickObject(Repo.GeolocationButton);
		element= WaitforElementReturn(Repo. Geolocationlat_value, 4000);
		System.out.println("Latitude: "+ element.getText());
		element= null;
		element= WaitforElementReturn(Repo. Geolocationlong_value, 4000);
		System.out.println("Longitude: "+ element.getText());
		ClickObject(Repo.GoggleLink);
		PauseExecution(2000);
		SpecificscreenShotoFSpecficRegion(By.xpath("//canvas[@class='widget-scene-canvas']"));
		PauseExecution(2000);
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Horizontal Slider
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void HorizontalSlider(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.HorizontalSliderLink);
		PauseExecution(2000);
		try {
			element = WaitforElementReturn(Repo.HorizontalSlider, 4000);
			Actions move = new Actions(driver);
			int height = element.getSize().getHeight();
			int width = element.getSize().getWidth();
			if(width>height){
				move.clickAndHold(element).moveByOffset(5,0).release().build().perform();
				System.out.println(" Element is horizonlay moved");
			}
			BrowserClosing(devicename);		
		}catch(Exception ex) {
			BrowserClosing(devicename);	
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Hovers
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Hovers(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Hovers);
		WebElement subElement;
		PauseExecution(2000);
		try {
			Actions action = new Actions(driver);
			element =WaitforElementReturn(Repo.ImageHovers1,3000);
			action.moveToElement(element).perform();
			subElement = driver.findElement(By.linkText("View profile"));
			action.moveToElement(subElement);
			action.click();
			action.perform();
			PauseExecution(5000);
			driver.navigate().back();
			element =WaitforElementReturn(Repo.ImageHovers2,3000);
			action.moveToElement(element).perform();

			subElement = driver.findElement(By.linkText("View profile"));
			action.moveToElement(subElement);
			action.click();
			action.perform(); 
			PauseExecution(5000);
			driver.navigate().back();
			element =WaitforElementReturn(Repo.ImageHovers3,3000);
			action.moveToElement(element).perform();

			subElement = driver.findElement(By.linkText("View profile"));
			action.moveToElement(subElement);
			action.click();
			action.perform(); 
			PauseExecution(5000);
			BrowserClosing(devicename);	
		}catch(Exception ex) {
			BrowserClosing(devicename);	
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Infinite Scroll
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Infinite_Scroll(String devicename,String Browser) throws IOException, InterruptedException{

		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		try {
			ClickObject(Repo.InfiniteScroll);
			PauseExecution(5000);
			System.out.println("..... Scrolling button of the page......");
			int scroll_count=0;
			do {
				ScrollVieWObject(Repo.Infinitefooter);
				PauseExecution(100);
				scroll_count++;
			} while(scroll_count<20);
			BrowserClosing(devicename);	
		}catch(Exception ex) {
			BrowserClosing(devicename);	
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}


	/************************************************************************************************************************************************************************************
	 * Test Case Name:JQueryUIMenus
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void JQueryUIMenus(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		PauseExecution(3000);
		ClickObject(Repo.JQueryUIMenus);
		//VerifyEnabilityCheck(Repo.enablelink,"");
		Actions Action = new Actions(driver);
		element=null;
		element =driver.findElement(Repo.enablelink);
		/*
		String jquery1 = "jQuery(\""+ element+"\").eq(0).mouseover();";
		JavascriptExecutor	js = (JavascriptExecutor) driver;
		js.executeScript(jquery1);
		 */
		Action.moveToElement(element);
		Action.click().build().perform();
		element =driver.findElement(Repo.Downloadlink);
		Action.moveToElement(element);
		Action.click().build().perform();
		element =driver.findElement(Repo.Downloadlink_PDF);
		Action.moveToElement(element);
		Action.click().build().perform();
		element =driver.findElement(Repo.jequery);
		Action.moveToElement(element);
		Action.click().build().perform();
		count=5;
		int i=0;
		int alertx=0;
		while(i++<count){
			try
			{
				Alert alert = driver.switchTo().alert();
				alert.accept();
				System.out.println("Alert  is accept successfully"); 
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println(" No Alert is found till now.. giving Another change to appear !!!!!!!!!!"); 
				alertx=i;
				Thread.sleep(1000);

				continue;
			}
		}

		ScreenShot();
		BrowserClosing(devicename);	

	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:JavaScript Alerts
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void JavaScriptAlerts(String devicename,String Browser) throws Exception{
		int i=0;
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.JavaScriptAlerts);
		System.out.println("Clicking 1st Js alert>>>>>>>>>>>>>>>>>>>>>>>>>");
		element =WaitforElementReturn(Repo.JSAlert, 3000);
		//driver.findElement(Repo.JSAlert);
		PauseExecution(1000);
		element.click();
		while(i++<5){
			try
			{
				Alert alert = driver.switchTo().alert();
				String alerttext = alert.getText().toUpperCase();
				PauseExecution(0);
				alert.accept();
				System.out.println("Alert : "+alerttext+"  is accept successfully"); 
				Assert.assertTrue(true,"Alert : "+alerttext+"  is accept successfully");
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println(" No Alert is found till now.. giving Another change to appear !!!!!!!!!!"); 
				Thread.sleep(1000);
				continue;
			}
		}
		System.out.println("Clicking 1st Js alert>>>>>>>>>>>>>>>>:   Result   :>>>>>>>>>");		
		GetAttributevalue(Repo.jsresult,"text");
		System.out.println("Clicking 2nd Js alert>>>>>>>>>>>> Dismising>>>>>>>>>>>>>");
		element =WaitforElementReturn(Repo.JSAlertConfirm,3000);
		element.click();
		while(i++<5){
			try
			{
				Alert alert = driver.switchTo().alert();
				String alerttext = alert.getText().toUpperCase();
				PauseExecution(0);
				alert.dismiss();
				System.out.println("Alert : "+alerttext+"  is  dismissed succesfully");
				Assert.assertTrue(true,"Alert : "+alerttext+"  is dismissed successfully");
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println(" No Alert is found till now.. giving Another change to appear !!!!!!!!!!"); 
				Thread.sleep(1000);
				continue;
			}
		}
		System.out.println("Clicking 2nd  Js alert>>>>>>>>>>>>>>>>:   Result   :>>>>>>>>>");		
		GetAttributevalue(Repo.jsresult,"text");
		System.out.println("Clicking 3rd Js alert>>>>>>>>>>>> typing>>>>>>>>>>>>>");
		element = WaitforElementReturn(Repo.JSAlertpromt,3000);
		element.click();
		while(i++<5){
			try
			{
				Alert alert = driver.switchTo().alert();
				String alerttext = alert.getText().toUpperCase();
				alert.sendKeys("How to Handle Alerts, JavaScript Alerts");
				PauseExecution(0);
				alert.accept();
				System.out.println("Alert : "+alerttext+"  is  accept succesfully");
				Assert.assertTrue(true,"Alert : "+alerttext+"  is  accept succesfully");
				break;
			}
			catch(NoAlertPresentException e)
			{
				System.out.println(" No Alert is found till now.. giving Another change to appear !!!!!!!!!!"); 
				Thread.sleep(1000);
				continue;
			}
		}

		System.out.println("Clicking 3rd  Js alert>>>>>>>>>>>>>>>>:   Result   :>>>>>>>>>");		
		GetAttributevalue(Repo.jsresult,"text");
		BrowserClosing(devicename);	
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:JavaScript onload event error
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws AWTException 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void JavaScriptonloadeventerror(String devicename,String Browser) throws IOException, InterruptedException, AWTException{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.JavaScript_onloadeventerror);
		GetAttributevalue(By.xpath("//p"),"text");
		ScreenShot();
		BrowserClosing(devicename);
	}


	/************************************************************************************************************************************************************************************
	 * Test Case Name:Key Presses
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//	//@Test(dataProvider="BrowserProvider")
	public void KeyPresses(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.KeyPresses);
		int[] Uname= {KeyEvent.VK_A};
		TypeWithRobot(Uname);
		GetAttributevalue(By.xpath("//p[@id='result']"),"text");	 
		BrowserClosing(devicename);
	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:Large & Deep DOM
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 * @throws SecurityException 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Large_DeepDOM(String devicename,String Browser) throws SecurityException, Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.Large_DeepDOM);

		element = driver.findElement(Repo.Large_DeepDOM_table);
		System.out.println(element.getText());
		WriteGetElementTextonNotePad(Repo.Large_DeepDOM_table,new Object(){}.getClass().getEnclosingMethod().getName().toString(),"");
		elements = driver.findElements(Repo.Large_DeepDOM_Sibling);
		System.out.println(elements.size());
		WriteGetElementTextonNotePad(Repo.Large_DeepDOM_Sibling,new Object(){}.getClass().getEnclosingMethod().getName().toString(),"list");
		BrowserClosing(devicename);
	}



	/************************************************************************************************************************************************************************************
	 * Test Case Name:Multiple Windows
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void MultipleWindows(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.MultipleWindows_Link);
		PauseExecution(1000);
		element = driver.findElement(Repo.NewWindow);
		element.click();
		PauseExecution(3000);
		String parentwin = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for(String win: windows) {
			if (!win.equalsIgnoreCase(parentwin)) {
				driver.switchTo().window(win);
				PauseExecution(1000);
				driver.manage().window().maximize();
				((JavascriptExecutor) driver).executeScript("window.focus();");
				PauseExecution(1000);
				element = driver.findElement(Repo.heading);
				System.out.println("Elemnet text is :    "+element.getText());
				ScreenShot();
			}
		}
		try {
			parentwin="";
			windows.clear();
			parentwin = driver.getWindowHandle();
			windows = driver.getWindowHandles();
			System.out.println(windows.size());
			Iterator<String>li = windows.iterator();

			while (li.hasNext()) {
				String Childwin= li.next();


				if (!parentwin.equalsIgnoreCase(Childwin)) {
					driver.close();
					driver.switchTo().window(Childwin);
					driver.manage().window().maximize();
					((JavascriptExecutor) driver).executeScript("window.focus();");
					element = driver.findElement(Repo.heading);
					System.out.println("Elemnet text is :    "+element.getText());
					break;
				}
			}
		}catch(Exception ex) {}
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Nested Frames
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void NestedFrames(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.NestedFrames);
		PauseExecution(1000);
		System.out.println("***************************** Nested Frames ************************************************************");
		elements = driver.findElements(Repo.Nested_Frames_Nested_Frame);
		System.out.println("*****************************Move first Frame************************************************************");
		System.out.println("Move first Frame>>>>>>>>>> '" + elements.get(0).getAttribute("name").toString().toUpperCase()+"'");
		driver.switchTo().defaultContent();
		String Framename = elements.get(0).getAttribute("name");

		driver.switchTo().frame(elements.get(0).getAttribute("name"));
		elements=null;
		elements = driver.findElements(Repo.Nested_Frames_Nested_Frame);
		for(WebElement ele : elements ) {
			System.out.println("Frame Name which is driver is switching >>>>>>>>>> '" + ele.getAttribute("name").toString().toUpperCase()+"'");
			driver.switchTo().frame(ele.getAttribute("name"));
			element = WaitforElementReturn(By.xpath("//body"), 3000);
			if (element!=null) {
				System.out.println(" frame Contains:  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+element.getText());	
			}else {
				element = WaitforElementReturn(Repo.Nested_Frames_Contents, 3000);
				System.out.println(" frame Contains: >>>>>>>>>>>>>>>>>>>>>>>> "+element.getText());	
			}
			driver.switchTo().defaultContent();	
			driver.switchTo().frame(Framename);
		}
		driver.navigate().back();
		ClickObject(Repo.Nested_FramesLink);
		PauseExecution(1000);
		elements = driver.findElements(Repo.Nested_Frames_Nested_Frame);
		System.out.println("*****************************Move Second Frame************************************************************");
		System.out.println("Move first Frame>>>>>>>>>> '" + elements.get(1).getAttribute("name").toString().toUpperCase()+"'");		
		driver.switchTo().defaultContent();		
		driver.switchTo().frame(elements.get(1).getAttribute("name"));
		element = WaitforElementReturn(By.xpath("//body"), 3000);
		if (element!=null) {
			System.out.println(" frame Contains:  "+element.getText());	
		}
		System.out.println("***************************** Nested Frames  END************************************************************");
		driver.navigate().back();
		BrowserClosing(devicename);
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Notification Messages
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:driver.get(driver.getCurrentUrl());  is used for refreshing the element to avoid stale element for ajax page
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void NotificationMessages(String devicename,String Browser) throws Exception{
		BrowserLaunchwithCapabilities(devicename);
		FocusOnUrl(Repo.StrtestUrl,0);
		ClickObject(Repo.NotificationMessages);
		//PauseExecution(1000);
		for( int i=0;i<3;i++) {
			System.out.println("***************************************************************************************** count " + i);
			GetAttributevalue(Repo.NotificationMessagesFlag,"text");
			ClickObjectwithRefresh(Repo.NotificationMessagesClickhere);
			PauseExecution(1000);
		}
	}

	/************************************************************************************************************************************************************************************
	 * Test Case Name:Redirection
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:driver.get(driver.getCurrentUrl());  is used for refreshing the element to avoid stale element for ajax page
	 * @throws AWTException 
	 **************************************************************************************************************************************************************************************/
	//@Test(dataProvider="BrowserProvider")
	public void Redirection(String devicename,String Browser) throws IOException, InterruptedException, AWTException{
		BrowserLaunchwithCapabilities(devicename);
		proxyserver.newHar("Status Codes");

		FocusOnUrl("http://seleniumeasy.com",0);
		//ClickObject(Repo.Redirection);
		//ClickObject(Repo.RedirectionClickhere);
		//ClickObject(Repo.Status_200);

		Har har = proxyserver.getHar();


		String harFilePath = System.getProperty("user.dir") + "/hars/ww.har";
		File harFile = new File(harFilePath);
		try {
			har.writeTo(harFile);
		} catch (IOException ex) {
			System.out.println (ex.toString());
			System.out.println("Could not find file " + harFilePath);
		}


		List<HarEntry> entries = har.getLog().getEntries();
		for (HarEntry entry : entries) {
			System.out.println("Request URL: " + entry.getRequest().getUrl());
			System.out.println("Entry response status: " + entry.getResponse().getStatus());
			System.out.println("Entry response text: " + entry.getResponse().getStatusText());

		}

	}
	/************************************************************************************************************************************************************************************
	 * Test Case Name:Secure File Download
	 * SL No: 9
	 * Objective: 
	 * Scenario: +Ve
	 * Note: 
	 * Comment:driver.get(driver.getCurrentUrl());  is used for refreshing the element to avoid stale element for ajax page
	 * @throws Exception 
	 **************************************************************************************************************************************************************************************/
	@Test(dataProvider="BrowserProvider")
	public void SecureFileDownload(String devicename,String Browser) throws Exception{
		String Url;
		BrowserLaunchwithCapabilities(devicename);
		if (driver.toString().contains("chrome")) {
			System.out.println(Repo.username);
			Url	= Repo.StrtestUrl.replace("http://", "http://"+Repo.username+":"+Repo.password+"@")+"/download_secure";
			FocusOnUrl(Url,1);
		}else {
			Url= Repo.StrtestUrl;
			FocusOnUrl(Repo.StrtestUrl,1);
			element =driver.findElement(Repo.SecureFileDownload_Link);
			element.click();
			Thread.sleep(1000);
			int[] Uname= {KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_M,KeyEvent.VK_I,KeyEvent.VK_N,KeyEvent.VK_TAB};
			int[] Pname= {KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_M,KeyEvent.VK_I,KeyEvent.VK_N,KeyEvent.VK_ENTER};
			TypeWithRobot(Uname);
			TypeWithRobot(Pname);
			PauseExecution(3000);
		}
		elements = driver.findElements(Repo.File_Download_DownloadableFiles);
		System.out.println(" Total Files no: " + elements.size() );
		for ( WebElement ele : elements) {
			System.out.println("File Name is : " + ele.getText()); 

			String link = ele.getAttribute("href");
			;
			System.out.println("**********************Cheking File Is downloadable or not !********************************");


			try {
				System.out.println("Creating Http Client >>>>>>  HttpClient httpclient = HttpClientBuilder.create().build() ");
				HttpClient httpclient = HttpClientBuilder.create().build();
				System.out.println("Creating HttpHead request >>>>>> HttpHead request = new HttpHead(link)");
				HttpHead request = new HttpHead(link);
				System.out.println("Creating HttpResponse response >>>>>> org.apache.http.HttpResponse response = httpclient.execute(request)");
				org.apache.http.HttpResponse response = httpclient.execute(request);
				String contentType = response.getFirstHeader("Content-Type").getValue();
				int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());
				System.out.println("contentType :" + contentType);
				System.out.println("contentLength :" + contentLength);
				if ((contentType.equals("application/octet-stream")) && (contentLength>0)) {
					System.out.println (ele.getText() + " Named file is downloadble");
					//ele.click();
					PauseExecution(1000);
					System.out.println("********************************************************************************************************");
				}else {
					System.out.println (ele.getText() + " Named file is not downloadble");
					System.out.println("********************************************************************************************************");
				}
			}catch(Exception e) {
				System.out.println (e.toString());
				BrowserClosing(devicename);
			}

		}
		BrowserClosing(devicename);

	}
}










