package com.company.DynamicPayloads;

import com.github.javafaker.Faker;

public class Payloads {

    public static String name;
    public static String isbn;
    public static String author;


    public static String addBook(String aisle) {
        Faker faker = new Faker();
        name = faker.name().firstName();
        isbn = name.substring(0, 3);
        author = faker.name().lastName();
        return "{\n" +
                "\n" +
                "\"name\":\"" + name + "\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"" + author + "\"\n" +
                "}\n";
    }

    public static String addBookDynamically(String name, String isbn, String aisle, String author) {
        return "{\n" +
                "\n" +
                "\"name\":\"" + name + "\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"" + author + "\"\n" +
                "}\n";
    }


}
