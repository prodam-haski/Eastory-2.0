package com.prodadimhaski.eastory2.ui.networkActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.rvadapters.ResultAdapter;
import com.prodadimhaski.eastory2.rvadapters.TopicAdapter;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.ResultDTO;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    RecyclerView resultView;
    List<ResultDTO> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activities);

        resultView = findViewById(R.id.testResult);

        NetworkService.getInstance()
                .getJSONApi()
                .getResult()
                .enqueue(new Callback<List<ResultDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ResultDTO>> call, @NonNull Response<List<ResultDTO>> response) {
                        resultList = response.body();
                        resultView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        final ResultAdapter recyclerAdapter = new ResultAdapter(getApplicationContext(), resultList);
                        resultView.setAdapter(recyclerAdapter);

                    }

                    @Override
                    public void onFailure(@NonNull Call<List<ResultDTO>> call, @NonNull Throwable t) {
                        System.out.println("fail");
                        Toast.makeText(ResultActivity.this, "xuina", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
