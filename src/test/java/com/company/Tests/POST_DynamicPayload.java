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
                given()
                        .body(Payloads.addBook("513")).log().all()
                        .when().post("/Library/Addbook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .and().body("Msg", equalTo("successfully added"))
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getString("ID");
        System.out.println(id);
    }


    @Test(priority = 1)
    public void GET_NewBook_Details() {
        baseURI = base;
        Response res =
                given().log().all()
                        .queryParam("AuthorName", Payloads.author)
                        .when().get("/Library/GetBook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .body("book_name[0]", equalTo(Payloads.name))
                        .body(" isbn[0]", equalTo(Payloads.isbn))
                        .body("aisle[0]", equalTo("513"))
                        .extract().response();
        res.prettyPrint();
    }

    public String deleteBook() {
        return "{\n" +
                "\"ID\" : \"" + id + "\"\n" +
                "} \n";
    }

    @Test(priority = 2)
    public void Delete_Book_By_ID() {
        baseURI = base;
        Response res =
                given().log().all()
                        .body(deleteBook())
                        .when().delete("/Library/DeleteBook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .extract().response();
        res.prettyPrint();
    }


}
