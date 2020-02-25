package com.prodadimhaski.eastory2.Eastory2.OldVersion.Interfaces;

import com.prodadimhaski.eastory2.R;



public interface TypeOfTest {

    static String[] TYPEOFTTEST = {"TaskAntiquity", "TaskMedival", "TaskNew1","TaskNew2","TaskSoviets","TaskNewest"};

    TestSetting setting = new TestSetting();
    class TestSetting{
        /*type of test   0- common 1-antiquity 2-medival 3-newtime1 4 -newtime2 5-soviets 6-newest*/
        int type;
        String period;
        int sizeOfTest;

        public int getType(){return type;}
        public void setType(int value){type=value;}
        public String getPeriod(){return  period;}
        public int getSizeOfTest() {return sizeOfTest;}

        public void setPeriod(){
            switch (type){
                case 0: period = "common";sizeOfTest = 12; break;
                case 1: period = "TaskAntiquity";sizeOfTest = 10;break;
                case 2: period = "TaskMedival";sizeOfTest = 10;break;
                case 3: period = "TaskNew1";sizeOfTest = 10;break;
                case 4: period = "TaskNew2";sizeOfTest = 10;break;
                case 5: period = "TaskSoviets";sizeOfTest = 10;break;
                case 6: period = "TaskNewest";sizeOfTest = 10;break;
            }
        }
    }
}
