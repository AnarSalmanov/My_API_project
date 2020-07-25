package com.company.Tests;

import com.company.Utils.DBUtil;
import org.testng.annotations.Test;
import sun.security.pkcs11.Secmod;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTesting {

    @Test
    public void example() throws SQLException {
        DBUtil.createConnectionToHrDB();
        // Give me first 10 employees in employees table
        String query = "select * from employees limit 10";
        ResultSet resultSet = DBUtil.executeQuery(query);
        while (resultSet.next()) {
            //find first_name by emp_id
            if (resultSet.getInt("employee_id") == 113) {
                System.out.println("Name of emp_id 113 is : "
                        + resultSet.getString("first_name"));
            }
            // print all firstName and last names
            System.out.println(resultSet.getString("first_name")
                    + " - " + resultSet.getString("last_name")
                    + " - " + resultSet.getInt("employee_id"));
        }
        DBUtil.destroyConnection();
    }
}
