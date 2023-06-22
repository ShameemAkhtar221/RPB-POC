package pocPackage;

import API.Pojo.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.TestData;
import resources.URLResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Test(groups = {"restAssuredTests"})
public class ProfileApiTest{

    Properties prop;
    RequestSpecification request;
    public String profileId;


    @BeforeTest
    public void before() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");
    }

    @Test(testName = "Add profile name", priority = 7, groups = {"profileCreation"})
    public void profileCreationApi() throws IOException {
        ProfilePostRequest profilePostRequest = new ProfilePostRequest(EmployeeApiTest.employeeId, "Automation Profile");

        request = RestAssured.given();
        System.out.println(EmployeeApiTest.employeeId);
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        request.body(profilePostRequest);
        request.log().all();

        Response response = request.post(URLResources.profilePostResource());
        ProfileCreationResponse profileCreationResponse = response.as(ProfileCreationResponse.class);
        profileId = profileCreationResponse.getData().get_id();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(profileCreationResponse.getData().getProfileName(), "Automation Profile");

    }


    @Test(testName = "Profile update", priority = 8, groups = {"profileCreation"})
    public void profileUpdate() throws IOException {
        ProfilePutRequest profilePutRequest = new ProfilePutRequest();
        TestData.profileBody(profilePutRequest);
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        request.body(profilePutRequest);
        request.log().all();
        Response response = request.put(URLResources.profilePutResource()+ "/" + profileId);
        System.out.println(response.asString());

        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
