package com.prodadimhaski.eastory2.interfaces;

import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.ArrayList;
import java.util.List;

public interface TempList {

    BufferList buffer = new BufferList();
    class BufferList{
        List<Question> bufferList;

        public BufferList() {
            bufferList = new ArrayList<Question>();
        }

        public List<Question> getBufferList() {
            return bufferList;
        }

        public void setBufferList(List<Question> bufferList) {
            this.bufferList = bufferList;
        }
    }
}
