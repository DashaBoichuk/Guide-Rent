package ua.com.up_site.guiderenttest.LocationPackage;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceInfo implements Parcelable {

    private String phone;
    private String webSite;
    private String address;
    private String hoursOfWork;
    private String description;
    private Double averageMark;
    private Integer countOfMarks;

    public PlaceInfo(String phone, String webSite, String address, String hoursOfWork, String description, Double averageMark, Integer countOfMarks) {
        this.phone = phone;
        this.webSite = webSite;
        this.address = address;
        this.hoursOfWork = hoursOfWork;
        this.description = description;
        this.averageMark = averageMark;
        this.countOfMarks = countOfMarks;
    }


    protected PlaceInfo(Parcel in) {
        phone = in.readString();
        webSite = in.readString();
        address = in.readString();
        hoursOfWork = in.readString();
        description = in.readString();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        dest.writeString(description);
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
