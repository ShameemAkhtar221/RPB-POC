package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class SkillsPage {
    public WebDriver driver;

    public SkillsPage(WebDriver driver){
        this.driver = driver;
    }

    By skillName = By.xpath("//input[@name='skillName']");
    By scrollbar = By.xpath("//div[@class='ant-slider-handle']");
    By addHereButton = By.xpath("//button[@type='button']/span[contains(text(), 'Add Here')]");
    By title = By.xpath("//input[@name='certName']");
    By institute = By.xpath("//input[@name='certInstitute']");
    By clickOnCalender = By.xpath("//input[@placeholder='Select month']");
    By chooseMonth = By.xpath("//tbody/tr[1]/td[1]");
    By saveAndContinue = By.xpath("//button[@type='button']/span[contains(text(), 'Save & Continue')]");



    public void setSkillsName(){
        List<WebElement> skills = driver.findElements(skillName);
        for (int i = 0; i < skills.size(); i ++){
            skills.get(i).sendKeys("Skill "+ i);
        }
    }

    public void setSkillsBar(){
        List<WebElement> skills = driver.findElements(scrollbar);
        for (int i = 0; i < skills.size(); i ++){
            Actions move = new Actions(driver);
            Action action = (Action) move.dragAndDropBy(skills.get(i), 210, 0).build();
            action.perform();
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

    public AcademicsPage setSaveAndContinue(){
        AcademicsPage academicsPage = new AcademicsPage(driver);
        driver.findElement(saveAndContinue).click();
        return academicsPage;
    }
}

