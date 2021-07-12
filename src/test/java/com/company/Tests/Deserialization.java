package com.company.Tests;

import com.company.Pojos.Google_Map_for_Deserialization;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

/**
 * url;
 * services;
 * expertise;
 * Courses courses;  // returns child Json 3 nodes
 * instructor;
 * linkedIn;
 */

public class Deserialization {
    @Test
    public void deserializeResponse() {
        baseURI = "https://rahulshettyacademy.com";
        Google_Map_for_Deserialization obj =
                given()
                        .param("apiKey", "value")
                        .when()
                        .get("/getCourse.php")
                        .as(Google_Map_for_Deserialization.class);
        String url = obj.getUrl();
//getting value of array element in response body
        String apiElement1 = obj.getCourses().getApi().get(0).getCourseTitle();
        String mobileElement2 = obj.getCourses().getMobile().get(1).getCourseTitle();
    }
}
