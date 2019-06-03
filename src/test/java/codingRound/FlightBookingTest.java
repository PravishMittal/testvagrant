package codingRound;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import pageFactory.FlightBookingPage;

public class FlightBookingTest extends CommonFunctions {
	
	@Given("^User logins into ClearTrip$")
	public void user_logins_into_ClearTrip() throws Throwable {
		ReadProperties rd = new ReadProperties();
		driver.get(rd.getProperty("URL"));
	}

	@Given("^User books a flight \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_books_a_flight(String source, String destination) throws Throwable {
		FlightBookingPage fbt = PageFactory.initElements(driver, FlightBookingPage.class);
		objClick(fbt.btnOneWay);

		if (isElementDisplayed(fbt.txtFromSource)) {
			objsetText(fbt.txtFromSource, source);
		}

		WebElement suggestionsFrom = driver.findElement(By.id("ui-id-1"));
		if(isListDisplayed(suggestionsFrom)){
			objClick(suggestionsFrom.findElements(By.tagName("li")).get(0));
		}
		
		if (isElementDisplayed(fbt.txtToDestination)) {
			objsetText(fbt.txtToDestination, destination);
		}

		WebElement suggestionsTo = driver.findElement(By.id("ui-id-2"));
		if(isListDisplayed(suggestionsTo)){
			objClick(suggestionsTo.findElements(By.tagName("li")).get(0));
		}

		objsetText(fbt.datePicker, getDate());
		
		// all fields filled in. Now click on search
		objClick(fbt.btnSearchFlights);

		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementDisplayed(fbt.titleSearchSummary));

		printReportStatement("Available flights are displayed", "Pass");
	}

	
}
