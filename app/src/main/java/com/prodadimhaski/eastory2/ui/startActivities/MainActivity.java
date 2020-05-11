package com.prodadimhaski.eastory2.ui.startActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.interfaces.Language;
import com.prodadimhaski.eastory2.localizer.LocaleHelper;
import com.prodadimhaski.eastory2.ui.networkActivities.NetworkSelection;

public class MainActivity extends AppCompatActivity implements Language {

    Button startButton;
    Button networkButton;
    Button changeButton;

    private String mLanguageCode = "be";
    LocaleHelper localeHelper = new LocaleHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        networkButton = findViewById(R.id.networkButton);
        changeButton = findViewById(R.id.changeLanguage);
        initButton();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void initButton() {
        startButton.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, TestSelection.class);
                startActivity(intent);

            } catch (Exception e) {
            }
        });

        changeButton.setOnClickListener(v -> {
            try {

                localeHelper.setLocale(MainActivity.this, change.changeLanguage());
                startButton.setText(R.string.start);
                networkButton.setText(R.string.network);
                changeButton.setText(R.string.change);
                change.swipeLanguage();
            } catch (Exception e) {}
        });

        networkButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NetworkSelection.class);
            startActivity(intent);
        });
    }

    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }
}
