package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AcademicsPage {
    public WebDriver driver;

    public AcademicsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By degree = By.xpath("//input[@name='academicDegreeName']");
    By institute = By.xpath("//input[@name='academicInstitute']");
    By clickOnCalender = By.xpath("//input[@placeholder='Select month']");
    By selectDate = By.xpath("//tbody/tr[1]/td[1]");
    By saveAndContinue = By.xpath("//button[@type='button']/span[contains(text(), 'Save & Continue')]");

    @FindBy(xpath = "//h5[contains(text(),'Want to add Trainings / Certifications??')]/following-sibling::button")
    WebElement addCertificateButton;

    @FindBy(css = "input[name='certName']")
    WebElement certName;

    @FindBy(css = "input[name='certInstitute']")
    WebElement certInstitute;

    @FindBy(xpath = "//div[contains(text(), 'Training')]/parent::div/following-sibling::div[1]//div[@class='dateWrapper']")
    List<WebElement> date;

    @FindBy(xpath = "//h5[contains(text(),'Want to add Awards & Honors?')]/following-sibling::button")
    WebElement addAwardButton;


    @FindBy(css = "input[name='awardTitle']")
    WebElement awardTitle;

    @FindBy(css = "textarea[name='awardDescription']")
    WebElement awardDescription;

    public void setDegree(){
        driver.findElement(degree).sendKeys("BS Software Engineering");
    }

    public void setInstitute(){
        driver.findElement(institute).sendKeys("COMSATS University Lahore");
    }

    public void setStartDate(){
        driver.findElement(clickOnCalender).click();
        driver.findElement(selectDate).click();
    }

    public void setEndDate(){
        driver.findElements(clickOnCalender).get(1).click();
        driver.findElements(selectDate).get(1).click();
    }

    public SkillsPage setSaveAndContinue(){
        SkillsPage skillsPage = new SkillsPage(driver);
        driver.findElement(saveAndContinue).click();
        return skillsPage;
    }

    public WebElement setCertificateButton(){
        return addCertificateButton;
    }

    public WebElement setCertName(){
        return certName;
    }

    public WebElement setCertInstitute(){
        return certInstitute;
    }

    public void setCertificateStartDate(){
        date.get(0).click();
    }

    public void setSelectCertStartDate(){
        driver.findElements(selectDate).get(2).click();
    }

    public void setSelectCertEndDate(){
        driver.findElements(selectDate).get(3).click();
    }

    public void setCertificateEndDate(){
        date.get(1).click();
    }

    public WebElement setAddAwardButton(){
        return addAwardButton;
    }

    public WebElement setAwardTitle(){
        return awardTitle;
    }

    public WebElement setAwardDescription(){
        return awardDescription;
    }
}
