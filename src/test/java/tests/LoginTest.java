package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Test class for login functionality.
 */
public class LoginTest extends BaseTest {

    // Page object representing the login page
    private LoginPage loginPage;

    // Initialize LoginPage before each test
    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(page);
    }

    // Test valid login using credentials from config
    @Test
    public void testLogin() {
        loginPage.performLogin();
    }
}

