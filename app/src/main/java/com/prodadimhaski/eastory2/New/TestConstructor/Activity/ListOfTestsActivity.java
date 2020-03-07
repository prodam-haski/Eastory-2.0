package com.prodadimhaski.eastory2.New.TestConstructor.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.prodadimhaski.eastory2.New.TestConstructor.Interfaces.SelectedList;
import com.prodadimhaski.eastory2.R;

public class ListOfTestsActivity extends AppCompatActivity implements SelectedList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tests);
        System.out.println(userList.getSelectedList());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
