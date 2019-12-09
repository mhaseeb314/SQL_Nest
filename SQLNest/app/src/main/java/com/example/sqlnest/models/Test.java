package com.example.sqlnest.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable{

    private int id;
    private int totalGainScore;
    private int totalTimeTaken;
    private String testName;
    private int totalCorrectAnswers;
    private ArrayList<Question> questions;

    public Test(int id, int totalGainScore, int totalTimeTaken, String testName, int totalCorrectAnswers, ArrayList<Question> questions) {
        this.id = id;
        this.totalGainScore = totalGainScore;
        this.totalTimeTaken = totalTimeTaken;
        this.testName = testName;
        this.totalCorrectAnswers = totalCorrectAnswers;
        this.questions = questions;
    }

    public void setToDefault(){

        this.totalGainScore = 0;
        this.totalTimeTaken = 0;
        this.totalCorrectAnswers = 0;
        for (int i = 0; i < questions.size() - 1; i++) {
            this.questions.get(i).setToDefault();
        }
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

    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

}
