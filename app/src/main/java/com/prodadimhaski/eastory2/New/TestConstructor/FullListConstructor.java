package com.prodadimhaski.eastory2.New.TestConstructor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor.Task;

import java.sql.SQLException;

import static com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest.setting;

public class FullListConstructor implements Language {

    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;

    public FullListConstructor(Context context) {
        this.context = context;
    }

    public Questions createFullList(String period){

        myDBHelper = new DatabaseHelper(context);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = myDb.rawQuery("SELECT * FROM " +period, null);
        cursor.moveToLast();
        int tableSize = cursor.getPosition()+1;
        Questions fullList = new Questions(tableSize);
        cursor.moveToFirst();

        for (int i=0;i<tableSize;i++)
        {
            int id = cursor.getInt(0);
            String question;
            if(change.getLanguage().equals("ru"))question = cursor.getString(1);
            else question = cursor.getString(2);
            fullList.setQuestions(i,question);
            fullList.setQuestionsId(i,id);
            cursor.moveToNext();
        }

        cursor.close();
        myDb.close();
        return fullList;
    }
}
