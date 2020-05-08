package com.prodadimhaski.eastory2.ui.constructorActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.rvadapters.UserTestAdapter;
import com.prodadimhaski.eastory2.utils.TestConstructorUtils;

import java.util.List;

import static java.lang.String.valueOf;

public class UserTests extends AppCompatActivity {
    List<String> periods;
    List<Question> questions;

    Spinner periodsSpinner;
    RecyclerView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tests);

        TestConstructorUtils testConstructorUtils = new TestConstructorUtils(getApplicationContext());
        periods = testConstructorUtils.getListOfTable();
        periodsSpinner = findViewById(R.id.user_tests);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodsSpinner.setAdapter(adapter);
        periodsSpinner.setSelection(getIntent().
                getIntExtra(ListOfTestsAdapter.ITEM_POSITION, 0));

        questionView = findViewById(R.id.questions);
        questionView.setLayoutManager(new LinearLayoutManager(this));

        final UserTestAdapter recyclerAdapter = new UserTestAdapter(this, questions);
        questionView.setAdapter(recyclerAdapter);

        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TestConstructorUtils testConstructorUtils = new TestConstructorUtils(getApplicationContext());
                questions = testConstructorUtils.creteListIgnoringLanguage(periodsSpinner.getSelectedItemPosition() + 7);
                recyclerAdapter.setQuestions(questions);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

