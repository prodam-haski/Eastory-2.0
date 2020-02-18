package com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.StartActivities.TestSelection;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.TaskManager;
import com.prodadimhaski.eastory2.R;

import java.util.List;

public class TestWindow extends AppCompatActivity implements TypeOfTest, Language {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_window);

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.setBackgroundDrawableResource(setting.backgroundInstall());

        final RadioButton userAnswer1=(RadioButton) findViewById(R.id.radioButton1);
        final RadioButton userAnswer2=(RadioButton) findViewById(R.id.radioButton2);
        final RadioButton userAnswer3=(RadioButton) findViewById(R.id.radioButton3);
        final RadioButton userAnswer4=(RadioButton) findViewById(R.id.radioButton4);
        final Button answer=(Button) findViewById(R.id.buttonAnswer);
        final Button image=(Button) findViewById(R.id.buttonImage);
        final Button description=(Button) findViewById(R.id.buttonDescription);
        final TextView textTask = (TextView)findViewById(R.id.textTask);
        final Button buttonNext=(Button)findViewById(R.id.buttonNext);
        final Button buttonPrev=(Button)findViewById(R.id.buttonPrevios);

        if(change.getLanguage().equals("by")){
            answer.setText(R.string.answer_by);
        }

        description.setClickable(false);
        image.setClickable(false);

        TaskManager manager = new TaskManager();


        manager.createList();

    }



    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(TestWindow.this, TestSelection.class);
            startActivity(intent);
            finish();
        }catch (Exception e){}
    }
}

