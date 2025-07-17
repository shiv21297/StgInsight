package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class ProjectTest extends BaseTest {

    @Test
    public void addProject() {
//        LoginPage loginPage = new LoginPage(page);
//        loginPage.login("shivas@stgit.com", "Reset@123");

        // Click on Projects link
        Locator projectsLink = page.getByText("Projects");
        projectsLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
        projectsLink.click();

        // Click on Add to add Project
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();

        // Fill in project details
        page.getByPlaceholder("Project Name").fill("StgProjectX");
        page.getByPlaceholder("Alias Name").fill("StgX");

        // Click on Project Type dropdown
        Locator projectTypeDropdown = page.locator("span:has-text('Select Project Type')");
        projectTypeDropdown.click();
        Locator projectType = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(Pattern.compile(".*Testing Project.*")));
        projectType.click();

        //CLick on Customer Dropdown and add Ford Motors
        Locator customerDropdown = page.locator("//div[@id='mat-select-value-5']");
        customerDropdown.click();
        Locator customer = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(Pattern.compile("FORD MOTOR COMPANY - USA")));
        customer.click();

        //CLick on Level_1 Manager and Select Rajesh
        Locator Manager1Dropdown = page.locator("//div[@id='mat-select-value-7']");
        Manager1Dropdown.click();
        Locator manager1 = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(Pattern.compile("Rajesh Sekar")));
        manager1.click();

        //Click on Level_2 Manager and Select Aruna
        Locator Manager2Dropdown = page.locator("//div[@id='mat-select-value-11']");
        Manager2Dropdown.click();
        Locator manager2 = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(Pattern.compile("Aruna Rani Durairaj")));
        manager2.click();

        // Start Date
        Locator startDateInput = page.locator("(//button[@aria-label='Open calendar'])[1]");
        startDateInput.click();
        // Select another visible date (e.g., 15th July 2025)
        page.locator("div.mat-calendar-body-cell-content:has-text('15')").click();

        // End Date
        Locator endDateInput = page.locator("(//button[@aria-label='Open calendar'])[2]");
        endDateInput.click();
        // Navigate to October 2025
        for (int i = 0; i < 3; i++) {
            page.locator("button.mat-calendar-next-button").click();
            page.waitForTimeout(500);
        }
        Locator endDateCell = page.locator("div.mat-calendar-body-cell-content:has-text('31')");
        endDateCell.click();

        // Tab multiple times until Estimated Effort field gets focus
        for (int i = 0; i < 5; i++) {
            page.keyboard().press("Tab");
            page.waitForTimeout(300);
        }
        //Click on Estimated Effort
        page.keyboard().type("7");

        // Fill Sprint Duration
        page.getByPlaceholder("Enter calendar days").fill("10");

        // Click Create button
        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile(".*Create.*"))).click();

        page.waitForTimeout(3000);
    }
}