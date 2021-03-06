package com.example.sqlnest.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.sqlnest.dao.CustomerDao;
import com.example.sqlnest.dao.UserDao;
import com.example.sqlnest.model.Customer;
import com.example.sqlnest.model.User;

@Database(entities = {Customer.class , User.class}, version = 1)
public abstract class AppDatabaseInstance extends RoomDatabase {

    private static AppDatabaseInstance INSTANCE;

    public abstract CustomerDao customerDao();

    public abstract UserDao userDao();

    public static AppDatabaseInstance getAppDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabaseInstance.class, "my-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
