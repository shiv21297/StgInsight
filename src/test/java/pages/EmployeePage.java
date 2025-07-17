package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;

public class EmployeePage {

    private static final Logger logger = LoggerFactory.getLogger(EmployeePage.class);
    private final Page page;

    private final Locator employeeLink;
    private final Locator searchInput;
    private final Locator employeeName;
    private final Locator projectDropdown;
    private final Locator billableOption;

    public EmployeePage(Page page) {
        this.page = page;
        this.employeeLink = page.getByText(Pattern.compile(".*Employee.*", Pattern.CASE_INSENSITIVE));
        this.searchInput = page.getByPlaceholder("Search");
        this.employeeName = page.getByText(Pattern.compile(".*Shiva Saravanan.*", Pattern.CASE_INSENSITIVE));
        this.projectDropdown = page.locator("//mat-select[@formcontrolname='projects']");
        this.billableOption = page.locator("mat-option").nth(1);
    }

    public void searchEmployee() {
        logger.info("Navigating to Employee page");
        employeeLink.click();
        logger.info("Start searching Employees");
        searchInput.click();
        searchInput.fill("shiv");
        page.keyboard().press("Enter");
        employeeName.click();
        projectDropdown.click();
        billableOption.click();
    }

    public void assignBillableProject() {
        projectDropdown.click();
    }
    public static void performLoginAndSearch(Page page) {
        //Perform Login
        LoginPage loginPage = new LoginPage(page);
        loginPage.performLogin();

        //After login Perform do employee search
        EmployeePage employeepage = new EmployeePage(page);
        employeepage.searchEmployee();
    }
}
