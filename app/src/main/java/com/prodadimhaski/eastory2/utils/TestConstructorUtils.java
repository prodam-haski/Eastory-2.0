package com.prodadimhaski.eastory2.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.prodadimhaski.eastory2.Room.Dao.LanguageDao;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.Dao.TopicDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Test;
import com.prodadimhaski.eastory2.Room.entities.Topic;
import com.prodadimhaski.eastory2.interfaces.Language;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class TestConstructorUtils implements Language {
    private Database db;
    private TopicDao topicDao;
    private TestDao testDao;
    private LanguageDao languageDao;

    public TestConstructorUtils(Context context) {
        db = Database.getInstance(context);
        topicDao = db.topicDao();
        testDao = db.testDao();
        languageDao = db.languageDao();
    }

    public List<String> getListOfTable() {
        return topicDao.getAllTopicsWithoutDefault();
    }

    public void createUserTest(List<Integer> questions, String testName) {
        int topicId = topicDao.lastId() + 1;
        Topic newTopic = new Topic(topicId, testName);
        topicDao.insert(newTopic);

        List<Test> newTest = new LinkedList<>();

        for (int i = 0; i < questions.size(); i++) {
            newTest.add(new Test(topicId, questions.get(i)));
        }

        testDao.insertAll(newTest);

        List<Question> questionList = testDao.getTopicWithQuestionsById(topicId);

        for (Question question : questionList) {
            System.out.println(question.getQuestion());
        }
    }

    public void deleteUserTest(String test) {
        topicDao.delete(new Topic(topicDao.getTopicId(test), test));
    }

    public List<Question> createFullList(int period) {
        return filterByLanguage(testDao.getTopicWithQuestionsById(period));
    }

    @NonNull
    private List<Question> filterByLanguage(@NonNull List<Question> allQuestions) {
        List<Question> resultList = new ArrayList<>();

        for (Question question : allQuestions) {
            System.out.println(question.getQuestion_id());
            if (languageDao.getLanguage(question.getLanguage_id()).equals(change.getLanguage())) {
                resultList.add(question);
            }
        }

        return resultList;
    }
}
