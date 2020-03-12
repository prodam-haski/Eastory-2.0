package com.prodadimhaski.eastory2.New.TestConstructor.Interfaces;

import java.util.ArrayList;
import java.util.List;

public interface SelectedList {

    UserSelectidList userList = new UserSelectidList();


    class UserSelectidList{
        List<Integer> selectedList;
        String selectedTable;
        String nameOfUserTable;


        UserSelectidList(){
            selectedList = new ArrayList<Integer>();
        }

        public List<Integer> getSelectedList() {
            return selectedList;
        }

        public void setSelectedList(List<Integer> selectedList) {
            this.selectedList = selectedList;
        }

        public void setSelectedTable(String selectedTable) {
            this.selectedTable = selectedTable;
        }

        public void setNameOfUserTable(String nameOfUserTable) {
            this.nameOfUserTable = nameOfUserTable;
        }

        public String getNameOfUserTable() {
            return nameOfUserTable;
        }

        public String getSelectedTable() {
            return selectedTable;
        }

    }
}
