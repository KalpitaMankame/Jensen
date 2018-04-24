package se.jensen.qa.ui.actions;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.openqa.selenium.WebElement;

import se.jensen.qa.helpers.JensenDriver;
import se.jensen.qa.helpers.JensenEnvConstants;
import se.jensen.qa.helpers.JsonParser;

public class LoginPageActions extends BaseActions {

	private Map<String, Map<String, String>> loginData;
	public LoginPageActions(JensenDriver jensenDriver) throws JsonParseException, JsonMappingException, IOException {
		super(jensenDriver);
		loginData = JsonParser.jsonToTwoLevelMap("Login/credentials.json");
	}

    public void loginAs(String role, String key) {
    	String [] credentials = loginData.get(role).get(key).split(":");
    	this.login(credentials[0], credentials[1]);
    	
    }
    private void login(String email, String password) {
    	System.out.println("loging in with user " + email);
    	jensenDriver.openUrl(JensenEnvConstants.BASE_URL);
    	WebElement loginBox = jensenDriver.waitAndFindElementByClass("div", "login-box");
    	jensenDriver.findElementByCssSelector(loginBox, "input[value='Logga in']").click();
    	WebElement signInWithDifferentAccount = jensenDriver.findElementByIdIfPresent("account-chooser-link");
    	if ( signInWithDifferentAccount != null) {
    		signInWithDifferentAccount.click();
    		jensenDriver.waitAndFindElementById("account-chooser-add-account").click();
    	} else {
    		WebElement addAccount = jensenDriver.findElementByIdIfPresent("account-chooser-add-account");
    		if (addAccount != null) {
    			addAccount.click();
    		}
    	}
    	jensenDriver.waitAndFindElementById("Email").sendKeys(email);
    	jensenDriver.findElementById("next").click();
    	jensenDriver.waitAndFindElementById("Passwd").sendKeys(password);
    	jensenDriver.waitAndFindElementById("signIn").click();
		jensenDriver.waitForPageLoad();
    	System.out.println("Home page loaded");
    }

}