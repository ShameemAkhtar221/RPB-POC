package pocPackage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import resources.base;

import java.io.File;
import java.io.IOException;


 public class LoginTest extends base {

    public static WebDriver driver;

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(dataProvider = "LoginTestData", priority = 0)
    public void login(String email, String password, String validData) throws IOException {

        driver.get(prop.getProperty("url"));
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail().sendKeys(email);
        loginPage.enterPassword().sendKeys(password);
        File file = loginPage.enterEmail().getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//screenshots//" +"email"+".png";
        FileUtils.copyFile(file, new File(destinationFile));
        loginPage.LoginButton().click();

        Assert.assertEquals(loginPage.enterEmail().getAttribute("value"), email);
        Assert.assertEquals(loginPage.enterPassword().getAttribute("value"), password);


        switch (validData){
            case "inValid":
                Assert.assertTrue(loginPage.emailErrorDisplayed());
                Assert.assertTrue(loginPage.passwordErrorDisplayed());
                Assert.assertEquals(driver.getCurrentUrl(), "https://qa.poc.synavos.com/login", "message");
                break;
            case "valid":
                DashboardPage dashboardPage = new DashboardPage(driver);
                Assert.assertEquals(dashboardPage.getDashboardText().getText(), "Dashboard", "message");
                break;
            default:
        }
    }

    @Test(priority = 1, testName = "Verify that admin is able to log out.")
    public void logout(){
        LogoutTest.logOut(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @DataProvider(name = "LoginTestData")
    public Object[][] TestData(){
        return new Object[][]{
                {"", "", "inValid"},
                {"derer@df", "234", "inValid"},
                {"admin@synavos.com", "admin@synavos", "valid"}
        };
    }
}
