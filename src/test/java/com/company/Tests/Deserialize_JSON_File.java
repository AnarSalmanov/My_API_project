package com.company.Tests;

import com.company.Pojos.Customer;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Deserialize_JSON_File {

    @Test
    public void readFromJsonFile() throws IOException {
        File filePath = new File("SingleData.json");
        // Parsing Json File to Pojo class object.
        Customer cus = new ObjectMapper().readValue(filePath, Customer.class);
        //Using getters of Customer class get appropriate variable from Json file
        String firstName = cus.getFirstName();
        String lastName = cus.getLastName();
        int employeeId = cus.getEmployeeId();
        System.out.println(firstName + " " + lastName + " " + employeeId);

        // output: Ismael Sciarra 111
    }
}
