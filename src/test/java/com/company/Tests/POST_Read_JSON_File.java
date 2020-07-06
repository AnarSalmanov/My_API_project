package com.company.Tests;

import com.company.DynamicPayloads.Payloads;
import com.company.Utils.JsonUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.springframework.context.annotation.DependsOn;
import org.testng.Assert;
import org.testng.annotations.*;
import sun.security.krb5.internal.PAData;


import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POST_Read_JSON_File {
    /**
     * For each time we need to use new file for static body.
     */

    String base = "http://216.10.245.166";
    String path = "addBook.json";

    @Test
    public void POST_NewBook_From_Json_File() throws IOException {
        baseURI = base;
        Response res =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .body(JsonUtil.readFromJsonFile(path))
                        .when().log().ifValidationFails()
                        .post("/Library/Addbook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .and().body("Msg", equalTo("successfully added"))
                        .extract().response();
        res.prettyPrint();

    }

}