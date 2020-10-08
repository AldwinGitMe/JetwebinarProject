package Library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeShots {
	
	public static void captureScreenshotOnFail(WebDriver driver, String screenshotName) throws IOException {
		try {
			TakesScreenshot shoot = (TakesScreenshot) driver;
			File src = shoot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(".//BugScreenshots//"+screenshotName+".png"));
			System.out.println(" SCREENSHOT TAKEN !!! ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(" ERROR WHEN TAKING SCREENSHOT "+e.getMessage());
		}
	}

}
