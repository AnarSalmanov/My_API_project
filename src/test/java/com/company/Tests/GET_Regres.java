package com.company.Tests;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.Matchers.*;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.Json;

import java.net.ResponseCache;
import java.util.List;

public class GET_Regres {

    @Test
    public void getAllUsers() {
        baseURI = "https://reqres.in/";
        Response res =
                given()
                        .accept(ContentType.JSON)
                        .queryParam("page", "2")
                        .when().get("/api/users")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("page", equalTo(2))
                        .body("total", equalTo(12))
                        .extract().response();
        // res.prettyPrint();

        // assert values from nodes
        JsonPath js = res.jsonPath();
        Assert.assertEquals(js.getString("ad.company"), "StatusCode Weekly");

        //Assert values from Arrays

        //1. Get all id's
        List<Integer> ids = js.getList("data.id");
        Assert.assertEquals(ids.size(), 6);
        //2. Get the array's size
        int arraySize = js.getInt("data.size()");
        Assert.assertEquals(arraySize, 6);
        //3. Find firstName where id is 10
        String firstName = "";
        for (int i = 0; i < arraySize; i++) {
            if (js.getInt("data[" + i + "].id") == 10) {
                firstName = js.getString("data[" + i + "].first_name");
                break;
            }
        }
        Assert.assertEquals(firstName, "Byron");

    }
}
