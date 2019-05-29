package codingRound;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.en.Given;

public class CommonFunctions extends TestRunner {

	public void printReportStatement(String msg, String status) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_MM_SS");
			Date dt = new Date();

			if (status.equalsIgnoreCase("Pass")) {
				TestRunner.logger.log(LogStatus.PASS, msg);
			} else {
				String yourfilepath = System.getProperty("user.dir") + "\\Screenshots\\";
				File snapshort_file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(snapshort_file, new File(yourfilepath + df.format(dt) + "test.png"));
				String image = TestRunner.logger.addScreenCapture(yourfilepath + df.format(dt) + "test.png");
				TestRunner.logger.log(LogStatus.FAIL, msg + image);

			}
		} catch (Exception e) {
			System.out.println("Not able to print to the report: " + e);
			Assert.fail("Failed");
		}
	}

}
