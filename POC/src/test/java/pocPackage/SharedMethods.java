package pocPackage;

import API.Pojo.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import resources.URLResources;
import resources.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SharedMethods extends base {

    static Properties prop;
    public static String access_token;
    static RequestSpecification request;

    @BeforeTest
    public void before() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");
    }

    public static String authorizations() throws IOException {
        prop  = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "resources" + File.separator + "data.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("RPBHost");

        LoginRequest loginRequest = new LoginRequest("admin@synavos.com", "admin@synavos");

        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(loginRequest);
        Response response = request.post(URLResources.loginResource());

        LoginResponse loginResponse = response.as(LoginResponse.class);
        access_token = loginResponse.getData().getAccess_token();
        return access_token;
    }
    public Map<String, String> getDepartmentsId() throws IOException {
        before();
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + access_token);
        Response response = request.get(URLResources.departmentResource());
        DepartmentsResponse departmentsResponse = response.as(DepartmentsResponse.class);

        Map<String, String> map = new HashMap<>();
        List<DepartmentsResponse.Department> departments = departmentsResponse.getDepartments();
        for (DepartmentsResponse.Department department : departments){
            String id = department.get_id();
            String title = department.getTitle();
            map.put(title, id);
        }
        return map;
    }

    public Map<String, String> getDesignationId() throws IOException {
        before();
        request = RestAssured.given();
        request.header("Content-Type", "application/json").header("Authorization", "Bearer " + access_token);
        Response response = request.get(URLResources.designationResource());
        DesignationResponse designationResponse = response.as(DesignationResponse.class);

        Map<String, String> map = new HashMap<>();
        List<DesignationResponse.Designation> designations = designationResponse.getData().getDesignations();
        for (DesignationResponse.Designation designation : designations){
            String id = designation.get_id();
            String title = designation.getTitle();
            map.put(title, id);
        }
        return map;
    }

}
