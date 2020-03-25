package com.prodadimhaski.eastory2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.prodadimhaski.eastory2.dbhelper.DatabaseHelper;
import com.prodadimhaski.eastory2.interfaces.SelectedList;
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
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_NEW);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database db = Database.getInstance(context);
        TopicDao topicDao = db.topicDao();

        Topic newTopic = new Topic(topicDao.lastId() + 1, userList.getNameOfUserTable());
        topicDao.insert(newTopic);

        Toast.makeText(context, newTopic.getTopic_id() + newTopic.getTopic(), Toast.LENGTH_SHORT).show();

        myDb.close();
    }

    public void deleteUserTest(String test) {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_NEW);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database db = Database.getInstance(context);
        TopicDao topicDao = db.topicDao();
        topicDao.delete(new Topic(topicDao.getTopicId(test), test));

        myDb.close();
    }
}
