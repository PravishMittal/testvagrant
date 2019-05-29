package codingRound;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import pageFactory.HotelBookingPage;

public class HotelBookingTest extends CommonFunctions {

	/*
	 * @FindBy(xpath = "//a[contains(text(),'Hotels')]") private WebElement
	 * hotelLink;
	 * 
	 * @FindBy(id = "Tags") private WebElement localityTextBox;
	 * 
	 * @FindBy(id = "SearchHotelsButton") private WebElement searchButton;
	 * 
	 * @FindBy(id = "travellersOnhome") private WebElement travellerSelection;
	 */

	// @Test
	// public void shouldBeAbleToSearchForHotels() throws InterruptedException {
	// setDriverPath();
	@Given("^User books a hotel$")
	public void user_books_a_hotel() throws Throwable {

		HotelBookingPage hbt = PageFactory.initElements(driver, HotelBookingPage.class);
		// driver.get("https://www.cleartrip.com/");
		Thread.sleep(3000);
		hbt.hotelLink.click();

		hbt.localityTextBox.sendKeys("Indiranagar, Bangalore");

		new Select(hbt.travellerSelection).selectByVisibleText("1 room, 2 adults");
		hbt.searchButton.click();
		
		printReportStatement("Available hotels are displayed", "Pass");

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
	 * new ChromeDriver(options); }
	 */

}
