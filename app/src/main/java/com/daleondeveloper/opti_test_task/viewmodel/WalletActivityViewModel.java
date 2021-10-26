package com.daleondeveloper.opti_test_task.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.daleondeveloper.opti_test_task.db.TestDataBase;
import com.daleondeveloper.opti_test_task.db.Wallet;

import java.util.List;

public class WalletActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Wallet>> walletList;
    private TestDataBase testDataBase;

    public WalletActivityViewModel(Application application){
        super(application);
        walletList =  new MutableLiveData<>();

        testDataBase = TestDataBase.getDBInstance(getApplication().getApplicationContext());
    }

    public MutableLiveData<List<Wallet>> getWalletListObserver(){
        return walletList;
    }

    public void getAllWalletList(){
        List<Wallet> tempWalletList = testDataBase.walletDao().getAll();
        if(tempWalletList.size() > 0){
            walletList.postValue(tempWalletList);
        }else{
            walletList.postValue(null);
        }
    }

    public void insertWallet(Wallet wallet){

        testDataBase.walletDao().insertWallet(wallet);
        getAllWalletList();
    }
    public void updateWallet(Wallet wallet){
        testDataBase.walletDao().updateWallet(wallet);
        getAllWalletList();
    }
    public void deleteWallet(Wallet wallet){
        testDataBase.walletDao().deleteWallet(wallet);
        getAllWalletList();
    }

}
