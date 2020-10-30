package com.company.Tests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.net.ResponseCache;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GET_Cyber_example {

    @Test
    public void testingTask() {
        baseURI = "https://api.github.com";
        basePath = "/users/{username}/repos";
        Map<String, String> queryParamSets = new HashMap<>();
        queryParamSets.put("type", "member");
        queryParamSets.put("sort", "pushed");
        queryParamSets.put("direction", "desc");
        Response res =
                given().log().all()
                        .queryParams(queryParamSets)
                        .pathParam("username", "ali")
                        .accept(ContentType.JSON)
                        .when()
                        .log().ifValidationFails()  // will print where it fails
                        .get()
                        .then().assertThat()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .statusLine(containsString("OK"))
                        .contentType(ContentType.JSON)
                        .header("server", "GitHub.com")
                        .header("content-type", "application/json; charset=utf-8")
                        .body("name[4]", equalTo("hatch"))
                        .body("owner[4].id", equalTo(2610172))
                        .log().all()
                        .extract().response();
        res.prettyPrint();
        //statusCode separate verify
        int statusCode = res.getStatusCode();
        Assert.assertNotEquals(statusCode, 400);
        //StatusLine separate verify
        String statusLine = res.getStatusLine();
        Assert.assertTrue(statusLine.contains("OK"));
        // Parse Json using JsonPath
        JsonPath js = res.jsonPath();
        //Asserting response body element with jsonPath
        Assert.assertEquals(js.getString("name[4]"), "hatch");
        Assert.assertEquals(js.getInt("owner[4].id"), 2610172);
        // Size of response array
        int idSize = js.getInt("id.size()");
        int nameSize = js.getInt("name.size()");
        Assert.assertEquals(idSize, 11);
        Assert.assertEquals(nameSize, 11);

        // Get all names in response - Note here there is not main array's name in response
        List<String> namesList = js.getList("name");
        List<Integer> idList = js.getList("id");
        // Verify 5th name is hatch
        String name_5th = namesList.get(4);
        // Verify owner id of 5th person is 2610172
        List<Integer> allOwnerIds = js.getList("owner.id");
        int id_5th = allOwnerIds.get(4);
        Assert.assertEquals(id_5th, 2610172);

        // Find all cookies in response
        Map<String, String> cookies = res.getCookies();
        for (String Key : cookies.keySet()) {
            System.out.println(Key + " " + cookies.get(Key));
        }
        //Find all Headers in response
        Headers headers = res.getHeaders();
        Map<String, String> headersMap = new HashMap<>();
        for (Header header : headers) {
            headersMap.put(header.getName(), header.getValue()); //String, String
        }
        System.out.println(headersMap);
    }


}
