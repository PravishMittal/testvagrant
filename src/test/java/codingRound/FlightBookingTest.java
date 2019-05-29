package codingRound;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;

public class FlightBookingTest extends CommonFunctions {

	@Given("^User logins into ClearTrip \"([^\"]*)\"$")
	public void user_logins_into_ClearTrip(String arg1) throws Throwable {
		System.out.println(arg1);
		driver.get(arg1);
	}

	// @Test
	// public void testThatResultsAppearForAOneWayJourney() throws
	// InterruptedException {
	@Given("^User books a flight$")
	public void user_books_a_flight() throws Throwable {
		// setDriverPath();

		// driver.get("https://www.cleartrip.com/");
		waitFor(2000);
		driver.findElement(By.id("OneWay")).click();
		Thread.sleep(5000);

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// wait for the auto complete options to appear for the origin

		waitFor(5000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		waitFor(5000);
		// select the first item from the destination auto complete list
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		// driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();
		driver.findElement(By.xpath("//input[@data-datepicker='click']")).click();

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();
		System.out.println(df.format(dt));

		driver.findElement(By.xpath("//input[@data-datepicker='click']")).sendKeys(df.format(dt));

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		waitFor(5000);
		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		printReportStatement("Available flights are displayed", "Pass");
		// close the browser
		//driver.quit();

	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/*
	 * private void setDriverPath() {
	 * 
	 * if (PlatformUtil.isMac()) { System.setProperty("webdriver.chrome.driver",
	 * System.getProperty("user.dir")+"chromedriver"); } if
	 * (PlatformUtil.isWindows()) { System.setProperty("webdriver.chrome.driver",
	 * System.getProperty("user.dir")+"chromedriver.exe"); } if
	 * (PlatformUtil.isLinux()) { System.setProperty("webdriver.chrome.driver",
	 * System.getProperty("user.dir")+"chromedriver_linux"); }
	 * 
	 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
	 * + "\\chromedriver.exe"); ChromeOptions options = new ChromeOptions();
	 * options.addArguments("--start-maximized", "--disable-extensions"); driver =
	 * new ChromeDriver(options);
	 * 
	 * }
	 */
}
