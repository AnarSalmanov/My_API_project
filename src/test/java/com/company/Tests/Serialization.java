package com.company.Tests;

import com.company.Pojos.Google_Map_for_Serialization;
import com.company.Pojos.Location;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Serialization {

    String id;

    @Test
    public void addingPlace() {
        Google_Map_for_Serialization google_map = new Google_Map_for_Serialization();
        google_map.setAccuracy(50);
        google_map.setName("arkansas");
        google_map.setAddress("304 Ables dr");
        google_map.setLanguage("russian");
        google_map.setPhone_number("4124131313");
        google_map.setWebsite("wwww.rahulshetty.com");
        // Create a List, for array inside main Json, pass object to setter
        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");
        google_map.setTypes(typeList);
        // Create an object of Location class,give value to setters, use object in main class
        Location location = new Location();
        location.setLat(-47.5554546);
        location.setLng(42.777777);
        google_map.setLocation(location);
        // all points set , now pass the object of main Json class to the body
        baseURI = "https://rahulshettyacademy.com";
        basePath = "maps/api/place/add/json";
        Response res =
                given().log().all()
                        .queryParam("key", "qaclick123")
                        .accept(ContentType.JSON)
                        .body(google_map).log().all()
                        .when().log().ifValidationFails()
                        .post()
                        .then().assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("status", equalTo("OK"))
                        .body("scope", equalTo("APP"))
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getString("id");
        System.out.println(id);

    }
    /**
     {
     "location": {                           <--- As a class
     "lat": -47.5554546,
     "lng": 42.777777
     },
     "accuracy": 50,
     "name": "arkansas",
     "phone_number": "4124131313",
     "address": "304 Ables dr",
     "types": [                              <----- As a List
     "shoe park",
     "shop"
     ],
     "website": "wwww.rahulshetty.com",
     "language": "russian"
     }
     */
}