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

public class Db_to_1_Json_File {
    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        String query = "select * from employees limit 5";
        List<Map<String, Object>> resultListMap = DBUtil.executeQueryAndGetResultMap(query);
        List<CustomerDetails_Pojo> customersList = new ArrayList<>();
        for (int i = 0; i < resultListMap.size(); i++) {
            CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
            customerDetails.setFirst_name(resultListMap.get(i).get("first_name").toString());
            customerDetails.setLast_name(resultListMap.get(i).get("last_name").toString());
            customerDetails.setEmployee_id(Integer.valueOf(resultListMap.get(i).get("employee_id").toString()));
            customersList.add(customerDetails);
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < customersList.size(); i++) {
            jsonArray.add(customersList.get(i));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customers", jsonArray); // key - value
        File filePath = new File("Multi_JSONs_in_1_Json.json"); //to be generated file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(filePath, jsonObject);

        DBUtil.destroyConnection();

    }
}
