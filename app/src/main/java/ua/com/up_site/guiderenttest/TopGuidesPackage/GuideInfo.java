package ua.com.up_site.guiderenttest.TopGuidesPackage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Random;

//Placeholder для класса гида. Используйте его для профиля
public class GuideInfo implements Parcelable {
    private String name;
    private String age;
    private ArrayList<String> interests = new ArrayList<>();
    private ArrayList<String> service = new ArrayList<>();
    private ArrayList<String> randomInterests = new ArrayList<>();

    private void setRandomInterests(){
        randomInterests.add("cats");
        randomInterests.add("dogs");
        randomInterests.add("cafe");
        randomInterests.add("opera");
        randomInterests.add("whatever");
    }


    private GuideInfo(String name, String age) {
        setRandomInterests();
        Random r = new Random();
        this.name = name;
        this.age = age;
        //Placeholder заполнение интересов и сервисов
        for (int i = 0; i < 9; i++) {
            interests.add(i, randomInterests.get(r.nextInt(5)));
        }

        for (int i = 0; i < 4; i++) {
            service.add(i, "blank");
        }
    }

    private GuideInfo(Parcel in) {
        name = in.readString();
        age = in.readString();
        interests = in.createStringArrayList();
        service = in.createStringArrayList();
    }

    public static final Creator<GuideInfo> CREATOR = new Creator<GuideInfo>() {
        @Override
        public GuideInfo createFromParcel(Parcel in) {
            return new GuideInfo(in);
        }

        @Override
        public GuideInfo[] newArray(int size) {
            return new GuideInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    ArrayList<String> getInterests() {
        return interests;
    }

    ArrayList<String> getService() {
        return service;
    }

    public static ArrayList<GuideInfo> createGuideList(int numGuides) {
        ArrayList<GuideInfo> guideInfoArrayList = new ArrayList<>();

        for (int i = 1; i <= numGuides; i++) {
            guideInfoArrayList.add(new GuideInfo("Ann", "22"));
        }

        return guideInfoArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeStringList(interests);
        dest.writeStringList(service);
    }
}
