package com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Check;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.StartActivities.TestSelection;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.Task;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.TaskManager;
import com.prodadimhaski.eastory2.R;

import java.util.List;

public class TestWindow extends AppCompatActivity implements TypeOfTest, Language, Check {

    private Task[] tasks;
    private int taskNumber = 0;
    private int tapCounter = 0;

    RadioButton userAnswer1;
    RadioButton userAnswer2;
    RadioButton userAnswer3;
    RadioButton userAnswer4;
    Button answer;
    Button image;
    Button description;
    TextView textTask;
    Button buttonNext;
    Button buttonPrev;
    ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_window);

        userAnswer1 = findViewById(R.id.radioButton1);
        userAnswer2 = findViewById(R.id.radioButton2);
        userAnswer3 = findViewById(R.id.radioButton3);
        userAnswer4 = findViewById(R.id.radioButton4);
        answer = findViewById(R.id.buttonAnswer);
        image = findViewById(R.id.buttonImage);
        description = findViewById(R.id.buttonDescription);
        textTask = findViewById(R.id.textTask);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrev = findViewById(R.id.buttonPrevios);

        //backgroundImage.setImageResource(R.drawable.commonback);

        if (change.getLanguage().equals("by")) {
            answer.setText(R.string.answer_by);
        }

        description.setClickable(false);
        image.setClickable(false);

        initButtons();

        TaskManager manager = new TaskManager(getApplicationContext());

        tasks = manager.createList();
        paint();
    }

    private void paint() {
        textTask.setText(tasks[taskNumber].getTaskText());
        userAnswer1.setText(tasks[taskNumber].getAnswers()[0]);
        userAnswer2.setText(tasks[taskNumber].getAnswers()[1]);
        userAnswer3.setText(tasks[taskNumber].getAnswers()[2]);
        userAnswer4.setText(tasks[taskNumber].getAnswers()[3]);
        control.setRightAnswer(tasks[taskNumber].getRightAnswer());
        description.setClickable(false);
        answer.setClickable(true);
    }

    private void initButtons() {
        description.setClickable(false);
        image.setClickable(false);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNext();
            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskNumber == 0) taskNumber = 9;
                else taskNumber--;
                paint();
            }
        });
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userAnswer = 0;
                if (userAnswer1.isChecked()) {
                    userAnswer = 1;
                    userAnswer1.setChecked(false);
                }
                if (userAnswer2.isChecked()) {
                    userAnswer = 2;
                    userAnswer2.setChecked(false);
                }
                if (userAnswer3.isChecked()) {
                    userAnswer = 3;
                    userAnswer3.setChecked(false);
                }
                if (userAnswer4.isChecked()) {
                    userAnswer = 4;
                    userAnswer4.setChecked(false);
                }
                tapCounter++;
                control.checkAnswer(userAnswer);
                answer.setClickable(false);
                description.setClickable(true);


            }
        });
    }

    private void toNext() {
        if (taskNumber == 9) taskNumber = 0;
        else taskNumber++;
        paint();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private int installBackground() {
        switch (setting.getType()) {
            case 0:
                return R.drawable.commonback;
            case 1:
                return R.drawable.polotsk1;
            case 2:
                return R.drawable.polotsk2;
            case 3:
                return R.drawable.new1;
            case 4:
                return R.drawable.newback;
            case 5:
                return R.drawable.newestback;
            case 6:
                return R.drawable.library;
        }
        return 0;
    }
}

