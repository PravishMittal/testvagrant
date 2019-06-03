package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelBookingPage {

	@FindBy(xpath = "//a[contains(text(),'Hotels')]")
	public WebElement lnkHotel;

	@FindBy(id = "Tags")
	public WebElement txtLocality;

	@FindBy(id = "SearchHotelsButton")
	public WebElement btnSearch;

	@FindBy(id = "travellersOnhome")
	public WebElement ddlTravellerSelection;
	
	@FindBy(xpath = "//label[@title='Check-in date']//..//..//input[@data-datepicker='click']")
	public WebElement datePickerCheckIn;
	
	@FindBy(xpath = "//label[@title='Check-out date']//..//..//input[@data-datepicker='click']")
	public WebElement datePickerCheckOut;

}
