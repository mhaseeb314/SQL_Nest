package com.example.sqlnest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sqlnest.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customers")
    List<Customer> getAll();

    @Query("SELECT COUNT(*) from customers")
    int countCustomers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Customer... customers);

    @Delete
    void delete(Customer customer);

    @Query("DELETE FROM customers WHERE name LIKE :name")
    void deleteByName(String name);

    @Query("DELETE FROM customers WHERE city LIKE :city")
    void deleteByCity(String city);

    @Query("DELETE FROM customers WHERE country LIKE :country")
    void deleteByCountry(String country);

    @Query("DELETE FROM customers WHERE id LIKE :id")
    void deleteById(int id);

    @Query("DELETE FROM customers")
    void deleteAllUsers();

    //updating queries to update all records accordingly
    @Query("UPDATE customers SET name = :name")
    void updateAllNames(String name);

    @Query("UPDATE customers SET city = :city")
    void updateAllCity(String city);

    @Query("UPDATE customers SET country = :country")
    void updateAllCountry(String country);

    //select queries for updating the record with multiple changing values
    //for example if we want to update name and city of record then we
    //will have to make update query that updates only name and city and
    // we will have to do this for all remaining columns respectively.
    // So we will select user and change it then insert that record, id of
    // record will be same so that record will be replaced when we will insert it.
    @Query("SELECT * FROM customers WHERE name LIKE :name")
    List<Customer> getCustomerByName(String name);

    @Query("SELECT * FROM customers WHERE id LIKE :id")
    List<Customer> getCustomerById(int id);

    @Query("SELECT * FROM customers WHERE city LIKE :city")
    List<Customer> getCustomerByCity(String city);

    @Query("SELECT * FROM customers WHERE country LIKE :country")
    List<Customer> getCustomerByCountry(String country);

}
