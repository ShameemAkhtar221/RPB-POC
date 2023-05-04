 package pocPackage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import resources.base;

import java.io.IOException;

public class LoginTest extends base {

    public WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(dataProvider = "LoginTestData")
    public void login(String email, String password) throws IOException {

        driver.get(prop.getProperty("url"));
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail().sendKeys(email);
        loginPage.enterPassword().sendKeys(password);
        loginPage.LoginButton().click();
        if(loginPage.LoginButton().isDisplayed()){
            Assert.assertEquals(driver.getCurrentUrl(), "https://dev.poc.synavos.com/login", "message");
        }else {
            DashboardPage dashboardPage = new DashboardPage(driver);
            Assert.assertEquals(dashboardPage.getDashboardText().getText(), "Dashboard", "message");
        }
    }

    @AfterTest
    public void teardown(){
        driver.close();
    }

    @DataProvider(name = "LoginTestData")
    public Object[][] TestData(){
        Object [][] loginData = new Object[3][2];

        loginData[0][0] = "";
        loginData[0][1] = "";

        loginData[1][0] = "derewt@df";
        loginData[1][1] = "232j4dsf";

        loginData[2][0] = "admin@synavos.com";
        loginData[2][1] = "admin@synavos";

        return loginData;
    }
}
