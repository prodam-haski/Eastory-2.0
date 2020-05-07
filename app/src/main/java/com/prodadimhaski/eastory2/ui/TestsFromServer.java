package com.prodadimhaski.eastory2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Topic;
import com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.rvadapters.TopicAdapter;
import com.prodadimhaski.eastory2.rvadapters.UserTestAdapter;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;
import com.prodadimhaski.eastory2.utils.TestConstructorUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsFromServer extends AppCompatActivity {

    List<TopicOTD> topics;
    RecyclerView topicsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_server);


        NetworkService.getInstance()
                .getJSONApi()
                .getAllTopic()
                .enqueue(new Callback<List<TopicOTD>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<TopicOTD>> call, @NonNull Response<List<TopicOTD>> response) {
                        topics = response.body();
                        topicsView = findViewById(R.id.topics);
                        topicsView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        final TopicAdapter recyclerAdapter = new TopicAdapter(getApplicationContext(), topics);
                        topicsView.setAdapter(recyclerAdapter);

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<TopicOTD>> call, @NonNull Throwable t) {
                        System.out.println("fail");
                        Toast.makeText(TestsFromServer.this, "xuina", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
