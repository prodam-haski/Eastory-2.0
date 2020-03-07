package com.prodadimhaski.eastory2.New.TestConstructor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.QuestionAdapter;
import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.FullListConstructor;
import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.Question;
import com.prodadimhaski.eastory2.New.TestConstructor.Interfaces.SelectedList;
import com.prodadimhaski.eastory2.R;

import java.util.ArrayList;
import java.util.List;

public class ConstructorActivity extends AppCompatActivity implements Language, TypeOfTest, SelectedList {
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodsSpinner.setAdapter(adapter);

        questionView = findViewById(R.id.questionList);
        questionView.setLayoutManager(new LinearLayoutManager(this));
        final QuestionAdapter recyclerAdapter = new QuestionAdapter(this, questions);
        questionView.setAdapter(recyclerAdapter);

        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FullListConstructor fullListConstructor = new FullListConstructor(getApplicationContext());
                questions = fullListConstructor.createFullList(TYPEOFTTEST[periodsSpinner.getSelectedItemPosition()]);
                recyclerAdapter.setQuestions(questions);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Integer> selectedQuestions = new ArrayList<>();
                selectedQuestions= recyclerAdapter.getNumberChecked();
                if(selectedQuestions.isEmpty()){
                    Toast.makeText(ConstructorActivity.this, "пустой список", Toast.LENGTH_SHORT).show();
                }
                else{
                    userList.setSelectedList(selectedQuestions);
                    userList.setSelectedTable(TYPEOFTTEST[periodsSpinner.getSelectedItemPosition()]);
                    Intent intent = new Intent(ConstructorActivity.this,ListOfTestsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}