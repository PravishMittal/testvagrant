package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightBookingPage {

	@FindBy(id = "OneWay")
	public WebElement btnOneWay;

	@FindBy(id = "FromTag")
	public WebElement txtFromSource;
	
	@FindBy(id = "ToTag")
	public WebElement txtToDestination;

	@FindBy(id = "SearchBtn")
	public WebElement btnSearchFlights;

	@FindBy(className = "searchSummary")
	public WebElement titleSearchSummary;
	
	@FindBy(xpath = "//input[@data-datepicker='click']")
	public WebElement datePicker;

}
