package com.example.sqlnest.models;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    private int id;
    private String question;
    private int correctAnswerId;
    private int questionTime;
    private int questionScore;
    private int gainScore;
    private ArrayList<Option> options;

    public Question() {
        this.id = 0;
        this.question = "";
        this.correctAnswerId = 0;
        this.questionTime = 0;
        this.questionScore = 0;
        this.gainScore = 0;
        this.options = new ArrayList<>();
    }

    public void setToDefault(){

        for (int i = 0; i < options.size() - 1; i++) {
            options.get(i).setSelectedOption(false);
        }
    }

    public Question(int id, String question,
                    int correctAnswerId,
                    int questionTime,
                    int questionScore,
                    int gainScore,
                    ArrayList<Option> options) {

        this.id = id;
        this.question = question;
        this.correctAnswerId = correctAnswerId;
        this.questionTime = questionTime;
        this.questionScore = questionScore;
        this.gainScore = gainScore;
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public int getQuestionTime() {
        return questionTime;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public int getGainScore() {
        return gainScore;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public void setQuestionTime(int questionTime) {
        this.questionTime = questionTime;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    public void setGainScore(int gainScore) {
        this.gainScore = gainScore;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }
}
