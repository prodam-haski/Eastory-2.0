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
import java.util.LinkedList;
import java.util.List;

public class TestConstructorUtils implements Language {
    private TopicDao topicDao;
    private TestDao testDao;
    private LanguageDao languageDao;

    public TestConstructorUtils(Context context) {
        Database db = Database.getInstance(context);
        topicDao = db.topicDao();
        testDao = db.testDao();
        languageDao = db.languageDao();
    }

    public List<String> getListOfTable() {
        return topicDao.getAllTopicsWithoutDefault();
    }

    public void createUserTest(List<Integer> questions, String testName) {
        int topicId = topicDao.lastId() + 1;
        topicDao.insert(new Topic(topicId, testName));

        for (int i = 0; i < questions.size(); i++) {
            testDao.insert(new Test(topicId, questions.get(i)));
            System.out.println(questions.get(i));
        }
    }

    public void deleteUserTest(String test) {
        int topicId = topicDao.getTopicId(test);
        topicDao.delete(new Topic(topicId, test));

        List<Test> removableTest = testDao.getTopicWithQuestionsId(topicId);
        for (int i = 0; i < removableTest.size(); i++) {
            testDao.deleteByTopic(new Test(topicId, removableTest.get(i).getQuestion_id()));
            System.out.println(removableTest.get(i));
        }
    }

    public List<Question> createFullList(int period) {
        return filterByLanguage(testDao.getTopicWithQuestionsById(period));
    }

    @NonNull
    private List<Question> filterByLanguage(@NonNull List<Question> allQuestions) {
        List<Question> resultList = new ArrayList<>();

        for (Question question : allQuestions) {
            if (languageDao.getLanguage(question.getLanguage_id()).equals(change.getLanguage())) {
                resultList.add(question);
            }
        }

        return resultList;
    }
}
