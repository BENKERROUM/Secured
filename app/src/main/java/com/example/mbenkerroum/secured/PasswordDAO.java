package com.example.mbenkerroum.secured;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by mbenkerroum on 15/02/2018.
 */

@Dao
public interface PasswordDAO {

    @Query("SELECT * FROM Password")
    List<Password> getAll();


    @Query("SELECT * FROM password WHERE uid LIKE :id LIMIT 1")
    Password findByPassword(int id);

    @Insert
    void insert(Password password);

    @Update
    void update(Password password);

    @Delete
    void delete(Password password);
}
