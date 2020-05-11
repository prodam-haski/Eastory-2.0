package com.prodadimhaski.eastory2.ui.constructorActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter;
import com.prodadimhaski.eastory2.rvadapters.UserTestAdapter;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;
import com.prodadimhaski.eastory2.utils.TestConstructorUtils;

import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

import static java.lang.String.valueOf;

public class UserTests extends AppCompatActivity {
    List<String> periods;
    List<Question> questions;
    List<TopicOTD> topics;

    Spinner periodsSpinner;
    RecyclerView questionView;

    Button uploadTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tests);

        TestConstructorUtils testConstructorUtils = new TestConstructorUtils(getApplicationContext());
        periods = testConstructorUtils.getListOfTable();
        periodsSpinner = findViewById(R.id.user_tests);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        periodsSpinner.setAdapter(adapter);
        periodsSpinner.setSelection(getIntent().
                getIntExtra(ListOfTestsAdapter.ITEM_POSITION, 0));

        questionView = findViewById(R.id.questions);
        questionView.setLayoutManager(new LinearLayoutManager(this));

        final UserTestAdapter recyclerAdapter = new UserTestAdapter(this, questions);
        questionView.setAdapter(recyclerAdapter);

        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TestConstructorUtils testConstructorUtils = new TestConstructorUtils(getApplicationContext());
                questions = testConstructorUtils.creteListIgnoringLanguage(periodsSpinner.getSelectedItemPosition() + 7);
                recyclerAdapter.setQuestions(questions);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uploadTest = findViewById(R.id.buttonUpload);
        uploadTest.setOnClickListener(v -> {

            NetworkService.getInstance().
                    getJSONApi().
                    getAllTopic().
                    enqueue(new Callback<List<TopicOTD>>() {
                        @Override
                        public void onResponse(Call<List<TopicOTD>> call, Response<List<TopicOTD>> response) {
                            topics = response.body();
                            Integer id = topics.get(topics.size() - 1).getId() + 1;
                            TopicOTD newTopic = new TopicOTD(id, periods.get(periodsSpinner.getSelectedItemPosition()));
                            NetworkService.getInstance().getJSONApi().sendTopic(newTopic).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });

                            List<TestOTD> quests = new LinkedList<>();
                            for(Question question: questions) {
                                quests.add(new TestOTD(Integer.valueOf(0), id, question.getQuestion_id()));
                            }
                            NetworkService.getInstance().getJSONApi().sendTest(quests).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<TopicOTD>> call, Throwable t) {
                            System.out.println("fail");
                        }
                    });
        });
    }
}

