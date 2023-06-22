package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.CommonMethods;
import resources.TestData;


public class ExperiencePage {
    public WebDriver driver;

    public ExperiencePage(WebDriver driver){
        this.driver = driver;
    }

    By startDate = By.xpath("//input[@name='startsFrom']");
    By chooseMonth = By.xpath("//tbody/tr[1]/td[1]");
    By jobDescription = By.xpath("//textarea[@name='jobDescription']");
    By addHere = By.xpath("//button[@type='button']/span[contains(text(), 'Add Here')]");
    By companyName = By.xpath("//input[@name='companyName']");
    By postExperienceDate = By.xpath("//input[@placeholder='Select month']");

    By saveAndContinue = By.xpath("//button[@type='button']/span[contains(text(), 'Save & Continue')]");

    By designation = By.cssSelector("input[name='designation']");

    public void addStartDate(){
        driver.findElement(startDate).click();
        driver.findElement(chooseMonth).click();
    }

    public WebElement setDesignation(){
        return driver.findElement(designation);
    }

    public WebElement setPreviousCompanyDesignation(){
        return driver.findElements(designation).get(1);
    }

    public void addJobDescription() throws InterruptedException {
//        CommonMethods.typeWithSpeed(jobDescription, TestData.jobDescription, TestData.speedLimit, driver);
        driver.findElement(jobDescription).sendKeys(TestData.jobDescription);
    }

    public WebElement clickAddHereButton(){
        return driver.findElement(addHere);
    }

    public WebElement enterCompanyName(){
        WebElement element =  driver.findElements(companyName).get(1);
        return element;
    }

    public void postExperienceStartDate(){
        driver.findElements(postExperienceDate).get(1).click();
        driver.findElements(chooseMonth).get(1).click();
    }
    public void postExperienceEndDate(){
        driver.findElements(postExperienceDate).get(2).click();
        driver.findElements(chooseMonth).get(2).click();
    }

    public void postJobDescription() {
        WebElement el = driver.findElements(jobDescription).get(1);
        el.sendKeys(TestData.jobDescription);
    }
    public AcademicsPage setSaveAndContinue(){
        AcademicsPage academicsPage = new AcademicsPage(driver);
        driver.findElement(saveAndContinue).click();
        return academicsPage;
    }

}
