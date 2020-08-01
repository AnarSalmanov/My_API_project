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
        String query = "select * from employees limit 5";
        List<Map<String, Object>> resultMap = DBUtil.executeQueryAndGetResultMap(query);
        List<CustomerDetails_Pojo> customersList = new ArrayList<>();
        for (int i = 0; i < resultMap.size(); i++) {
            CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
            customerDetails.setFirst_name(resultMap.get(i).get("first_name").toString());
            customerDetails.setLast_name(resultMap.get(i).get("last_name").toString());
            customerDetails.setEmployee_id(Integer.valueOf(resultMap.get(i).get("employee_id").toString()));
            customersList.add(customerDetails);
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < customersList.size(); i++) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(customersList.get(i));
            jsonArray.add(jsonString);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        String finalJson = jsonObject.toJSONString().replace("\\\"", "\"")
                .replace("\"{", "{").replace("}\"", "}");
        System.out.println(finalJson);  // In Body we will use this String

        DBUtil.destroyConnection();

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
