package com.s22010578.eduapp;

public class Class {
    private int id;
    private String name;
    private String location;
    private String teacherEmail;

    public Class() {
    }

    public Class(int id, String name, String location, String teacherEmail) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.teacherEmail = teacherEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}
