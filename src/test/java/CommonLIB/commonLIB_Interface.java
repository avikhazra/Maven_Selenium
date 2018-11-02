package CommonLIB;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.lightbody.bmp.BrowserMobProxy;

public interface commonLIB_Interface {
	public  void  ClickObjectwithRefresh(By strxpath);
	public void ValidateElementText(By strxpath, String strtestdata);
	public void WriteGetElementTextonNotePad(By strxpath ,String Methodname, String listType ) throws IOException, Exception;
	public void TypeWithRobot(int[] keys) throws InterruptedException, Exception;
	public void PauseExecution(int pausedtime) throws InterruptedException, Exception;
	public  void  ClickObjectIfExists(By strxpath);
	public  void  HighlisghtThexpath(By strxpath);
	public  void  ScrollVieWObject(By strxpath);
	public  String  GetElementColorTextPositions(By strxpath);
	public void SpecificscreenShotoFSpecficRegion(By strxpath) throws IOException ;
	public void CheckCheckBox(By strxpath, String Check_Uncheck);
	public void CheckCheckBoxStatusChecking(By strxpath );
	public void PageReadyStateCheck(int intmilisecond) throws Exception ;
	public  WebElement  WaitforElementReturn(final By strxpath, int intsecond) ;
	public void VerifyElementExists(By strxpath,String strtestdata);
	public void DragAndDrop(By drop,By drag);
	public void SelectDropDown(By strxpath,String strtestdata);
	public void VerifySelectedvalueonDropdown(By strxpath,String strtestdata);
	public void VerifyDropdownContains(By strxpath,String strtestdata);
	public void VerifyEnabilityCheck(By strxpath,String strtestdata);
	public void VerifyVisibilityCheck(By strxpath,String strtestdata);
	public void SetOnparam(By strxpath,String strtestdata);
	public void GetAttributevalue(By strxpath,String strtestdata);
	public void ScreenShot() throws IOException ;
	public WebElement WaitforElementReturnForClick(final By strxpath, int intsecond) ;
	public  void  ClickObject(By strxpath) ;
	public WebElement elementretrun(By strxpath);

}
