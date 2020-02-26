package com.prodadimhaski.eastory2.Eastory2.OldVersion.TestActivities;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces.TypeOfTest;

class Checking implements TypeOfTest {

    private int rightAnswer;
    private int score = 0;
    private boolean userIsRight=false;
    private boolean[] isAnswered = new boolean[setting.getSizeOfTest()];

    void setRightAnswer(int value){rightAnswer=value;}
    int getRightAnswer(){return rightAnswer;}
    int getScore(){return score;}
    boolean getUserIsRight(){return userIsRight;}
    void setUserIsRight(){userIsRight=false;}
    boolean getIsAnswered(int number){return isAnswered[number];}

    Checking(){
        for (int i= 0;i<setting.getSizeOfTest();i++){
            isAnswered[i]=false;
        }
    }

    void checkAnswer(int answer, int number) {
        isAnswered[number]=true;
        if (rightAnswer == answer) {
            score++;
            userIsRight=true;
        }
    }
}

