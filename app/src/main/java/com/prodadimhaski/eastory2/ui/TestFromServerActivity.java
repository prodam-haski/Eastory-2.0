package com.prodadimhaski.eastory2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.interfaces.Name;
import com.prodadimhaski.eastory2.interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.ResultDTO;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.utils.Checking;
import com.prodadimhaski.eastory2.utils.Task;
import com.prodadimhaski.eastory2.utils.TaskManager;

import java.util.List;

import static com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter.ITEM_POSITION;

public class TestFromServerActivity extends AppCompatActivity implements TypeOfTest, Name {
    private int id;

    private List<TestOTD> tasksID;
    private Task[] tasks;

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
    ProgressBar progressBar;

    private int taskNumber = 0;
    private int tapCounter = 0;

    Checking control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra(ITEM_POSITION,0);
        setContentView(R.layout.activity_test_window);

        userAnswers = findViewById(R.id.radioButtons);
        userAnswer1 = findViewById(R.id.radioButton1);
        userAnswer2 = findViewById(R.id.radioButton2);
        userAnswer3 = findViewById(R.id.radioButton3);
        userAnswer4 = findViewById(R.id.radioButton4);

        answer = findViewById(R.id.buttonAnswer);
        image = findViewById(R.id.buttonImage);

        description = findViewById(R.id.buttonDescription);
        description.setVisibility(View.INVISIBLE);
        textTask = findViewById(R.id.textTask);
        buttonNext = findViewById(R.id.buttonNext);
        buttonPrev = findViewById(R.id.buttonPrevios);

        progressBar = findViewById(R.id.progressBar);

        TaskManager manager = new TaskManager(getApplicationContext());

        tasks = manager.createListFromServer(id);

        control = new Checking();
        progressBar.setMax(setting.getSizeOfTest());
        Toast.makeText(this, R.string.rollUp, Toast.LENGTH_SHORT).show();
        initButtons();
        paint();

    }

    private void initButtons() {

        buttonNext.setOnClickListener(v -> toNext());
        buttonPrev.setOnClickListener(v -> toPrev());

        answer.setOnClickListener(v -> {
            if (wasAnswered()) {
                tapCounter++;
                answer.setClickable(false);
                progressBar.incrementProgressBy(1);
                toNext();

            } else {
                Toast.makeText(TestFromServerActivity.this, R.string.hasAnswer, Toast.LENGTH_SHORT).show();
            }
        });

        image.setOnClickListener(v -> {
            if (tasks[taskNumber].getImage() == null) {
                Toast.makeText(TestFromServerActivity.this, R.string.noImage, Toast.LENGTH_SHORT).show();
            } else {
                Dialog imageWindow = new Dialog(TestFromServerActivity.this);
                imageWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
                imageWindow.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                imageWindow.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });


                ImageView imageTask = new ImageView(TestFromServerActivity.this);
                Bitmap imageResult = BitmapFactory.decodeByteArray(tasks[taskNumber].getImage(), 0, tasks[taskNumber].getImage().length);
                imageTask.setImageBitmap(imageResult);
                imageWindow.addContentView(imageTask, new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                imageWindow.show();
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.90);

                imageWindow.getWindow().setLayout(width, height);
            }
        });

    }

    private void toNext() {
        if (tapCounter == setting.getSizeOfTest()) finishTest();
        else {
            if (taskNumber == setting.getSizeOfTest() - 1) taskNumber = 0;
            else taskNumber++;
            if (control.getIsAnswered(taskNumber)) {
                toNext();
            } else paint();
        }
    }

    private void toPrev() {
        if (tapCounter == setting.getSizeOfTest()) finishTest();
        else {
            if (taskNumber == 0) taskNumber = setting.getSizeOfTest() - 1;
            else taskNumber--;
            if (control.getIsAnswered(taskNumber)) {
                toPrev();
            } else paint();
        }
    }


    private boolean wasAnswered() {

        int userAnswer;
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
            default:
                return false;
        }
        userAnswers.setClickable(false);
        userAnswers.clearCheck();
        control.checkAnswer(userAnswer, taskNumber);

        return true;
    }

    @SuppressLint("ResourceType")
    private void paint() {
        if (tasks[taskNumber].getImage() != null) {
            Toast.makeText(this, R.string.hasImage, Toast.LENGTH_SHORT).show();
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

    public void onStop() {
        control.setScore(0);
        finishTest();
        super.onStop();
    }

    private void finishTest(){

        ResultDTO resultDTO = new ResultDTO(nameOfStudent.getName(),id,control.getScore());
        NetworkService.getInstance().getJSONApi().sendResult(resultDTO);

        Dialog descriptionDialog = new Dialog(TestFromServerActivity.this);
        descriptionDialog.getWindow();
        descriptionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        descriptionDialog.setContentView(R.layout.result_window);

        TextView descriptionText = descriptionDialog.findViewById(R.id.textResult);
        descriptionText.setText(R.string.result);
        descriptionText.append(" " + control.getScore() + "/" + setting.getSizeOfTest());


        Button buttonFinish = descriptionDialog.findViewById(R.id.buttonFinish);
        buttonFinish.setText(R.string.finish);
        buttonFinish.setVisibility(View.VISIBLE);
        buttonFinish.setOnClickListener(v -> finish());

        descriptionDialog.show();


    }
}
