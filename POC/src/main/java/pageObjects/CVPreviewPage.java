package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.CommonMethods;
import resources.TestData;

import java.util.List;

public class CVPreviewPage {
    public WebDriver driver;

    public CVPreviewPage(WebDriver driver){
        this.driver = driver;
    }

    By profileName = By.xpath("//div[@class='nam-title']/div[2]");
    By profileDeleteIcon = By.xpath("//button[@type='button']/img");
    By profileDeleteConfirmation = By.xpath("//button[@type='button']/span[contains(text(), 'Yes')]");

    public WebElement profileName(){
        return driver.findElement(profileName);
    }

    //comment
    public void deleteProfile(){
        List<WebElement> list =  driver.findElements(profileDeleteIcon);
        for (int i = 0; i < list.size(); i++){
            String atr = list.get(i).getAttribute("src");
            if(atr.equalsIgnoreCase("https://dev.poc.synavos.com/icons/delete-white.svg")){
                list.get(i).click();
            }
        }
        CommonMethods.waitForElement(driver, profileDeleteConfirmation, TestData.explicitWait).click();
    }
}
