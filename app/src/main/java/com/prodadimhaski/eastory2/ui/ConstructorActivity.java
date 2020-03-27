package com.prodadimhaski.eastory2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.interfaces.Language;
import com.prodadimhaski.eastory2.interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.rvadapters.QuestionAdapter;
import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.utils.TestConstructorUtils;

import java.util.List;

public class ConstructorActivity extends AppCompatActivity implements Language, TypeOfTest {
    String[] periods;
    List<Question> questions;

    Spinner periodsSpinner;
    RecyclerView questionView;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constructor);

        if (change.getLanguage().equals("ru")) {
            periods = new String[]{getResources().getString(R.string.antiquity_ru),
                    getResources().getString(R.string.medival_ru),
                    getResources().getString(R.string.new_ru),
                    getResources().getString(R.string.newtime_ru),
                    getResources().getString(R.string.soviets_ru),
                    getResources().getString(R.string.newesttime_ru)};
        } else periods = new String[]{getResources().getString(R.string.antiquity_by),
                getResources().getString(R.string.medival_by),
                getResources().getString(R.string.new_by),
                getResources().getString(R.string.newtime_by),
                getResources().getString(R.string.soviets_by),
                getResources().getString(R.string.newesttime_by)};

        periodsSpinner = findViewById(R.id.periods);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodsSpinner.setAdapter(adapter);

        questionView = findViewById(R.id.questionList);
        questionView.setLayoutManager(new LinearLayoutManager(this));
        QuestionAdapter recyclerAdapter = new QuestionAdapter(this, questions);
        questionView.setAdapter(recyclerAdapter);

        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TestConstructorUtils testConstructorUtils = new TestConstructorUtils(getApplicationContext());
                questions = testConstructorUtils.createFullList(TYPEOFTTEST_INT[periodsSpinner.getSelectedItemPosition()]);
                recyclerAdapter.setQuestions(questions);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(v -> {
            if (recyclerAdapter.getCheckedQuestions().isEmpty()){
                Toast.makeText(this, "Пустой же!", Toast.LENGTH_SHORT).show();
            }
            else{
                TestConstructorUtils constructor = new TestConstructorUtils(getApplicationContext());
                constructor.createUserTest(recyclerAdapter.getCheckedQuestions(),
                        getIntent().getStringExtra(ListOfTestsActivity.TEST_NAME));
                finish();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}