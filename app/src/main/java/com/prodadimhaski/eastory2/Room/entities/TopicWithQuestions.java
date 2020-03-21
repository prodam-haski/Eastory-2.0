package com.prodadimhaski.eastory2.Room.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class TopicWithQuestions {
    @Embedded private Topic topic;

    @Relation(
            parentColumn = "topicId",
            entityColumn = "questionId",
            associateBy = @Junction(Test.class)
    )
    private List<Question> question;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
}
