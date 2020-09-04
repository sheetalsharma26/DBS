package dbs.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {

	
	public static String captureScreenshot(WebDriver driver) {
		String screenshotPath = System.getProperty("user.dir") + "./Screenshots/" + getCurrentDateTime() + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			org.openqa.selenium.io.FileHandler.copy(src, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Screenshot not saved " + e.getMessage());
		}
		return screenshotPath;
	}
	public static String getCurrentDateTime() {
		DateFormat customDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customDateFormat.format(currentDate);
	}
	public static String getCurrentDate() {
		DateFormat customDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm");
		Date currentDate = new Date();
		return customDateFormat.format(currentDate);
	}
}
