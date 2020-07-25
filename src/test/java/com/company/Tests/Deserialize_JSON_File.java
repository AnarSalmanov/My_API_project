package com.company.Tests;

import com.company.Pojos.CustomerDetails_Pojo;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Deserialize_JSON_File {

    @Test
    public void readFromJsonFile() throws IOException {
        File filePath = new File("SingleData.json");
        ObjectMapper objectMapper = new ObjectMapper();
        // Parsing Json File to Pojo class object , store data in Pojo class object.
        CustomerDetails_Pojo customer = objectMapper.readValue(filePath, CustomerDetails_Pojo.class);
        String first_name = customer.getFirst_name();
        String last_name = customer.getLast_name();
        int employee_id = customer.getEmployee_id();
        System.out.println(first_name + " " + last_name + " " + employee_id);
        //output:
        //John Chen 110
    }
}
