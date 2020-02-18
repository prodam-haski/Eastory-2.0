package com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;

import java.util.List;
import java.util.Random;

public class TaskManager implements Language, TypeOfTest {

    //private List<Task> listTask;
    private Task[] listTask = new Task[SIZE];
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;


    public Task[] createList(){
        for (int i=0;i<SIZE;i++){

            switch (setting.getType()){
                //  case 0: listTask.add(createTask());break;
                case 1: listTask[i] = createTask("TaskAntiquity");break;
                case 2: listTask[i] = createTask("TaskMedival");break;
                case 3: listTask[i] = createTask("TaskNew1");break;
                case 4: listTask[i] = createTask("TaskNew2");break;
                case 5: listTask[i] = createTask("Soviets");break;
                case 6: listTask[i] = createTask("TaskNewest");break;
            }
        }

        return listTask;
    }

    private Task createTask(String period){
        Task task = new Task();
        final Random random = new Random();
        String text = new String();
        byte[] image;
        String[] answers = new String[4];
        int rightAnswer;
        String textDescrition = new String() ;

        Cursor cursor = myDb.rawQuery("SELECT * FROM "+period, null);
        //cursor.moveToPosition(random.nextInt(20));
        cursor.moveToPosition(1);

        if(change.getLanguage().equals("by")){
            text = cursor.getString(2);
            textDescrition = cursor.getString(14);
            for (int j=7, i = 0;j<11;j++, i++){
                answers[i]=cursor.getString(j);
            }
        }

        if(change.getLanguage().equals("ru")){
            text = cursor.getString(1);
            textDescrition = cursor.getString(13);
            for (int j=3, i = 0;j<7;j++, i++){
                answers[i]=cursor.getString(j);
            }
        }

        rightAnswer= cursor.getInt(11);
        image=cursor.getBlob(12);

        task.setAnswers(answers);
        task.setImage(image);
        task.setRightAnswer(rightAnswer);
        task.setTaskText(text);
        task.setDescription(textDescrition);

        cursor.close();
        return task;
    }

}