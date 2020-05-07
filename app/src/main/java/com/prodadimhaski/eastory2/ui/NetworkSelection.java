package com.prodadimhaski.eastory2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.interfaces.Name;

import java.io.IOException;

public class NetworkSelection extends AppCompatActivity implements Name {
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


        testConstructor.setOnClickListener(v -> {
            try{
                Intent intent = new Intent(NetworkSelection.this, ListOfTestsActivity.class);
                startActivity(intent);

            }catch (Exception e){}
        });

        createServer.setOnClickListener(v -> {
            final AlertDialog nameDialog = new AlertDialog.Builder(NetworkSelection.this).create();
            LayoutInflater inflater = getLayoutInflater();
            View nameView = inflater.inflate(R.layout.create_window, null);

            final TextView textView = nameView.findViewById(R.id.textView);
            textView.setText(R.string.enterServerName);
            final EditText testName = nameView.findViewById(R.id.editText);
            final Button create = nameView.findViewById(R.id.buttonCreateTest);
            final Button cancel = nameView.findViewById(R.id.buttonCancel);

            cancel.setOnClickListener(v1 -> nameDialog.cancel());
            create.setOnClickListener(v12 -> {
                if (!testName.getText().toString().trim().equals("")) {
                    nameDialog.dismiss();


                } else
                    Toast.makeText(NetworkSelection.this,
                            R.string.enterServerName, Toast.LENGTH_SHORT).show();
            });

            nameDialog.setView(nameView);
            nameDialog.show();
        });

        joinServer.setOnClickListener(v -> {
            final AlertDialog nameDialog = new AlertDialog.Builder(NetworkSelection.this).create();
            LayoutInflater inflater = getLayoutInflater();
            View nameView = inflater.inflate(R.layout.create_window, null);

            final TextView textView = nameView.findViewById(R.id.textView);
            textView.setText(R.string.enterStudentName);
            final EditText studentName = nameView.findViewById(R.id.editText);
            final Button create = nameView.findViewById(R.id.buttonCreateTest);
            create.setText(R.string.next);
            final Button cancel = nameView.findViewById(R.id.buttonCancel);

            cancel.setOnClickListener(v1 -> nameDialog.cancel());
            create.setOnClickListener(v12 -> {
                if (!studentName.getText().toString().trim().equals("")) {
                    nameDialog.dismiss();
                    nameOfStudent.setName(studentName.getText().toString().trim());
                    Intent intent = new Intent(NetworkSelection.this, TestsFromServer.class);
                    startActivity(intent);
                } else
                    Toast.makeText(NetworkSelection.this,
                            R.string.enterStudentName, Toast.LENGTH_SHORT).show();
            });

            nameDialog.setView(nameView);
            nameDialog.show();

        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
