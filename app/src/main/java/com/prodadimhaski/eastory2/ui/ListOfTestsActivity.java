package com.prodadimhaski.eastory2.ui;

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

import com.prodadimhaski.eastory2.utils.TableManager;
import com.prodadimhaski.eastory2.utils.UserTableConstructor;
import com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.interfaces.SelectedList;
import com.prodadimhaski.eastory2.R;

import java.util.List;

public class ListOfTestsActivity extends AppCompatActivity implements SelectedList {

    List<String> tableList;
    RecyclerView recyclerView;
    ListOfTestsAdapter adapter;

    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tests);

        tableList = (new TableManager(getApplicationContext()).getListOfTable());
        System.out.println(tableList);
        recyclerView = findViewById(R.id.testsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ListOfTestsAdapter(tableList);
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
                String swiped = tableList.get(viewHolder.getAdapterPosition());
                UserTableConstructor constructor = new UserTableConstructor(getApplicationContext());
                constructor.deleteUserTest(swiped);
                tableList.remove(swiped);
                adapter.notifyDataSetChanged();

                final AlertDialog nameDialog = new AlertDialog.Builder(ListOfTestsActivity.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View nameView = inflater.inflate(R.layout.create_window, null);

                final EditText testName = nameView.findViewById(R.id.editText);
                final Button create = nameView.findViewById(R.id.buttonCreateTest);
                final Button cancel = nameView.findViewById(R.id.buttonCancel);

                create.setOnClickListener(v -> {
                    nameDialog.dismiss();
                    if (!testName.getText().toString().trim().equals("" )) {
                        userList.setNameOfUserTable(testName.getText().toString());
                        Intent intent = new Intent(ListOfTestsActivity.this, ConstructorActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(ListOfTestsActivity.this, "фвла", Toast.LENGTH_SHORT).show();
                });

                cancel.setOnClickListener(v -> nameDialog.cancel());

                nameDialog.setView(nameView);
                nameDialog.show();
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
        adapter = new ListOfTestsAdapter(tableList);
        recyclerView.setAdapter(adapter);
    }


    public void initButton() {
        create = findViewById(R.id.buttonCreate);
        create.setOnClickListener(v -> {
            final AlertDialog nameDialog = new AlertDialog.Builder(ListOfTestsActivity.this).create();
            LayoutInflater inflater = getLayoutInflater();
            View nameView = inflater.inflate(R.layout.create_window, null);

            final EditText testName = nameView.findViewById(R.id.editText);
            final Button create = nameView.findViewById(R.id.buttonCreateTest);
            final Button cancel = nameView.findViewById(R.id.buttonCancel);

            create.setOnClickListener(v12 -> {
                nameDialog.dismiss();
                if (!testName.getText().toString().trim().equals("" )) {
                    userList.setNameOfUserTable(testName.getText().toString());
                    Intent intent = new Intent(ListOfTestsActivity.this, ConstructorActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(ListOfTestsActivity.this, "фвла", Toast.LENGTH_SHORT).show();
            });

            cancel.setOnClickListener(v1 -> nameDialog.cancel());

            nameDialog.setView(nameView);
            nameDialog.show();
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
