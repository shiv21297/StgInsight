package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPage {

    //Logger instance
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    // Playwright page instance representing the browser tab/session
    private final Page page;

    // Locators for Elements
    private final Locator emailField;
    private final Locator passwordField;
    private final Locator loginButton;

    // Credentials from Config
    private final String email = ConfigReader.get("username");
    private final String password = ConfigReader.get("password");
    private final String loginUrl = ConfigReader.get("baseURL");
    private final Locator usernameElement;

    /**
     * Initializes page and element locators.
     */
    public LoginPage(Page browserpage) {
        this.page = browserpage;
        this.emailField = page.getByLabel("Email");
        this.passwordField = page.getByLabel("Password");
        this.loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Pattern.compile(".*Sign in.*", Pattern.CASE_INSENSITIVE)));
        this.usernameElement = page.getByText(Pattern.compile(".*Shivas.*", Pattern.CASE_INSENSITIVE));
    }

    // Performs Loginflow with verification
    public void performLogin() {
        logger.info("Starting login process");
        validateConfig();
        navigateToLoginPage();
        doLogin(email, password);
        verifySuccessfulLogin();
        logger.info("Page title after login: {}", page.title());
    }

    // Navigates the browser to Loginurl
    private void navigateToLoginPage() {
        page.navigate(loginUrl);
    }

    // Fills Login_form and clicks Signin
    private void doLogin(String email, String password) {
        logger.debug("Filling in login form");
        emailField.fill(email);
        passwordField.fill(password);
        loginButton.click();
        logger.debug("Clicked login button");
    }

    // Verifies successful login
    private void verifySuccessfulLogin() {
        // Wait for page to redirect
        logger.debug("Verifying successful login Url");
        page.waitForURL(url -> url.contains("/adminreport"));

        // Assert username is visible
        assertThat(usernameElement).isVisible();
        logger.debug("Verifying user element visibility");

        // Login success
        logger.debug("Logged in successfully with email: {}", email);
    }

    private void validateConfig() {
        // Preconditions: Check if config values are valid
        logger.debug("Validating config");
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email is missing in config");
        if (password == null || password.trim().isEmpty())
            throw new IllegalArgumentException("Password is missing in config");
        if (loginUrl == null || loginUrl.trim().isEmpty())
            throw new IllegalArgumentException("Login url is missing in config");

    }
}