package CommonLIB;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.script.ScriptEngine;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Sets;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;

//import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator.Slicing;


import org.openqa.selenium.Proxy;

@SuppressWarnings("unused")
public class InitialSetUp implements IntialStepup_Interface {
	public  RemoteWebDriver driver;
	int count=0;
	public WebElement element;
	public DesiredCapabilities caps;
	public String driverinstance="";
	public BrowserMobProxy proxyserver;
	/***********************************************************************
	 * Name : Data Provider
	 * 
	 ***********************************************************************/

	@DataProvider(name ="BrowserProvider")
	public Object[][]BrowserProvider() {
		return new Object[][] {
			{"Chrome","Browser"},
			//{"IE","Browser"},
			//{"FireFox","Browser"},
		};

	}



	/***********************************************************************
	 * Name : BrowserLaunchwithCapabilities
	 * @return  as WebDriver
	 * ReturnType: Webdriver
	 * @throws IOException 
	 * @throws InterruptedException 
	 * 
	 ***********************************************************************/

	@SuppressWarnings("deprecation")
	public WebDriver BrowserLaunchwithCapabilities(String strdrivername) throws IOException, InterruptedException {		
		String Driverpath = System.getProperty("user.dir");
		System.out.println(Driverpath);
		int drivercount =0;
		  BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy
		    System.out.println("BrowserMob Proxy running on port: " + proxy.getPort());
		    Proxy seleniumProxy = getSeleniumProxy(proxy);
		if (!driverinstance.isEmpty() ) {
			if(strdrivername.equalsIgnoreCase("IE") && driverinstance.contains("internet")){
				System.out.println(" " + strdrivername  + "  Browser is opening......................");
				caps= DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,false);
				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				caps.setCapability(CapabilityType.PROXY, seleniumProxy);
				driver = new InternetExplorerDriver(caps);
				proxyserver.enableHarCaptureTypes(
		                CaptureType.REQUEST_CONTENT,
		                CaptureType.RESPONSE_CONTENT);

				drivercount=1;
			}
			if(strdrivername.equalsIgnoreCase("Chrome") && driverinstance.contains("chrome")){
				System.out.println(" " + strdrivername  + "  Browser is opening...................");
				caps = DesiredCapabilities.chrome();    
				ArrayList<String> switches = new ArrayList<String>();    
				switches.add("--proxy-server=localhost:8080");    
				caps.setCapability("chrome.switches", switches); 
				caps.setCapability(CapabilityType.PROXY, seleniumProxy);
				ChromeOptions options= new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
				options.addArguments("--always-authorize-plugins");
				options.addArguments("disable-infobars");
				options.addArguments("--disable-notifications");
				options.merge(caps);
				driver = new ChromeDriver( options);
				proxyserver.enableHarCaptureTypes(
		                CaptureType.REQUEST_CONTENT,
		                CaptureType.RESPONSE_CONTENT);
				drivercount=1;
			}
			if(strdrivername.equalsIgnoreCase("firefox") &&driverinstance.contains("firefox")){
				System.out.println(" " + strdrivername  + "  Browser is opening");
				drivercount=1;
			}
		}
		if (drivercount==0) {
			driverinstance="";
			Thread.sleep(1000);

		}
		if(strdrivername.equalsIgnoreCase("IE") && driverinstance.isEmpty()){
			System.out.println(" Previous " + strdrivername  + "  Browser is closing..........");
			System.out.println("**********************************************************************************************************************************");
			System.out.println("**********************************************************************************************************************************");
			System.out.println(" " + strdrivername  + "  Browser is opening.........................");
			System.out.println(Driverpath+ "\\Drivers\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver",Driverpath+ "\\Drivers\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe");
			caps= DesiredCapabilities.internetExplorer();

			caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,false);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			driver = new InternetExplorerDriver();
			proxyserver.enableHarCaptureTypes(
	                CaptureType.REQUEST_CONTENT,
	                CaptureType.RESPONSE_CONTENT);



		}
		if(strdrivername.equalsIgnoreCase("chrome")&& driverinstance.isEmpty()){

			System.out.println(" Previous " + strdrivername  + "  Browser is closing..........");
			System.out.println("**********************************************************************************************************************************");
			System.out.println(Driverpath+ "\\Drivers\\chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",Driverpath+ "\\Drivers\\chromedriver_win32\\chromedriver.exe");
			System.out.println("**********************************************************************************************************************************");
			System.out.println(" " + strdrivername  + "  Browser is opening..................");
			caps = DesiredCapabilities.chrome();    
			ArrayList<String> switches = new ArrayList<String>();    
			switches.add("--proxy-server=localhost:8080");    
			caps.setCapability("chrome.switches", switches); 
			ChromeOptions options= new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
			options.addArguments("--always-authorize-plugins");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.merge(caps);
try {
			//driver = new ChromeDriver(options);
	driver = new ChromeDriver();
			
			
}catch(Exception ex) {
	System.out.println(ex.toString());
}
/*
			proxyserver.enableHarCaptureTypes(
	                CaptureType.REQUEST_CONTENT,
	                CaptureType.RESPONSE_CONTENT);

*/


			//driver = new RemoteWebDriver(DesiredCapabilities.chrome());
			//driver.get("http://www.google.com");

		}
		if(strdrivername.equalsIgnoreCase("firefox")&& driverinstance.isEmpty()){
			/*
			System.setProperty("webdriver.gecko.driver",Driverpath+ "\\Drivers\\geckodriver-v0.21.0-win32\\geckodriver.exe");
			//System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");

			//FirefoxOptions options = new FirefoxOptions();
			//options.setLogLevel(FirefoxDriverLogLevel.DEBUG);

			driver = new FirefoxDriver();

			 */
		}
		//driver.manage().deleteAllCookies();
		int wincount=0;
		Set<String> WindowFocus = driver.getWindowHandles();
		wincount=WindowFocus.size();
		if (wincount==1) {
			driver.manage().window().maximize();	

			System.out.println(wincount+ " Browser is opened");


		}else if (wincount>1) {
			for (String count: WindowFocus) {
				driver.quit();
			}
		}else if (wincount==0) {
			//driver = new RemoteWebDriver();
		}
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		return driver;
	}	

	/***********************************************************************
	 * Name : BrowserClosing
	 * @return 
	 * ReturnType: void
	 * @throws IOException 
	 * 
	 ***********************************************************************/

	public String BrowserClosing(String strdrivername) throws IOException {
		System.out.println(" " + strdrivername  + "  Browser is closing.........................");
		
		proxyserver.stop();
		driver.close();
		driver.quit();
		System.out.println(" " + strdrivername  + "  Browser is closed succesfully");

		return driverinstance=	driver.toString();
	}


	/***********************************************************************
	 * Name :FocusOnUrl 
	 * Return type: WebDriver
	 * @throws InterruptedException 
	 * 
	 ***********************************************************************/
	public WebDriver FocusOnUrl(String strurl,  int Alertwaitcount) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.focus();");
		
		 
		driver.get(strurl);
		System.out.println(" Start to Wait for Alert to Come........................."); 

		count=Alertwaitcount;
		if (count>0) {
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

			if (alertx==count) {
				System.out.println(" NO Alert  is  not found");
			}
		}

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		String WindowFocus = driver.getWindowHandle();
		driver.switchTo().window(WindowFocus);
		driver.manage().deleteAllCookies();
		return driver;
	}

	@AfterTest
	public void teardownAftertest() throws IOException  {
		
		System.out.println("teardownAftertest....................................");
		
		
		if (!driver.toString().contains("null")) {
			proxyserver.stop();
			driver.quit();
			
		}
		if (driver.toString().equalsIgnoreCase("chrome")) {
			proxyserver.stop();
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		}
		if (driver.toString().equalsIgnoreCase("internet")) {
			proxyserver.stop();
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
		}
 System.out.println(" Requesting JVM to run  Garbage Collector>>>>>>>>>>>>>>>>>>");
 System.gc();
 //Runtime.getRuntime().gc();
	}


	@BeforeTest
	public void Beforetestenothing()  {
		
		System.out.println("Beforetestenothing....................................");
		System.out.println("Using Before test for use After test");
		System.out.println("we can use before test as browserlaunch");
	}
	
	public Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
	    try {
	        String hostIp = Inet4Address.getLocalHost().getHostAddress();
	        seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
	        seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
	    } catch (UnknownHostException e) {
	        e.printStackTrace();
	       
	    }
	    return seleniumProxy;
	}

	public BrowserMobProxy getProxyServer() {
	    proxyserver = new BrowserMobProxyServer();
	    proxyserver.setTrustAllServers(true);
	    proxyserver.setHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
	    proxyserver.start();
	    return proxyserver;
	}

	@Override
	protected void finalize() throws Throwable  
    {  
        System.out.println("Finalize method called");  
    }

}