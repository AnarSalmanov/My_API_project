package com.company.Pojos;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"name", "age", "salary"})
public class Employee implements Serializable {

    private String name;
    private String age;
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
