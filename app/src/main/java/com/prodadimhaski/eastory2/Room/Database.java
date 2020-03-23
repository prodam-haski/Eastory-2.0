package com.prodadimhaski.eastory2.Room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Test;
import com.prodadimhaski.eastory2.Room.entities.Topic;

@androidx.room.Database(entities = {Question.class, Topic.class, Test.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract TestDao testDao();

    public static synchronized Database getInstance(Context context)  {
        if (instance == null) {
            try {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class,
                        "ForEastory2")
                        .createFromAsset(context.getFilesDir().getPath() + DatabaseHelper.DB_NEW)
                        .build();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}