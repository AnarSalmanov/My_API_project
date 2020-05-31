package com.company.Tests;

import com.company.Pojos.Google_Map;
import com.company.Pojos.Location;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POST_Google_place {

    String id;

    @Test
    public void addingPlace() {
        // simple point give value direct using setter methods of main Json payload
        Google_Map google_map = new Google_Map();
        google_map.setAccuracy(50);
        google_map.setName("arkansas");
        google_map.setAddress("304 Ables dr");
        google_map.setLanguage("russian");
        google_map.setPhone_number("4124131313");
        google_map.setWebsite("wwww.rahulshetty.com");
        // Create a List for array inside main Json payload, then add the value
        // then past that List into setter method of main Json payload class
        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");
        google_map.setTypes(typeList);
        // Create an object of the Class which we created for Child Json in main payload
        // use that object and setter method of that class to give the value to the variables
        // then pass that object into setter method from main Json payload class
        Location location = new Location();
        location.setLat(-47.5554546);
        location.setLng(42.777777);
        google_map.setLocation(location);
        // all points set , now pass the object of main Json class to the body
        baseURI = "https://rahulshettyacademy.com";
        Response res =
                given().log().all()
                        .queryParam("key", "qaclick123")
                        .accept(ContentType.JSON)
                        .body(google_map)
                        .when()
                        .post("maps/api/place/add/json")
                        .then()
                        .assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("status", equalTo("OK"))
                        .body("scope", equalTo("APP"))
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getString("id");
        System.out.println(id);
    }
}
