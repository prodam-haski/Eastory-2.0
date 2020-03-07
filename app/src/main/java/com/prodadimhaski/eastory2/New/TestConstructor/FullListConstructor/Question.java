package com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor;

public class Question {

    private String question;
    private int questionId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Question(String question, int questionId) {
        this.question = question;
        this.questionId = questionId;
    }
}