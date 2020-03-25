package com.prodadimhaski.eastory2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.dbhelper.DatabaseHelper;
import com.prodadimhaski.eastory2.interfaces.Language;
import com.prodadimhaski.eastory2.Room.Dao.LanguageDao;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.Room.entities.Question;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FullListConstructor implements Language {

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;
    private Database db;

    public FullListConstructor(Context context) {
        this.context = context;
    }

    public List<Question> createFullList(int period){

        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_OLD);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db = Database.getInstance(context);
        TestDao testDao = db.testDao();
        List<Question> fullList;

        fullList = filterByLanguage(testDao.getTopicWithQuestionsById(period));

        return fullList;
    }

    private List<Question> filterByLanguage(List<Question> allQuestions) {
        LanguageDao languageDao = db.languageDao();
        List<Question> resultList = new ArrayList<>();

        for(Question question: allQuestions) {
            System.out.println(question.getQuestion_id());
            if (languageDao.getLanguage(question.getLanguage_id()).equals(change.getLanguage())) {
                resultList.add(question);
            }
        }

        return resultList;
    }
}