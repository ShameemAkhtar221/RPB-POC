package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.CommonMethods;
import resources.TestData;

public class EmployeeSuccessScreen {

    public WebDriver driver;
    CommonMethods commonMethods;
    public EmployeeSuccessScreen(WebDriver driver){
        this.driver = driver;
        commonMethods = new CommonMethods(driver);
    }

    By successMessageXPath = By.xpath("//h3[contains(text(), 'You have added this employee')]");
    By createProfile = By.xpath("//button[@type='button']");
    By profileName = By.xpath("//input[@name='profileName']");
    By doneButton = By.xpath("//button[@type='button']/span[contains(text(), 'Done')]");

    public String getSuccessMessage() {
        String successMessage = CommonMethods.waitForElement(driver, successMessageXPath, TestData.explicitWait).getText();
        return successMessage;
    }

    public void createProfileButton(){
        CommonMethods.waitForElement(driver, createProfile, TestData.explicitWait).click();
    }

    public WebElement enterProfileName(){
        WebElement element = CommonMethods.waitForElement(driver, profileName, TestData.explicitWait);
        return element;
    }

    public InfoPage doneButton(){
        InfoPage infoPage = new InfoPage(driver);
        driver.findElement(doneButton).click();
        return infoPage;
    }
}
