package com.asynctaskcoffee.ui.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.asynctaskcoffee.ui.db.table.Customer;

import java.util.List;

@Dao
public interface CustomerDao {

    @Query("SELECT * FROM customer")
    List<Customer> getAll();

    @Query("SELECT EXISTS(SELECT * FROM customer WHERE email =:userEmail AND password=:password)")
    Boolean login (String userEmail, String password );
    @Query("SELECT * FROM customer WHERE email =:userEmail AND password=:password")
    Customer loginUser (String userEmail, String password );
    @Insert
    void insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update
    int update(Customer customer);


}
