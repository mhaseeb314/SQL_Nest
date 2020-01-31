package com.example.sqlnest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers")//for making table
public class Customer {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id = 0;
    @ColumnInfo(name = "name")
    private String name = "";
    @ColumnInfo(name = "city")
    private String city = "";
    @ColumnInfo(name = "country")
    private String country = "";

    @Ignore
    public Customer() {
    }

    public Customer(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
