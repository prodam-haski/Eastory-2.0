package com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;

import java.sql.SQLException;
import java.util.Random;

public class TaskManager implements Language, TypeOfTest {

    private Task[] listTask = new Task[setting.getSizeOfTest()];
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;

    public Task[] createList() {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_OLD);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = myDb.rawQuery("SELECT * FROM " + setting.getPeriod(), null);
        cursor.moveToLast();
        int tableSize = cursor.getPosition()+1;
        int[] position = sampleRandomNumbersWithoutRepetition(0,tableSize,setting.getSizeOfTest());

        for (int i = 0; i < setting.getSizeOfTest(); i++) {
            cursor.moveToPosition(position[i]);
            listTask[i] = createTask(cursor);
        }

        cursor.close();
        myDb.close();
        return listTask;
    }

    public Task[]createMixedList(){
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_OLD);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int i = 0;
        for (String s : TYPEOFTTEST) {
            Cursor cursor = myDb.rawQuery("SELECT * FROM " + s, null);
            cursor.moveToLast();
            int tableSize = cursor.getPosition()+1;
            int[] position = sampleRandomNumbersWithoutRepetition(0, tableSize, 2);
            for(int j=0;j<2;j++){
                cursor.moveToPosition(position[j]);
                listTask[i]=createTask(cursor);
                i++;
            }
            cursor.close();
        }
        myDb.close();
        return listTask;
    }

    private Task createTask(Cursor cursor) {

        String text = new String();
        byte[] image;
        String[] answers = new String[4];
        int rightAnswer;
        String textDescription = new String();

        if (change.getLanguage().equals("by")) {
            text = cursor.getString(2);
            textDescription = cursor.getString(14);
            for (int j = 7, i = 0; j < 11; j++, i++) {
                answers[i] = cursor.getString(j);
            }
        }

        if (change.getLanguage().equals("ru")) {
            text = cursor.getString(1);
            textDescription = cursor.getString(13);
            for (int j = 3, i = 0; j < 7; j++, i++) {
                answers[i] = cursor.getString(j);
            }
        }

        rightAnswer = cursor.getInt(11);
        image = cursor.getBlob(12);

        return new Task(answers, rightAnswer, text, textDescription, image);
    }

    public TaskManager(Context context) {
        this.context = context;
    }

    public static int[] sampleRandomNumbersWithoutRepetition(int start, int end, int count) {
        Random rng = new Random();

        int[] result = new int[count];
        int cur = 0;
        int remaining = end - start;
        for (int i = start; i < end && count > 0; i++) {
            double probability = rng.nextDouble();
            if (probability < ((double) count) / (double) remaining) {
                count--;
                result[cur++] = i;
            }
            remaining--;
        }
        return result;
    }
}