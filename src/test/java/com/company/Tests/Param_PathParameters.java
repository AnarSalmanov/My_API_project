package com.company.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Param_PathParameters {
    /**
     * Path parameter is a part of URL , format-->  URL/{name} or  URL/:name (can be any thing)
     * In test should be passed in resource section -->  /resource /{name}
     * Note: In Postman We should pass as /resource/:name .
     * Otherwise path param section will not be active
     */

    @Test
    public void GET_pathParam() {
        baseURI = "https://api.got.show/api/show";
        Response res =
                given().log().all()
                        .pathParam("name", "Yohn Royce")
                        .accept(ContentType.JSON).log().all()
                        .when().log().ifValidationFails()
                        .get("/characters/{name}")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("house", equalTo("House Royce"))
                        .extract().response();
        res.prettyPrint();


    }
}
