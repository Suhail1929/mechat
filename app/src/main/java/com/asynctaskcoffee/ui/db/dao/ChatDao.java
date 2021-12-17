package com.asynctaskcoffee.ui.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.asynctaskcoffee.ui.db.table.Chat;

import java.util.List;

@Dao
public interface ChatDao {

    @Query("SELECT * FROM chat")
    List<Chat> getAllChat();

    @Insert
    void insert(Chat chat);



}
