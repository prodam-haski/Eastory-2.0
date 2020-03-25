package com.prodadimhaski.eastory2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.dbhelper.DatabaseHelper;

import java.util.List;

public class RoomTest extends AppCompatActivity {
    EditText testNum;
    Button getQuestions;
    TextView resultText;
    Database db;
    TestDao testDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        testNum = findViewById(R.id.testNum);
        getQuestions = findViewById(R.id.get_questions);
        resultText = findViewById(R.id.result_text);

        db = Database.getInstance(getApplicationContext());
        testDao = db.testDao();

        getQuestions.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(testNum.getText().toString());
                System.out.println(id);

                List<Question> questionList = testDao.getTopicWithQuestionsById(id);

                for (Question question : questionList) {
                    if (question.getLanguage_id() == 2)
                        System.out.println(question.getQuestion());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
