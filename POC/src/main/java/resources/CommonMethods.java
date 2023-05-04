package resources;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class CommonMethods {

    public WebDriver driver;

    public CommonMethods(WebDriver driver){
        this.driver = driver;
    }

    public static WebElement waitForElement(WebDriver driver, By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public void clearAndType(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public static WebElement scrollElementIntoView(By webElement, WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(webElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(500l);
        return element;
    }

    public static void typeWithSpeed( By element, String value, long speedDelay, WebDriver driver) throws InterruptedException {
        driver.findElement(element).click();
        for (int i = 0; i < value.length(); i++) {
            driver.findElement(element).sendKeys(value.substring(i, (i + 1)));
            sleep(speedDelay);
        }
    }

}
