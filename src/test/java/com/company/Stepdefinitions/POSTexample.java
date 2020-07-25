package com.company.Stepdefinitions;


import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
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

    public Response postRes;
    public Response getRes;
    public String id;
    public String baseUri;




    @Given("I make post request to {string} to {string} resource")
    public void i_make_post_request_to_to_resource(String string, String string2) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Laylow");
        jsonObject.put("salary", "777");
        jsonObject.put("age", "33");
        System.out.println(jsonObject);
        baseUri = string; // initializing;
        baseURI = baseUri;
        postRes =
                given().accept(ContentType.JSON)
                        .body(jsonObject)
                        .when().post(string2)
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().response();
        postRes.prettyPrint();
    }

    @Given("Response Json should contain new Employee info")
    public void response_Json_should_contain_new_Employee_info() {
        JsonPath js = postRes.jsonPath();
        Assert.assertNotNull(js.getString("id"));
        id = js.getString("id");

    }


    @When("I send Get request with created Id to {string} resource")
    public void i_send_Get_request_with_created_Id_to_resource(String string) {
        baseURI = baseUri;
        getRes =
                given()
                        .pathParam("id", id)
                        .accept(ContentType.JSON)
                        .when()
                        .get(string).then().assertThat().statusCode(200)
                        .extract().response();
    }
    @When("Employee Json body should match with Posted Json")
    public void employee_Json_body_should_match_with_Posted_Json() {
       JsonPath jsonPath = getRes.jsonPath();
       Assert.assertEquals(jsonPath.getString("name"),"Laylow");
    }

}
