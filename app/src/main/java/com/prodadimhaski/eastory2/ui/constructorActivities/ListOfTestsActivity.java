package com.prodadimhaski.eastory2.ui.constructorActivities;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.utils.TestConstructorUtils;
import com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.R;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class ListOfTestsActivity extends AppCompatActivity {

    List<String> tableList;
    RecyclerView recyclerView;
    ListOfTestsAdapter adapter;
    ConstraintLayout constraintLayout;

    Button create;

    public static final String TEST_NAME = "testName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_tests);

        constraintLayout = findViewById(R.id.list_of_test_layout);
        tableList = (new TestConstructorUtils(getApplicationContext()).getListOfTable());
        System.out.println(tableList);
        recyclerView = findViewById(R.id.testsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new ListOfTestsAdapter(tableList, getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        constraintLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.canvas));

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Удалить тест?")
                        .setPositiveButton("Да.", (dialog, which) -> {
                            String swiped = tableList.get(viewHolder.getAdapterPosition());
                            TestConstructorUtils constructor = new TestConstructorUtils(getApplicationContext());
                            constructor.deleteUserTest(swiped);
                            tableList.remove(swiped);
                            adapter.notifyDataSetChanged();
                        }).setNegativeButton("Нет", null)
                        .create();*/
                final AlertDialog confirmationDialog = new AlertDialog.Builder(ListOfTestsActivity.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View nameView = inflater.inflate(R.layout.delete_window, null);

                final Button deleteTest= nameView.findViewById(R.id.buttonDeleteTest);
                final Button cancelDelete = nameView.findViewById(R.id.buttonCancelDelete);

                deleteTest.setOnClickListener(v -> {
                    confirmationDialog.dismiss();
                    String swiped = tableList.get(viewHolder.getAdapterPosition());
                    TestConstructorUtils constructor = new TestConstructorUtils(getApplicationContext());
                    constructor.deleteUserTest(swiped);
                    tableList.remove(swiped);
                    adapter.notifyDataSetChanged();
                });
                cancelDelete.setOnClickListener(v1 -> {
                    adapter.notifyDataSetChanged();
                    confirmationDialog.cancel();
                });

                confirmationDialog.setView(nameView);
                confirmationDialog.show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        initButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tableList = (new TestConstructorUtils(getApplicationContext()).getListOfTable());
        adapter = new ListOfTestsAdapter(tableList, getApplicationContext());
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
                TestConstructorUtils testConstructorUtils = new TestConstructorUtils(getApplicationContext());
                if (!testName.getText().toString().trim().equals("")) {
                    if (testConstructorUtils.isTopicExist(testName.getText().toString())) {
                        Toast.makeText(ListOfTestsActivity.this,
                                R.string.alreadyExist, Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(ListOfTestsActivity.this, ConstructorActivity.class);
                        intent.putExtra(TEST_NAME, testName.getText().toString());
                        startActivity(intent);
                    }
                } else
                    Toast.makeText(ListOfTestsActivity.this,
                            R.string.enterName, Toast.LENGTH_SHORT).show();
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
