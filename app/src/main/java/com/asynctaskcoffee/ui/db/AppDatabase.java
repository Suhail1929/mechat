package com.asynctaskcoffee.ui.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.asynctaskcoffee.ui.db.dao.ChatDao;
import com.asynctaskcoffee.ui.db.dao.CustomerDao;
import com.asynctaskcoffee.ui.db.table.Chat;
import com.asynctaskcoffee.ui.db.table.Customer;

@Database(entities = {Chat.class, Customer.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ChatDao chatDao();
    public abstract CustomerDao customerDao();
}

