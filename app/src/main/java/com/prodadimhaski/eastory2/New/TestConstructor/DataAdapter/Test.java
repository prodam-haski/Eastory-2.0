package com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter;

public class Test {
    private String testName;
    private boolean isSelected = false;

    public Test(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
