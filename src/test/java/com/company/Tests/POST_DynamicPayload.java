package com.company.Tests;

import com.company.DynamicPayloads.Payloads;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POST_DynamicPayload {

    private String id;
    String base = "http://216.10.245.166";

    @Test(priority = 1)
    public void POST_NewBook() {
        baseURI = base;
        Response res =
                given().accept(ContentType.JSON)
                        .body(Payloads.addBookDynamically("5 minutes", "13", "15", "Anar")).log().all()
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
