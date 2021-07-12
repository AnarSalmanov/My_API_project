package com.company.Tests;

import com.company.DynamicPayloads.Payloads;
import com.company.Utils.ExcelUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;


public class DDT_POST {

    private static String id; // If no static then will be null.


    ExcelUtil excelUtil = new ExcelUtil("AddBook.xlsx", "books");

    @DataProvider
    public Object[][] testData() {
        Object[][] data = excelUtil.getDataArray();
        return data;
    }

    @Test(dataProvider = "testData")
    public void post_NewBook(String name, String isbn, String aisle, String author) {
        baseURI = "http://216.10.245.166";
        Response res =
                given().log().all()
                        .accept(ContentType.JSON)
                        .body(Payloads.addBookDynamically(name, isbn, aisle, author))
                        .when().post("/Library/Addbook.php")
                        .then().log().ifValidationFails()
                        .assertThat().statusCode(200).contentType(ContentType.JSON)
                        .and().body("Msg", equalTo("successfully added"))
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getString("ID");
        System.out.println("generated id is " + id);
        delete_Book_By_ID();
    }


    @Test
    public void delete_Book_By_ID() {
        baseURI = "http://216.10.245.166";
        Response res =
                given().log().all()
                        .body(Payloads.deleteBook(id)).log().ifValidationFails()
                        .when().delete("/Library/DeleteBook.php")
                        .then().assertThat().statusCode(not(200)).contentType(ContentType.JSON)
                        .extract().response();
        res.prettyPrint();
        System.out.println("deleted id : " + id);
    }


}
