package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.CommonMethods;
import resources.TestData;

import java.util.List;

public class AddEmployeePage{

    public WebDriver driver;
    public String userEmail;

    public AddEmployeePage(WebDriver driver){
        this.driver = driver;
    }

    By profileImage = By.xpath("//input[@type='file']");
    By name = By.xpath("//input[@name='name']");
    By designation = By.xpath("//label[contains(text(), 'Designation')]/following-sibling::div");
    By designationOption = By.xpath("//div[@title='Sr. SQA Engineer']");
    By department = By.xpath("//label[contains(text(), 'Department')]/following-sibling::div");
    By departmentOption = By.xpath("//div[@title='Engineering']");
    By dateOfBirthCalender = By.xpath("//label[contains(text(), 'Date Of Birth')]/following-sibling::div");
    By dateOfBirth = By.xpath("//a[@class='ant-picker-today-btn']");
    By email = By.xpath("//input[@name='email']");
    By phoneNumber = By.xpath("//input[@type='tel']");
    By dateOfJoiningCalender = By.xpath("//label[contains(text(), 'Date Of Joining')]/following-sibling::div");
    By dateOfJoining = By.xpath("//a[@class='ant-picker-today-btn']");
    By addButton = By.xpath("//div[@class='ant-modal-body']//button[@type='button']");


    public WebElement setProfileImage() {
        return driver.findElement(profileImage);
    }
    public void enterName() throws InterruptedException {
        Thread.sleep(10);
        WebElement element = CommonMethods.waitForElement(driver, name, 60);
        element.sendKeys(TestData.employeeName);
    }
    public void selectDesignation(){
        WebElement selectElement = driver.findElement(designation);
        selectElement.click();

        WebElement selectOption = driver.findElement(designationOption);
        selectOption.click();
    }
    public void selectDepartment(){
        WebElement selectElement = driver.findElement(department);
        selectElement.click();

        WebElement selectOption = driver.findElement(departmentOption);
        selectOption.click();
    }
    public void selectDateOfBirth(){
        driver.findElement(dateOfBirthCalender).click();
        driver.findElement(dateOfBirth).click();
    }
    public void setEmail(){
        userEmail = CommonMethods.generateRandomEmail();
        driver.findElement(email).sendKeys(userEmail);
    }

    public String getUserEmail(){
        return userEmail;
    }

    public WebElement enterPhoneNumber(){
        return driver.findElement(phoneNumber);
    }
    public void selectDateOfJoining(){
        driver.findElement(dateOfJoiningCalender).click();
        List<WebElement> list = driver.findElements(dateOfJoining);
        list.get(1).click();
    }
    public EmployeeSuccessScreen addEmployee(){
        driver.findElement(addButton).click();
        EmployeeSuccessScreen employeeSuccessScreen = new EmployeeSuccessScreen(driver);
        return employeeSuccessScreen;
    }

}
