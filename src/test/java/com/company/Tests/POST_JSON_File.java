package com.company.Tests;

import com.company.Utils.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class POST_JSON_File {
    /**
     * For each time we need to use new file for static body.
     */


    String filePath = System.getProperty("user.dir") + "/addBook_staticData.json";

    @Test
    public void POST_NewBook_From_Json_File() throws IOException {
        baseURI = "http://216.10.245.166";
        basePath = "Library/Addbook.php";
        Response res =
                given()
                        .header("Content-Type", "application/json")
                        .body(JsonUtil.readFromJsonFile(filePath)).log().body()
                        .when().log().ifValidationFails()
                        .post()
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .and().body("Msg", equalTo("successfully added"))
                        .extract().response();
        res.prettyPrint();

    }

}
