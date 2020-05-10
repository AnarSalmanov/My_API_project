package com.company.Pojos;

import com.google.gson.annotations.SerializedName;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {"name", "job"})
public class RegresUser implements Serializable {

    private String name;
    private String job;

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

}
