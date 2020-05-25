package com.example.roomtutorial.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomtutorial.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDAO {

    @Query("SELECT * FROM customer")
    LiveData<List<Customer>> getAll();

    @Query("SELECT * FROM customer WHERE uid = :customerID LIMIT 1")
    Customer findByID(int customerID);

    @Insert
    void insertAll(Customer... customers);

    @Insert
    long insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update
    void updateCustomers(Customer... customers);

    @Query("DELETE FROM customer")
    void deleteAll();
}
