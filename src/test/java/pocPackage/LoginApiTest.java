package pocPackage;

import API.Pojo.LoginResponse;
import API.Pojo.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.URLResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Test(groups = {"restAssuredTests"})
public class LoginApiTest {
    Properties prop;
    RequestSpecification request;

    @BeforeTest
    public void before() throws IOException {
        prop  = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");

    }

    @Test(testName = "Verify that the response status code is 200 OK")
    public void LoginAPISuccessfulLogin() {

        LoginRequest loginRequest = new LoginRequest("admin@synavos.com", "admin@synavos");

        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.body(loginRequest);
        Response response = request.post(URLResources.loginResource());

        LoginResponse loginResponse = response.as(LoginResponse.class);

        Assert.assertEquals(200, loginResponse.getStatusCode());
        Assert.assertNotNull(loginResponse.getData().getAccess_token());
        System.out.println(response.asString());

    }

    @Test(testName = "Verify that the response status code is 401 Unauthorized with invalid credentials")
    public void testInvalidCredentials() {
        LoginRequest loginRequest = new LoginRequest("admin@synavos.com", "wrongpassword");

        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.body(loginRequest);
        Response response = request.post(URLResources.loginResource());

        Assert.assertEquals(response.getStatusCode(), 400);

        System.out.println(response.asString());
        String errorMessage = response.getBody().asString();
        Assert.assertTrue(errorMessage.contains("Invalid email or password!"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        LoginResponse.Data data = loginResponse.getData();
        if(data != null){
            Assert.assertNull(loginResponse.getData().getAccess_token());
        }
        Assert.assertEquals(loginResponse.getError(), "Bad Request");
        if(loginResponse.getPath() != null){
            Assert.assertEquals("/auth/login", loginResponse.getPath());
        }
    }

    @Test(testName = "Verify that the response status code is 401 Unauthorized with empty credentials")
    public void testEmptyCredentials() {
        LoginRequest loginRequest = new LoginRequest("", "");

        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.body(loginRequest);
        Response response = request.post(URLResources.loginResource());

        Assert.assertEquals(response.getStatusCode(), 401);

        System.out.println(response.asString());
        String errorMessage = response.getBody().asString();
        Assert.assertTrue(errorMessage.contains("Unauthorized"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        LoginResponse.Data data = loginResponse.getData();
        if(data != null){
            Assert.assertNull(loginResponse.getData().getAccess_token());
        }
        Assert.assertNull(loginResponse.getError());
        if(loginResponse.getPath() != null){
            Assert.assertEquals("/auth/login", loginResponse.getPath());
        }
    }

    @Test(testName = "Verify that the response status code is 400 Bad Request with missing email parameter")
    public void testMissingEmailParameter() {
        LoginRequest loginRequest = new LoginRequest("", "admin@synavos");

        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.body(loginRequest);
        Response response = request.post(URLResources.loginResource());

        Assert.assertEquals(response.getStatusCode(), 401);

        System.out.println(response.asString());
        String errorMessage = response.getBody().asString();
        Assert.assertTrue(errorMessage.contains("Unauthorized"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        LoginResponse.Data data = loginResponse.getData();
        if(data != null){
            Assert.assertNull(loginResponse.getData().getAccess_token());
        }
        Assert.assertNull(loginResponse.getError());
        if(loginResponse.getPath() != null){
            Assert.assertEquals("/auth/login", loginResponse.getPath());
        }
    }

    @Test(testName = "Verify that the response status code is 400 Bad Request with missing password parameter")
    public void testMissingPasswordParameter() {
        LoginRequest loginRequest = new LoginRequest("admin@synavos.com", "");

        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        request.body(loginRequest);
        Response response = request.post(URLResources.loginResource());

        Assert.assertEquals(response.getStatusCode(), 401);

        System.out.println(response.asString());
        String errorMessage = response.getBody().asString();
        Assert.assertTrue(errorMessage.contains("Unauthorized"));

        LoginResponse loginResponse = response.as(LoginResponse.class);
        LoginResponse.Data data = loginResponse.getData();
        if(data != null){
            Assert.assertNull(loginResponse.getData().getAccess_token());
        }
        Assert.assertNull(loginResponse.getError());
        if(loginResponse.getPath() != null){
            Assert.assertEquals("/auth/login", loginResponse.getPath());
        }
    }
}
