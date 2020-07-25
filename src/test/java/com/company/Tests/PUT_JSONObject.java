package com.company.Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.*;

public class PUT_JSONObject {


    @Test
    public void updateUser() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Anar");
        jsonObject.put("job", "Engineer");
        baseURI = "https://reqres.in/";
        basePath="/api/users/3";
        Response res =
                given().log().all()
                        .accept(ContentType.JSON)
                        .body(jsonObject)
                        .when().log().ifValidationFails()
                        .put()
                        .then().assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        String updatedMessage = js.getString("updatedAt");
        //current day
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDay = simpleDateFormat.format(date);
        System.out.println(currentDay);
        Assert.assertTrue(updatedMessage.contains(currentDay)); //1 day ahead

    }


}

