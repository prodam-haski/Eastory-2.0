package com.prodadimhaski.eastory2.Eastory2.OldVersion.StartActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.R;

public class MainActivity extends AppCompatActivity implements Language {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = (Button)findViewById(R.id.startButton);
        final Button networkButton = (Button)findViewById(R.id.networkButton) ;
        final Button changeButton = (Button)findViewById(R.id.changeLanguage);

        if(change.getLanguage().equals("by")){
            startButton.setText(R.string.start_by);
            changeButton.setText(R.string.change_by);
            networkButton.setText(R.string.network_by);

        }

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this, TestSelection.class);
                    startActivity(intent);

                }catch (Exception e){}
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (change.getLanguage().equals("ru")) {
                        startButton.setText(R.string.start_by);
                        changeButton.setText(R.string.change_by);
                        networkButton.setText(R.string.network_by);
                        change.swipeLanguage();
                    }
                    else {
                        startButton.setText(R.string.start_ru);
                        changeButton.setText(R.string.change_ru);
                        networkButton.setText(R.string.network_ru);
                        change.swipeLanguage();
                    }

                }catch (Exception e){}
            }
        });

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //System button back double pressed
    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onBackPressed(){

        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            backToast= Toast.makeText(getBaseContext(),"Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();

    }
}
