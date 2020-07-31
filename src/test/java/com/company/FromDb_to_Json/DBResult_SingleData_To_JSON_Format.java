package com.company.FromDb_to_Json;

import com.company.Pojos.CustomerDetails_Pojo;
import com.company.Utils.DBUtil;
import com.company.Utils.JsonUtil;
import org.testng.annotations.Test;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBResult_SingleData_To_JSON_Format {

    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        String query = "select * from employees limit 1";
        ResultSet resultSet = DBUtil.executeQuery(query);
        CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int employeeID = resultSet.getInt("employee_id");
            customerDetails.setFirst_name(firstName);
            customerDetails.setLast_name(lastName);
            customerDetails.setEmployee_id(employeeID);
        }
        DBUtil.destroyConnection();
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(customerDetails);
        System.out.println(jsonPayload);
    }
}
