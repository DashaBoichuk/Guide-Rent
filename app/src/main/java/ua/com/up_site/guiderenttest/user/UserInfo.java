package ua.com.up_site.guiderenttest.user;

public abstract class UserInfo {
    protected String name;
    protected String age;
    private String login;


    protected UserInfo(String name, String age) {
        this.name = name;
        this.age = age;
    }

    protected UserInfo() {
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
