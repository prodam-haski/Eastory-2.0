package com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces;

import android.graphics.drawable.Drawable;

import com.prodadimhaski.eastory2.R;



public interface TypeOfTest {

    static int SIZE = 10;

    TestSetting setting = new TestSetting();

    class TestSetting{
        /*type of test   0- common 1-antiquity 2-medival 3-newtime1 4 -newtime2 5-soviets 6-newest*/
        int type;
        String period;

        public int getType(){return type;}
        public void setType(int value){type=value;}
        public String getPeriod(){return  period;}

        public void setPeriod(){
            switch (type){
                //  case 0: listTask.add(createTask());break;
                case 1: period = "TaskAntiquity";break;
                case 2: period = "TaskMedival";break;
                case 3: period = "TaskNew1";break;
                case 4: period = "TaskNew2";break;
                case 5: period = "TaskSoviets";break;
                case 6: period = "TaskNewest";break;
            }
        }


    }
}
