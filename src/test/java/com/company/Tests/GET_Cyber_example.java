package com.company.Tests;

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

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class GET_Cyber_example {


    @Test
    public void testingTask() {
        baseURI = "https://api.github.com";
        Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("type", "member");
        queryParamMap.put("sort", "pushed");
        queryParamMap.put("direction", "desc");
        Response res =
                given().log().all()
                        .queryParams(queryParamMap)
                        .pathParam("username", "ali")
                        .accept(ContentType.JSON)
                        .when().get("/users/{username}/repos")
                        //.log().ifValidationFails()  // will print where it fails
                        .then().log().ifValidationFails()
                        .assertThat()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .statusLine(containsString("OK"))
                        .contentType(ContentType.JSON)
                        .header("server", "GitHub.com")
                        .header("content-type", "application/json; charset=utf-8")
                        .body("name[4]", equalTo("hatch"))
                        .body("owner[4].id", equalTo(2610172))
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
        for (Header h : headers) {
            System.out.println(h.getName() + " " + h.getValue()); //String, String

        }

    }

    public static int binaryPatternMatching(String pattern, String s) {
        s = s.replaceAll("[aeiou]", "0").replaceAll("[bcdfghjklmnpqrstvwxz]", "1");
        int matches = 0;
        int stop = s.length() - pattern.length();
        for (int i = 0; i <= stop; i++) {
            String sub = s.substring(pattern.length(), i);
            if (sub.equals(pattern)) {
                matches++;
            }
        }
        return matches;
    }


}
