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

public class Db_To_Json_Files {

    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        String query = "select * from employees limit 3";
        List<Map<String, Object>> resultMap = DBUtil.executeQueryAndGetResultMap(query);
        List<CustomerDetails_Pojo> customersList = new ArrayList<>();
        for (int i = 0; i < resultMap.size(); i++) {
            CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
            customerDetails.setFirst_name(resultMap.get(i).get("first_name").toString());
            customerDetails.setLast_name(resultMap.get(i).get("last_name").toString());
            customerDetails.setEmployee_id(Integer.valueOf(resultMap.get(i).get("employee_id").toString()));
            customersList.add(customerDetails);
        }
        for (int i = 0; i < customersList.size(); i++) {
            File filePath = new File("Db_to_Json_" + (i+1) + ".json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(filePath, customersList.get(i)); //path , object

        }

        DBUtil.destroyConnection();
    }


}
