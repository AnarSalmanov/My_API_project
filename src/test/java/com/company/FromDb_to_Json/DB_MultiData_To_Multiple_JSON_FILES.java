package com.company.FromDb_to_Json;

import com.company.Pojos.CustomerDetails_Pojo;
import com.company.Utils.DBUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DB_MultiData_To_Multiple_JSON_FILES {

    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        // Give me first 5 employee in employees table
        String query = "select * from employees limit 5";
        // Store result in List Of maps
        List<Map<String, Object>> resultMap = DBUtil.executeQueryAndGetResultMap(query);
        //Creating a list of CustomerDetails class before looping
        List<CustomerDetails_Pojo> customersList = new ArrayList<>();
        for (int i = 0; i < resultMap.size(); i++) {
            //Create object of Pojo class inside loop in order to create a new object for each looping.
            CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
            customerDetails.setFirst_name(resultMap.get(i).get("first_name").toString());
            customerDetails.setLast_name(resultMap.get(i).get("last_name").toString());
            customerDetails.setEmployee_id(Integer.valueOf(resultMap.get(i).get("employee_id").toString()));
            // adding all created objects to the list.
            customersList.add(customerDetails);
        }
        DBUtil.destroyConnection();
        // Create multiple Json file
        for (int i = 0; i < customersList.size(); i++) {
            File filePath = new File("MultiData" + i + " .json");
            ObjectMapper objectMapper = new ObjectMapper();
            //Iterate and get each object from List and write as JSON to Json file
            objectMapper.writeValue(filePath, customersList.get(i));
        }


    }


}
