package com.prodadimhaski.eastory2.Room;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.Room.Dao.LanguageDao;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.Dao.TopicDao;
import com.prodadimhaski.eastory2.Room.entities.Language;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Test;
import com.prodadimhaski.eastory2.Room.entities.Topic;

import java.io.File;

@androidx.room.Database(entities = {Question.class, Topic.class, Test.class, Language.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract TestDao testDao();
    public abstract LanguageDao languageDao();
    public abstract TopicDao topicDao();

    public static String ASSET_DIR;

    public static synchronized Database getInstance(Context context)  {
        if (instance == null) {
            try {
                ASSET_DIR = context.getFilesDir().getPath() + "/"+ DatabaseHelper.DB_NEW;
                Log.d("Asset dir", ASSET_DIR);
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class,
                        "AppDB.db")
                        .createFromFile(new File(ASSET_DIR))
                        .allowMainThreadQueries()
                        .build();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
