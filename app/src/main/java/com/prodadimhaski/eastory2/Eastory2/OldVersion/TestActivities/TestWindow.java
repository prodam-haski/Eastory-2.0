package com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Check;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.Task;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.TaskManager;
import com.prodadimhaski.eastory2.R;

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
    RadioGroup userAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_window);

        userAnswers = findViewById(R.id.radioButtons);
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

        backgroundImage = findViewById(R.id.testBackground);
        backgroundImage.setImageResource(installBackground());

        if (change.getLanguage().equals("by")) {
            answer.setText(R.string.answer_by);
        }

        initButtons();

        TaskManager manager = new TaskManager(getApplicationContext());
        tasks = manager.createList();

        paint();
    }

    @SuppressLint("ResourceType")
    private void paint() {
        textTask.setText(tasks[taskNumber].getTaskText());

        userAnswer1.setText(tasks[taskNumber].getAnswers()[0]);
        userAnswer2.setText(tasks[taskNumber].getAnswers()[1]);
        userAnswer3.setText(tasks[taskNumber].getAnswers()[2]);
        userAnswer4.setText(tasks[taskNumber].getAnswers()[3]);

        userAnswer1.setBackgroundResource(R.drawable.style_btn_answer);
        userAnswer2.setBackgroundResource(R.drawable.style_btn_answer);
        userAnswer3.setBackgroundResource(R.drawable.style_btn_answer);
        userAnswer4.setBackgroundResource(R.drawable.style_btn_answer);

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
                toPrev();
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                wasAnswer();
                tapCounter++;
                answer.setClickable(false);
                description.setClickable(true);
                control.setUserIsRight();

            }
        });
    }

    private void toNext() {
        if (taskNumber == 9) taskNumber = 0;
        else taskNumber++;
        if(control.getIsAnswered(taskNumber)){toNext();}
        else paint();
    }
    private void toPrev(){
        if (taskNumber == 0) taskNumber = 9;
        else taskNumber--;
        if(control.getIsAnswered(taskNumber)){toPrev();}
        else paint();
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

    @SuppressLint("ResourceAsColor")
    private void wasAnswer(){

        int userAnswer = 0;
        switch (userAnswers.getCheckedRadioButtonId()){
            case R.id.radioButton1: userAnswer = 1 ;break;
            case R.id.radioButton2: userAnswer = 2;break;
            case R.id.radioButton3: userAnswer = 3;break;
            case R.id.radioButton4: userAnswer = 4;break;
        }
        userAnswers.setClickable(false);
        control.checkAnswer(userAnswer,taskNumber);
        if (control.getUserIsRight()){
            switch (userAnswers.getCheckedRadioButtonId()){
                case R.id.radioButton1: userAnswer1.setBackgroundResource(R.drawable.style_btn_right);break;
                case R.id.radioButton2: userAnswer2.setBackgroundResource(R.drawable.style_btn_right);break;
                case R.id.radioButton3: userAnswer3.setBackgroundResource(R.drawable.style_btn_right);break;
                case R.id.radioButton4: userAnswer4.setBackgroundResource(R.drawable.style_btn_right);break;
            }
        }
        else {
            switch (userAnswers.getCheckedRadioButtonId()){
                case R.id.radioButton1: userAnswer1.setBackgroundResource(R.drawable.style_btn_false);break;
                case R.id.radioButton2: userAnswer2.setBackgroundResource(R.drawable.style_btn_false);break;
                case R.id.radioButton3: userAnswer3.setBackgroundResource(R.drawable.style_btn_false);break;
                case R.id.radioButton4: userAnswer4.setBackgroundResource(R.drawable.style_btn_false);break;
            }
            switch (control.getRightAnswer()){
                case 1: userAnswer1.setBackgroundResource(R.drawable.style_btn_right);break;
                case 2: userAnswer2.setBackgroundResource(R.drawable.style_btn_right);break;
                case 3: userAnswer3.setBackgroundResource(R.drawable.style_btn_right);break;
                case 4: userAnswer4.setBackgroundResource(R.drawable.style_btn_right);break;
            }
        }
        userAnswers.clearCheck();

    }
}





