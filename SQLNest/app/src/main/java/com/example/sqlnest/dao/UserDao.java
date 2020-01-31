package com.example.sqlnest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.sqlnest.model.Customer;
import com.example.sqlnest.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user where u_email LIKE  :email")
    User findUserByEmail(String email);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}
