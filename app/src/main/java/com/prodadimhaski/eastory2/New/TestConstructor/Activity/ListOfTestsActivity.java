package com.prodadimhaski.eastory2.New.TestConstructor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.TableManager;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities.TestWindow;
import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.New.TestConstructor.Interfaces.SelectedList;
import com.prodadimhaski.eastory2.R;

import java.util.ArrayList;
import java.util.List;

public class ListOfTestsActivity extends AppCompatActivity implements SelectedList {

    List<String> tableList = new ArrayList<String>();
    RecyclerView recyclerView;
    ListOfTestsAdapter adapter;

    Button create;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tests);

        tableList=(new TableManager(getApplicationContext()).getListOfTable());
        System.out.println(tableList);
        recyclerView = findViewById(R.id.testsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ListOfTestsAdapter(getApplicationContext(),tableList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        initButton();
    }

    public void initButton(){
        create = findViewById(R.id.buttonCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog nameDialog = new AlertDialog.Builder(ListOfTestsActivity.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View nameView = inflater.inflate(R.layout.create_window,null);

                final EditText testName = nameView.findViewById(R.id.editText);
                final Button create = nameView.findViewById(R.id.buttonCreateTest);
                final Button cancel = nameView.findViewById(R.id.buttonCancel);

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      nameDialog.dismiss();
                      if(!testName.getText().toString().trim().equals("")){
                          userList.setSelectedTable(testName.getText().toString());
                          Intent intent = new Intent(ListOfTestsActivity.this,ConstructorActivity.class);
                          startActivity(intent);
                      }
                      else Toast.makeText(ListOfTestsActivity.this, "фвла", Toast.LENGTH_SHORT).show();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameDialog.cancel();
                    }
                });

                nameDialog.setView(nameView);
                nameDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
