package codingRound;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import cucumber.runtime.CucumberException;

public class CommonFunctions extends TestRunner {

	public void printReportStatement(String msg, String status) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_MM_SS");
			Date dt = new Date();

			if (status.equalsIgnoreCase("Pass")) {
				TestRunner.logger.log(LogStatus.PASS, msg);
			} else {
				String yourfilepath = System.getProperty("user.dir") + "\\Screenshots\\";
				File snapshort_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(snapshort_file, new File(yourfilepath + df.format(dt) + "test.png"));
				String image = TestRunner.logger.addScreenCapture(yourfilepath + df.format(dt) + "test.png");
				TestRunner.logger.log(LogStatus.FAIL, msg + image);

			}
		} catch (Exception e) {
			System.out.println("Not able to print to the report: " + e);
			Assert.fail("Failed");
		}
	}

	public void objClick(WebElement obj) throws Exception {
		int findobj = 0;
		while (findobj <= 5) {
			try {

				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.elementToBeClickable(obj));
				//printReportStatement(obj.getText() + " object is clicked", "Pass");
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", obj);
				findobj = 6;
			} catch (StaleElementReferenceException sere) {
				findobj = 6;
			} catch (CucumberException sere) {
				findobj = 6;
			} catch (Exception e) {
				//printReportStatement("Object either doesn't exist or not found. Exception: " + e, "Fail");
				findobj = 6;
			}
		}
	}

	public void objsetText(WebElement obj, String str) throws Exception {
		int findobj = 0;
		while (findobj <= 5) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.visibilityOf(obj));
				obj.clear();
				obj.sendKeys(str);
				//printReportStatement(str + " is provided in " + obj.toString(), "Pass");
				findobj = 6;
			} catch (StaleElementReferenceException sere) {
				findobj=6;
			} catch (Exception e) {
				//printReportStatement("Object either doesn't exist or not found. Exception: " + e, "Fail");

			}
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean elementDisplayed = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement ele = (WebElement) wait.until(ExpectedConditions.visibilityOf(element));

			if (ele.isDisplayed()) {
				elementDisplayed = true;
			}
		} catch (Exception e) {

			elementDisplayed = false;
		}
		return elementDisplayed;
	}

	public boolean isListDisplayed(WebElement element) throws Exception {
		boolean elementStatus = false;
		int findobj = 0;
		while (findobj <= 10) {
			try {
				List<WebElement> options = element.findElements(By.tagName("li"));

				if (options.size() == 0) {
					Thread.sleep(1000);
					findobj++;
					continue;
				} else {
					elementStatus = true;
					break;
				}
			} catch (Exception e) {
				//printReportStatement("Suggestions are not displayed. Exception: " + e, "Fail");
				elementStatus = false;
			}

		}

		return elementStatus;
	}
	
	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		return df.format(dt);
	}
}
