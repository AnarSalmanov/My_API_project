package com.company.Tests;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.hamcrest.Matchers.*;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import java.net.ResponseCache;

public class GET_accepType_Res_Header {


    /**
     * VERIFY CONTENT TYPE AND HEADER (SOME SECTION) OF THE RESPONSE
     * When I send GET request to this URL with Header "Accept" as "application/json")
     * then, I should get StatusCode 200 and ContentType of response is Json
     * and print response.
     */
    @Test
    public void GET_verify_Header_section() {
        //sending request with header "Accept","application/json" - also known as accept type
        Response res =
                given().accept(ContentType.JSON)
                        .when().get("https://uinames.com/api/")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .header("Server", "cloudflare") //Verify some section of Header in response
                        .extract().response();
        res.prettyPrint();


    }


}
