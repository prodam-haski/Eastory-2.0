package com.prodadimhaski.eastory2.Room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tests", primaryKeys = {"topicId", "questionId"})
public class Test {
    private int topicId;
    @ColumnInfo(index = true)
    private int questionId;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
