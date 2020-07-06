package com.company.Tests;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.net.ResponseCache;
import java.util.List;


public class Param_query {
/**
 *  /? key=value -> is used as a queryParameters. Used for filter the response.
 * /: and / { data} used for path parameters, it is a part of URL.
 */


    /**
     * Given Accept type is Json
     * And params are limit 24
     * Then status code is 200
     * ContentType JSON
     * Employee size should be 24
     * Find id of Garrett Winters
     */
    // passing query parameters in key+value format
    @Test
    public void GET_queryParam() {
        baseURI = "http://dummy.restapiexample.com/api/v1";
        Response res =
                given().log().all()
                        .queryParam("limit", "24")
                        .accept(ContentType.JSON)
                        .when().log().ifValidationFails()
                        .get("/employees")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("data.id", hasSize(24))
                        .extract().response();
        JsonPath js = res.jsonPath();
        //Find the count of all elements in response array
        int peopleCount = js.getInt("data.size()");
        //Find all id's count in response array and verify count is 2
        int idCountResponse = js.getInt("data.id.size()");
        Assert.assertEquals(peopleCount, 24);
        // Store all names in List and print them
        List<String> namesList = js.getList("data.employee_name");
        System.out.println(namesList);
        // Find id of Garret Winters in response
        String id = "";
        for (int i = 0; i < peopleCount; i++) {
            if (js.getString("data[" + i + "].employee_name").equals("Garrett Winters")) {
                id = js.get("data[" + i + "].id");
            }
        }
        System.out.println("Id is : " + id);
    }




}
