package com.company.Pojos;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

//used to generate Json body in this order --> 1.name , 2.id , 3.salary
@JsonPropertyOrder(value = {"name", "emp_Id", "salary"})
public class Employee_jackson implements Serializable {

    @JsonIgnore  //used to exclude the value from body if we put on top of variable.
    private int emp_Id;
    private String name;
    private double salary;


    public void setEmp_Id(int emp_Id) {
        this.emp_Id = emp_Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getEmp_Id() {
        return emp_Id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }


}
