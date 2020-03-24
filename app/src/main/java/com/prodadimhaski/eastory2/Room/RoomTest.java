package com.prodadimhaski.eastory2.Room;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.AssetDatabaseOpenHelper;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.TopicWithQuestions;

import java.sql.SQLException;
import java.util.List;

public class RoomTest extends AppCompatActivity {
    EditText testNum;

    Button getQuestions;
    SQLiteDatabase myDb;
    AssetDatabaseOpenHelper adb;
    Database db;
    TestDao testDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        testNum = findViewById(R.id.testNum);
        getQuestions = findViewById(R.id.get_questions);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), DatabaseHelper.DB_NEW);
        databaseHelper.create_db();
        try {
            myDb = databaseHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //adb = new AssetDatabaseOpenHelper(this);
        //sqLiteDatabase = adb.openDatabase();

        db = Database.getInstance(getApplicationContext());
        testDao = db.testDao();

        getQuestions.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(testNum.getText().toString());
                System.out.println(id);

                Log.d("Copied path",
                        getApplicationContext().getFilesDir().getPath() + "/" + DatabaseHelper.DB_NEW);

                TopicWithQuestions topicWithQuestions = testDao.getTopicWithQuestionsById(id);
                List<Question> questionList = topicWithQuestions.getQuestion();

                for (Question question : questionList) {
                    System.out.println(question.getQuestion());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
