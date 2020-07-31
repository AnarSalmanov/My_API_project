package com.company.FromDb_to_Json;

import com.company.Pojos.CustomerDetails_Pojo;
import com.company.Utils.DBUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DB_Multiple_JSON_To_1_JSON_File {
    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        // Give me first 5 employee in employees table
        String query = "select * from employees limit 5";
        // Store result in List Of maps
        List<Map<String, Object>> resultListMap = DBUtil.executeQueryAndGetResultMap(query);
        //Creating a list of CustomerDetails class before looping
        List<CustomerDetails_Pojo> customersList = new ArrayList<>();
        for (int i = 0; i < resultListMap.size(); i++) {
            //Create object of Pojo class inside loop in order to create a new object for each looping.
            CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
            // Getting result from db as a List of Maps but Could be regular ResultSet too
            Object firstName = resultListMap.get(i).get("first_name");
            Object lastName = resultListMap.get(i).get("last_name");
            Object employeeID = resultListMap.get(i).get("employee_id");
            // Setting value of Pojo class variables using setter methods.
            customerDetails.setFirst_name(firstName.toString());
            customerDetails.setLast_name(lastName.toString());
            customerDetails.setEmployee_id(Integer.valueOf(employeeID.toString()));
            // adding all created objects to the list.
            customersList.add(customerDetails);
        }


        // 1. Looping, getting objects from List and adding to JSONArray
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < customersList.size(); i++) {
            jsonArray.add(customersList.get(i));
        }
        //close db connection
        DBUtil.destroyConnection();
        // 2. Create JSONObject, key = nameOfArray , value = JSONArray reference
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);


        // Create 1 Json file with multiple Json data on that
        File filePath = new File("MultipleJSONs.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(filePath, jsonObject);


    }
}
