package com.s22010578.eduapp;

public class Quiz {
    private int id;
    private String title;
    private int classId;

    public Quiz(int id, String title, int classId) {
        this.id = id;
        this.title = title;
        this.classId = classId;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
