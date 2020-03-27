package com.prodadimhaski.eastory2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.prodadimhaski.eastory2.interfaces.Language;
import com.prodadimhaski.eastory2.R;

public class NetworkSelection extends AppCompatActivity implements Language {
    Button createServer;
    Button joinServer;
    Button testConstructor;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_selection);
        constraintLayout = findViewById(R.id.networkselection_layout);
        constraintLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.networkbackground));

        createServer = findViewById(R.id.buttonCreate);
        joinServer = findViewById(R.id.buttonJoin);
        testConstructor = findViewById(R.id.buttonConstructor);

        if(change.getLanguage().equals("by")){
            createServer.setText(R.string.create_by);
            joinServer.setText(R.string.join_by);
            testConstructor.setText(R.string.constructor_by);
        }

        testConstructor.setOnClickListener(v -> {
            try{
                Intent intent = new Intent(NetworkSelection.this, ListOfTestsActivity.class);
                startActivity(intent);

            }catch (Exception e){}
        });

        createServer.setOnClickListener(v -> {

        });

        joinServer.setOnClickListener(v -> {

        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
