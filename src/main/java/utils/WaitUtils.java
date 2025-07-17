package utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitUtils {

    public static boolean waitForSelectorVisible(Page page, String selector, int timeout) {
        try {
            page.waitForSelector(selector,
                    new Page.WaitForSelectorOptions()
                            .setTimeout(timeout)
                            .setState(WaitForSelectorState.VISIBLE)
            );
            return true;
        } catch (Exception e) {
            System.err.println("Element not visible: " + selector + " | Exception: " + e.getMessage());
            return false;
        }
    }
}
