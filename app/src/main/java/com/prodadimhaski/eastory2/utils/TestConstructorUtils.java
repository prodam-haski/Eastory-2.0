package com.prodadimhaski.eastory2.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.prodadimhaski.eastory2.Room.Dao.LanguageDao;
import com.prodadimhaski.eastory2.Room.Dao.TestDao;
import com.prodadimhaski.eastory2.Room.Dao.TopicDao;
import com.prodadimhaski.eastory2.Room.Database;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Topic;
import com.prodadimhaski.eastory2.interfaces.Language;
import com.prodadimhaski.eastory2.interfaces.SelectedList;


import java.util.ArrayList;
import java.util.List;

public class TestConstructorUtils implements SelectedList, Language {
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
        return topicDao.getAllTopics();
    }

    public void createUserTest() {
        Topic newTopic = new Topic(topicDao.lastId() + 1, userList.getNameOfUserTable());
        topicDao.insert(newTopic);
    }

    public void deleteUserTest(String test) {
        topicDao.delete(new Topic(topicDao.getTopicId(test), test));
    }

    public List<Question> createFullList(int period){
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
