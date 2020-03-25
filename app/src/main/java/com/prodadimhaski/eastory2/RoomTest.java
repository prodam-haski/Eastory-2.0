package com.prodadimhaski.eastory2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.dbhelper.DatabaseHelper;
import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.entities.Question;

import java.sql.SQLException;
import java.util.List;

public class RoomTest extends AppCompatActivity {
    EditText testNum;
    Button getQuestions;
    TextView resultText;
    SQLiteDatabase myDb;
    Database db;
    TestDao testDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        testNum = findViewById(R.id.testNum);
        getQuestions = findViewById(R.id.get_questions);
        resultText = findViewById(R.id.result_text);

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

                List<Question> questionList = testDao.getTopicWithQuestionsById(id);
/*
                StringBuilder result = new StringBuilder();

                for (Question question : questionList) {
                    result.append(question.getQuestion()).append("\n");
                }
                resultText.setText(result);
*/
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
