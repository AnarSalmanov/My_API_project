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

import static io.restassured.RestAssured.*;  // given, when , then comes from static package, import manual
import static org.hamcrest.Matchers.*;     // hamcrest matchers are static package, import them manual


public class GET_JSONpath_examples {

    /**
     * Go to Endpoint "https://uinames.com/api/?amount=25
     * And verify there is 25 names reflecting in response body
     * And statusCode is 200 , contentType J.son
     */
    @Test
    public void verifyingDataInBody() {
        Response res =
                given().queryParam("amount", "25")
                        .when().get("https://uinames.com/api")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .extract().response();
        JsonPath js = res.jsonPath();
        // Find count of elements in the array in response
        int arraySize = js.getInt("array.size()");
        Assert.assertEquals(arraySize, 25);
        //Store all names of array response to List and print
        List<String> allNames = js.getList("name");
        System.out.println(allNames);
        // Find how many names in array
        System.out.println(js.getInt("name.size()"));


    }


}
