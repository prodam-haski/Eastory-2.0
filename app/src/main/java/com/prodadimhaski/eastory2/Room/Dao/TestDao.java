package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.prodadimhaski.eastory2.Room.entities.TopicWithQuestions;

import java.util.List;

@Dao
public interface TestDao {
    @Transaction
    @Query("SELECT * FROM tests WHERE topicId = :id")
    List<TopicWithQuestions> getTopicWithQuestions(int id);
}
