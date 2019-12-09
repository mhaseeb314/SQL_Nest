package com.example.sqlnest.models;

import java.io.Serializable;

public class Option implements Serializable {

    private int id;
    private String option;
    private boolean selectedOption;

    public Option(){

        this.id = 0;
        this.option = "";
        selectedOption = false;
    }

    public Option(int id, String option, boolean selectedOption) {
        this.id = id;
        this.option = option;
        this.selectedOption = selectedOption;
    }

    public int getId() {
        return id;
    }

    public String getOption() {
        return option;
    }

    public boolean isSelectedOption() {
        return selectedOption;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setSelectedOption(boolean selectedOption) {
        this.selectedOption = selectedOption;
    }
}
