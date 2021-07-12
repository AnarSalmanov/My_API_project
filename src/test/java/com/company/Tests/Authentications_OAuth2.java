package com.company.Tests;


import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Authentications_OAuth2 {
    @Test
    public void OAuth2_E2E() {
        // 1.Go to Authentication page, provide credentials, click next, copy URL,store in String.
        String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
        // String partialCode = url.split("code=")[1]; // array[i]
        int begin = url.indexOf("4");
        int end = url.indexOf("scope") - 1;
        String code = url.substring(begin, end);
        System.out.println("code : " + code);
        // 2.Use that code in request body send POST request to Google OAuth2
        Response postRes =
                given()
                        .urlEncodingEnabled(false)
                        .queryParam("code", code)
                        .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("state", "verifyfjdss")
                        .queryParam("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                        // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                        .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                        .when().log().all()
                        .post("https://www.googleapis.com/oauth2/v4/token")
                        .then().extract().response();
        postRes.prettyPrint();
        // 3. Get access token from response
        JsonPath jsonPath = postRes.jsonPath();
        String accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);
        // 4. Use access token to pass OAuth2 authentication
        Response getRes =
                given().accept(ContentType.JSON)
                        //it can be in header as well.
                        .queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
                        .when()
                        .get("https://rahulshettyacademy.com/getCourse.php")
                        .then().extract().response();
        System.out.println(getRes);
    }
}










