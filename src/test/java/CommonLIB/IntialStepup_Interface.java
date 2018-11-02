package CommonLIB;

import java.io.IOException;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import net.lightbody.bmp.BrowserMobProxy;

public interface IntialStepup_Interface {
	public Object[][]BrowserProvider();
	public WebDriver BrowserLaunchwithCapabilities(String strdrivername) throws IOException, InterruptedException;
	public String BrowserClosing(String strdrivername) throws IOException;
	public WebDriver FocusOnUrl(String strurl,  int Alertwaitcount) throws InterruptedException ;
	public void teardownAftertest() throws IOException ;
	public void Beforetestenothing();
	public Proxy getSeleniumProxy(BrowserMobProxy proxyServer);
	public BrowserMobProxy getProxyServer();
}
