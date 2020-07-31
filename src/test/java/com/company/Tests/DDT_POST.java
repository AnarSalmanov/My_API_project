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

    String base = "http://216.10.245.166";
    ExcelUtil excelUtil = new ExcelUtil("AddBook.xlsx", "books");

    @DataProvider
    public Object[][] testData() {
        Object[][] data = excelUtil.getDataArray();
        return data;
    }

    @Test(dataProvider = "testData")
    public void POST_NewBook(String name, String isbn, String aisle, String author) {
        baseURI = base;
        Response res =
                given().log().all()
                        .accept(ContentType.JSON)
                        .body(Payloads.addBookDynamically(name, isbn, aisle, author)).log().ifValidationFails()
                        .when().post("/Library/Addbook.php")
                        .then().assertThat().statusCode(200).contentType(ContentType.JSON)
                        .and().body("Msg", equalTo("successfully added"))
                        .extract().response();
        res.prettyPrint();
        JsonPath js = res.jsonPath();
        id = js.getString("ID");
        System.out.println(id);

        DDT_POST ddt_post = new DDT_POST();
        ddt_post.Delete_Book_By_ID();
    }


     @Test(dependsOnMethods = "POST_NewBook")
    public void Delete_Book_By_ID() {
        baseURI = base;
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
