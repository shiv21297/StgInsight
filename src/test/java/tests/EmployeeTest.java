package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.EmployeePage;


public class EmployeeTest extends BaseTest {

    @Test
    public void testSearchEmployee() {
        EmployeePage.performLoginAndSearch(page);
    }
}