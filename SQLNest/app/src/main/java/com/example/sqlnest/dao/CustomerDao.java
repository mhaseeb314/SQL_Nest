package com.example.sqlnest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sqlnest.model.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customers")
    List<Customer> getAll();

//    @Query("SELECT * FROM customers where name LIKE  :firstName AND last_name LIKE :lastName")
//    Customer findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from customers")
    int countCustomers();

    @Insert
    void insertAll(Customer... customers);

    @Delete
    void delete(Customer customer);
}
