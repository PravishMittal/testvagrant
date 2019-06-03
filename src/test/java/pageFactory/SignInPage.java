package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage {

	@FindBy(linkText = "Your trips")
	public WebElement lnkYourTrip;

	@FindBy(id = "SignIn")
	public WebElement btnSignIn;
	
	@FindBy(id = "signInButton")
	public WebElement btnSignInModalWindow;
	
	@FindBy(id = "errors1")
	public WebElement errorModalWindow;
}
