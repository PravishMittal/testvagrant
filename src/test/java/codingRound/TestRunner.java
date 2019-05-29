package codingRound;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Feature", glue = { "codingRound" })

public class TestRunner extends AbstractTestNGCucumberTests {

	TestNGCucumberRunner testNGCucumberRunner;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		Calendar currentDate1 = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateN = formatter.format(currentDate1.getTime()).replace("/", "_");
		String dateNow = dateN.replace(":", "_");
		report = new ExtentReports(System.getProperty("user.dir") + "\\Reports\\ExtentReport_" + dateNow + ".html");

	}

	@BeforeMethod()
	public void abcd() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized", "--disable-extensions","--disable-notifications");
		driver = new ChromeDriver(options);

	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {

		String x1 = cucumberFeature.getCucumberFeature().getPath();

		logger = report.startTest(x1);
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		Calendar currentDate1 = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		String dateN = formatter.format(currentDate1.getTime()).replace("/", "_");
		String dateNow = dateN.replace(":", "_");
		if (result.getStatus() == ITestResult.FAILURE) {

			String yourfilepath = System.getProperty("user.dir") + "\\Screenshots\\";
			File snapshort_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(snapshort_file, new File(yourfilepath + dateNow + "test.png"));
			String image = logger.addScreenCapture(yourfilepath + dateNow + "test.png");
			logger.log(LogStatus.FAIL, "Scenario Failure" + image + result.getMethod());
			report.endTest(logger);
			report.flush();
		} else {
			String yourfilepath = System.getProperty("user.dir") + "\\Screenshots\\";
			File snapshort_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(snapshort_file, new File(yourfilepath + dateNow + "test.png"));
			String image = logger.addScreenCapture(yourfilepath + dateNow + "test.png");
			logger.log(LogStatus.PASS, "Scenario Success" + image);
			report.endTest(logger);
			report.flush();
		}
		driver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}

}
