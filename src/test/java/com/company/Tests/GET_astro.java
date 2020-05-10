package com.company.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GET_astro {

    @Test
    public void getAstro() {
        baseURI = "http://api.open-notify.org";
        Response res =
                given().accept(ContentType.JSON)
                        .when().get("/astros.json")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("message", equalTo("success")).and()
                        .body("number", equalTo(3))
                        .extract().response();
        JsonPath js = res.jsonPath();
        // verify the count of people
        int peopleCount = js.get("people.size()");
        Assert.assertEquals(peopleCount, 3);
        // Verify count of Names in response and print names
        List<String> names = js.getList("people.name");
        System.out.println(names);
        // use loop print names
        for (int i = 0; i < peopleCount; i++) {
            System.out.println(js.getString("people[" + i + "].name"));
        }
        // verify 3 names exist there
        int nameCount = names.size();
        Assert.assertEquals(nameCount, 3);
        // Verify person 2 is Anatoly Ivanishin
        String person2 = js.getString("people[1].name");
        Assert.assertEquals(person2, "Anatoly Ivanishin");


    }


}
