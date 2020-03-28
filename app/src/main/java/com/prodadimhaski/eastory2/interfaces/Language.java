package com.prodadimhaski.eastory2.interfaces;

public interface Language {
    Changing change = new Changing();

    class Changing {
        String language = "ru";
        String supportLanguage = "be";

        public String getLanguage(){return language;}

        public void setLanguage(String value){language=value;}

        public void swipeLanguage(){
            if (language.equals("ru"))setLanguage("by");
            else setLanguage("ru");
        }

        public String changeLanguage(){
            if (supportLanguage.equals("be")){
                supportLanguage = "ru";
                return "be";
            }
            else {
                supportLanguage = "be";
                return "ru";
            }
        }
    }
}