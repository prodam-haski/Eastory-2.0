package com.prodadimhaski.eastory2.Room.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.prodadimhaski.eastory2.Room.entities.Language;
import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.List;

@Dao
public interface LanguageDao {

    @Query("SELECT language FROM languages WHERE language_id = :id")
    String getLanguage(int id);

    @Query("SELECT language_id FROM languages WHERE language = :language")
    int getLanguageId(String language);

    @Query("SELECT COUNT(*) FROM languages")
    int amountOfLanguages();
}
