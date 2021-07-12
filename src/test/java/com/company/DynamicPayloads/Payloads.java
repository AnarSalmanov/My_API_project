package com.company.DynamicPayloads;

import com.github.javafaker.Faker;

public class Payloads {

    /**
     * Before string part(in value) which you want make dynamic in payload
     * we need to replace variable part with below way in the body String.
     *   " + variable + " -> "" included
     * ex :    "\"name\":\""  + variable + "\",\n" +
     *
     * Note: If while copying the Json body input and pasting somewhere it
     * includes lots of weird attributes, then use following link and past
     * that Json to the edit-box and click ESCAPE. Then place that under the
     * reusable payload methods. Look at methods in this class
     * https://www.freeformatter.com/json-escape.html
     */

    // This returns ready Json String to use in payload body, passing data dynamically in parameters
    public static String addBookDynamically(String name, String isbn, String aisle, String author) {
        return "{\n" +
                "\"name\":\"" + name + "\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"" + author + "\"\n" +
                "}\n";
    }

    // This returns ready Json String to use in payload body, passing data dynamically in parameters
    public static String deleteBook(String id) {
        return "{\n" +
                "\"ID\" : \"" + id + "\"\n" +
                "} \n";
    }


}
