package com.prodadimhaski.eastory2.Room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "languages")
public class Language {
    @PrimaryKey
    private int id;

    private String language;
}
