package ua.com.up_site.guiderenttest.user_model;

import java.util.ArrayList;

public abstract class UserInfo {
    protected String name;
    String age;
    String login;
    String email;
    boolean email_verified;
    String picture;
    String locale;
    float rating;
    boolean sex;
    boolean isBusy;
    ArrayList<String> interests = new ArrayList<>();


    UserInfo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
