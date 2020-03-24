package com.prodadimhaski.eastory2.Room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "languages")
public class Language {
    @PrimaryKey
    private int language_id;

    private String language;

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
