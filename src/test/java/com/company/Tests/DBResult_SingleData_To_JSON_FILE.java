package com.company.Tests;

import com.company.Pojos.CustomerDetails_Pojo;
import com.company.Utils.DBUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBResult_SingleData_To_JSON_FILE {
    @Test
    public void example() throws SQLException, IOException {
        DBUtil.createConnectionToHrDB();
        // Give me first  employee in employees table
        String query = "select * from employees limit 1";
        ResultSet resultSet = DBUtil.executeQuery(query);
        // Creating object of POJO class
        CustomerDetails_Pojo customerDetails = new CustomerDetails_Pojo();
        // Loop get results from Db and assign to the Pojo class variables using setters.
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int employeeID = resultSet.getInt("employee_id");
            customerDetails.setFirst_name(firstName);
            customerDetails.setLast_name(lastName);
            customerDetails.setEmployee_id(employeeID);
        }
        // Create file object with the path of to be generated Json file
        File filePath = new File("SingleData.json");
        // Use ObjectMapper class of Jackson library to write value to Jason file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(filePath, customerDetails);

        DBUtil.destroyConnection();
    }
}
