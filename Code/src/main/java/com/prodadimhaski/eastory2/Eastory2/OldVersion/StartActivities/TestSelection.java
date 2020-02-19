package com.prodadimhaski.eastory2.Eastory2.OldVersion.StartActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities.TestWindow;
import com.prodadimhaski.eastory2.R;

public class TestSelection extends AppCompatActivity implements Language, TypeOfTest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_selection);

        final Button buttonCommon = (Button)findViewById(R.id.buttonCommon);
        final Button buttonAnciety = (Button)findViewById(R.id.buttonAnciety);
        final Button buttonMedival = (Button)findViewById(R.id.buttonMedival);
        final Button buttonNew1 = (Button)findViewById(R.id.buttonNew1);
        final Button buttonNew2 = (Button)findViewById(R.id.buttonNew2);
        final Button buttonSoviets = (Button)findViewById(R.id.buttonSoviets);
        final Button buttonNewest = (Button)findViewById(R.id.buttonNewest);


        if(change.getLanguage().equals("by")){
            buttonCommon.setText(R.string.common_by);
            buttonAnciety.setText(R.string.antiquity_by);
            buttonMedival.setText(R.string.medival_by);
            buttonNew1.setText(R.string.new_by);
            buttonNew2.setText(R.string.newtime_by);
            buttonSoviets.setText(R.string.soviets_by);
            buttonNewest.setText(R.string.newesttime_by);
        }

        Button buttons[] = new Button[] {buttonCommon, buttonAnciety,
                buttonMedival, buttonNew1, buttonNew2, buttonSoviets, buttonNewest};

        for (int i = 0; i < buttons.length; i ++) {
            buttons[i].setOnClickListener(startTest(i));
        }

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private View.OnClickListener startTest(final int type) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.setType(type);
                setting.setPeriod();
                Intent intent = new Intent(getApplicationContext(), TestWindow.class);
                startActivity(intent);
            }
        };
    }

    //System button back

    @Override
    public void onBackPressed(){
            finish();
    }
}
