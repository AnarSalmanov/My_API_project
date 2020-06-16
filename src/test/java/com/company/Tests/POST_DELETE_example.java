package com.company.Tests;

import io.restassured.RestAssured;
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


public class POST_DELETE_example {
    public static int id;

    /**
     * Accept type Json
     * Create new employee
     * name : Nazi
     * age :30
     * salary: 3000
     * Must get unique id and store the id
     */

    @Test(priority = 1)
    public void POST_newEmployee() {
        baseURI = "http://dummy.restapiexample.com/api/v1";
        Response res = given().accept(ContentType.JSON)
                .body("{\"name\":\"Nazi\",\"salary\":\"3000\",\"age\":\"30\"}")
                .when().post("/create")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getInt("data.id");
        System.out.println("Id is :" + id);
        Assert.assertEquals(js.getString("data.name"), "Nazi");
    }

    @Test(priority = 2)
    public void DELETE_createdOne() {
        baseURI = "http://dummy.restapiexample.com/api/v1";
        Response res =
                given().pathParam("id", id)
                        .accept(ContentType.JSON)
                        .when().delete("/delete/{id}")
                        .then().assertThat().statusCode(200).extract().response();
        res.prettyPrint();

    }


}
