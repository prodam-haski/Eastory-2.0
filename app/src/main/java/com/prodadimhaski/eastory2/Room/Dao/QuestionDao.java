package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Query("SELECT * FROM questions WHERE question_id = :id")
    Question getQuestion(int id);
}
