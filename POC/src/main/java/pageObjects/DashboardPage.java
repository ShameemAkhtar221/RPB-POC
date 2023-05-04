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
    By newlyAddedEmployee = By.xpath("//tbody/tr[2]//strong");
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

    public String setNewlyAddedEmployee(){
        String employeeName = CommonMethods.waitForElement(driver, newlyAddedEmployee, TestData.explicitWait).getText();
        return employeeName;
    }

    public int getTotalEmployee(){
        int employee = Integer.parseInt(driver.findElement(totalEmployees).getText());
        System.out.println(Integer.valueOf(employee));
        return employee;
    }
    public int getTotalProfiles(){
        int profiles = Integer.parseInt(driver.findElement(totalProfiles).getText());
        return profiles;
    }
    public int getCompletedProfile(){
        int completed = Integer.parseInt(driver.findElement(completedProfile).getText());
        return completed;
    }
    public int getDraftProfile(){
        int draft = Integer.parseInt(driver.findElement(draftProfile).getText());
        return draft;
    }

    public void setLogOut(){
        CommonMethods.waitForElement(driver, logOut, TestData.explicitWait).click();
    }
}
