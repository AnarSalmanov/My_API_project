package com.company.JSON;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;


public class JSONObject_ {

    /**
     * JSONObject js = new JSONObject();
     * js.put("key" ,"value");
     * Note:
     * Key must be String.
     * Value can be single data(String, int, boolean  etc) , Object obj and JSONArray.
     */

    public static void main(String[] args) throws IOException {
        //Create an object and add values
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Anar");
        jsonObject.put("job", "Engineer");
        System.out.println(jsonObject.toJSONString());
        //Creating JSONObject with "string","object" pair
        JSONObject js2 = new JSONObject();
        js2.put("company", "Bakcell");
        js2.put("employee", jsonObject);
        System.out.println(js2.toJSONString());

        /**  output
         {
         "company": "Bakcell",  -> single data
         "employee": {          -> object
            "name": "Anar",
            "job": "Engineer"
         }
         }
         */
    }
}
