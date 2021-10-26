package com.daleondeveloper.opti_test_task.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.daleondeveloper.opti_test_task.R;
import com.daleondeveloper.opti_test_task.db.Wallet;
import com.daleondeveloper.opti_test_task.viewmodel.WalletActivityViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class AddWalletActivity extends AppCompatActivity  {

    private WalletActivityViewModel viewModel;

    private EditText dateEditText;
    private EditText titleEditText;
    private EditText priceEditText;

    private AutoCompleteTextView editTextFilledExposedDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        //Відображення кнопки назад на ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

       initEditText();
       initDropDownMenu();
       initViewModel();
    }
    public void insertWallet(View view){
        Wallet wallet = new Wallet();
        String name = titleEditText.getText().toString();
        String priceStr  = priceEditText.getText().toString();
        //Перевірка ціни і назви чи не пусті
        if(name.isEmpty()){
            Toast.makeText(this,R.string.error_empty_name,Toast.LENGTH_SHORT).show();
            return;
        }else if(priceStr.isEmpty()){
            Toast.makeText(this,R.string.error_empty_price,Toast.LENGTH_SHORT).show();
            return;
        }
        int price = Integer.parseInt(priceStr);
        wallet.setName(name);
        wallet.setData(dateEditText.getText().toString());
        //перевірка чи вибраний елемент з випадаючого списку
        if(editTextFilledExposedDropdown.getEditableText().toString().equals("Расход")){
            price *= -1;
        }else if(!editTextFilledExposedDropdown.getEditableText().toString().equals("Приход")){
            Toast.makeText(this,R.string.error_empty_type,Toast.LENGTH_SHORT).show();
            return;
        }
        wallet.setPrice(price);
        viewModel.insertWallet(wallet);
        this.finish();
    }

    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(WalletActivityViewModel.class);
    }
    private void initEditText(){
        titleEditText = findViewById(R.id.title_edit_text);
        priceEditText = findViewById(R.id.price_edit_text);
        dateEditText = findViewById(R.id.date_edit_text);
        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.setKeyListener(null);
        Calendar calendar = Calendar.getInstance();
        setDateEditText(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataPicker();
            }
        });
    }
    private void initDropDownMenu(){
        String[] type = new String[] {"Приход", "Расход"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.support_simple_spinner_dropdown_item,
                        type);

        editTextFilledExposedDropdown =
                findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(adapter);
        editTextFilledExposedDropdown.setText(type[0],false);
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
    private void openDataPicker(){
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDateEditText(year,month,dayOfMonth);
            }
        },YEAR,MONTH,DATE);
        date.show();
    }
    private void setDateEditText(int year,int month,int dayOfMonth){
        Calendar showCalendar = Calendar.getInstance();
        showCalendar.set(Calendar.YEAR,year);
        showCalendar.set(Calendar.MONTH,month);
        showCalendar.set(Calendar.DATE,dayOfMonth);

        CharSequence formateDate = DateFormat.format("dd MMM yyyy",showCalendar);

        dateEditText.setText(formateDate);
    }
}