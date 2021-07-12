package com.company.JSON;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

//if we don't put this order it will give random order in JSON
@JsonPropertyOrder(value = {"name", "age", "sale", "middleName"})
//if class don't implements to serializable then will give random order in JSON
public class Employee implements Serializable {
    private String name;
    private int age;
    private boolean sale;
    private String middleName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleAName) {
        this.middleName = middleAName;
    }


}
