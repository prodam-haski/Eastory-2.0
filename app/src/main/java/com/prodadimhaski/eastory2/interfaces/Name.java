package com.prodadimhaski.eastory2.interfaces;

public interface Name {

    Naming nameOfStudent = new Naming();
    class Naming {
        String name = " ";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
