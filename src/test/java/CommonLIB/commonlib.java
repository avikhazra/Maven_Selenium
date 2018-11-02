package CommonLIB;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Function;
import com.google.common.io.FileWriteMode;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.Object;
import org.openqa.selenium.support.ui.*;

@SuppressWarnings("unused")
public class commonlib extends InitialSetUp {
	public Robot robot;
	public KeyEvent KeyEvent;
	public Point Point;
	public String color;
	public boolean checkCondition;
	public List<WebElement> elements;
	public String textreturn;
	public Actions Act;
	public String[] publicArray;
	public HashMap<String,String> GenHashmap;
	public org.testng.asserts.SoftAssert softAssert= new SoftAssert();
	public String  FunctionName;
	public WebDriverWait wait;

	/************************************************************************************************************************************************************************
	 *  FunctionName: ClickObjectwithRefresh
	 * Argument : Xpath
	 ************************************************************************************************************************************************************************/
	public  void  ClickObjectwithRefresh(By strxpath) {

		((JavascriptExecutor) driver).executeScript("window.focus();");
		String AfterDomproperty ;
		String previuosDomproperty;
		element=null;
		int i=0;
		//driver.findElement(strxpath);
		try {
			previuosDomproperty = driver.getPageSource().toString();
			System.out.println("driver.get(driver.getCurrentUrl());  is used for refreshing the element to avoid stale element");
			driver.get(driver.getCurrentUrl());
			element= WaitforElementReturnForClick(strxpath, 1000);
			FunctionName="ClickObject>>>>>> ";
			element.click();
			AfterDomproperty= driver.getPageSource().toString();
			if (!previuosDomproperty.equals(AfterDomproperty)) {
				System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is successfully clicked");
				Assert.assertTrue(true,strxpath + "is successfully clicked");
			}else {
				driver.get(driver.getCurrentUrl());
				while(previuosDomproperty.equals(AfterDomproperty)){
					element= WaitforElementReturnForClick(strxpath, 1000);
					element.click();
					AfterDomproperty="";
					Thread.sleep(2000);
					AfterDomproperty = driver.getPageSource().toString();
					if (i==3) {
						break;
					}
					i++;
				}

				if (!previuosDomproperty.equals(AfterDomproperty)) {
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is successfully clicked");
					Assert.assertTrue(true, FunctionName+strxpath + "is clicked successfully");
				}else {
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is  not clicked");
					Assert.assertTrue(false,FunctionName+strxpath + "is  not clicked successfully");
				}
			}

		}catch(Exception ex) {

			System.out.println(FunctionName+"    :>>>>>>"+strxpath.toString().replace("By.xpath:", "") +"   Exception is "+ ex.toString() + " for causing :" + ex.getMessage());

			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}
	/***************************************************************************************************************************************************************************
	 * FunctionName: ValidateElementText
	 * Argument : Xpath,TestData
	 * 
	 **************************************************************************************************************************************************************************/
	public void ValidateElementText(By strxpath, String strtestdata) {
		FunctionName="ValidateElementText>>>>>> ";
		element=null;
		element= driver.findElement(strxpath);
		try {
			if (element!=null) {
				String elemnettext = element.getText().toString();

				if (elemnettext.equals("")){
					elemnettext=  element.getAttribute("value");
				}
				if (!strtestdata.equals("")) {
					if (elemnettext.toUpperCase().contains(strtestdata.toUpperCase())) {
						System.out.println(FunctionName+"'"+strtestdata.toUpperCase() + "' is present on specific " + strxpath.toString());
						Assert.assertTrue(true,strtestdata.toUpperCase() + "is present on specific " + strxpath.toString());	
					}else {
						System.out.println(FunctionName+"'"+strtestdata + " ' is  not present on specific " + strxpath.toString());
						Assert.assertTrue(false,strtestdata + " is not present on specific " + strxpath.toString());	
					}

				}else {
					System.out.println(FunctionName +strtestdata + " can not be null" + strxpath.toString());

					Assert.assertTrue(false,strtestdata + "can not be null" + strxpath.toString());
				}
			}else {
				System.out.println(FunctionName+strtestdata +"Element Not found"  + strxpath.toString());
				Assert.assertTrue(false,strtestdata +"Element Not found"  + strxpath.toString());	
			}
		}catch(Exception ex) {
			System.out.println(FunctionName+"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}
	/********************************************************************************************************************************************************************************
	 *  FunctionName: WriteGetElementTextonNotePad
	 * Argument : Xpath,TestData
	 * @throws IOException 
	 *********************************************************************************************************************************************************************************/
	public void WriteGetElementTextonNotePad(By strxpath ,String Methodname, String listType ) throws IOException {
		//By strxpath
		element=null;
		elements = null;
		String elemnettext=null;
		Date dnow = new Date();
		SimpleDateFormat dateF= new SimpleDateFormat(" dd_mm_yyyy_hh_mm_ss_SS");
		String Filename =Methodname+"_"+ dateF.format(dnow);
		System.out.println(System.getProperty("user.dir"));
		File Notepad = new File (System.getProperty("user.dir")+"//NotePad//"+Filename+".txt");
		BufferedWriter NotepadWriter = new BufferedWriter(new FileWriter(Notepad));
		NotepadWriter.write("****************************************************************************************************" );
		try {
			if (listType.equalsIgnoreCase("list")) {
				elements=null;
				elements= driver.findElements(strxpath);
				for ( WebElement ele: elements) {
					String[] elementtexted = ele.getText().split("");
					if (elementtexted.length==0) {
						elementtexted=ele.getAttribute("value").split("");
					}
					for(String text :elementtexted ) {
						NotepadWriter.newLine();
						NotepadWriter.write(text.toString());
					}
					NotepadWriter.newLine();
				}
			}else {
				element=null;
				element= driver.findElement(strxpath);
				String elementtext = element.getText();
				if (elementtext.equals("")) {
					elementtext=element.getAttribute("value");
				}
				NotepadWriter.newLine();
				NotepadWriter.write(elementtext.toString());
				NotepadWriter.newLine();
			}
			NotepadWriter.write("****************************************************************************************************");	
			NotepadWriter.flush();
			NotepadWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**********************************************************************************************************************************************************
	 *  FunctionName: TypeWithRobot
	 * Argument :TestData
	 * @throws AWTException 
	 * @throws InterruptedException 
	 ******************************************************************************************************************************************************/
	public void TypeWithRobot(int[] keys) throws InterruptedException {

		try {
			robot= new Robot();
			for( int key: keys) {
				robot.keyPress(key);
				robot.delay(20);
				robot.keyRelease(key);
			}

		}catch(AWTException ex) {
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/*********************************************************************************************************************************************************************************************
	 *  FunctionName: PauseExecution
	 * Argument :TestData
	 * @throws InterruptedException 
	 * 
	 * 
	 *********************************************************************************************************************************************************************************************************************/
	public void PauseExecution(int pausedtime) throws InterruptedException {
		long starttime,Endtime;
		Thread.sleep(1000);
		starttime = System.currentTimeMillis();
		do {
			Endtime= System.currentTimeMillis();

		}while(Endtime-starttime<pausedtime);
	}

	/***********************************************************************************************************************************************************************************
	 *  FunctionName: ClickObjectIfExists
	 * Argument : Xpath
	 ***********************************************************************************************************************************************************************************/
	public  void  ClickObjectIfExists(By strxpath) {
		FunctionName= "ClickObjectIfExists>>>>> ";
		((JavascriptExecutor) driver).executeScript("window.focus();");
		String AfterDomproperty ;
		String previuosDomproperty;
		element=null;
		int i=0;
		try {
			element = driver.findElement(strxpath);
			if (element!=null) {
				previuosDomproperty = driver.getPageSource().toString();

				element.click();
				AfterDomproperty= driver.getPageSource().toString();
				if (!previuosDomproperty.equals(AfterDomproperty)) {
					System.out.println(FunctionName+strxpath + "is successfully clicked");
				}else {
					while(previuosDomproperty.equals(AfterDomproperty)){
						element.click();
						AfterDomproperty="";
						AfterDomproperty = driver.getPageSource().toString();
						if (i==3) {
							break;
						}
						i++;
					}
					if (!previuosDomproperty.equals(AfterDomproperty)) {
						System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is clicked successfully");
					}else {
						System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is  not clicked successfully");
					}
				}
			}
		}catch(Exception ex) {

		}
	}

	/**********************************************************************************************************************************************************************
	 *  FunctionName: HighlisghtThexpath
	 * Argument : Xpath
	 *********************************************************************************************************************************************************************/
	public  void  HighlisghtThexpath(By strxpath){
		FunctionName= "HighlisghtThexpath>>>>> ";
		element=null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			List<WebElement>element = driver.findElements(strxpath);
			System.out.println(element.size());
			for(WebElement ele: element) {
				js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", ele);
				Thread.sleep(2000);
				System.out.println(FunctionName+"Object : '"+ele.getText()+"' Object is Highlighted.. " );
				driver.navigate().refresh();
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}

	}


	/************************************************************************************************************************************************************************
	 *  FunctionName: ScrollVieWObject
	 * Argument : Xpath
	 ***********************************************************************************************************************************************************************/
	public  void  ScrollVieWObject(By strxpath){
		FunctionName= "ScrollVieWObject>>>>> ";
		element=null;
		try {
			element = driver.findElement(strxpath);
			if (element!=null) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", element);
				Assert.assertTrue(true,"Page is Scrolled to : " + strxpath.toString());
				PauseExecution(3000);
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}


	/**************************************************************************************************************************************************************************
	 *  FunctionName: GetElementColorTextPositions
	 * Argument : Xpath
	 *************************************************************************************************************************************************************************/
	public  String  GetElementColorTextPositions(By strxpath){
		FunctionName= "GetElementColorTextPositions>>>>> ";
		element=null;
		String text;
		try {
			List<WebElement>  element=driver.findElements(strxpath);
			for(WebElement ele:element ) {
				Point = ele.getLocation();
				color = ele.getCssValue("color");
				text= ele.getText();
				String textreturn ="Button with text  : '"+ text+"' has Loaction :  X :" +Point.getX()+" Y:  "+ Point.getY()+ " and back-ground color :"+ color;
				System.out.println(FunctionName+"Button with text  : '"+ text+"' has Loaction :  X :" +Point.getX()+" Y:  "+ Point.getY()+ " and back-ground color :"+ color);
				Assert.assertTrue(true,FunctionName+"Button with text  : '"+ text+"' has Loaction : " +Point.getX()+" "+ Point.getY()+ " and back-ground color :"+ color);
			}
		}catch(Exception ex) {
			textreturn=null;
			Assert.assertTrue(false,FunctionName+"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
		return textreturn;

	}
	/*******************************************************************************************************************************************************************
	 *  FunctionName: SpecificscreenShotoFSpecficRegion
	 * Argument : Xpath
	 * @throws FindFailed 
	 *******************************************************************************************************************************************************************/

	public void SpecificscreenShotoFSpecficRegion(By strxpath) throws IOException {
		((JavascriptExecutor) driver).executeScript("window.focus();");
		FunctionName="SpecificscreenShotoFSpecficRegion ";
		String format = "PNG";
		Date dnow = new Date();
		SimpleDateFormat dateF= new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss_SS");
		String fileName = "SpecificscreenShotoFSpecficRegion_"+ dateF.format(dnow)+"."+format;
		String Driverpath = System.getProperty("user.dir");
		element= null;
		String height= "";
		String wdith="";
		int intwdith=1;
		int intheight=1;
		try {
			element = driver.findElement(strxpath);
			Point = element.getLocation();
			wdith= element.getAttribute("width");
			height= element.getAttribute("height");
		}catch(Exception e) {
		}
		try {
			if (wdith!=null) {
				intwdith= Integer.parseInt(wdith) ;
			}else {
				intwdith =Point.getX()/2;
			}
			if (height!=null) {
				intheight= Integer.parseInt(height) ;
			}else {
				intheight=Point.getY()/2;
			}
			new Actions(driver).moveToElement(element).perform();
			Robot robot = new Robot();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			System.out.println("ScreenSize : " + screenSize);
			Rectangle captureRect = new Rectangle(Point.getX(), Point.getY(), intwdith, intheight); 
			BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
			File file = new File(Driverpath+"\\Screen Shot\\"+fileName);

			ImageIO.write(screenFullImage, format, file);
			//System.out.println(FunctionName + "file Path : "+ file);


		} catch (Exception ex) {
			System.err.println(ex);
		}

		/*
		try {
			Settings.OcrTextRead = true;
			Settings.OcrTextSearch = true;
			FileManager.loadLibrary("VisionProxy");
			String Driverpath = System.getProperty("user.dir");
			Pattern pic = new Pattern (Driverpath+"\\"+fileName);
			Screen S = new Screen();
			S.type("r",Key.WIN);
			S.find(pic).text();

		}catch(Exception x) {

		}
		 */
	}


	/***********************************************************************************************************************************************************************
	 *  FunctionName: CheckCheckBox
	 * Argument : Xpath
	 * @throws FindFailed 
	 ***********************************************************************************************************************************************************************/
	public void CheckCheckBox(By strxpath, String Check_Uncheck) {
		FunctionName= "CheckCheckBox>>>>> ";
		element=null;
		try {

			element= driver.findElement(strxpath);


			if (element!=null) {
				if (Check_Uncheck.equalsIgnoreCase("Check")){
					if (element.isSelected()) {

						System.out.println(FunctionName+"Element is already Checked. It can not be check again checking may causes Unchecking");
						Assert.assertTrue(false,FunctionName+"Element is already Checked. It can not be check again checking may causes Unchecking");

					}else {

						ClickObjectIfExists(strxpath);
						checkCondition=element.isSelected();
						PauseExecution(1000);
						try {

							if  (checkCondition==false) {
								element.sendKeys(Keys.SPACE);
								PauseExecution(1000);
							}

						}catch(Exception e) {
							System.out.println(FunctionName+"Element can not be checked through sendkey"+strxpath+" Message   :"+ e.getMessage()+" Cuase : "+e.getCause()  );
						}
						checkCondition=element.isSelected();


						PauseExecution(1000);
						if (checkCondition==false) {

							JavascriptExecutor js = (JavascriptExecutor)driver;
							js.executeScript("arguments[0].click()", element);
							PauseExecution(1000);
						}	
						checkCondition=element.isSelected();
						if (checkCondition==false) {
							Assert.assertTrue(false,FunctionName+"Selenim  is not able to check this Element " + strxpath);
						}
					}

				}else {
					if (!element.isSelected()) {
						System.out.println(FunctionName+"Element is already UNChecked. It can not be UNcheck again checking may causes checking");
						Assert.assertTrue(false,FunctionName+"Element is already UNChecked. It can not be UNcheck again checking may causes checking");

					}else {
						ClickObjectIfExists(strxpath);
						checkCondition=element.isSelected();
						PauseExecution(1000);

						try {
							if  (checkCondition==true) {
								element.sendKeys(Keys.SPACE);
								PauseExecution(1000);
							}
						}catch(Exception e){
							System.out.println(FunctionName+"Element can not be UNchecked through sendkey"+strxpath+" Message   :"+ e.getMessage()+" Cuase : "+e.getCause()  );
						}

						checkCondition=element.isSelected();

						if (checkCondition==true) {
							JavascriptExecutor js = (JavascriptExecutor)driver;
							js.executeScript("arguments[0].click();", element);
							PauseExecution(1000);
						}	
						checkCondition=element.isSelected();
						if (checkCondition==true) {
							Assert.assertTrue(false,FunctionName+"Selenim  is not able to uncheck this Element " + strxpath);
						}

					}

				}
			}

		}catch(Exception ex) {
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}


	/**********************************************************************************************************************************************************************
	 *  FunctionName: CheckCheckBoxStatusChecking
	 * Argument : Xpath
	 * 
	 **********************************************************************************************************************************************************************/
	public void CheckCheckBoxStatusChecking(By strxpath ) {
		FunctionName= "CheckCheckBoxStatusChecking>>>>> ";
		element= null;
		element= driver.findElement(strxpath);
		try {
			if(element!=null) {
				System.out.println("**********************************************************************************************************************************");
				System.out.println("************************* Checking check box status by 'isSelected' method*********************************************************");
				boolean checkbox =element.isSelected();
				if (checkbox== true) {
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "")+"  is checked on first place");
				}else {
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "")+"  is not checked on first place");
				}
				System.out.println("**********************************************************************************************************************************");
				System.out.println("**********************************************************************************************************************************");
				System.out.println("Checking check box status by 'checked' attribute present or not and also it is null or not. if 'checked' attribute present then check box is checked .");
				String Checkattribute= element.getAttribute("checked");
				if (Checkattribute==null) {
					System.out.println(FunctionName+"Checked attributre is not present.");
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "")+"  is not checked on first place");
				}else {
					System.out.println(FunctionName+"Checked attributre is present and it is blank");
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "")+"  is checked on first place");
				}
			}
			System.out.println("**********************************************************************************************************************************");
			System.out.println("**********************************************************************************************************************************");
		}catch(Exception ex) {
			Assert.assertTrue(false,FunctionName+"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}


	/****************************************************************************************************************************************************************************
	 *  FunctionName: PageReadyStateCheck
	 * Argument : intmilisecond
	 * @throws InterruptedException 
	 * 
	 **************************************************************************************************************************************************************************/
	public void PageReadyStateCheck(int intmilisecond) throws InterruptedException {
		FunctionName= "PageReadyStateCheck>>>>> ";
		System.out.println("It will Wait for page to load");
		final JavascriptExecutor js = (JavascriptExecutor)driver;
		try {
			WebDriverWait wait = new WebDriverWait(driver,intmilisecond);
			System.out.println(FunctionName+"Ready state Checking------------------");
			wait.until(new ExpectedCondition<Boolean>(){

				public Boolean apply(WebDriver driver) {
					System.out.println(FunctionName+"Ready state is checked");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					return js.executeScript("return document.readyState").equals("complete");
				}
			});
			System.out.println(FunctionName+"Jquert checking...................");
			wait.until(new ExpectedCondition<Boolean>(){

				public Boolean apply(WebDriver driver) {

					while(true) {
						if((Boolean) js.executeScript("return jQuery.active==0")) {
							System.out.println(FunctionName+"Jquert ready state is checked");
							break;
						}
						try {
							Thread.sleep(1000);
						}catch(Exception ex) {
						}
					}
					return true;
				}
			});
			while((!js.executeScript("return document.readyState").equals("complete"))) {
				Thread.sleep(1000);
			}
			System.out.println(FunctionName+"Page Ready state is found:  Page is loaded properly");
			Assert.assertTrue(true,FunctionName+"Page Ready state is found:  Page is loaded properly");
		}catch(Exception ex) {}
	}


	/***********************************************************************************************************************************************************************************
	 *  FunctionName: WaitforElementReturn
	 * Argument : intmilisecond
	 **********************************************************************************************************************************************************************************/
	@SuppressWarnings({ "deprecation", "unchecked" })
	public WebElement WaitforElementReturn(final By strxpath, int intsecond) {
		//element= null;
		//driver=driver;

		try {
			PageReadyStateCheck(intsecond);
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout(intsecond, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class);
				/*
			try {
				PageReadyStateCheck(2000);
				element=null;
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						element=driver.findElement(strxpath);
						return element ;
					}
				});
			}catch(Exception e) {
				element= null;		
			}
				 */
				element=null;
				element = wait.until(ExpectedConditions.visibilityOf(elementretrun(strxpath)));//(strxpath));//());//(driver.findElement(strxpath)));
		} catch(Exception ex) {
			element= null;	
			System.out.println("element   : is null");
		}
		return element;	
	}

	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: VerifyElementExists
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void VerifyElementExists(By strxpath,String strtestdata) {
		FunctionName= "VerifyElementExists>>>>> ";
		try {
			element=null;
			PageReadyStateCheck(2000);
			//element= WaitforElementReturn(strxpath,3000);
			elements= driver.findElements(strxpath);
			for (WebElement element: elements ) {
				if (strtestdata=="") {
					if(element!=null){
						System.out.println(FunctionName+"Test data is Null, 'VerifyElementExists' is checking Object is present or Not.>>>>"+strxpath.toString().replace("By.xpath:", "")+ "  Is present.");
						Assert.assertTrue(true,FunctionName+"Test data is Null, 'VerifyElementExists' is checking Object is present or Not.>>>"+strxpath.toString().replace("By.xpath:", "")+ "  Is present.");
					}else {
						System.out.println(FunctionName+"Test data is Null, 'VerifyElementExists' is checking Object is present or Not.>>>>"+strxpath.toString().replace("By.xpath:", "")+ "  Is not present.");
						Assert.assertTrue(true,FunctionName+"Test data is Null, 'VerifyElementExists' is checking Object is present or Not.>>>"+strxpath.toString().replace("By.xpath:", "")+ "  Is not present.");
					}
				}else {

					if(strtestdata.equalsIgnoreCase("exists")||strtestdata.equalsIgnoreCase("notexists")) {
						if (strtestdata.equalsIgnoreCase("exists")) {
							if(element!=null){
								System.out.println(FunctionName+"Test data is "+strtestdata+", 'VerifyElementExists' is checking Object is present>>>> Hence "+strxpath.toString().replace("By.xpath:", "")+ "  Is present.");
								Assert.assertTrue(true,FunctionName+"Test data is "+strtestdata+ ", 'VerifyElementExists' is checking Object is present>>> Hence "+strxpath.toString().replace("By.xpath:", "")+ "  Is present.");
							}else {
								System.out.println(FunctionName+"Test data is "+strtestdata+", 'VerifyElementExists' is checking Object is present>>>> All though "+strxpath.toString().replace("By.xpath:", "")+ "  Is not  present.");
								Assert.assertTrue(false,FunctionName+"Test data is "+strtestdata+", 'VerifyElementExists' is checking Object is present>>> All though "+strxpath.toString().replace("By.xpath:", "")+ "  Is not present.");
							}
						}else if (strtestdata.equalsIgnoreCase("notexists")) {

							if(element!=null){
								System.out.println(FunctionName+"Test data is "+strtestdata+", 'VerifyElementExists' is checking Object is not present>>>> Allthough "+strxpath.toString().replace("By.xpath:", "")+ "  Is present.");
								Assert.assertTrue(false,"Test data is "+strtestdata+ ", 'VerifyElementExists' is checking Object is  not present>>>Allthough "+strxpath.toString().replace("By.xpath:", "")+ "  Is present.");
							}else {
								System.out.println(FunctionName+"Test data is "+strtestdata+", 'VerifyElementExists' is checking Object is not  present>>>>Hence  "+strxpath.toString().replace("By.xpath:", "")+ "  Is not  present.");
								Assert.assertTrue(true,FunctionName+"Test data is "+strtestdata+", 'VerifyElementExists' is checking Object is  not present>>> AHence "+strxpath.toString().replace("By.xpath:", "")+ "  Is not present.");
							}
						}
					}else {

						System.out.println(FunctionName +"Test data is neither  exists nor notexists. Please check the test data");
						Assert.assertTrue(false, FunctionName +"Test data is neither  exists nor notexists. Please check the test data");
					}
				}
			}
		}catch(Exception ex) {
			System.out.println(FunctionName+"Error " +ex.toString() + "happens");
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: DragAndDrop
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void DragAndDrop(By drop,By drag) {
		try {
			WebElement dropele = driver.findElement(drop); 
			WebElement dragele = driver.findElement(drag); 
			if (dropele!=dragele) {
				if ((dragele!=null)&&(dropele!=null)) {
					Act = new Actions(driver);
					/*
					// X :315 Y:  87 and back-groud color :rgba(34, 34, 34, 1)
					//Button with text  : 'B' has Loaction :  X :530 Y:  87  
					dragele.click();
					Act.dragAndDrop(dragele, dropele).build().perform();
					Act.build().perform();
				  Actions builder = new Actions(driver);
				    builder.keyDown(Keys.CONTROL)
				        .click(fromelement)
				        .dragAndDrop(fromelement, Toelement)
				        .keyUp(Keys.CONTROL);

				        Action selected = builder.build();

				        selected.perform();
					 */	
					//Actions action = new Actions(driver);
					//Action dragdrop = action.clickAndHold(fromelement).moveToElement(Toelement).release(Toelement).build();
					//dragdrop.perform();

				}
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}


	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: SelectDropDown
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void SelectDropDown(By strxpath,String strtestdata) {
		element = null;
		FunctionName= "SelectDropDown>>>>> ";
		try {
			element= driver.findElement(strxpath);
			if (element!=null) {
				if (strtestdata!=null) {
					Select select = new Select(element);
					select.selectByVisibleText(strtestdata);
					WebElement Seletedele= select.getFirstSelectedOption();
					String SelectedText=  Seletedele.getText();
					if (SelectedText.toUpperCase().equals(strtestdata.toUpperCase())) {
						System.out.println(FunctionName+strtestdata+ " is  selected. Succesfully!!");
						Assert.assertTrue(true,strtestdata+ " is  selected. Succesfully!!");
					}else {
						System.out.println(FunctionName+strtestdata+ " is not selected. Please re try!!");
						Assert.assertTrue(false,strtestdata+ " is not selected. Please re try!!"); 
					}
				}else {
					System.out.println(FunctionName+"Test data is not present.");
					Assert.assertTrue(false,"Test data is not present."); 
				}
			}else {
				System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "")+ " is not present.");
				Assert.assertTrue(false,strxpath.toString().replace("By.xpath:", "")+ " is not present.");
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}
	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: VerifySelectedvalueonDropdown
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void VerifySelectedvalueonDropdown(By strxpath,String strtestdata) {
		element = null;
		FunctionName= "VerifySelectedvalueonDropdown>>>>> ";

		try {
			element= driver.findElement(strxpath);
			if (element!=null) {
				if (strtestdata!=null) {
					Select select = new Select(element);
					WebElement Seletedele= select.getFirstSelectedOption();
					String AlreadyselectedValue = Seletedele.getText().toString();
					if (AlreadyselectedValue.toUpperCase().equalsIgnoreCase(strtestdata.toUpperCase())) {
						System.out.println(FunctionName+"'"+strtestdata.toUpperCase()+ " '  is selected in  "+ strxpath.toString().replace("By.xpath:", ""));
						Assert.assertTrue(true,"FunctionName"+strtestdata.toUpperCase()+ " '  is selected in  "+ strxpath.toString().replace("By.xpath:", "")); 	
					}else {
						System.out.println(FunctionName+"' "+strtestdata.toUpperCase()+ " '   is  not selected in  "+ strxpath.toString().replace("By.xpath:", "")+ "selected value "+ AlreadyselectedValue);
						Assert.assertTrue(true,strtestdata.toUpperCase()+ "   is  not selected in  "+ strxpath.toString().replace("By.xpath:", "")+ "selected value "+ AlreadyselectedValue); 	
					}
				}else {
					Assert.assertTrue(false,FunctionName+"Test data is not present."); 
				}
			}else {
				Assert.assertTrue(false,FunctionName+strxpath.toString().replace("By.xpath:", "")+ " is not present.");
			}

		}catch(Exception ex) {
			Assert.assertTrue(false,FunctionName+"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: VerifyDropdownContains
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void VerifyDropdownContains(By strxpath,String strtestdata) {
		element= null;
		FunctionName="VerifyDropdownContains>>>> ";
		try {		
			strtestdata.replace("|", "\\|");
			publicArray= strtestdata.split("\\|");
			int Arraysize=	publicArray.length;

			element = driver.findElement(strxpath);
			if(element!=null) {
				Select select = new Select(element);
				elements = select.getOptions();
				GenHashmap= new HashMap<String,String>();
				for(WebElement ele: elements ) {
					String eleText = ele.getText().toUpperCase();
					if (eleText!=null) {
						GenHashmap.put(eleText, "");
					}
				}
				for (int i=0;i<Arraysize;i++) {
					if(GenHashmap.containsKey(publicArray[i].toUpperCase())) {
						System.out.println(FunctionName+"'"+ publicArray[i] +" ' is present on drop down " + strxpath.toString().replaceAll("By.xpath:", ""));
						Assert.assertTrue(true ,FunctionName+"'"+ publicArray[i] +" ' is present on drop down " + strxpath.toString().replaceAll("By.xpath:", "")); 
						GenHashmap.remove(publicArray[i].toUpperCase());
					}else {
						System.out.println(FunctionName+"'"+publicArray[i] +"' is  not present on drop down " + strxpath.toString().replaceAll("By.xpath:", ""));
					}
				}

				if(GenHashmap.size()>0) {
					Iterator Iterator= GenHashmap.entrySet().iterator();
					while(Iterator.hasNext()) {
						Map.Entry pair=(Map.Entry) Iterator.next();
						System.out.println(FunctionName +"'"+pair.getKey().toString() +"' is  not present on drop down " + strxpath.toString().replaceAll("By.xpath:", ""));
						softAssert.assertTrue(false,FunctionName+"'"+pair.getKey().toString() +"' is  not present on drop down " + strxpath.toString().replaceAll("By.xpath:", ""));
					}
				}

			}else {
				System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not present.");
				Assert.assertTrue(false,strxpath.toString().replace("By.xpath:", "")+ " is not present.");
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,FunctionName +"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: VerifyEnabilityCheck
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void VerifyEnabilityCheck(By strxpath,String strtestdata) {
		element = null;
		//element= null;
		FunctionName="VerifyEnabilityCheck>>>> ";
		try {
			PauseExecution(3000);
			element= WaitforElementReturn(strxpath,3000);
			if (element!=null) {
				if (strtestdata.equals("")) {
					if (element.isEnabled()) {
						System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Enabled.");
						Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Enabled.");	
					}else {
						System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not Enabled.");
						Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not  Enabled.");	
					}
				} else {
					if(strtestdata.equalsIgnoreCase("enable")||strtestdata.equalsIgnoreCase("disable")) {
						if(strtestdata.equalsIgnoreCase("enable")){
							if (element.isDisplayed()) {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Enabled.and test data: "+strtestdata);
								Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Enabled.and test data: "+strtestdata);	
							}else {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Enabled. and test data: "+strtestdata);
								Assert.assertTrue(false,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Enabled.and test data: "+strtestdata);
							}
						} 
						if(strtestdata.equalsIgnoreCase("disable")){
							if (!element.isDisplayed()) {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Enabled.and test data: "+strtestdata);
								Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Enabled.and test data: "+strtestdata);	
							}else {
								try {
								  String AttributeVlaue = element.getAttribute("disabled");
								  if (AttributeVlaue.equalsIgnoreCase("true")) {
									  System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Enabled.and test data: "+strtestdata);
										Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Enabled.and test data: "+strtestdata);
								  }else {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is   Enabled. and test data: "+strtestdata);
								Assert.assertTrue(false,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is   Enabled.and test data: "+strtestdata);
								  }
								}catch(Exception ex) {
									
										Assert.assertTrue(false,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is   Enabled.and test data: "+strtestdata);
								}
								  
							}
						} 
					}else {
						System.out.println(FunctionName +"Test data is neither  enable nor disable. Please check the test data");
						Assert.assertTrue(false, FunctionName +"Test data is neither  exists nor notexists. Please check the test data");
					}
				}
			}else {
				System.out.println(FunctionName +"Element is no Present in page");
				Assert.assertTrue(false,FunctionName +"Element is no Present in page");	
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,FunctionName +"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}


	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: VerifyVisibilityCheck
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void VerifyVisibilityCheck(By strxpath,String strtestdata) {
		
		element= null;
		
		try {
			PauseExecution(3000);
			element= WaitforElementReturn(strxpath,200);
			FunctionName="VerifyVisibilityCheck>>>> ";
			if (element!=null) {
				if (strtestdata!=null) {
					if (element.isDisplayed()) {
						System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Visible.");
						Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Visible.");	
					}else {
						System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not Visible.");
						Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not  Visible.");	
					}
				} else {
					if(strtestdata.equalsIgnoreCase("exists")||strtestdata.equalsIgnoreCase("notexists")) {
						if(strtestdata.equalsIgnoreCase("exists")){
							if (element.isDisplayed()) {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Visible.and test data: "+strtestdata);
								Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  Visible.and test data: "+strtestdata);	
							}else {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Visible. and test data: "+strtestdata);
								Assert.assertTrue(false,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Visible.and test data: "+strtestdata);
							}
						} 
						if(strtestdata.equalsIgnoreCase("notexists")){
							if (!element.isDisplayed()) {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Visible.and test data: "+strtestdata);
								Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is  not Visible.and test data: "+strtestdata);	
							}else {
								System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is   Visible. and test data: "+strtestdata);
								Assert.assertTrue(false,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is   Visible.and test data: "+strtestdata);
							}
						} 
					}else {
						System.out.println(FunctionName +"Test data is neither  exists nor notexists. Please check the test data");
						Assert.assertTrue(false, FunctionName +"Test data is neither  exists nor notexists. Please check the test data");
					}
				}
			}else {
				System.out.println(FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not  Visible.");
//				Assert.assertTrue(false,FunctionName +"Element is no Present in page");	
				Assert.assertTrue(true,FunctionName +FunctionName +strxpath.toString().replace("By.xpath:", "")+ " is not  Visible.");
			}
		}catch(Exception ex) {
			Assert.assertTrue(false,FunctionName +"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}
	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: SetOnparam
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void SetOnparam(By strxpath,String strtestdata) {
		element = null;
		FunctionName="SetOnparam >>>> ";
		try {
			PauseExecution(3000);
			element= WaitforElementReturn(strxpath,3000);

			if (element!=null) {

				if (!strtestdata.equals("")) {
					element.click();
					element.clear();
					System.out.println(FunctionName +"strtestdata is " + strtestdata);
					element.sendKeys(strtestdata);
				}else {
					System.out.println(FunctionName +"strtestdata is null");
				}
			}else {
				driver.quit();
				System.out.println(FunctionName +"Element is null");
				Assert.assertTrue(false,FunctionName +"Element is null");
			}
		}catch(Exception ex) {
			driver.quit();
			Assert.assertTrue(false,FunctionName +"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/*****************************************************************************************************************************************************************************************
	 *  FunctionName: GetAttributevalue
	 * Argument :
	 ****************************************************************************************************************************************************************************************/
	public void GetAttributevalue(By strxpath,String strtestdata) {
		element = null;
		FunctionName="GetAttributevalue >>>> ";
		try {
			PauseExecution(3000);
			element= WaitforElementReturn(strxpath,3000);
			FunctionName="GetAttributevalue >>>> ";
			if (element!=null) {

				if (!strtestdata.equals("")) {
					if (strtestdata.equalsIgnoreCase("text")){
						String AtrributeValue= element.getText();
						if (!AtrributeValue.equals("")){
							System.out.println(FunctionName + " Text : '" +strtestdata + " ' of element is : '" + AtrributeValue +"'");
							Assert.assertTrue(true ,FunctionName + " Text : '" +strtestdata + " ' of element is : '" + AtrributeValue +"'");
						}else {
							System.out.println(FunctionName + "strtestdata is null");
							Assert.assertTrue(false,FunctionName +"strtestdata is null");	
						}
					}else {
						String AtrributeValue= element.getAttribute(strtestdata).toString();
						System.out.println(FunctionName + " Attribute : '" +strtestdata + " ' of element is : '" + AtrributeValue +"'");
						Assert.assertTrue(true ,FunctionName + " Attribute : '" +strtestdata + " ' of element is : " + AtrributeValue);
					}
				}else {
					System.out.println(FunctionName +"strtestdata is null");
				}
			}else {
				driver.quit();
				System.out.println(FunctionName +"Element is null");
				Assert.assertTrue(false,FunctionName +"Element is null");
			}
		}catch(Exception ex) {
			
			Assert.assertTrue(false,FunctionName +"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}

	/*******************************************************************************************************************************************************************
	 *  FunctionName: ScreenShot
	 * Argument : Xpath
	 * @throws FindFailed 
	 *******************************************************************************************************************************************************************/

	public void ScreenShot() throws IOException {
		((JavascriptExecutor) driver).executeScript("window.focus();");
		FunctionName="SpecificscreenShotoFSpecficRegion ";
		String format = "PNG";
		Date dnow = new Date();
		SimpleDateFormat dateF= new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss_SS");
		String fileName = "SpecificscreenShotoFSpecficRegion_"+ dateF.format(dnow)+"."+format;
		String Driverpath = System.getProperty("user.dir");
		try {
			Robot robot = new Robot();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle capture =  new Rectangle(screenSize);
			BufferedImage screenFullImage = robot.createScreenCapture(capture);
			File file = new File(Driverpath+"\\Screen Shot\\"+fileName);

			ImageIO.write(screenFullImage, format, file);
			//System.out.println(FunctionName + "file Path : "+ file);

		} catch (Exception ex) {
			System.err.println(ex);
		}

		/*
		try {
			Settings.OcrTextRead = true;
			Settings.OcrTextSearch = true;
			FileManager.loadLibrary("VisionProxy");
			String Driverpath = System.getProperty("user.dir");
			Pattern pic = new Pattern (Driverpath+"\\"+fileName);
			Screen S = new Screen();
			S.type("r",Key.WIN);
			S.find(pic).text();

		}catch(Exception x) {

		}
		 */
	}


	/***********************************************************************************************************************************************************************************
	 *  FunctionName: WaitforElementReturnForClick
	 * Argument : intmilisecond
	 **********************************************************************************************************************************************************************************/
	@SuppressWarnings({ "deprecation", "unchecked" })
	public WebElement WaitforElementReturnForClick(final By strxpath, int intsecond) {
		//element= null;
		//driver=driver;

		try {
			PageReadyStateCheck(intsecond);

			String[] xpath ;	
			int i=0;
			if ((strxpath.toString().replace("By.xpath:", "").contains("Index"))){
				String StrXpath= strxpath.toString().replace("By.xpath:", "");
				StrXpath= StrXpath.replace("|", "\\|");
				xpath = StrXpath.split("\\|");
				xpath[1]= xpath[1].replace("Index", "");
				xpath[1]= xpath[1].replace("=", "");
				i = Integer.parseInt(xpath[1]);
				xpath[0]=xpath[0].replace("\\", "");
				elements = driver.findElements(By.xpath(xpath[0]));
			}else {
				elements = driver.findElements(strxpath);
			}
			if (!elements.isEmpty()) {	

				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout(intsecond, TimeUnit.SECONDS)
						.pollingEvery(5, TimeUnit.SECONDS)
						.ignoring(NoSuchElementException.class);
				element=null;
				element = wait.until(ExpectedConditions.visibilityOf(elements.get(i)));//(strxpath));//());//(driver.findElement(strxpath)));
				//System.out.println("element   :"+ element.toString());

				if (element!=null) {
					Thread.sleep(2000);
					WebDriverWait WebDriverWaitImplicite = new WebDriverWait(driver, intsecond);
					element=wait.until(ExpectedConditions.elementToBeClickable(elements.get(i)));
					System.out.println("element located");
				}

			}else {
				element= null;	 
			}
		} catch(Exception ex) {
			element= null;	
			System.out.println("element   : is null");
		}
		return element;	
	}

	/************************************************************************************************************************************************************************
	 *  FunctionName: ClickObject
	 * Argument : Xpath
	 ************************************************************************************************************************************************************************/
	public  void  ClickObject(By strxpath) {
		((JavascriptExecutor) driver).executeScript("window.focus();");
		String AfterDomproperty ;
		String previuosDomproperty;
		element=null;
		int i=0;
		try {
			previuosDomproperty = driver.getPageSource().toString();
			element= WaitforElementReturnForClick(strxpath, 1000);
			FunctionName="ClickObject>>>>>> ";
			element.click();
			AfterDomproperty= driver.getPageSource().toString();
			if (!previuosDomproperty.equals(AfterDomproperty)) {
				System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is successfully clicked");
				Assert.assertTrue(true,strxpath + "is successfully clicked");
			}else {
				while(previuosDomproperty.equals(AfterDomproperty)){
					element= WaitforElementReturnForClick(strxpath, 1000);
					element.click();
					AfterDomproperty="";
					Thread.sleep(2000);
					AfterDomproperty = driver.getPageSource().toString();
					if (i==3) {
						break;
					}
					i++;
				}

				if (!previuosDomproperty.equals(AfterDomproperty)) {
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is successfully clicked");
					Assert.assertTrue(true, FunctionName+strxpath + "is clicked successfully");
				}else {
					System.out.println(FunctionName+strxpath.toString().replace("By.xpath:", "") + "is  not clicked");
					Assert.assertTrue(false,FunctionName+strxpath + "is  not clicked successfully");
				}
			}

		}catch(Exception ex) {

			System.out.println(FunctionName+"    :>>>>>>"+strxpath.toString().replace("By.xpath:", "") +"   Exception is "+ ex.toString() + " for causing :" + ex.getMessage());

			Assert.assertTrue(false,"Exception is "+ ex.toString() + " for causing :" + ex.getMessage());
		}
	}
	
	/************************************************************************************************************************************************************************
	 *  FunctionName: elementretrun
	 * Argument : Xpath
	 ************************************************************************************************************************************************************************/	
 public WebElement elementretrun(By strxpath) {
	 try {
		String[] xpath ;	
		int i=0;
		if ((strxpath.toString().replace("By.xpath:", "").contains("Index"))){
			String StrXpath= strxpath.toString().replace("By.xpath:", "");
			StrXpath= StrXpath.replace("|", "\\|");
			xpath = StrXpath.split("\\|");
			xpath[1]= xpath[1].replace("Index", "");
			xpath[1]= xpath[1].replace("=", "");
			i = Integer.parseInt(xpath[1]);
			xpath[0]=xpath[0].replace("\\", "");
			elements = driver.findElements(By.xpath(xpath[0]));
		}else {
			elements = driver.findElements(strxpath);
		}
		if (elements.isEmpty()) {
			element =null;
		}else {
			element = elements.get(i);
		}
	 }catch(Exception ex) {
		 element =null;
	 }
	 return element;
 }
 

}








