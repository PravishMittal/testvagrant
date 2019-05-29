package codingRound;

import org.openqa.selenium.By;
import org.testng.Assert;

import cucumber.api.java.en.Given;

public class SignInTest extends CommonFunctions {

	// @Test
	// public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

	// setDriverPath();
	@Given("^User checks the trip$")
	public void user_checks_the_trip() throws Throwable {

		// driver.get("https://www.cleartrip.com/");
		waitFor(2000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();

		waitFor(5000);
		driver.switchTo().frame("modal_window");
		driver.findElement(By.id("signInButton")).click();

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
		printReportStatement("SignIn Error is displayed", "Pass");
		// driver.quit();
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}

	/*
	 * private void setDriverPath() {
	 * 
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
	 * new ChromeDriver(options); }
	 */

}
