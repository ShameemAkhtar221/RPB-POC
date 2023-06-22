package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.CommonMethods;
import resources.TestData;

public class InfoPage {
    public WebDriver driver;

    public InfoPage(WebDriver driver){
        this.driver = driver;
    }

    By infoTab = By.xpath("//div[@class='ant-tabs-tab ant-tabs-tab-active']/div");
    By git = By.xpath("//input[@name='gitLink']");
    By linkedIn = By.xpath("//input[@name='linkedLink']");
    By summery = By.xpath("//textarea[@name='basicInfoSummary']");
    By saveAndContinue = By.xpath("//button[@type='button']/span[contains(text(), 'Save & Continue')]");


    public WebElement activeTab(){
        return CommonMethods.waitForElement(driver, infoTab, TestData.explicitWait);
    }
    public WebElement addGitAddress(){
        return CommonMethods.waitForElement(driver, git, TestData.explicitWait);
    }
    public WebElement addLinkedInAddress(){
        return driver.findElement(linkedIn);
    }
    public void addSummery() throws InterruptedException {
        //CommonMethods.typeWithSpeed(summery, TestData.infoSummery, TestData.speedLimit, driver);
        driver.findElement(summery).sendKeys(TestData.infoSummery);
    }
    public ExperiencePage saveAndContinueButton() throws InterruptedException {
        ExperiencePage experiencePage = new ExperiencePage(driver);
        CommonMethods.scrollElementIntoView(saveAndContinue, driver).click();
        return experiencePage;
    }

}
