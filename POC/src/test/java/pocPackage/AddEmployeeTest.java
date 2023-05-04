package pocPackage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;
import resources.TestData;
import resources.base;


import java.io.IOException;

public class AddEmployeeTest extends base {

    public WebDriver driver;
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

    @Test(priority = 0, testName = "")
    public void addEmployee() throws InterruptedException {
        driver.get(prop.getProperty("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail().sendKeys("admin@synavos.com");
        loginPage.enterPassword().sendKeys("admin@synavos");
        loginPage.LoginButton().click();

        dashboardPage = new DashboardPage(driver);

        totalEmployees = dashboardPage.getTotalEmployee();
        totalProfiles = dashboardPage.getTotalProfiles();
        completedProfiles = dashboardPage.getCompletedProfile();
        draftProfiles = dashboardPage.getDraftProfile();
        System.out.println(totalEmployees +" " +totalProfiles+ " " + completedProfiles +" " + draftProfiles);


        dashboardPage.clickAddEmployee().click();
        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.setProfileImage().sendKeys("/home/lubna/Downloads/profileImage.jpeg");
        addEmployeePage.enterName();
        addEmployeePage.selectDesignation();
        addEmployeePage.selectDepartment();
        addEmployeePage.selectDateOfBirth();
        addEmployeePage.enterEmail().sendKeys("shameem.akhtar@synavos.com");
        addEmployeePage.enterPhoneNumber().sendKeys("3460730678");
        addEmployeePage.selectDateOfJoining();
        successScreen = addEmployeePage.addEmployee();
        Assert.assertEquals(successScreen.getSuccessMessage(), "You have added this employee");
    }

    @Test(priority = 1, dependsOnMethods = "addEmployee")
    public void preProfileInfo(){
        EmployeeSuccessScreen employeeSuccessScreen = new EmployeeSuccessScreen(driver);
        employeeSuccessScreen.createProfileButton();
        employeeSuccessScreen.enterProfileName().sendKeys("Automation Engineer");
        infoPage = employeeSuccessScreen.doneButton();
        Assert.assertEquals(infoPage.activeTab().getText(), "Info");

    }

    @Test(priority = 2, dependsOnMethods = "preProfileInfo")
    public void profileInfo() throws InterruptedException {
        infoPage.addGitAddress().sendKeys("https://github.com/ShameemAkhtar123");
        infoPage.addLinkedInAddress().sendKeys("https://www.linkedin.com/in/shameem-akhtar-a33a08185/");
        infoPage.addSummery();
        experiencePage = infoPage.saveAndContinueButton();
        Assert.assertEquals(infoPage.activeTab().getText(), "Experience");
    }

    @Test(priority = 3, dependsOnMethods = "profileInfo")
    public void profileExperience() throws InterruptedException {
        experiencePage.addStartDate();
        experiencePage.addJobDescription();
        experiencePage.clickAddHereButton().click();
        experiencePage.enterCompanyName().sendKeys("Previous Company");
        experiencePage.postExperienceStartDate();
        experiencePage.postExperienceEndDate();
        experiencePage.postJobDescription();
        skillsPage = experiencePage.setSaveAndContinue();
        Assert.assertEquals(infoPage.activeTab().getText(), "Skills");
    }

    @Test(priority = 4, dependsOnMethods = "profileExperience")
    public void profileSkills(){
        skillsPage.setSkillsName();
        skillsPage.setSkillsBar();
    }

    @Test(priority = 5, dependsOnMethods = "profileSkills")
    public void profileCertificate(){
        skillsPage.setAddHereButton();
        skillsPage.setTitle();
        skillsPage.setInstitute();
        skillsPage.setStartDate();
        skillsPage.setEndDate();
        academicsPage = skillsPage.setSaveAndContinue();
        Assert.assertEquals(infoPage.activeTab().getText(), "Academics");
    }

    @Test(priority = 6, dependsOnMethods = "profileCertificate")
    public void profileAcademics(){
        academicsPage.setDegree();
        academicsPage.setInstitute();
        academicsPage.setStartDate();
        academicsPage.setEndDate();
        projectsPage = academicsPage.setSaveAndContinue();
        Assert.assertEquals(infoPage.activeTab().getText(), "Projects");
    }

    @Test(priority = 7, dependsOnMethods = "profileAcademics")
    public void profileProjects() throws InterruptedException {
        projectsPage.setProjectName();
        projectsPage.setProjectType();
        projectsPage.setProjectStack();
        projectsPage.setProjectLink();
        projectsPage.setImage();
        cvPreviewPage = projectsPage.setFinishButton();
        Assert.assertEquals(cvPreviewPage.profileName().getText(), TestData.employeeName);
    }

    @Test(priority = 8, dependsOnMethods = "profileProjects")
    public void deleteProfile(){
        cvPreviewPage.deleteProfile();
        Assert.assertEquals(successScreen.getSuccessMessage(), "You have added this employee");
    }

    @Test(priority = 9, dependsOnMethods = "deleteProfile")
    public void deleteEmployee() throws InterruptedException {
        dashboardPage.clickDashBoard();
        dashboardPage.deleteEmployee();
        Thread.sleep(5L);
        Assert.assertFalse(dashboardPage.setNewlyAddedEmployee().equalsIgnoreCase(TestData.employeeName));
    }

    @Test(priority = 10, dependsOnMethods = "addEmployee")
    public void totalEmployee(){
        dashboardPage.clickDashBoard();
        Assert.assertTrue(dashboardPage.getTotalEmployee() ==totalEmployees);
    }

    @Test(priority = 11, dependsOnMethods = "profileProjects")
    public void totalProfiles(){
        Assert.assertTrue(dashboardPage.getTotalProfiles() == totalProfiles);
    }

    @Test(priority = 12, dependsOnMethods = "profileProjects")
    public void completedProfiles(){
        Assert.assertTrue(dashboardPage.getCompletedProfile() == completedProfiles);
    }

    @Test(priority = 13, dependsOnMethods = "profileProjects")
    public void draftProfiles(){
        Assert.assertTrue(dashboardPage.getDraftProfile() == draftProfiles);
    }

    @Test(priority = 14)
    public void logOut(){
        dashboardPage.setLogOut();
    }


    @AfterTest
    public void teardown(){
        driver.close();
    }

}