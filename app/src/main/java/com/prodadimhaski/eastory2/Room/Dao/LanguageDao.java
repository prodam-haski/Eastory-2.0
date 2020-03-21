package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.List;

@Dao
public interface LanguageDao {

    @Query("SELECT * FROM languages")
    List<Question> getAll();

    @Query("SELECT * FROM languages WHERE id = :id")
    Question getLanguage(int id);
}
