package ua.com.up_site.guiderenttest;

import java.util.ArrayList;

public class GuideInfo {
    String name;
    String age;

    public GuideInfo(String name, String age) {
        this.name = name;
        this.age = age;
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
        ArrayList<GuideInfo> guideInfoArrayList = new ArrayList<GuideInfo>();

        for (int i = 1; i <= numGuides; i++) {
            guideInfoArrayList.add(new GuideInfo("Ann", "22"));
        }

        return  guideInfoArrayList;
    }
}
