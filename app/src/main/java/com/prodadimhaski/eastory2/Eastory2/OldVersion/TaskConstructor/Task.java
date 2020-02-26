package com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor;

import java.sql.Blob;
import java.util.List;

public class Task {

    public static final int AMOUNT_OF_ANSWERS = 4;
    private String taskText;
    private String[] answers;
    private int rightAnswer;
    private byte[] image;
    private String description;

    public String getTaskText() {
        return taskText;
    }
    public int getRightAnswer() {
        return rightAnswer;
    }
    public String[] getAnswers() {
        return answers;
    }
    public byte[] getImage() { return image; }
    public String getDescription() {
        return description;
    }

    Task(String[] answers, int rightAnswer, String text, String description, byte[] image) {
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.taskText = text;
        this.description = description;
        this.image = image;
    }

}
