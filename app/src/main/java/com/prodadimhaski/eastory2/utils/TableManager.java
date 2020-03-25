package com.prodadimhaski.eastory2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.Room.Dao.TopicDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.dbhelper.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;

    private List<String> listOfTable = new ArrayList<>();

    public TableManager(Context context) {
        this.context = context;
    }

    public List<String> getListOfTable() {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_NEW);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Database db = Database.getInstance(context);
        TopicDao topicDao = db.topicDao();

        listOfTable = topicDao.getDefaultTopics();

        return listOfTable;
    }
}
