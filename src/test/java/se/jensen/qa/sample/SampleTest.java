package se.jensen.qa.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import se.jensen.qa.SeleniumAbstractTest;

public class SampleTest extends SeleniumAbstractTest{

//	@Override
//	protected void initSubPageActions() {
//	}

    @Test(description="Launches the WordPress site")
    public void openGoogle(){
        final String searchKey = "TestNG";
        System.out.println("Search " + searchKey + " in google");
        driver.navigate().to("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        System.out.println("Enter " + searchKey);
        element.sendKeys(searchKey);
        System.out.println("submit");
        element.submit();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase()
                    .startsWith(searchKey.toLowerCase());
            }
        });
        System.out.println("Got " + searchKey + " results");
    }

}
