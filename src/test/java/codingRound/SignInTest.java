package codingRound;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import pageFactory.FlightBookingPage;
import pageFactory.SignInPage;

public class SignInTest extends CommonFunctions {

	@Given("^User checks the trip \"([^\"]*)\"$")
	public void user_checks_the_trip(String errorMessage) throws Throwable {

		SignInPage sip = PageFactory.initElements(driver, SignInPage.class);

		objClick(sip.lnkYourTrip);

		objClick(sip.btnSignIn);

		driver.switchTo().frame("modal_window");
		objClick(sip.btnSignInModalWindow);

		String errors1 = sip.errorModalWindow.getText();
		Assert.assertTrue(errors1.contains(errorMessage));
		printReportStatement("SignIn Error is displayed", "Pass");

	}

}
