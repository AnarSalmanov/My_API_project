package com.company.Stepdefinitions;


import io.cucumber.java.en.*;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;


public class POSTexample {

    public Response res;
    public int id;

    @Given("Base Uri {string}")
    public void base_Uri(String string) {
        baseURI = string;
    }

    @Given("Content type Json")
    public void content_type_Json() {
        Map<String, String> employeeMap = new HashMap<>();
        employeeMap.put("name", "Layla");
        employeeMap.put("salary", "7000");
        employeeMap.put("age", "2");
        System.out.println(employeeMap);
        given().accept(ContentType.JSON)
                .body(employeeMap);
    }

    @When("I post a new Employee  to resource {string}")
    public void i_post_a_new_Employee_to_resource(String string) {
        res = when().post(string).then().extract().response();
    }

    @Then("Status code should be {int}")
    public void status_code_should_be(int int1) {
        Assert.assertEquals(res.statusCode(), int1);
    }

    @Then("Response Json should contain new Employee info")
    public void response_Json_should_contain_new_Employee_info() {
        JsonPath js = res.jsonPath();
        res.prettyPrint();
        Assert.assertEquals(js.getString("data.name"), "LayLa");
    }

    @When("I send Get request with created Id")
    public void i_send_Get_request_with_created_Id() {
        baseURI = "http://dummy.restapiexample.com/api/v1";
        given().accept(ContentType.JSON).pathParam("id", id)
                .when().get("/employee/" + id)
                .then().assertThat().statusCode(200);
    }

    @Then("Employee Json body should match with Posted Json")
    public void employee_Json_body_should_match_with_Posted_Json() {

    }


}
