package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AcademicsPage {
    public WebDriver driver;

    public AcademicsPage(WebDriver driver){
        this.driver = driver;
    }

    By degree = By.xpath("//input[@name='academicDegreeName']");
    By institute = By.xpath("//input[@name='academicInstitute']");
    By clickOnCalender = By.xpath("//input[@placeholder='Select month']");
    By selectDate = By.xpath("//tbody/tr[1]/td[1]");
    By saveAndContinue = By.xpath("//button[@type='button']/span[contains(text(), 'Save & Continue')]");


    public void setDegree(){
        driver.findElement(degree).sendKeys("Certificate Title");
    }

    public void setInstitute(){
        driver.findElement(institute).sendKeys("Certificate Institute");
    }

    public void setStartDate(){
        driver.findElement(clickOnCalender).click();
        driver.findElement(selectDate).click();
    }

    public void setEndDate(){
        driver.findElements(clickOnCalender).get(1).click();
        driver.findElements(selectDate).get(1).click();
    }

    public ProjectsPage setSaveAndContinue(){
        ProjectsPage projectsPage = new ProjectsPage(driver);
        driver.findElement(saveAndContinue).click();
        return projectsPage;
    }
}
