package se.jensen.qa.ui.actions;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.interactions.Actions;

import se.jensen.qa.helpers.JensenDriver;

public class HomePageActions extends BaseActions {
	
	private static final Map<String, String> TABS = new HashMap<>();
	static {
		TABS.put("dashboard", "#/");
		TABS.put("news", "#/nyheter/");
		TABS.put("courses", "#/courses/");
		TABS.put("assignments", "#/assignments/");
		TABS.put("lessons", "#/lektioner");
		TABS.put("coach", "#/coach/");
		TABS.put("study", "#/study/");
		TABS.put("warnings", "#/varningar/");
		TABS.put("grading", "#/grading/");
		TABS.put("profile", "#/profile/");
		TABS.put("logout", "logout");
	}

	public HomePageActions(JensenDriver jensenDriver) {
		super(jensenDriver);
	}
	public void changeTab(String tab) {
    	Actions action = new Actions(jensenDriver.getDriver());
    	jensenDriver.waitAndFindElementByCssSelector("a[href='" + TABS.get(tab) +"']").click();
		jensenDriver.waitForPageLoad();
		if (!tab.equals("logout")) {
			action.moveToElement(jensenDriver.waitAndFindElementByClass("ul", "sidemenu-nav")).perform();
		}
    }

    public void logout() throws InterruptedException {
		System.out.println("logging out");
    	this.changeTab("logout");
		System.out.println("logged out");
    	System.out.println("deleting cookies");
    	// this is temporary thing till logout is fixed. sometimes user is not logged out completely
    	jensenDriver.deleteAllCookies();
    	Thread.sleep(1000);
    }

}
