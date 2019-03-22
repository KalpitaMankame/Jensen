package se.jensen.qa.helpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JensenDriver {

	private WebDriver driver;

	public JensenDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void quit() {
		driver.quit();
	}
	
	public void goBack() {
		driver.navigate().back();
	}

	public WebElement findElementByClass(String ele, String cssClass) {
		return driver.findElement(By.cssSelector(ele + "[class='" + cssClass + "']"));
	}

	public WebElement findElementByCssSelector(String cssSelector) {
		return driver.findElement(By.cssSelector(cssSelector));
	}

	public WebElement findElementByCssSelector(WebElement parent, String cssSelector) {
		return parent.findElement(By.cssSelector(cssSelector));
	}

	// public WebElement findElementByAttributes(String ele, String att) {
	// return driver.findElement(By.xpath("//" + ele + "[" + att + "]"));
	// }
	//
	// public WebElement findElementByAttributes(WebElement parent, String ele,
	// String attributes) {
	// return parent.findElement(By.xpath("//" + ele + "[" + attributes + "]"));
	// }

	public WebElement findElementById(String id) {
		return driver.findElement(By.id(id));
	}

	public WebElement findElementByIdIfPresent(String id) {
		List<WebElement> list = driver.findElements(By.id(id));
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public WebElement waitAndFindElementByClass(String ele, String cssClass) {
		return (new WebDriverWait(driver, 30)).until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ele + "[class='" + cssClass + "']")));
	}

	public WebElement waitAndFindElementByCssSelector(String cssSelector) {
		return (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
	}

	public List<WebElement> findElementsByCssSelector(String cssSelector) {
		return driver.findElements(By.cssSelector(cssSelector));
	}

	public List<WebElement> waitAndFindElementsByCssSelector(String cssSelector) {
		return (new WebDriverWait(driver, 30))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
	}

	public List<WebElement> findElementsByCssSelector(WebElement parent, String cssSelector) {
		return parent.findElements(By.cssSelector(cssSelector));
	}

	public WebElement waitAndFindElementById(String id) {
		return (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	// public WebElement waitAndFindElementByAttributes(String ele, String
	// attributes) {
	// return (new WebDriverWait(driver, 30))
	// .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//" + ele +
	// "[" + attributes + "]")));
	// }

	public void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public WebElement waitAndFindJensenTextArea(String name) {
		return (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("div[name=\"" + name + "\"] .ta-editor [contenteditable=\"true\"]")));
	}

	public void searchAndSelectFromJensenSelectComponent(String parentDivId, String searchInput) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement parentDiv = this.waitAndFindElementById(parentDivId);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				String classNames = parentDiv.getAttribute("class");
				if (classNames.equals("chosen-container chosen-container-single")) {
					return true;
				} else {
					return false;
				}
			}
		});
		parentDiv.click();
		WebElement searchBox = this.findElementByCssSelector(parentDiv, "div.chosen-search input");
		searchBox.sendKeys(searchInput);
		searchBox.sendKeys(Keys.RETURN);
 	}

	public WebElement findRowInJensenTable(String dataTitleName, String value) {
		List<WebElement> list = this.waitAndFindElementsByCssSelector("div .table-wrap > ul > li:not(.-header)");
		System.out.println(list);
		return list.stream().filter(e -> {
			WebElement columnName = this.findElementByCssSelector(e, "div[data-title-text='" + dataTitleName + "']");
			return value.equals(columnName.getText());
		}).findFirst().orElse(null);
	}

	public boolean isDeletableRowInJensenTable(String dataTitleName, String value) {
		WebElement row = this.findRowInJensenTable(dataTitleName, value);
		if (row != null) {
			WebElement arrow = this.findElementByCssSelector(row,
					"div[data-title-text='Namn'] > div.icon-entypo-chevron-small-down");
			arrow.click();
			List<WebElement> list = this.findElementsByCssSelector(row, "div.-full.-collapsible button.btn.btn-danger");
			return list.size() > 0;
		}
		return false;
	}

	public void deleteRowInJensenTable(String dataTitleName, String value) throws InterruptedException {
		WebElement row = this.findRowInJensenTable(dataTitleName, value);
		if (row != null) {
			WebElement arrow = this.findElementByCssSelector(row,
					"div[data-title-text='Namn'] > div.icon-entypo-chevron-small-down");
			arrow.click();
			WebElement deleteButton = this.findElementByCssSelector(row,
					"div.-full.-collapsible button.btn.btn-danger");
			deleteButton.click();
			Thread.sleep(7000);
		}
	}

	public void clickRowInJensenTable(String dataTitleName, String value) throws InterruptedException {
		WebElement row = this.findRowInJensenTable(dataTitleName, value);
			if (row != null) {
			row.click();
		}
	}
	
	public void writeGoogleDoc() {
		driver.getCurrentUrl();
		WebElement content = driver.findElement(By.className("kix-lineview"));
		content.sendKeys("Sample Text");
	}
	
	public void switchTabs() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}
}
