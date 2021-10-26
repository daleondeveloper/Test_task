package com.daleondeveloper.opti_test_task.db;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =  {Wallet.class}, version = 3)
public  abstract class TestDataBase extends RoomDatabase {

    public abstract WalletDao walletDao();
    private static TestDataBase INSTANCE;

    public static TestDataBase getDBInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TestDataBase.class,"TestDataBase").
                    allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
