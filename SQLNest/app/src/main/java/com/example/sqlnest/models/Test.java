package com.example.sqlnest.models;

import java.util.ArrayList;

public class Test {

    private int id;
    private int totalGainScore;
    private int totalTimeTaken;
    private String testName;
    private ArrayList<Question> questions;

    public Test() {
        this.id = 0;
        this.totalGainScore = 0;
        this.totalTimeTaken = 0;
        this.testName = "";
        this.questions = new ArrayList<>();
    }


    public Test(int id, int totalGainScore, int totalTimeTaken, String testName, ArrayList<Question> questions) {
        this.id = id;
        this.totalGainScore = totalGainScore;
        this.totalTimeTaken = totalTimeTaken;
        this.testName = testName;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalGainScore() {
        return totalGainScore;
    }

    public void setTotalGainScore(int totalGainScore) {
        this.totalGainScore = totalGainScore;
    }

    public int getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(int totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
