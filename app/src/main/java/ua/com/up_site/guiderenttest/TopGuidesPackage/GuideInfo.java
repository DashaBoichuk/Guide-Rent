package ua.com.up_site.guiderenttest.TopGuidesPackage;

import java.util.ArrayList;

//Placeholder для класса гида. Используйте его для профиля
public class GuideInfo {
    private String name;
    private String age;
    private String[] interests;
    private String[] service;

    private GuideInfo(String name, String age) {
        this.name = name;
        this.age = age;
        //Placeholder заполнение интересов и сервисов
        for (int i = 0; i < 3; i++) {
            interests[i] = "blank";
        }

        for (int i = 0; i < 6; i++) {
            service[i] = "blank";
        }
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

    public static ArrayList<GuideInfo> createGuideList(int numGuides) {
        ArrayList<GuideInfo> guideInfoArrayList = new ArrayList<>();

        for (int i = 1; i <= numGuides; i++) {
            guideInfoArrayList.add(new GuideInfo("Ann", "22"));
        }

        return  guideInfoArrayList;
    }
}
