package com.daleondeveloper.opti_test_task.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WalletDao {

    @Query("SELECT * from Wallet")
    List<Wallet> getAll();

    @Insert
    void insertWallet(Wallet... wallets);

    @Update
    void updateWallet(Wallet wallet);

    @Delete
    void deleteWallet(Wallet wallet);

    @Query("DELETE from Wallet")
    void deleteAll();
}
