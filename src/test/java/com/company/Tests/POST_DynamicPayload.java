package com.company.Tests;

import com.company.DynamicPayloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.springframework.context.annotation.DependsOn;
import org.testng.Assert;
import org.testng.annotations.*;
import sun.security.krb5.internal.PAData;


import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POST_DynamicPayload {

    private String id;
    String base = "http://216.10.245.166";

    @Test(priority = 0)
    public void POST_NewBook() {
        baseURI = base;
        Response res =
                given() .accept(ContentType.JSON)
                        .body(Payloads.addBookDynamically("5 minutes","13","15","Anar")).log().all()
                        .when().post("/Library/Addbook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .and().body("Msg", equalTo("successfully added"))
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getString("ID");
        System.out.println(id);
    }




    @Test(priority = 2)
    public void Delete_Book_By_ID() {
        baseURI = base;
        Response res =
                given().log().all()
                        .body(Payloads.deleteBook(id))
                        .when().delete("/Library/DeleteBook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .extract().response();
        res.prettyPrint();
    }


}
