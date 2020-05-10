package com.company.Tests;

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
import java.util.List;

public class GET_Cyber {

    @Test
    public void testingTask() {
        baseURI = "https://api.github.com";
        Response res =
                given()
                        .pathParam("username", "ali")
                        .queryParam("type", "member")
                        .queryParam("sort", "pushed")
                        .queryParam("direction", "desc")
                        .accept(ContentType.JSON)
                        .when().get("/users/{username}/repos")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("owner.id[4]", equalTo(2610172))
                        .extract().response();
        res.prettyPrint();
        // Verify there is 11 id's
        JsonPath js = res.jsonPath();
        int idSize = js.getInt("id.size()");
        Assert.assertEquals(idSize, 11);
        // Get all names
        List<String> names = js.getList("name");
        System.out.println(names);
        // Verify 5th name is hatch
        String name_5th = names.get(4);
        Assert.assertEquals(name_5th, "hatch");
        // Verify owner id of 5th person is 2610172
        List<Integer> allOwnerIds = js.getList("owner.id");
        int id_5th = allOwnerIds.get(4);
        Assert.assertEquals(id_5th, 2610172);

    }
}
