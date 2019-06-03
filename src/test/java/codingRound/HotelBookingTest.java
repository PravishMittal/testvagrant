package codingRound;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import pageFactory.HotelBookingPage;

public class HotelBookingTest extends CommonFunctions {

	@Given("^User books a hotel \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_books_a_hotel(String locality, String travellers) throws Throwable {

		HotelBookingPage hbt = PageFactory.initElements(driver, HotelBookingPage.class);

		objClick(hbt.lnkHotel);

		if (isElementDisplayed(hbt.txtLocality)) {
			objsetText(hbt.txtLocality, locality);
		}

		WebElement suggestionsFrom = driver.findElement(By.id("ui-id-1"));
		if (isListDisplayed(suggestionsFrom)) {
			hbt.txtLocality.sendKeys(Keys.ENTER);
		}

		hbt.datePickerCheckIn.sendKeys(Keys.ENTER);
		hbt.datePickerCheckOut.sendKeys(Keys.ENTER);

		new Select(hbt.ddlTravellerSelection).selectByVisibleText(travellers);

		objClick(hbt.btnSearch);

		printReportStatement("Available hotels are displayed", "Pass");

	}

}
