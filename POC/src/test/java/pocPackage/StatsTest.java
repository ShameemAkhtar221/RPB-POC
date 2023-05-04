package pocPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.URLResources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class StatsTest{

    Properties prop;

    @BeforeTest
    public void before() throws IOException {
        prop  = new Properties();
        FileInputStream fis = new FileInputStream("/home/lubna/IdeaProjects/RPB-POC/POC/src/main/java/resources/data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");

    }

    @Test()
    public void totalEmployeeStat(){

        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "admin@synavos.com");
        requestParams.put("password", "admin@synavos");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toString());
        Response response = request.post(URLResources.loginResource());
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }
}
