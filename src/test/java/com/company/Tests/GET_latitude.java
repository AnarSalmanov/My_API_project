package com.company.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GET_latitude {

    @Test
    public void latitute() {
        baseURI = "http://api.open-notify.org";
        Response res = given().accept(ContentType.JSON)
                .when().get("/iss-now.json")
                .then().assertThat().statusCode(200).contentType(ContentType.JSON).body("message", equalTo("success"))
                .body("iss_position.longitude", equalTo("-127.8665"))
                .body("iss_position.latitude", equalTo("48.2900"))
                .body("timestamp", equalTo(1586296831)).extract().response();


    }

}
