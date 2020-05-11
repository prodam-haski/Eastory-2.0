package com.prodadimhaski.eastory2.ui.networkActivities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.interfaces.TempList;
import com.prodadimhaski.eastory2.rvadapters.TopicAdapter;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;
import com.prodadimhaski.eastory2.ui.testActivities.TestFromServerActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestsFromServer extends AppCompatActivity implements TempList {

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
                                 Toast.makeText(getApplicationContext(), "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show();
                             }
                         }
                );
    }
}
