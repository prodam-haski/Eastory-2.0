package com.prodadimhaski.eastory2.New.TestConstructor;

public class Questions {

    private String[] questions;
    private int[] questionsId;

    public String[] getQuestion() {
        return questions;
    }

    public int[] getQuestionId() {
        return questionsId;
    }

    public void setQuestions(int position,String value){
        questions[position]=value;
    }
    public void setQuestionsId(int position,int value){
        questionsId[position]=value;
    }

    Questions(int size) {
        questions = new String[size];
        questionsId = new int[size];
    }
}
