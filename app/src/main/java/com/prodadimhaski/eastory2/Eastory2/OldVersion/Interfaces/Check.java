package com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces;

import com.prodadimhaski.eastory2.R;

public interface Check {

    Checking control=new Checking();
    class Checking{

        static int SIZE = 10;

        private int rightAnswer;
        private int score = 0;
        private boolean userIsRight=false;
        private boolean[] isAnswered = new boolean[SIZE];

        public void setRightAnswer(int value){rightAnswer=value;}
        public int getRightAnswer(){return rightAnswer;}
        public int getScore(){return score;}
        public boolean getUserIsRight(){return userIsRight;}
        public void setUserIsRight(){userIsRight=false;}
        public boolean getIsAnswered(int number){return isAnswered[number];}

        Checking(){
            for (int i= 0;i<SIZE;i++){
                isAnswered[i]=false;
            }
        }

        public void checkAnswer(int answer, int number) {
            isAnswered[number]=true;
            if (rightAnswer == answer) {
                score++;
                userIsRight=true;
            }
        }
    }
}
