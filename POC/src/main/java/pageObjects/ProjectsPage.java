package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.CommonMethods;

public class ProjectsPage {

    public WebDriver driver;

    public ProjectsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h5[contains(text(),'add Projects?')]/following-sibling::button")
    WebElement projectSection;

    By projectName = By.xpath("//input[@name='projectName']");
    By projectType = By.xpath("//input[@name='projectType']");
    By projectStack = By.xpath("//input[@name='projectTechStack']");
    By projectLink = By.xpath("//input[@name='projectLink']");
    By image = By.xpath("//input[@type='file']");
    By finish = By.xpath("//button[@type='button']/span[contains(text(), 'Finish')]");


    public void setProjectSection(){
        projectSection.click();
    }

    public void setProjectName(){
        driver.findElement(projectName).sendKeys("Project Name");
    }

    public void setProjectType(){
        driver.findElement(projectType).sendKeys("Project Type");
    }

    public void setProjectStack(){
        driver.findElement(projectStack).sendKeys("Project Stack");
    }

    public void setProjectLink(){
        driver.findElement(projectLink).sendKeys("https://dev.poc.synavos.com/");
    }

    public void setImage(){
        driver.findElement(image).sendKeys("/home/lubna/Downloads/projectImage.png");
    }

    public CVPreviewPage setFinishButton() throws InterruptedException {
        CVPreviewPage cvPreviewPage = new CVPreviewPage(driver);
        Thread.sleep(5L);
        CommonMethods.scrollElementIntoView(finish, driver);
        driver.findElement(finish).click();
        return cvPreviewPage;
    }
}
