package com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseHelper extends SQLiteOpenHelper implements TypeOfTest {
    public static final String TABLEANTIQUITY = "TaskAntiquity";
    public static final String TABLEMEDIVAL = "TaskMedival";
    public static final String TABLENEW1 = "TaskNew1";
    public static final String TABLENEW2 = "TaskNew2";
    public static final String TABLENEWEST = "TaskNewest";
    public static final String TABLESOVIETS = "TaskSoviets";

    private static String DB_PATH;
    private static String DB_NAME;
    private static final int SCHEMA = 2;

    public static final String DB_OLD = "ForEastory.db";
    public static final String DB_NEW = "ForEastoryNewBD.db";

    private Context myContext;

    public DatabaseHelper(Context context, String dbName) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext = context;
        DB_NAME = dbName;
        DB_PATH = context.getFilesDir().getPath() + "/"+ DB_NAME;
        Log.d("files dir + name", DB_PATH);
    }

       @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }

    public void create_db(){
        InputStream myInput;
        OutputStream myOutput;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                myInput = myContext.getAssets().open(DB_NAME);
                String outFileName = DB_PATH;

                myOutput = new FileOutputStream(outFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
}