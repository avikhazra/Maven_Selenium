package CommonLIB;
import org.openqa.selenium.By;

public class Repository {
	//Elemental Selenium
	public By ElementalSelenium_Link= By.xpath("//a[text()='Elemental Selenium']");
	public String StrtestUrl= "http://the-internet.herokuapp.com/";
	public By A_Btesting= By.xpath("//a[@href='/abtest']");
	public By heading= By.xpath("//div[@class='example']/h3"); 
	public By Description= By.xpath("//div[@class='example']/child::p");
	public By BodyDescription= By.xpath("//body");
	public By Basic_Auth_Link= By.xpath("//a[text()='Basic Auth']");
	public By Broken_Images_Link= By.xpath("//a[text()='Broken Images']");
	public By Images= By.xpath("//div[@class='example']/child::img");
	public By Challenging_DOM_Link= By.xpath("//a[text()='Challenging DOM']");
	public By Challenging_DOM_button=  By.xpath("//a[@class='button']");
	public By Challenging_DOM_button_alert=  By.xpath("//a[@class='button alert']");
	public By Challenging_DOM_button_success=  By.xpath("//a[@class='button success']");
	public By Challenging_DOM_canvas =By.xpath("//canvas[@id='canvas']");
	public By Checkboxes_Link= By.xpath("//a[text()='Checkboxes']");
	public By Checkboxes_Checkbox1= By.xpath("//form[@id='checkboxes']/input[1]");
	public By Checkboxes_Checkbox2= By.xpath("//form[@id='checkboxes']/input[2]");
	public By Disappearing_Elements_Link= By.xpath("//a[text()='Disappearing Elements']");
	public By Disappearing_Elements_Home= By.xpath("//a[text()='Home']");
	public By Disappearing_Elements_About= By.xpath("//a[text()='About']");
	public By Disappearing_Elements_ContactUs= By.xpath("//a[text()='Contact Us']");
	public By Disappearing_Elements_Portfolio= By.xpath("//a[text()='Portfolio']");
	public By Disappearing_Elements_Gallery= By.xpath("//a[text()='Gallery']");
	public By Drag_and_Drop_Link= By.xpath("//a[text()='Drag and Drop']");
	public By Drag_and_Drop_ObjectA= By.xpath("//div[@id='column-a']");
	public By Drag_and_Drop_ObjectB= By.xpath("//div[@id='column-b']");
	public By Dropdown_Link= By.xpath("//a[text()='Dropdown']");
	public By select_dropdown= By.xpath("//select[@id='dropdown']");
	public By DynamicContent= By.xpath("//a[text()='Dynamic Content']");
	public By DynamicContent_clickhere=By.xpath("//a[text()='click here']");
	public By DynamicContent_Images =By.xpath("//div[@class='large-2 columns']/img");
	public By DynamicContent_Images_Description =By.xpath("//div[@class='large-10 columns']");
	public By Dynamic_Controls_Link= By.xpath("//a[text()='Dynamic Controls']");
	public By Dynamic_Controls_Remove= By.xpath("//button[text()='Remove']");
	public By Dynamic_Controls_Add= By.xpath("//button[text()='Add']");
	public By  Dynamic_Controls_MSGcheckbox= By.xpath("//form[@id='checkbox-example']/p[@id='message']");
	public By  Dynamic_Controls_MSGinput= By.xpath("//form[@id='input-example']/p[@id='message']");
	public By Dynamic_loading= By.xpath("//div[@id='loading']");
	public By Dynamic_Controls_Enable= By.xpath("//button[text()='Enable']");
	public By Dynamic_Controls_textbox= By.xpath("//input[@type='text']");
	public By Dynamic_Controls_checkbox= By.xpath("//input[@type='checkbox']");
	public By DynamicLoading_Link= By.xpath("//a[text()='Dynamic Loading']");
	public By DynamicLoading_Example1_Link= By.xpath("//a[text()='Example 1: Element on page that is hidden']");
	public By DynamicLoading_heading= By.xpath("//div[@class='example']/h4"); 
	public By DynamicLoading_Examples_button= By.xpath("//div[@id='start']/button"); 
	public By DynamicLoading_Example1_Finish= By.xpath("//div[@id='finish']/h4"); 
	public By DynamicLoading_Example2_Link= By.xpath("//a[text()='Example 2: Element rendered after the fact']");
	public By ExitIntent_Link= By.xpath("//a[text()='Exit Intent']");
	public By ExitIntent_modalWindow_Title= By.xpath("//div[@class='modal-title']/h3");
	public By ExitIntent_modalWindow_Body= By.xpath("//div[@class='modal-body']/p");
	public By ExitIntent_modalWindow_close= By.xpath("//div[@class='modal-footer']/p[text()='Close']");
	public By File_Download_Link = By.xpath("//a[text()='File Download']");

	public By File_Download_DownloadableFiles = By.xpath("//div[@class='example']/a");

	public By FileUploadLink =By.xpath("//a[text()='File Upload']");

	public By browseLink =By.xpath("//input[@id='file-upload']");
	public By uploadLink =By.xpath("//input[@id='file-submit']");
	//Floating Menu
	public By FloatingMenu = By.xpath("//a[text()='Floating Menu']"); 
	public By FloatingMenu_Home = By.xpath("//a[text()='Home']"); 
	public By FloatingMenu_News = By.xpath("//a[text()='News']"); 
	public By FloatingMenu_Contact = By.xpath("//a[text()='Contact']");
	public By FloatingMenu_About = By.xpath("//a[text()='About']");
	public By ForgotPassword= By.xpath("//a[text()='Forgot Password']");
	public By ForgotPasswordEmail= By.xpath("//input[@id='email']");
	public By ForgotPasswordButton= By.xpath("//button[@id='form_submit']");
	//
	public By ForgotPasswordContent= By.xpath("//div[@id='content']");
	public By ForgotPasswordheading= By.xpath("//div[@class='example']/h2"); 
	//
	public By FormAuthenticationLink= By.xpath("//a[text()='Form Authentication']");
	public By FormAuthenticationheading= By.xpath("//div[@class='example']/h2");
	public By FormAuthentication_username= By.xpath("//input[@id='username']");
	public By FormAuthentication_password= By.xpath("//input[@id='password']");
	public By FormAuthentication_Login= By.xpath("//button[@type='submit']");
	public By FormAuthentication_MsgFlash= By.xpath("//div[@id='flash-messages']/div");
	public By FormAuthentication_Msg= By.xpath("//div[@class='example']/h4"); 
	public By FormAuthentication_LogOut = By.xpath("//a[@class='button secondary radius']");

	//Frames
	public By FramesLink= By.xpath("//a[text()='Frames']");
	public By 	Nested_FramesLink= By.xpath("//a[text()='Nested Frames']");
	public By 	Nested_Frames_Nested_Frame= By.xpath("//frameset[@frameborder=1]/frame");
	public By 	Nested_Frames_Contents= By.xpath("//div[@id='content']");
	public By 	iFramesLink= By.xpath("//a[text()='iFrame']");
	public By   iframeTextarea = By.xpath("//body[@id='tinymce']");
//
	//Geolocation
	public By GeolocationLink= By.xpath("//a[text()='Geolocation']");
	public By GeolocationButton = By.xpath("//button[text()='Where am I?']");
	public By GeolocationDemo = By.xpath("//p[@id='demo']");
	public By Geolocationlat_value = By.xpath("//div[@id='lat-value']");
	public By Geolocationlong_value = By.xpath("//div[@id='long-value']");
	public By GoggleLink = By.xpath("//a[text()='See it on Google']");
	//Horizontal Slider
	public By HorizontalSliderLink = By.xpath("//a[text()='Horizontal Slider']");
	public By HorizontalSlider = By.xpath("//div[@class='sliderContainer']/input");
	
	public By Hovers = By.xpath("//a[text()='Hovers']");
	public By ImageHovers1 = By.xpath("//div[@class='example']/div/img|Index=0");
	public By ImageHovers2 = By.xpath("//div[@class='example']/div/img|Index=1");
	public By ImageHovers3 = By.xpath("//img[@alt='User Avatar']|Index=2");
	public By VeiwProfilelink = By.xpath("//a[@text()='View profile']");
	public By InfiniteScroll = By.xpath("//a[text()='Infinite Scroll']");
	public By Infinitefooter = By.xpath("//div[@id='page-footer']");
	public By JQueryUIMenus = By.xpath("//a[text()='JQuery UI Menus']");
	public By enablelink = By.xpath("//ul[@id='menu']/child::li/a[@id='ui-id-2']");
	public By Disablelink = By.xpath("//a[@id='ui-id-1']");
	public By Downloadlink = By.xpath("//a[@id='ui-id-4']");
	public By Downloadlink_PDF = By.xpath("//a[@id='ui-id-6']");
	public By Downloadlink_CSV = By.xpath("//a[@id='ui-id-7']");
	public By Downloadlink_Excel = By.xpath("//a[@id='ui-id-6']");
	public By jequery = By.xpath("//a[@id='ui-id-5']");
	public By jequery_menu = By.xpath("//a[text()='menu']");
	
	public By JavaScriptAlerts= By.xpath("//a[text()='JavaScript Alerts']");
	public By JSAlert=By.xpath("//button[text()='Click for JS Alert']");
	public By JSAlertConfirm=By.xpath("//button[text()='Click for JS Confirm']");
	public By JSAlertpromt=By.xpath("//button[text()='Click for JS Prompt']");
	
	public By JavaScript_onloadeventerror= By.xpath("//a[text()='JavaScript onload event error']");
	public By JavaScript_onloadeventerrortext= By.xpath("//body[onload='loadError()']/p");
	public By jsresult = By.xpath("//p[@id='result']");
	public By KeyPresses=By.xpath("//a[text()='Key Presses']");
	public By Large_DeepDOM=By.xpath("//a[text()='Large & Deep DOM']");
	public By Large_DeepDOM_table=By.xpath("//table[@id='large-table']");
	public By Large_DeepDOM_Sibling=By.xpath("//div[contains(@id,'sibling-')]");
	
	public By MultipleWindows_Link=By.xpath("//a[text()='Multiple Windows']");
	public By NewWindow=By.xpath("//a[text()='Click Here']");
	
	public By NestedFrames=By.xpath("//a[text()='Nested Frames']");
	public By NotificationMessages=By.xpath("//a[text()='Notification Messages']");
	public By NotificationMessagesClickhere=By.xpath("//a[text()='Click here']");
	public By NotificationMessagesFlag=By.xpath("//div[@id='flash-messages']/div");
	public By Redirection=By.xpath("//a[text()='Redirect Link']");
	public By RedirectionClickhere=By.xpath("//a[text()='here']");
	//
	public By SecureFileDownload_Link=By.xpath("//a[text()='Secure File Download']");
	public String username="admin";

	public String password= "admin";
	public By Status_200 = By.xpath("//a[text()='200']");
		
}




