package com.s22010578.eduapp;

public class Attempt {
    private int id;
    private int studentId;
    private int quizId;
    private int score;

    public Attempt(int id, int studentId, int quizId, int score) {
        this.id = id;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getScore() {
        return score;
    }
}
