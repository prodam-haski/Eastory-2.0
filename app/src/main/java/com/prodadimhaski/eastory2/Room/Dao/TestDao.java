package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.List;

@Dao
public interface TestDao {

    @Query("SELECT * FROM tests " +
            "JOIN questions ON tests.question_id = questions.question_id " +
            "WHERE topic_id = :id")
    List<Question> getTopicWithQuestionsById(int id);
}
