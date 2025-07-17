package base;

import com.microsoft.playwright.*;
import utils.ConfigReader;

public class BrowserFactory {

    public static Browser createBrowserContext(Playwright playwright, String browserName) {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(ConfigReader.get("headless")))
                .setSlowMo(Double.parseDouble(utils.ConfigReader.get("slowMo"))); // Add slowMo

        return switch (browserName.toLowerCase()) {
            case "chrome" -> playwright.chromium().launch(options.setChannel("chrome"));
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }
}
