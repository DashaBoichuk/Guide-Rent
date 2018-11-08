package ua.com.up_site.guiderenttest.places;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Random;

public class PlaceInfo implements Parcelable {

    //Google data
    @Expose(serialize = false)
    private String phone;
    @Expose(serialize = false)
    private String webSite;
    @Expose(serialize = false)
    private String address;
    @Expose(serialize = false)
    private String hoursOfWork;
    @Expose(serialize = false)
    private String comment;

    //Get from server
    @Expose(serialize = false)
    private Double averageMark;
    @Expose(serialize = false)
    private Integer countOfMarks;

    //ServerData
    private String name;
    private String description;
    //private Bitmap image;
    private int category;
    private int google_id;


    //From server
    public PlaceInfo(Double averageMark, String name, String comment, int category, int google_id) {
        this.averageMark = averageMark;
        this.name = name;
        this.comment = comment;
        // this.image = image;
        this.category = category;
        this.google_id = google_id;
    }


    //To server for testing!
    public PlaceInfo() {
        Random r = new Random();
        ArrayList<String> randomNames = new ArrayList<>();
        ArrayList<String> randomComment = new ArrayList<>();

        randomNames.add("Оперный театр");
        randomNames.add("Театр, но не оперный");
        randomNames.add("Оперный, но не театр");

        this.name = randomNames.get(r.nextInt(2));

        randomComment.add("Очень хорошо, ребята");
        randomComment.add("Чётко");
        randomComment.add("Резко");

        this.description = randomComment.get(r.nextInt(2));

        this.category = r.nextInt(5);

        this.google_id = r.nextInt(235792);

    }

    //Для тестирования в дизайне
    public PlaceInfo(String phone, String webSite, String address, String hoursOfWork, String comment, Double averageMark, Integer countOfMarks) {
        this.phone = phone;
        this.webSite = webSite;
        this.address = address;
        this.hoursOfWork = hoursOfWork;
        this.comment = comment;
        this.averageMark = averageMark;
        this.countOfMarks = countOfMarks;
    }


    private PlaceInfo(Parcel in) {
        phone = in.readString();
        webSite = in.readString();
        address = in.readString();
        hoursOfWork = in.readString();
        comment = in.readString();
        if (in.readByte() == 0) {
            averageMark = null;
        } else {
            averageMark = in.readDouble();
        }
        if (in.readByte() == 0) {
            countOfMarks = null;
        } else {
            countOfMarks = in.readInt();
        }
    }

    public static final Creator<PlaceInfo> CREATOR = new Creator<PlaceInfo>() {
        @Override
        public PlaceInfo createFromParcel(Parcel in) {
            return new PlaceInfo(in);
        }

        @Override
        public PlaceInfo[] newArray(int size) {
            return new PlaceInfo[size];
        }
    };

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHoursOfWork() {
        return hoursOfWork;
    }

    public void setHoursOfWork(String hoursOfWork) {
        this.hoursOfWork = hoursOfWork;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public Integer getCountOfMarks() {
        return countOfMarks;
    }

    public void setCountOfMarks(int countOfMarks) {
        this.countOfMarks = countOfMarks;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(phone);
        dest.writeString(webSite);
        dest.writeString(address);
        dest.writeString(hoursOfWork);
        dest.writeString(comment);
        if (averageMark == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(averageMark);
        }
        if (countOfMarks == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(countOfMarks);
        }
    }
}
