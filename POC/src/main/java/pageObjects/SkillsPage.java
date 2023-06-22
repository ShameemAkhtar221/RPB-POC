package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SkillsPage {
    public WebDriver driver;

    public SkillsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h5[contains(text(),'add Technical Skills?')]/following-sibling::button")
    WebElement technicalSection;

    @FindBy(xpath = "//div[contains(text(), 'Technical Skills')]/parent::div/following-sibling::div[2]/button")
    List<WebElement> addMoreTechnicalSkill;
    By technicalSkillName = By.xpath("//input[@name='skillName']");

    @FindBy(xpath = "//h5[contains(text(),'add Non Technical Skills?')]/following-sibling::button")
    WebElement NonTechnicalSection;

    @FindBy(xpath = "//div[contains(text(), 'Non Technical Skills')]/parent::div/following-sibling::div[2]/button")
    WebElement addMoreNonTechnicalSkill;

    @FindBy(xpath = "//div[contains(text(), 'Non Technical Skills')]/parent::div/following-sibling::div[1]//input[@name='skillName']")
    List<WebElement> nonTechnicalSkillName;

    @FindBy(xpath = "//h5[contains(text(),'Want to add Tools?')]/following-sibling::button")
    WebElement toolsSection;

    @FindBy(xpath = "//div[contains(text(), 'Tools')]/parent::div/following-sibling::div[2]/button")
    WebElement addMoreTools;

    @FindBy(xpath = "//div[contains(text(), 'Tools')]/parent::div/following-sibling::div[1]//input[@name='skillName']")
    List<WebElement> toolName;

    By addHereButton = By.xpath("//button[@type='button']/span[contains(text(), 'Add Here')]");
    By title = By.xpath("//input[@name='certName']");
    By institute = By.xpath("//input[@name='certInstitute']");
    By clickOnCalender = By.xpath("//input[@placeholder='Select month']");
    By chooseMonth = By.xpath("//tbody/tr[1]/td[1]");
    By saveAndContinue = By.xpath("//button[@type='button']/span[contains(text(), 'Save & Continue')]");



    public void setTechnicalSection(){
        technicalSection.click();
    }

    public void addMoreTechnicalSkill(){
        for (int i = 0; i < 5; i++){
            addMoreTechnicalSkill.get(0).click();
        }
    }

    public void setTechnicalSkillsNames(){
        List<WebElement> skills = driver.findElements(technicalSkillName);
        for (int i = 0; i < skills.size(); i ++){
            skills.get(i).sendKeys("Skill "+ i);
        }
    }


    public void setNonTechnicalSection(){
        NonTechnicalSection.click();
    }

    public void addMoreNonTechnicalSkill(){
        for (int i = 0; i < 5; i++){
            addMoreNonTechnicalSkill.click();
        }
    }

    public void setNonTechnicalSkillsNames(){
        for (int i = 0; i < nonTechnicalSkillName.size(); i ++){
            nonTechnicalSkillName.get(i).sendKeys("Skill "+ i);
        }
    }

    public void setToolsSection(){
        toolsSection.click();
    }

    public void setAddMoreTools(){
        for (int i = 0; i < 5; i++){
            addMoreTools.click();
        }
    }

    public void setToolNames(){
        for (int i = 0; i < toolName.size(); i ++){
            toolName.get(i).sendKeys("Tool "+ i);
        }
    }

    public void setAddHereButton(){
        driver.findElement(addHereButton).click();
    }

    public void setTitle(){
        driver.findElement(title).sendKeys("Certificate Title");
    }

    public void setInstitute(){
        driver.findElement(institute).sendKeys("Certificate Institute");
    }

    public void setStartDate(){
        driver.findElement(clickOnCalender).click();
        driver.findElement(chooseMonth).click();
    }

    public void setEndDate(){
        driver.findElements(clickOnCalender).get(1).click();
        driver.findElements(chooseMonth).get(1).click();
    }

    public ProjectsPage setSaveAndContinue(){
        ProjectsPage projectsPage = new ProjectsPage(driver);
        driver.findElement(saveAndContinue).click();
        return projectsPage;
    }
}

