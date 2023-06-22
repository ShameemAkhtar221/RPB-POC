package pocPackage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;
import resources.TestData;
import resources.base;


import java.io.File;
import java.io.IOException;

public class AddEmployeeTest extends base {

    public WebDriver driver;

    AddEmployeePage addEmployeePage;
    DashboardPage dashboardPage;
    InfoPage infoPage;
    ExperiencePage experiencePage;
    SkillsPage skillsPage;
    AcademicsPage academicsPage;
    ProjectsPage projectsPage;
    CVPreviewPage cvPreviewPage;
    EmployeeSuccessScreen successScreen;
    int totalEmployees;
    int totalProfiles;
    int completedProfiles;
    int draftProfiles;

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(priority = 0, testName = "Verify that admin is able to add the employee with valid data.")
    public void addEmployee() throws InterruptedException {
        driver.get(prop.getProperty("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail().sendKeys("admin@synavos.com");
        loginPage.enterPassword().sendKeys("admin@synavos");
        loginPage.LoginButton().click();

        dashboardPage = new DashboardPage(driver);
        Thread.sleep(5L);

        totalEmployees = dashboardPage.getTotalEmployee();
        totalProfiles = dashboardPage.getTotalProfiles();
        completedProfiles = dashboardPage.getCompletedProfile();
        draftProfiles = dashboardPage.getDraftProfile();

        dashboardPage.clickAddEmployee().click();
        addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.setProfileImage().sendKeys(System.getProperty("user.dir")+ File.separator + "images" + File.separator + "profileImage.jpeg");
        addEmployeePage.enterName();
        addEmployeePage.selectDesignation();
        addEmployeePage.selectDepartment();
        addEmployeePage.selectDateOfBirth();
        addEmployeePage.setEmail();
        addEmployeePage.enterPhoneNumber().sendKeys("3460730678");
        addEmployeePage.selectDateOfJoining();
        successScreen = addEmployeePage.addEmployee();
        Assert.assertEquals(successScreen.getSuccessMessage(), "You have added this employee");
    }


    @Test(priority = 1, dependsOnMethods = "addEmployee", testName = "Verify the admin is able to add our profile name and navigate to the info. page")
    public void preProfileInfo(){
        EmployeeSuccessScreen employeeSuccessScreen = new EmployeeSuccessScreen(driver);
        employeeSuccessScreen.createProfileButton();
        employeeSuccessScreen.enterProfileName().sendKeys("Automation Engineer");
        infoPage = employeeSuccessScreen.doneButton();
        Assert.assertEquals(infoPage.activeTab().getText(), "Info");

    }

    @Test(priority = 2, dependsOnMethods = "preProfileInfo", testName = "Verify that admin is able to add profile info and save it.")
    public void profileInfo() throws InterruptedException {
        infoPage.addGitAddress().sendKeys("https://github.com/ShameemAkhtar123");
        infoPage.addLinkedInAddress().sendKeys("https://www.linkedin.com/in/shameem-akhtar-a33a08185/");
        infoPage.addSummery();
        experiencePage = infoPage.saveAndContinueButton();
        Assert.assertEquals(infoPage.activeTab().getText(), "Experience");
    }

    @Test(priority = 3, dependsOnMethods = "profileInfo", testName = "Verify that admin is able to add profile experience and save it.")
    public void profileExperience() throws InterruptedException {
        experiencePage.addStartDate();
        experiencePage.setDesignation().sendKeys("Sr. SQA Engineer");
        experiencePage.addJobDescription();
        experiencePage.clickAddHereButton().click();
        experiencePage.enterCompanyName().sendKeys("Previous Company");
        experiencePage.postExperienceStartDate();
        experiencePage.postExperienceEndDate();
        experiencePage.setPreviousCompanyDesignation().sendKeys("Sr. SQA Engineer");
        experiencePage.postJobDescription();
        academicsPage = experiencePage.setSaveAndContinue();
        Assert.assertEquals(infoPage.activeTab().getText(), "Academics");
    }

    @Test(priority = 4, testName = "Verify that admin is able to add profile academics and save it.")
    public void profileAcademics(){
        academicsPage.setDegree();
        academicsPage.setInstitute();
        academicsPage.setStartDate();
        academicsPage.setEndDate();

        academicsPage.setCertificateButton().click();
        academicsPage.setCertName().sendKeys("Certified Automation Professional (CAP)");
        academicsPage.setCertInstitute().sendKeys("International Software Testing Qualification Board");
        academicsPage.setCertificateStartDate();
        academicsPage.setSelectCertStartDate();
        academicsPage.setCertificateEndDate();
        academicsPage.setSelectCertEndDate();

        academicsPage.setAddAwardButton().click();
        academicsPage.setAwardTitle().sendKeys("Employee of the Month");
        academicsPage.setAwardDescription().sendKeys("Synavos Solutions");

        skillsPage = academicsPage.setSaveAndContinue();
        Assert.assertEquals(infoPage.activeTab().getText(), "Skills");
    }

    @Test(priority = 5, testName = "Verify that admin is able to add profile skills and save it.")
    public void profileSkills(){
        skillsPage.setTechnicalSection();
        skillsPage.addMoreTechnicalSkill();
        skillsPage.setTechnicalSkillsNames();

        skillsPage.setNonTechnicalSection();
        skillsPage.addMoreNonTechnicalSkill();
        skillsPage.setNonTechnicalSkillsNames();

        skillsPage.setToolsSection();
        skillsPage.setAddMoreTools();
        skillsPage.setToolNames();

        projectsPage = skillsPage.setSaveAndContinue();
        Assert.assertEquals(infoPage.activeTab().getText(), "Projects");

    }

    @Test(priority = 6, testName = "Verify that admin is able to add profile projects and save it.")
    public void profileProjects() throws InterruptedException {
        projectsPage.setProjectSection();
        projectsPage.setProjectName();
        projectsPage.setProjectType();
        projectsPage.setProjectStack();
        projectsPage.setProjectLink();
        projectsPage.setImage();
        cvPreviewPage = projectsPage.setFinishButton();
        Assert.assertEquals(cvPreviewPage.profileName().getText(), TestData.employeeName);
    }

    @Test(priority = 7, testName = "Verify that admin is able to delete their profile")
    public void deleteProfile(){
        cvPreviewPage.deleteProfile();
        Assert.assertEquals(successScreen.getSuccessMessage(), "You have added this employee");
    }

    @Test(priority = 8, testName = "Verify that admin is able to delete employee")
    public void deleteEmployee() throws InterruptedException {
        dashboardPage.clickDashBoard();
        dashboardPage.deleteEmployee();
        Thread.sleep(5L);
        Assert.assertFalse(dashboardPage.setNewlyAddedEmployeeEmail().equalsIgnoreCase(addEmployeePage.getUserEmail()));
    }

    @Test(priority = 9, testName = "Verify that total employees count is increased after created it")
    public void totalEmployee(){
        dashboardPage.clickDashBoard();
        Assert.assertTrue(dashboardPage.getTotalEmployee() ==totalEmployees);
    }

    @Test(priority = 10,  testName = "Verify that total profiles count is increased after completed a profile")
    public void totalProfiles(){
        Assert.assertTrue(dashboardPage.getTotalProfiles() == totalProfiles);
    }

    @Test(priority = 11, testName = "Verify that completed profiles count is increased after completing a profile")
    public void completedProfiles(){
        Assert.assertTrue(dashboardPage.getCompletedProfile() == completedProfiles);
    }

    @Test(priority = 12, testName = "Verify that draft profiles count is correct")
    public void draftProfiles(){
        Assert.assertTrue(dashboardPage.getDraftProfile() == draftProfiles);
    }

    @Test(priority = 13, testName = "Verify that admin is able to log out.")
    public void logout(){
        LogoutTest.logOut(driver);
    }

    @AfterTest()
    public void teardown(){
        driver.close();
    }


}
