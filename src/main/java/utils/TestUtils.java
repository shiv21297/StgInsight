package utils;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    public static String takeScreenshot(Page page, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "screenshots/" + screenshotName + " " + timestamp + ".png";

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));
        System.out.println("Screenshot saved to: " + screenshotPath);

        return screenshotPath;  //so it can be used in Extent Reports
    }
}
