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

public class POST_maps {

    @Test

    public void post_Map() {

        baseURI = "https://rahulshettyacademy.com";
        Response res = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Accept" ,"application/json")
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
        res.prettyPrint();


    }


}
