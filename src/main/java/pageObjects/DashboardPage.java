package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.CommonMethods;
import resources.TestData;


public class DashboardPage {

    public WebDriver driver;
    CommonMethods commonMethods;


    public DashboardPage(WebDriver driver){
        this.driver = driver;
        commonMethods = new CommonMethods(driver);
    }
    By dashboard = By.xpath("//span[contains(text(), 'Dashboard')]");
    By addEmployee = By.xpath("//span[contains(text(), 'Add Employee')]");
    By deleteButton = By.xpath("//tbody/tr[2]/td[7]/div/div[2]");
    By deleteConformation = By.xpath("//button[@type='button']/span[contains(text(), 'Yes')]");
    By dashboardIcon = By.xpath("//ul[@role='menu']/li[1]");
    By newlyAddedEmployeeEmail = By.xpath("//tbody/tr[2]/td[4]");
    By totalEmployees = By.xpath("//span[contains(text(), 'Total Employees')]/following-sibling::h4");
    By totalProfiles = By.xpath("//span[contains(text(), 'Total Profiles')]/following-sibling::h4");
    By completedProfile = By.xpath("//span[contains(text(), 'Completed Profiles')]/following-sibling::h4");
    By draftProfile = By.xpath("//span[contains(text(), 'Drafts')]/following-sibling::h4");
    By logOut = By.xpath("//ul[@role='menu']/li[3]");


    public WebElement getDashboardText(){
        return driver.findElement(dashboard);
    }

    public WebElement clickAddEmployee(){
        return driver.findElement(addEmployee);
    }


    public void deleteEmployee(){
        driver.findElement(deleteButton).click();
        commonMethods.waitForElement(driver, deleteConformation, 10).click();
    }

    public void clickDashBoard(){
        CommonMethods.waitForElement(driver, dashboardIcon, TestData.explicitWait).click();
    }

    public String setNewlyAddedEmployeeEmail(){
        String employeeEmail = CommonMethods.waitForElement(driver, newlyAddedEmployeeEmail, TestData.explicitWait).getText();
        return employeeEmail;
    }

    public int getTotalEmployee(){
        int employee = Integer.parseInt(CommonMethods.waitForElement(driver, totalEmployees, 10).getText());
        return employee;
    }
    public int getTotalProfiles(){
        int profiles = Integer.parseInt(CommonMethods.waitForElement(driver, totalProfiles, 10).getText());
        return profiles;
    }
    public int getCompletedProfile(){
        int completed = Integer.parseInt(CommonMethods.waitForElement(driver, completedProfile, 10).getText());
        return completed;
    }
    public int getDraftProfile(){
        int draft = Integer.parseInt(CommonMethods.waitForElement(driver, draftProfile, 10).getText());
        return draft;
    }

    public void setLogOut(){
        CommonMethods.waitForElement(driver, logOut, TestData.explicitWait).click();
    }
}
