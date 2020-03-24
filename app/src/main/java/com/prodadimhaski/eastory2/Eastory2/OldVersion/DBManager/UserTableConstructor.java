package com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.Test;
import com.prodadimhaski.eastory2.New.TestConstructor.Interfaces.SelectedList;
import com.prodadimhaski.eastory2.Room.Dao.TopicDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.Room.entities.Topic;

import java.sql.SQLException;

public class UserTableConstructor implements SelectedList {
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;

    public UserTableConstructor(Context context) {
        this.context = context;
    }

    public void createUserTest() {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_OLD);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database db = Database.getInstance(context);
        TopicDao topicDao = db.topicDao();

        topicDao.insert(new Topic(topicDao.lastId() + 1, userList.getNameOfUserTable()));
       /*
        myDb.execSQL("CREATE TABLE " + userList.getNameOfUserTable() +
                "( _id INTEGER PRIMARY KEY, " +
                "task_ru TEXT," +
                "task_by TEXT, " +
                "answer1_ru TEXT, " +
                "answer2_ru TEXT, " +
                "answer3_ru TEXT, " +
                "answer4_ru TEXT, " +
                "answer1_by TEXT, " +
                "answer2_by TEXT, " +
                "answer3_by TEXT, " +
                "answer4_by TEXT, " +
                "rightAnswer INTEGER, " +
                "image BLOB, " +
                "description_ru TEXT, " +
                "description_by TEXT)");

        */
        myDb.close();
    }

    public void deleteUserTest(Test test) {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_OLD);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
/*
        System.out.println("DROP TABLE " + test.getTestName());
        myDb.execSQL("DROP TABLE " + test.getTestName());
*/

        Database db = Database.getInstance(context);
        TopicDao topicDao = db.topicDao();
        topicDao.delete(new Topic(topicDao.getTopicId(test.getTestName()), test.getTestName()));

        myDb.close();
    }
}
