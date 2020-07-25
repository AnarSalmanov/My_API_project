package com.company.Tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

public class Authentications_Basic {
    @Test
    public void basicAuthentication() {
        baseURI = "http://localhost:9090";
        Response res =
                given()
                        .auth().basic("Anar", "Noname20391271_")
                        .when()
                        .get("/login")
                        .then().assertThat().statusCode(200).extract().response();
        res.prettyPrint();
    }
}
