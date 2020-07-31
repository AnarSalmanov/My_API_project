package com.company.FromDb_to_Json;

import com.company.Pojos.CustomerDetails_Pojo;
import com.company.Utils.DBUtil;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DB_To_JSON_Format_Pavan {
    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        // Give me first 5 employee in employees table
        String query = "select * from employees limit 5";
        // Store result in List Of maps
        List<Map<String, Object>> resultMap = DBUtil.executeQueryAndGetResultMap(query);
        //Creating a list of CustomerDetails class before looping
        List<CustomerDetails_Pojo> customersList = new ArrayList<>();
        // Iterate in order to be able to perform value assigning
        for (int i = 0; i < resultMap.size(); i++) {
            //Create object of Pojo class inside loop in order to create a new object for each looping.
            CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
            // Inside loop getting result from db
            Object firstName = resultMap.get(i).get("first_name");
            Object lastName = resultMap.get(i).get("last_name");
            Object employeeID = resultMap.get(i).get("employee_id");
            // Setting value of Pojo class variables using setter methods.
            customerDetails.setFirst_name(String.valueOf(firstName));
            customerDetails.setLast_name(String.valueOf(lastName));
            customerDetails.setEmployee_id(Integer.valueOf((Integer) employeeID));
            // adding all created objects to the list.
            customersList.add(customerDetails);
        }
        DBUtil.destroyConnection();
        // dependency Gson for JSONObject, json-simple for JSONArray
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < customersList.size(); i++) {
            // converting java object into Json string
            Gson gson = new Gson();
            String jsonString = gson.toJson(customersList.get(i));
            jsonArray.add(jsonString);
        }
        //Adding JsonArray to a Map in key-value pair
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray); // here data is array name
        String finalJson = jsonObject.toJSONString().replace("\\\"", "\"")
                .replace("\"{", "{").replace("}\"", "}");
        System.out.println(finalJson);  // In Body we will use this String
        /**  Will return this
         * {
         *   "data": [
         *     {
         *       "first_name": "John",
         *       "last_name": "Chen",
         *       "employee_id": 110
         *     },
         *     {
         *       "first_name": "Ismael",
         *       "last_name": "Sciarra",
         *       "employee_id": 111
         *     },
         *     {
         *       "first_name": "Jose Manuel",
         *       "last_name": "Urman",
         *       "employee_id": 112
         *     },
         *     {
         *       "first_name": "Luis",
         *       "last_name": "Popp",
         *       "employee_id": 113
         *     },
         *     {
         *       "first_name": "Den",
         *       "last_name": "Raphaely",
         *       "employee_id": 114
         *     }
         *   ]
         * }
         */
    }
}
