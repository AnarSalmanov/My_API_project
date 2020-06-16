package com.company.Tests;

import com.company.Pojos.Employee;
import com.company.Utils.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class POST_DELETE_usingPOJO {

    public int id;

    @Test(priority = 1)
    public void POST_newEmployee() throws IOException {
        Employee employee = new Employee();
        employee.setName("Samir");
        employee.setAge("34");
        employee.setSalary("50");
        String employeeBody = JsonUtil.convertJavaToJson(employee);
        baseURI = "http://dummy.restapiexample.com/api/v1";
        Response res =
                given().log().all().accept(ContentType.JSON)
                        .body(employeeBody)
                        .when().post("/create")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .extract().response();
        JsonPath js = res.jsonPath();
        id = js.get("data.id");
        System.out.println("Id is :" + id);
        Assert.assertEquals(js.getString("data.name"), "Samir");

    }

    @Test(priority = 2)
    public void DELETE_createdOne() {
        baseURI = "http://dummy.restapiexample.com/api/v1";
        Response res =
                given().pathParam("id", id)
                        .accept(ContentType.JSON)
                        .when().delete("/delete/{id}")
                        .then().assertThat().statusCode(200)
                        .body("message", equalTo("Error! Not able to delete record"))
                        .extract().response();
        res.prettyPrint();

    }

}



