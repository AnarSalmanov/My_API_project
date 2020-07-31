package com.company.Tests;

import com.company.Utils.DBUtil;
import org.testng.annotations.Test;
import sun.security.pkcs11.Secmod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseTesting {

    @Test
    public void example() throws SQLException {
        DBUtil.createConnectionToHrDB();
        // Give me first 10 employees in employees table
        String query = "select * from employees limit 10";
        List<Map<String, Object>> employees = DBUtil.executeQueryAndGetResultMap(query);
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).get("first_name"));
            System.out.println(employees.get(i).get("last_name"));
        }
        DBUtil.destroyConnection();
    }
}
