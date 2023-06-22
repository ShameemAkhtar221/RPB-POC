package pocPackage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;


public class LogoutTest{
    public WebDriver driver;

    @Test(priority = 1, testName = "Verify that admin is able to log out.")
    public static void logOut(WebDriver driver){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.setLogOut();
    }

}
