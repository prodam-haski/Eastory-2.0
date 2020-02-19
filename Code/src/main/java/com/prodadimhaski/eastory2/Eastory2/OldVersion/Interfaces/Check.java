package com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces;

public interface Check {

    Checking control=new Checking();
    class Checking{
        private int rightAnswer;
        private int score = 0;

        public void setRightAnswer(int value){rightAnswer=value;}
        public int getScore(){return score;}

        public boolean checkAnswer(int answer) {
            if (rightAnswer == answer) {
                score++;
                return true;
            }
            else return false;
        }
    }
}
