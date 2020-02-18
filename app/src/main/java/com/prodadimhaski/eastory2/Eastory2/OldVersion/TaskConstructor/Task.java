package com.prodadimhaski.eastory2.Eastory2.OldVersion.TaskConstructor;

import java.util.List;

public class Task  {

    private String taskText;
    private String[] answers;
    private int rightAnswer;
    private byte[] image;
    private String description;
    public void setDescription(String value){description=value;}

    public void setTaskText(String value){taskText=value;}
    public void setRightAnswer(int value){rightAnswer=value;}
    public void setImage(byte[] value){image=value;}
    public void setAnswers(String[] value){
        for (int i = 0; i<4;i++)
        answers[i]=value[i];
    }



    public String getTaskText(){return taskText;}
    public int getRightAnswer(){return rightAnswer;}
    public String[] getAnswers(){return answers;}
    public byte[] getImage(){return image;}
    public String getDescription(){return description;}

}
