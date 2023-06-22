package pocPackage;

import API.Pojo.EmployeeDashboardResponse;
import API.Pojo.GetEmployeeResponse;
import API.Pojo.PostEmployeeRequest;
import API.Pojo.PostEmployeeResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.CommonMethods;
import resources.URLResources;
import resources.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Test(groups = {"restAssuredTest"})
public class EmployeeApiTest{

    Properties prop;
    RequestSpecification request;
    List<String> emails;
    public static String[] emailsArray;
    public static String employeeId;
    int totalEmployee;
    SharedMethods sharedMethods = new SharedMethods();

    @BeforeTest
    public void before() throws IOException {
        prop  = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");
    }


    @Test(testName = "Employee/dashboard - Verify the status code is 200 success", priority = 0, groups = "employeeCreation")
    public void employeeDashboardAPISuccess() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.get(URLResources.employeeDashboardResource());


        EmployeeDashboardResponse employeeDashboardResponse = response.as(EmployeeDashboardResponse.class);
        totalEmployee = employeeDashboardResponse.getData().getTotalEmployees();

        Assert.assertEquals(employeeDashboardResponse.getStatusCode(), 200);
        Assert.assertEquals(employeeDashboardResponse.getMessage(), "success");
    }

    @Test(testName = "Employee/dashboard - Verify the status code is 400 bad request", priority = 1, groups = "employeeCreation")
    public void employeeDashboardAPIError() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.get("wrong url");

        EmployeeDashboardResponse employeeDashboardResponse = response.as(EmployeeDashboardResponse.class);

        Assert.assertEquals(employeeDashboardResponse.getStatusCode(), 500);
        Assert.assertEquals(employeeDashboardResponse.getError(), "Internal Server Error");
    }

    @Test(priority = 2, testName = "Verify the Get API response status code is 201 created", groups = "employeeCreation")
    public void employeeGetApiValid() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.get(URLResources.employeeResource());

        GetEmployeeResponse getEmployeeResponse = response.as(GetEmployeeResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        int listSize = getEmployeeResponse.getData().getEmployees().size();

        for (int i = 0; i < listSize; i++) {
            emailsArray = new String[listSize];
            emails = Collections.singletonList(getEmployeeResponse.getData().getEmployees().get(i).getEmail());
        }
        emailsArray = new String[listSize];
        for (int i = 0; i < listSize; i++){
            String email = getEmployeeResponse.getData().getEmployees().get(i).getEmail();
            emailsArray[i] = email;
        }
    }
    @Test(testName = "Verify the Get API response status code is 500 error with invalid path", priority = 3, groups = "employeeCreation")
    public void employeeGetApiInvalid() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " +SharedMethods.authorizations());
        Response response = request.get("employe");

        GetEmployeeResponse getEmployeeResponse = response.as(GetEmployeeResponse.class);
        Assert.assertEquals(response.getStatusCode(), 500);
        Assert.assertEquals(getEmployeeResponse.getError(), "Internal Server Error");
    }

    @Test(testName = "Verify the Employee Creation API for difference responses", dataProvider = "testData", priority = 4, groups = "employeeCreation")
    public void employeeApiValidData(String name, String designation, String department, String dataOfBirth, String phoneNumber, String dateOfJoining, String email, String profileImage) throws IOException {

        PostEmployeeRequest postEmployeeRequest = new PostEmployeeRequest(name, designation, department, dataOfBirth, phoneNumber, dateOfJoining, email, profileImage);

        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " +SharedMethods.authorizations());
        request.body(postEmployeeRequest);

        Response response = request.post(URLResources.employeeResource());

        PostEmployeeResponse postEmployeeResponse = response.as(PostEmployeeResponse.class);

        if(name == null && designation == null && department == null && dataOfBirth == null && phoneNumber == null && dateOfJoining == null && email == null && profileImage == null){
            Assert.assertEquals(postEmployeeResponse.getStatusCode(), 400);
            Assert.assertTrue(postEmployeeResponse.getMessage().toString().contains("profilePicture should not be empty, profilePicture must be a string, name must be shorter than or equal to 50 characters, name should not be empty, name must be a string, designation should not be empty, designation must be a string, department should not be empty, department must be a string, Please Enter dateOfBirth in valid format YYYY-MM-DD, dateOfBirth must be a valid ISO 8601 date string, dateOfBirth should not be empty, dateOfBirth must be a string, email should not be empty, email must be an email, phone should not be empty, phone must be a string, Please Enter dateOfJoining in valid format YYYY-MM-DD, dateOfJoining must be a valid ISO 8601 date string, dateOfJoining should not be empty, dateOfJoining must be a string"));

        } else if(name == null || designation == null || department == null || dataOfBirth == null || phoneNumber == null || dateOfJoining == null || email == null || profileImage == null || name.isEmpty() || designation.isEmpty() || department.isEmpty() || dataOfBirth.isEmpty() || phoneNumber.isEmpty() || dateOfJoining.isEmpty() || email.isEmpty() || profileImage.isEmpty()){
            Assert.assertEquals(postEmployeeResponse.getStatusCode(), 400);
            Assert.assertTrue(postEmployeeResponse.getMessage().toString().contains("designation should not be empty, department should not be empty"));

        } else if (postEmployeeResponse.getStatusCode().equals(200)) {
            employeeId = postEmployeeResponse.getData().get_id();

            Assert.assertEquals(postEmployeeResponse.getMessage(), "success");
            Assert.assertEquals(postEmployeeResponse.getStatusCode(), 200);
            Assert.assertEquals(postEmployeeResponse.getData().getName(), name);
            Assert.assertEquals(postEmployeeResponse.getData().getEmail(), email);
            Assert.assertEquals(postEmployeeResponse.getData().getPhone(), phoneNumber);

        } else if (emailsArray.length > 0){
             for (int i = 0; i < emailsArray.length; i++){
                if (email.equals(emailsArray[i])){
                    Assert.assertEquals(postEmployeeResponse.getStatusCode(), 400);
                    Assert.assertEquals(postEmployeeResponse.getMessage(), "Employee Already exist with same email");
                    return;
                }
            }

            Assert.assertEquals(postEmployeeResponse.getMessage(), "success");
            Assert.assertEquals(postEmployeeResponse.getStatusCode(), 200);
            Assert.assertEquals(postEmployeeResponse.getData().getName(), name);
            Assert.assertEquals(postEmployeeResponse.getData().getEmail(), email);
            Assert.assertEquals(postEmployeeResponse.getData().getPhone(), phoneNumber);
        }

    }

    @Test(testName = "Employee/dashboard - Verify the employee count is increased by 1 after employee creation", priority = 5, groups = "employeeCreation")
    public void verifyEmployeeCount() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.get(URLResources.employeeDashboardResource());

        EmployeeDashboardResponse employeeDashboardResponse = response.as(EmployeeDashboardResponse.class);
        int total = employeeDashboardResponse.getData().getTotalEmployees();

        Assert.assertEquals(employeeDashboardResponse.getStatusCode(), 200);
        Assert.assertEquals(employeeDashboardResponse.getMessage(), "success");
        Assert.assertEquals(total, totalEmployee+1);
    }

    @Test(testName = "Verify that the response status code is 400 Bad Request with Empty Body", priority = 6, groups = "employeeCreation")
    public void employeePostApiEmptyData() throws IOException {

        PostEmployeeRequest postEmployeeRequest = new PostEmployeeRequest();

        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " +SharedMethods.authorizations());
        request.body(postEmployeeRequest);

        Response response = request.post(URLResources.employeeResource());

        PostEmployeeResponse postEmployeeResponse = response.as(PostEmployeeResponse.class);

        Assert.assertEquals(postEmployeeResponse.getError(), "Bad Request");
        Assert.assertEquals(postEmployeeResponse.getStatusCode(), 400);

    }

    @Test(priority = 9, testName = "Verify the delete employee API", groups = "employeeDeletion")
    public void deleteEmployee() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.delete(URLResources.employeeResource()+ "/" +employeeId);

        System.out.println(response.asString());

        PostEmployeeResponse delete = response.as(PostEmployeeResponse.class);
        Assert.assertEquals(delete.getStatusCode(), 200);
        Assert.assertEquals(delete.getData().get_id(), employeeId);
    }


    @DataProvider(name = "testData")
    public Object[][] testData() throws IOException {
        return new Object[][]{
                // Valid data
                {"Shan Shahid", sharedMethods.getDesignationId().get("Sr. SQA Engineer"), sharedMethods.getDepartmentsId().get("Engineering"), "2011-04-13", "+923222021113", "2022-04-05", CommonMethods.generateRandomEmail(), "https://poc.synavos.com/uploads/928d5601-49c7-4566-8263-020568e5e63d.jpg"},
                // Invalid data
                {"", "", "", "", "", "", "", ""},
                // Empty data
                {null, null, null, null, null, null, null, null},
                //Missing designation and department
                {"Shan Shahid", "", "", "2011-04-13", "+923222021113", "2022-04-05", "shaann.shahshhid@gmail.com", "https://poc.synavos.com/uploads/928d5601-49c7-4566-8263-020568e5e63d.jpg"},
                // Duplicate email
                {"Shan Shahid", sharedMethods.getDesignationId().get("Sr. SQA Engineer"), sharedMethods.getDepartmentsId().get("Engineering"), "2011-04-13", "+923222021113", "2022-04-05", "zeeshan.aslam@synavos.com", "https://poc.synavos.com/uploads/928d5601-49c7-4566-8263-020568e5e63d.jpg"}
        };
    }

}
