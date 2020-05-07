package com.prodadimhaski.eastory2.utils;

import com.prodadimhaski.eastory2.interfaces.TypeOfTest;

public class Checking implements TypeOfTest {

    private int rightAnswer;
    private int score = 0;
    private boolean alwaysZero= false;
    private boolean userIsRight=false;
    private boolean[] isAnswered = new boolean[setting.getSizeOfTest()];

    public void setRightAnswer(int value){rightAnswer=value;}
    public int getRightAnswer(){return rightAnswer;}
    public int getScore(){return score;}
    public boolean getUserIsRight(){return userIsRight;}
    public void setUserIsRight(){userIsRight=false;}
    public boolean getIsAnswered(int number){return isAnswered[number];}

    public Checking(){
        for (int i= 0;i<setting.getSizeOfTest();i++){
            isAnswered[i]=false;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAlwaysZero() {
        alwaysZero = true;
        score = 0;
    }

    public void checkAnswer(int answer, int number) {
        isAnswered[number]=true;
        if (rightAnswer == answer&&!alwaysZero) {
            score++;
            userIsRight=true;
        }
    }
}

