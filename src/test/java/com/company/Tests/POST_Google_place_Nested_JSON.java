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

public class POST_Google_place_Nested_JSON {

    String id;

    @Test
    public void addingPlace() {
        // simple point give value to Main class variables using setter methods
        Google_Map google_map = new Google_Map();
        google_map.setAccuracy(50);
        google_map.setName("arkansas");
        google_map.setAddress("304 Ables dr");
        google_map.setLanguage("russian");
        google_map.setPhone_number("4124131313");
        google_map.setWebsite("wwww.rahulshetty.com");
        // Create a List, for array inside main Json payload, then add the value
        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");
        // then pass that List into setter method of main Json payload class
        google_map.setTypes(typeList);

        // Create an object of the Class which we created for Child Json
        // use that object and setter method of that class to set variables
        Location location = new Location();
        location.setLat(-47.5554546);
        location.setLng(42.777777);
        // then pass that object into setter method from main Json payload class
        google_map.setLocation(location);
        // all points set , now pass the object of main Json class to the body
        baseURI = "https://rahulshettyacademy.com";
        System.out.println(google_map.toString());
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
