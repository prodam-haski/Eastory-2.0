package com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.Task;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.TaskManager;
import com.prodadimhaski.eastory2.R;

public class TestWindow extends AppCompatActivity implements TypeOfTest, Language {

    private Task[] tasks;
    private int taskNumber = 0;
    private int tapCounter = 0;

    Checking control = new Checking();

    RadioGroup userAnswers;
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
        if (tasks[taskNumber].getImage()!=null) {
            if (change.getLanguage().equals("ru"))Toast.makeText(this, R.string.hasImage_ru, Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, R.string.hasImage_by, Toast.LENGTH_SHORT).show();
        }
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
        answer.setClickable(true);
    }

    private void initButtons() {

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
                control.setUserIsRight();

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tasks[taskNumber].getImage()==null){
                    if (change.getLanguage().equals("ru")) Toast.makeText(TestWindow.this, R.string.noImage_ru, Toast.LENGTH_SHORT).show();
                    else Toast.makeText(TestWindow.this, R.string.noImage_by, Toast.LENGTH_SHORT).show();
                }
                else{
                    Dialog imageWindow = new Dialog(TestWindow.this);
                    imageWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    imageWindow.getWindow().setBackgroundDrawable(
                            new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    imageWindow.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            //nothing;
                        }
                    });


                    ImageView imageTask = new ImageView(TestWindow.this);
                    Bitmap imageResult = BitmapFactory.decodeByteArray(tasks[taskNumber].getImage(),0,tasks[taskNumber].getImage().length);
                    imageTask.setImageBitmap(imageResult);
                    imageWindow.addContentView(imageTask, new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    imageWindow.show();
                    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
                    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

                    imageWindow.getWindow().setLayout(width, height);
                }
            }
        });

        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (control.getIsAnswered(taskNumber)) {
                    Dialog descriptionDialog = new Dialog(TestWindow.this);
                    descriptionDialog.setContentView(R.layout.description_window);

                    if (change.getLanguage().equals("ru")) {
                        descriptionDialog.setTitle(R.string.description_ru);
                    } else descriptionDialog.setTitle(R.string.description_by);

                    TextView descriptionText = descriptionDialog.findViewById(R.id.textDescription);
                    descriptionText.setText(tasks[taskNumber].getDescription());
                    descriptionDialog.show();
                }
                else{
                        if (change.getLanguage().equals("ru"))
                            Toast.makeText(TestWindow.this, R.string.noDescription_ru, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(TestWindow.this, R.string.noDescription_by, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        private void toNext () {
            if (tapCounter == 10) finishTest();
            else {
                if (taskNumber == 9) taskNumber = 0;
                else taskNumber++;
                if (control.getIsAnswered(taskNumber)) {
                    toNext();
                } else paint();
            }
        }
        private void toPrev () {
            if (tapCounter == 10) finishTest();
            else {
                if (taskNumber == 0) taskNumber = 9;
                else taskNumber--;
                if (control.getIsAnswered(taskNumber)) {
                    toPrev();
                } else paint();
            }
        }

        @Override
        public void onBackPressed () {
            finish();
        }

        private int installBackground () {
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
        private void wasAnswer () {

            int userAnswer = 0;
            switch (userAnswers.getCheckedRadioButtonId()) {
                case R.id.radioButton1:
                    userAnswer = 1;
                    break;
                case R.id.radioButton2:
                    userAnswer = 2;
                    break;
                case R.id.radioButton3:
                    userAnswer = 3;
                    break;
                case R.id.radioButton4:
                    userAnswer = 4;
                    break;
            }
            userAnswers.setClickable(false);
            control.checkAnswer(userAnswer, taskNumber);
            if (control.getUserIsRight()) {
                switch (userAnswers.getCheckedRadioButtonId()) {
                    case R.id.radioButton1:
                        userAnswer1.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                    case R.id.radioButton2:
                        userAnswer2.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                    case R.id.radioButton3:
                        userAnswer3.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                    case R.id.radioButton4:
                        userAnswer4.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                }
            } else {
                switch (userAnswers.getCheckedRadioButtonId()) {
                    case R.id.radioButton1:
                        userAnswer1.setBackgroundResource(R.drawable.style_btn_false);
                        break;
                    case R.id.radioButton2:
                        userAnswer2.setBackgroundResource(R.drawable.style_btn_false);
                        break;
                    case R.id.radioButton3:
                        userAnswer3.setBackgroundResource(R.drawable.style_btn_false);
                        break;
                    case R.id.radioButton4:
                        userAnswer4.setBackgroundResource(R.drawable.style_btn_false);
                        break;
                }
                switch (control.getRightAnswer()) {
                    case 1:
                        userAnswer1.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                    case 2:
                        userAnswer2.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                    case 3:
                        userAnswer3.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                    case 4:
                        userAnswer4.setBackgroundResource(R.drawable.style_btn_right);
                        break;
                }
            }
            userAnswers.clearCheck();
        }

        private void finishTest () {
            Dialog descriptionDialog = new Dialog(TestWindow.this);
            descriptionDialog.setContentView(R.layout.result_window);

            TextView descriptionText = descriptionDialog.findViewById(R.id.textResult);
            if (change.getLanguage().equals("ru")) {
                descriptionText.setText(R.string.result_ru);
            } else descriptionText.setText(R.string.result_by);
            descriptionText.append(" " + control.getScore() + "/10");


            Button buttonFinish = descriptionDialog.findViewById(R.id.buttonFinish);
            if (change.getLanguage().equals("ru")) {
                buttonFinish.setText(R.string.finish_ru);
            } else buttonFinish.setText(R.string.finish_by);
            buttonFinish.setVisibility(View.VISIBLE);
            buttonFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            descriptionDialog.show();

        }
    }





