package resources;


import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {

    public WebDriver driver;
    public Properties prop;
    RequestSpecification request;
    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);

        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            String driverName = System.getenv("CHROMEDRIVER_PATH");
            ChromeOptions options = new ChromeOptions();

            if(driverName == null){
                WebDriverManager.chromedriver().setup();
//                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver");
                options.addArguments("--headless");
                driver = new ChromeDriver();
            }else {
                options.addArguments("--headless");
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                System.setProperty("webdriver.chrome.driver", driverName);
                driver = new ChromeDriver(options);
            }
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            String driverName = System.getenv("FIREFOX_DRIVER_PATH");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("IE")) {
            //code
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }


    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

}
