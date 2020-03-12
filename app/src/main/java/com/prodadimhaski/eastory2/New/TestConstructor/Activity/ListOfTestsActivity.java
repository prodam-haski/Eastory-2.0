package com.prodadimhaski.eastory2.New.TestConstructor.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.TableManager;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.UserTableConstructor;
import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.Test;
import com.prodadimhaski.eastory2.New.TestConstructor.Interfaces.SelectedList;
import com.prodadimhaski.eastory2.R;

import java.util.ArrayList;
import java.util.List;

public class ListOfTestsActivity extends AppCompatActivity implements SelectedList {

    List<Test> tableList = new ArrayList<Test>();
    RecyclerView recyclerView;
    ListOfTestsAdapter adapter;

    Button create;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tests);

        tableList = (new TableManager(getApplicationContext()).getListOfTable());
        System.out.println(tableList);
        recyclerView = findViewById(R.id.testsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ListOfTestsAdapter(getApplicationContext(), tableList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Test swiped = tableList.get(viewHolder.getAdapterPosition());
                UserTableConstructor constructor = new UserTableConstructor(getApplicationContext());
                constructor.deleteUserTest(swiped);
                tableList.remove(swiped);
                adapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



        initButton();
    }

    @Override
    protected void onResume(){
        super.onResume();
        tableList = (new TableManager(getApplicationContext()).getListOfTable());
        adapter = new ListOfTestsAdapter(getApplicationContext(), tableList);
        recyclerView.setAdapter(adapter);
    }


    public void initButton() {
        create = findViewById(R.id.buttonCreate);
        delete = findViewById(R.id.buttonDelete);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog nameDialog = new AlertDialog.Builder(ListOfTestsActivity.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View nameView = inflater.inflate(R.layout.create_window, null);

                final EditText testName = nameView.findViewById(R.id.editText);
                final Button create = nameView.findViewById(R.id.buttonCreateTest);
                final Button cancel = nameView.findViewById(R.id.buttonCancel);

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nameDialog.dismiss();
                        if (!testName.getText().toString().trim().equals("" )) {
                            userList.setNameOfUserTable(testName.getText().toString());
                            Intent intent = new Intent(ListOfTestsActivity.this, ConstructorActivity.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(ListOfTestsActivity.this, "фвла", Toast.LENGTH_SHORT).show();
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserTableConstructor constructor = new UserTableConstructor(getApplicationContext());
                List<Test> selectedTests = new ArrayList<Test>();

                for (Test test: tableList) {
                    if (test.isSelected()) {
                        selectedTests.add(test);
                    }
                }
                constructor.deleteUserTest(selectedTests);
                tableList = (new TableManager(getApplicationContext()).getListOfTable());
                adapter = new ListOfTestsAdapter(getApplicationContext(), tableList);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
