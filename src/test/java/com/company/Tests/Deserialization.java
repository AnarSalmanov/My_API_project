package com.company.Tests;

import com.company.Pojos.Google_Map_for_Deserialization;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
        basePath = "getCourse.php";

        Google_Map_for_Deserialization obj =
                given()
                        .param("apiKey", "value")
                        .when()
                        .get()
                        .as(Google_Map_for_Deserialization.class);
        String url = obj.getUrl();
//getting value of array element in response body
        String apiElement1 = obj.getCourses().getApi().get(0).getCourseTitle();
        String mobileElement2 = obj.getCourses().getMobile().get(1).getCourseTitle();
    }
}
