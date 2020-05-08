package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.prodadimhaski.eastory2.Room.entities.Test;
import com.prodadimhaski.eastory2.Room.entities.Topic;

import java.util.List;

@Dao
public interface TopicDao {

    @Query("SELECT topic FROM topics")
    List<String> getAllTopics();

    @Query("SELECT topic FROM topics WHERE topic_id IN ('1', '2', '3', '4', '5', '6')")
    List<String> getDefaultTopics();

    @Query("SELECT topic FROM topics WHERE topic_id > '6'")
    List<String> getAllTopicsWithoutDefault();

    @Query("SELECT topic_id FROM topics ORDER BY topic_id DESC LIMIT 1")
    int lastId();

    @Query("SELECT topic_id FROM topics WHERE topic = :name")
    int getTopicId(String name);

    @Query("SELECT * FROM topics WHERE topic = :name")
    Topic ifExists(String name);

    @Query("SELECT topic FROM topics WHERE topic_id = :id")
    String getTopicNameById(int id);

    @Insert
    void insert(Topic topic);

    @Delete
    void delete(Topic topic);
}
