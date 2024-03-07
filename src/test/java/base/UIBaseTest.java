package base;

import com.microsoft.playwright.Page;
import core.factory.PlaywrightFactory;
import core.pages.FlyDubai;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import java.util.Properties;

public class UIBaseTest {

	PlaywrightFactory pf;
	Page page;
	protected Properties prop;

	protected FlyDubai flyDubai;
	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		pf = new PlaywrightFactory();

		prop = pf.init_prop();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		page = pf.initBrowser(prop);
		page.locator("text=Accept All").click();
		flyDubai = new FlyDubai(page);
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
