package com.company.FromDb_to_Json;

import com.company.Pojos.Customer;
import com.company.Utils.DBUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Db_To_Json_Files {

    @Test
    public void example() throws IOException {
        DBUtil.createConnectionToHrDB();
        String query = "select * from employees limit 3";
        List<Map<String, Object>> resultListOfMap = DBUtil.executeQueryAndGetResultMap(query);
        List<Customer> customersList = new ArrayList<>();
        for (int i = 0; i < resultListOfMap.size(); i++) {
            Customer cus = new Customer();
            cus.setFirstName(resultListOfMap.get(i).get("first_name").toString());
            cus.setLastName(resultListOfMap.get(i).get("last_name").toString());
            cus.setEmployeeId(Integer.parseInt(resultListOfMap.get(i).get("employee_id").toString()));
            File filePath = new File("Db_to_Json_" + (i) + ".json");
            ObjectMapper objMap = new ObjectMapper();
            objMap.writeValue(filePath, cus);
        }


    }


}
