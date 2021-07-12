package com.company.FromDb_to_Json;

import com.company.Pojos.Customer;
import com.company.Utils.DBUtil;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DB_To_JSON_Format {
    @Test
    public void example(){
        DBUtil.createConnectionToHrDB();
        String query = "select * from employees limit 5";
        List<Map<String, Object>> resultMap = DBUtil.executeQueryAndGetResultMap(query);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < resultMap.size(); i++) {
            Customer cus = new Customer();
            cus.setFirstName(resultMap.get(i).get("first_name").toString());
            cus.setLastName(resultMap.get(i).get("last_name").toString());
            cus.setEmployeeId(Integer.parseInt(resultMap.get(i).get("employee_id").toString()));
            Gson gson = new Gson();
            String objToJson = gson.toJson(cus);
            jsonArray.add(objToJson);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        String finalJson = jsonObject.toJSONString().replace("\\\"", "\"")
                .replace("\"{", "{").replace("}\"", "}");
        System.out.println(finalJson);


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
