package se.jensen.qa;

import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import se.jensen.qa.helpers.JensenDriver;
import se.jensen.qa.ui.actions.AssignmentPageActions;
import se.jensen.qa.ui.actions.HomePageActions;
import se.jensen.qa.ui.actions.LessonsPageActions;
import se.jensen.qa.ui.actions.LoginPageActions;

public abstract class SeleniumAbstractTest {

	private static ResourceBundle driverProperties = ResourceBundle.getBundle("driver");
	protected WebDriver driver;
	protected JensenDriver jensenDriver;
	protected LoginPageActions loginPageActions;
	protected HomePageActions homePageActions;
	protected LessonsPageActions lessonsPageActions;
	protected AssignmentPageActions assignmentPageActions;

	@BeforeSuite()
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", driverProperties.getString("webdriver.chrome.driver"));
		System.setProperty("webdriver.gecko.driver", driverProperties.getString("webdriver.gecko.driver"));
	}

//	@AfterTest(alwaysRun = true)
//	public void tearDown() throws InterruptedException {
//		System.out.println("Quitting the browser");
//		Thread.sleep(5000);
//		jensenDriver.quit();
//	}

	private void initDefaultPageActions() {
		try {
			loginPageActions = new LoginPageActions(jensenDriver);
			homePageActions = new HomePageActions(jensenDriver);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
		this.initPageActions();
	}

	protected void initPageActions() {

	}

	@BeforeTest
	@Parameters({ "browser" })
	public void selectBrowser(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			throw new Exception("no such browser " + browser);
		}
		jensenDriver = new JensenDriver(driver);
		initDefaultPageActions();
	}

	//@AfterMethod
	public void logout() throws InterruptedException {
		homePageActions.logout();
	}

}
