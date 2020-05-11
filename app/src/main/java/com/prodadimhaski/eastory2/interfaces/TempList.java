package com.prodadimhaski.eastory2.interfaces;

import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;

import java.util.ArrayList;
import java.util.List;

public interface TempList {

    BufferList buffer = new BufferList();
    class BufferList{
        List<Question> bufferList;
        List<TopicOTD> testOTDList;

        public BufferList() {
            bufferList = new ArrayList<Question>();
            testOTDList = new ArrayList<TopicOTD>();
        }

        public List<Question> getBufferList() {
            return bufferList;
        }

        public void setBufferList(List<Question> bufferList) {
            this.bufferList = bufferList;
        }

        public List<TopicOTD> getTestOTDList() {
            return testOTDList;
        }

        public void setTestOTDList(List<TopicOTD> testOTDList) {
            this.testOTDList = testOTDList;
        }
    }
}
