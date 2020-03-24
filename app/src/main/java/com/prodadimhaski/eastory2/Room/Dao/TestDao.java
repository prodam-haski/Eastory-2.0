package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.prodadimhaski.eastory2.Room.entities.TopicWithQuestions;

@Dao
public interface TestDao {

    @Query("SELECT * FROM tests WHERE topic_id = :id")
    TopicWithQuestions getTopicWithQuestionsById(int id);
}
