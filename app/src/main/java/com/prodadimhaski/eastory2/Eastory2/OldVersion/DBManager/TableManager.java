package com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableManager {
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;

    private List<Test> listOfTable = new ArrayList<Test>();

    public TableManager(Context context) {
        this.context = context;
    }

    public List<Test> getListOfTable() {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_OLD);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = myDb.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name!='sqlite_sequence'AND name!='android_metadata'",null);
        cursor.moveToLast();
        int tableSize = cursor.getPosition()+1;
        for (int i = 0;i<tableSize;i++){
            cursor.moveToPosition(i);
            listOfTable.add(new Test(cursor.getString(0)));
        }
        cursor.close();
        return listOfTable;
    }
}
