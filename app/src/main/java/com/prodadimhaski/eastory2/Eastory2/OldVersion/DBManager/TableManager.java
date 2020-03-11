package com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;

    private List<String> listOfTable = new ArrayList<String>();

    public TableManager(Context context) {
        this.context = context;
    }

    public List<String> getListOfTable() {
        myDBHelper = new DatabaseHelper(context);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = myDb.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='sqlite_sequence'",null);
        cursor.moveToLast();
        int tableSize = cursor.getPosition();
        for (int i = 0;i<tableSize;i++){
            cursor.moveToPosition(i);
            listOfTable.add(cursor.getString(0));
        }
        cursor.close();
        return listOfTable;
    }
}
