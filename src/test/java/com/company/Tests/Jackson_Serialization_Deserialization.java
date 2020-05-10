package com.company.Tests;

import com.company.Pojos.Employee;
import com.company.Pojos.Employee_jackson;
import com.company.Utils.JsonUtil;
import org.testng.annotations.Test;

import java.io.IOException;

public class Jackson_Serialization_Deserialization {
    /**
     * This class is used for testing purpose
     * Linked  -->  Employee_jackson
     * linked JsonUtil
     */

    @Test
    public static void creatingBodyForRequest() {
        // Serialization  --> Java Object to Json
        Employee_jackson obj1 = new Employee_jackson();
        obj1.setEmp_Id(100);
        obj1.setName("Anar");
        obj1.setSalary(2500);
        String obj1AsJson = JsonUtil.convertJavaToJson(obj1);
        System.out.println(obj1AsJson);

        // Deserialization --> Json to Java Object
        Employee_jackson obj2 = JsonUtil.convertJsonToJava(obj1AsJson, Employee_jackson.class);
        System.out.println(obj2.getEmp_Id() + " - " + obj2.getName() + " - " + obj2.getSalary());


    }


}
