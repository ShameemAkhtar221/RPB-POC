package pocPackage;

import API.Pojo.DesignationResponse;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Test(groups = {"restAssuredTests"})
public class DesignationApiTest {

    Properties prop;
    RequestSpecification request;

    SharedMethods sharedMethods = new SharedMethods();

    @BeforeTest
    public void before() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");
    }

    @Test(testName = "Verify the designation api is give 200 ok status code")
    public void verifyDesignationApi() throws IOException {
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + SharedMethods.authorizations());
        Response response = request.get(URLResources.designationResource());
        DesignationResponse designationResponse = response.as(DesignationResponse.class);

        Map<String, String> map = new HashMap<>();
        List<DesignationResponse.Designation> designations = designationResponse.getData().getDesignations();
        for (DesignationResponse.Designation designation : designations){
            String id = designation.get_id();
            String title = designation.getTitle();
            map.put(title, id);
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
