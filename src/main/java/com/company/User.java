package com.company;

import java.util.ArrayList;

public class User {
    public String firstName;
    public String aboutyou;
    public String interests;
    public String hobby;
    public String gender;
    public String age;
    public String username;
    public String current_querty;

    public User(String firstName, String aboutyou, String interests, String hobby, String gender, String age, String current_querty) {
        this.firstName = firstName;
        this.aboutyou = aboutyou;
        this.interests = interests;
        this.hobby = hobby;
        this.gender = gender;
        this.age = age;
        this.current_querty = current_querty;
    }
    public User( String aboutyou, String interests, String hobby, String gender, String age,String username) {
        this.aboutyou = aboutyou;
        this.interests = interests;
        this.hobby = hobby;
        this.gender = gender;
        this.age = age;
        this.username =username;
    }

    public String toString() {
        return aboutyou + "\n" +
                interests + "\n" +
                hobby + "\n" +
                gender + "\n" +
                age ;
    }
}
