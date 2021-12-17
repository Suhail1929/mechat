package com.asynctaskcoffee.ui.db;

import android.content.Context;

import androidx.room.Room;


public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //notre objet de base de données d'applications
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //création de la base de données de l'application avec le générateur de base de données Room
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "ChatDb").fallbackToDestructiveMigration().build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
