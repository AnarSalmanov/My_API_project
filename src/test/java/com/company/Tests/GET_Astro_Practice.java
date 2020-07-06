package com.company.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GET_Astro_Practice {

    @Test
    public void getAstro() {
        baseURI = "http://api.open-notify.org";
        Response res =
                given().accept(ContentType.JSON)
                        .when().get("/astros.json")
                        .then().assertThat()
                        .contentType(ContentType.JSON)
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .statusLine(containsString("OK"))
                        .header("Connection", "keep-alive")
                        .body("message", equalTo("success"))
                        .body("number", equalTo(5))
                        .extract().response();
        res.prettyPrint();
        //statusCode separate verify
        int statusCode = res.getStatusCode();
        System.out.println("StatusCode: " + statusCode);
        Assert.assertNotEquals(statusCode, 400);
        //StatusLine separate verify
        String statusLine = res.getStatusLine();
        System.out.println("StatusLine: " + statusLine);
        Assert.assertTrue(statusLine.contains("OK"));

        // Get all headers and store it in the map to verify easily
        // Impossible direct get as map from response
        Headers headers = res.getHeaders();
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue());
        }
        System.out.println(headersMap.toString());
        Assert.assertTrue(headersMap.containsKey("Server"));
        // Get all cookies and verify
        Map<String, String> cookies = res.getCookies();
        System.out.println("Cookies are here");
        for (String key : cookies.keySet()) {
            System.out.println(key + " = " + cookies.get(key));
        }

        // VERIFY RESPONSE BODY
        JsonPath js = res.jsonPath();
        // verify the size of people array in response
        int peopleSize = js.get("people.size()");
        Assert.assertEquals(peopleSize, 5);
        // Verify count of Names in response and print names
        List<String> names = js.getList("people.name");
        // verify 3 names exist there
        int nameCount = names.size();
        Assert.assertEquals(nameCount, 5);
        // Use loop and print names
        for (int i = 0; i < peopleSize; i++) {
            System.out.println(js.getString("people[" + i + "].name"));
        }
        // Verify person 2 is Anatoly Ivanishin - index_1
        String person2 = js.getString("people[1].name");
        Assert.assertEquals(person2, "Anatoly Ivanishin");
    }
/**
 * {
 *     "message": "success",
 *     "number": 5,
 *     "people": [
 *         {
 *             "craft": "ISS",
 *             "name": "Chris Cassidy"
 *         },
 *         {
 *             "craft": "ISS",
 *             "name": "Anatoly Ivanishin"
 *         },
 *         {
 *             "craft": "ISS",
 *             "name": "Ivan Vagner"
 *         },
 *         {
 *             "craft": "ISS",
 *             "name": "Doug Hurley"
 *         },
 *         {
 *             "craft": "ISS",
 *             "name": "Bob Behnken"
 *         }
 *     ]
 * }
 */


}
