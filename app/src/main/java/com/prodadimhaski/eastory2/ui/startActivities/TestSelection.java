package com.prodadimhaski.eastory2.ui.startActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.prodadimhaski.eastory2.interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.ui.testActivities.TestWindow;

public class TestSelection extends AppCompatActivity implements  TypeOfTest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);

        final Button buttonCommon = findViewById(R.id.buttonCommon);
        final Button buttonAnciety = findViewById(R.id.buttonAnciety);
        final Button buttonMedival = findViewById(R.id.buttonMedival);
        final Button buttonNew1 = findViewById(R.id.buttonNew1);
        final Button buttonNew2 = findViewById(R.id.buttonNew2);
        final Button buttonSoviets = findViewById(R.id.buttonSoviets);
        final Button buttonNewest = findViewById(R.id.buttonNewest);


        Button[] buttons = new Button[]{buttonCommon, buttonAnciety,
                buttonMedival, buttonNew1, buttonNew2, buttonSoviets, buttonNewest};

        for (int i = 0; i < buttons.length; i ++) {
            buttons[i].setOnClickListener(startTest(i));
        }

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private View.OnClickListener startTest(final int type) {
        return v -> {
            setting.setType(type);
            setting.setPeriod();
            Intent intent = new Intent(getApplicationContext(), TestWindow.class);
            startActivity(intent);
        };
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}

