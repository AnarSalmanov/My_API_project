package com.company.Pojos;

public class Nested_Json_Courses {

    private String name;
    private Courses courses; // --> This is a nested Json, declare as class not as String
    private String job;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }


}
