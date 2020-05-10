package com.company.Pojos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Employee {
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private String age;
    @SerializedName("salary")
    private String salary;


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary;
    }


}
