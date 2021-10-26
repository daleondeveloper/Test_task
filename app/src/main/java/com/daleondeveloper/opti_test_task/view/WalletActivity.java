package com.daleondeveloper.opti_test_task.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.daleondeveloper.opti_test_task.R;
import com.daleondeveloper.opti_test_task.db.Wallet;
import com.daleondeveloper.opti_test_task.view.adapter.WalletListAdapter;
import com.daleondeveloper.opti_test_task.viewmodel.WalletActivityViewModel;

import java.util.List;

public class WalletActivity extends AppCompatActivity {

    private WalletActivityViewModel viewModel;
    private RecyclerView recyclerView;
    private WalletListAdapter walletListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        //Відображення кнопки назад на ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.walletRecycleView);
        initRecycleView();
        initViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //перевірка чи не зявилися нові елементи поки активність була не в фокусі
        viewModel.getAllWalletList();
    }

    private void initRecycleView(){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        walletListAdapter = new WalletListAdapter(this);
        recyclerView.setAdapter(walletListAdapter);
    }
    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(WalletActivityViewModel.class);
        viewModel.getWalletListObserver().observe(this, new Observer<List<Wallet>>() {
            @Override
            public void onChanged(List<Wallet> wallets) {
                walletListAdapter.setWalletList(wallets);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void addWallet(View view){
        startActivity(new Intent(this,AddWalletActivity.class));
    }
}