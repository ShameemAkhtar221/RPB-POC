package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    By login = By.xpath("//input[@name='email']");
    By password = By.xpath("//input[@name='password']");
    By loginButton = By.xpath("//button[@type='button']");

    public WebElement enterEmail(){
        return driver.findElement(login);
    }

    public WebElement enterPassword(){
        return driver.findElement(password);
    }

    public WebElement LoginButton(){
        return driver.findElement(loginButton);
    }
}
