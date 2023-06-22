package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='button']")
    WebElement loginButton;

    @FindBy(css = "input[name=email] + div")
    WebElement emailError;

    @FindBy(xpath = "//div[contains(text(),'password')]")
    WebElement passwordError;

    public WebElement enterEmail(){
        return email;
    }

    public Boolean emailErrorDisplayed(){
        return emailError.isDisplayed();
    }

    public WebElement enterPassword(){
        return password;
    }


    public Boolean passwordErrorDisplayed(){
        return passwordError.isDisplayed();
    }

    public WebElement LoginButton(){
        return loginButton;
    }

}
