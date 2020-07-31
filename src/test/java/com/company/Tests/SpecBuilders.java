package com.company.Tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpecBuilders {
    /**
     * Request and Response spec builders
     * It is used to combine common/repeated steps for every test .
     */
    static RequestSpecification reqSpec; // interface
    static ResponseSpecification resSpec; //interface

    // RequestSpecBuilder - Building as RequestSpecification
    public static RequestSpecification reqSpec() {
        Map<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("type", "member");
        queryParamsMap.put("sort", "pushed");
        queryParamsMap.put("direction", "desc");
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParamsMap)
                .addPathParam("username", "ali")
                .build();
        return reqSpec;
    }

    // ResponseSpecBuilder - Building as ResponseSpecifications
    public static ResponseSpecification resSpec() {
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        return resSpec;
    }


    @Test
    public void withSpecBuilders() {
        Response res =
                given()
                        .spec(reqSpec())
                        .when()
                        .get("/users/{username}/repos")
                        .then().assertThat()
                        .spec(resSpec())
                        .body("name[4]", equalTo("hatch"))
                        .body("owner[4].id", equalTo(2610172))
                        .extract().response();
        res.prettyPrint();
    }
}