package com.company.Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class Authentications_Basic {
    @Test
    public void basicAuthentication() {
        baseURI = "http://localhost:9090";
        basePath = "/login";  //resource
        Response res =
                given()
                        .auth().basic("Anar", "Noname13")
                        .accept(ContentType.JSON)
                        .when()
                        .get()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        //  .contentType(ContentType.JSON)
                        .extract().response();
        res.prettyPrint();
    }
}
