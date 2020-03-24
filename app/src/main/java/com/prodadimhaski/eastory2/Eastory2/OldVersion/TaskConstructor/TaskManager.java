package com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.Language;
import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;
import com.prodadimhaski.eastory2.Room.Dao.LanguageDao;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.TopicWithQuestions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskManager implements Language, TypeOfTest {

    private Task[] listTask = new Task[setting.getSizeOfTest()];
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase myDb;
    private Context context;
    private Database db;

    public Task[] createList() {
        myDBHelper = new DatabaseHelper(context, DatabaseHelper.DB_NEW);
        myDBHelper.create_db();
        try {
            myDb = myDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db = Database.getInstance(context);
        TestDao testDao = db.testDao();

        TopicWithQuestions topicWithQuestions = testDao.getTopicWithQuestionsById(setting.getType());
        List<Question> questions = getQuestionsByLanguage(topicWithQuestions.getQuestion());

        int[] position = sampleRandomNumbersWithoutRepetition(0, questions.size(), setting.getSizeOfTest());

        for (int i = 0; i < setting.getSizeOfTest(); i++) {
            listTask[i] = createTask(questions.get(position[i]));
        }

        myDb.close();
        return listTask;
    }
/*
    public Task[] createMixedList() {
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
            int tableSize = cursor.getPosition() + 1;
            int[] position = sampleRandomNumbersWithoutRepetition(0, tableSize, 2);
            for (int j = 0; j < 2; j++) {
                cursor.moveToPosition(position[j]);
                listTask[i] = createTask(cursor);
                i++;
            }
            cursor.close();
        }
        myDb.close();
        return listTask;
    }
*/
    private Task createTask(Question question) {

        String text = new String();
        byte[] image;
        String[] answers = new String[4];
        int rightAnswer;
        String textDescription = new String();

        text = question.getQuestion();
        textDescription = question.getDescription();
        answers[0] = question.getAnswer_1();
        answers[1] = question.getAnswer_2();
        answers[2] = question.getAnswer_3();
        answers[3] = question.getAnswer_4();
        rightAnswer = question.getRight_answer();
        image = question.getImage();

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

    private List<Question> getQuestionsByLanguage(List<Question> allQuestions) {
        LanguageDao languageDao = db.languageDao();
        List<Question> resultList = new ArrayList<>();

        for(Question question: allQuestions) {
            System.out.println(question.getQuestion_id());
            if (languageDao.getLanguage(question.getLanguage_id()).equals(change.getLanguage())) {
                resultList.add(question);
            }
        }

        return resultList;
    }
}