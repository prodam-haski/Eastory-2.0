package com.prodadimhaski.eastory2.ui.testActivities;

import androidx.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prodadimhaski.eastory2.interfaces.Name;
import com.prodadimhaski.eastory2.interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.ResultDTO;
import com.prodadimhaski.eastory2.utils.Checking;
import com.prodadimhaski.eastory2.utils.Task;
import com.prodadimhaski.eastory2.utils.TaskManager;
import com.prodadimhaski.eastory2.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter.ITEM_POSITION;

public class TestWindow extends AppCompatActivity implements TypeOfTest, Name {

    private Task[] tasks;
    private int taskNumber = 0;
    private int tapCounter = 0;

    Checking control;

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
    ProgressBar progressBar;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_window);
        id = getIntent().getIntExtra(ITEM_POSITION, 0);

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
        if (!setting.isTestFromServer()) {
            backgroundImage.setImageResource(installBackground());
        }

        progressBar = findViewById(R.id.progressBar);


        initButtons();

        TaskManager manager = new TaskManager(getApplicationContext());
        if (setting.isTestFromServer()) {
            try {
                tasks = manager.createListFromServer(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, R.string.rollUp, Toast.LENGTH_SHORT).show();
            description.setVisibility(View.INVISIBLE);
        } else {

            if (setting.getType() == 0) tasks = manager.createMixedList();
            else tasks = manager.createList();
        }

        control = new Checking();
        progressBar.setMax(setting.getSizeOfTest());
        paint();
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

    private void initButtons() {

        buttonNext.setOnClickListener(v -> toNext());
        buttonPrev.setOnClickListener(v -> toPrev());

        answer.setOnClickListener(v -> {
            if (wasAnswered()) {
                tapCounter++;
                answer.setClickable(false);
                progressBar.incrementProgressBy(1);
                control.setUserIsRight();
                if(setting.isTestFromServer()){toNext();};
            } else {
                Toast.makeText(TestWindow.this, R.string.hasAnswer, Toast.LENGTH_SHORT).show();
            }
        });

        image.setOnClickListener(v -> {
            if (tasks[taskNumber].getImage() == null) {
                Toast.makeText(TestWindow.this, R.string.noImage, Toast.LENGTH_SHORT).show();
            } else {
                Dialog imageWindow = new Dialog(TestWindow.this);
                imageWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
                imageWindow.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                imageWindow.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });


                ImageView imageTask = new ImageView(TestWindow.this);
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

        description.setOnClickListener(v -> {
            if (control.getIsAnswered(taskNumber)) {
                Dialog descriptionDialog = new Dialog(TestWindow.this);
                descriptionDialog.getWindow();
                descriptionDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                descriptionDialog.setContentView(R.layout.description_window);
                TextView descriptionText = descriptionDialog.findViewById(R.id.textDescription);
                descriptionText.setText(tasks[taskNumber].getDescription());
                descriptionDialog.show();
            } else {
                Toast.makeText(TestWindow.this, R.string.noDescription, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onStop() {
        if (setting.isTestFromServer()) {
            control.setAlwaysZero();
        }
        super.onStop();
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
    private boolean wasAnswered() {

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
            default:
                return false;
        }
        userAnswers.setClickable(false);
        control.checkAnswer(userAnswer, taskNumber);

        if(!setting.isTestFromServer()) {
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
        }
        userAnswers.clearCheck();
        return true;
    }

    private void finishTest() {
        if (setting.isTestFromServer()) {
            ResultDTO resultDTO = new ResultDTO(nameOfStudent.getName(), id, control.getScore());
            NetworkService.getInstance().getJSONApi().sendResult(resultDTO).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            });
            finish();
        } else {
            Dialog descriptionDialog = new Dialog(TestWindow.this);
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
}





