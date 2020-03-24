package com.prodadimhaski.eastory2.New.NetworkActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.New.TestConstructor.Activity.ConstructorActivity;
import com.prodadimhaski.eastory2.New.TestConstructor.Activity.ListOfTestsActivity;
import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.RoomTest;

public class NetworkSelection extends AppCompatActivity implements Language {
    Button createServer;
    Button joinServer;
    Button testConstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_selection);

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
            try{
                Intent intent = new Intent(NetworkSelection.this, RoomTest.class);
                startActivity(intent);

            }catch (Exception e){}
        });

        joinServer.setOnClickListener(v -> {

        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
