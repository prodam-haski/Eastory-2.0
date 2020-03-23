package com.prodadimhaski.eastory2.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Test;
import com.prodadimhaski.eastory2.Room.entities.TopicWithQuestions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTest extends AppCompatActivity {
    EditText testNum;
    Button getQuestions;
    SQLiteDatabase myDb;
    Database db;
    TestDao testDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testNum = findViewById(R.id.testNum);
        getQuestions = findViewById(R.id.getQuestions);

        setContentView(R.layout.test);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), DatabaseHelper.DB_NEW);
        databaseHelper.create_db();
        try {
            myDb = databaseHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db = Database.getInstance(getApplicationContext());
        testDao = db.testDao();

        if (getQuestions != null)
            getQuestions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        TopicWithQuestions topicWithQuestions = testDao.getTopicWithQuestionsById(Integer.parseInt(testNum.getText().toString()));
                        List<Question> questionList = topicWithQuestions.getQuestion();

                        for (Question question : questionList) {
                            System.out.println(question.getQuestion());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }
}
