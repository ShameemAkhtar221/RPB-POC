package pocPackage;

import API.Pojo.DepartmentsResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.URLResources;
import resources.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Test(groups = {"restAssuredTests"})
public class DepartmentsApiTest {

    Properties prop;
    RequestSpecification request;

    @BeforeTest
    public void before() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");
    }

    @Test(testName = "Verify the departments api is give 200 ok status code")
    public void getDepartmentsId() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.get(URLResources.departmentResource());
        DepartmentsResponse departmentsResponse = response.as(DepartmentsResponse.class);

        Map<String, String> map = new HashMap<>();
        List<DepartmentsResponse.Department> departments = departmentsResponse.getDepartments();
        for (DepartmentsResponse.Department department : departments){
            String id = department.get_id();
            String title = department.getTitle();
            map.put(title, id);
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
