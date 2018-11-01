package ua.com.up_site.guiderenttest.models;

public class CommonData {

    private int imgMain;
    private String title;
    private String title2;
    private int imgStar;
    private String valueStar;
    private int imgClock;
    private String valueClock;
    private String quantityPhoto;
    private int imgPeople;
    private String quantityPeople;
    private String visitors;


    public CommonData(int imgMain, String title, String title2, int imgStar, String valueStar, int imgClock, String valueClock, String quantityPhoto, int imgPeople, String quantityPeople, String visitors) {

        this.imgMain = imgMain;
        this.title = title;
        this.title2 = title2;
        this.imgStar = imgStar;
        this.valueStar = valueStar;
        this.imgClock = imgClock;
        this.valueClock = valueClock;
        this.quantityPhoto = quantityPhoto;
        this.imgPeople = imgPeople;
        this.quantityPeople = quantityPeople;
        this.visitors = visitors;
    }

    public CommonData(int imgMain, String title, int imgStar, String valueStar, int imgClock, String valueClock, String quantityPhoto, int imgPeople, String quantityPeople, String visitors) {

        this.imgMain = imgMain;
        this.title = title;
        this.imgStar = imgStar;
        this.valueStar = valueStar;
        this.imgClock = imgClock;
        this.valueClock = valueClock;
        this.quantityPhoto = quantityPhoto;
        this.imgPeople = imgPeople;
        this.quantityPeople = quantityPeople;
        this.visitors = visitors;

    }


    public CommonData(int imgMain, String title, String title2, int imgStar, String valueStar, String valueClock, String quantityPhoto, int imgPeople, String quantityPeople, String visitors) {
        this.imgMain = imgMain;
        this.title = title;
        this.title2 = title2;
        this.imgStar = imgStar;
        this.valueStar = valueStar;
        this.valueClock = valueClock;
        this.quantityPhoto = quantityPhoto;
        this.imgPeople = imgPeople;
        this.quantityPeople = quantityPeople;
        this.visitors = visitors;
    }

    public int getImgMain() {
        return imgMain;
    }

    public void setImgMain(int imgMain) {
        this.imgMain = imgMain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getImgStar() {
        return imgStar;
    }

    public void setImgStar(int imgStar) {
        this.imgStar = imgStar;
    }

    public String getValueStar() {
        return valueStar;
    }

    public void setValueStar(String valueStar) {
        this.valueStar = valueStar;
    }

    public int getImgClock() {
        return imgClock;
    }

    public void setImgClock(int imgClock) {
        this.imgClock = imgClock;
    }

    public String getValueClock() {
        return valueClock;
    }

    public void setValueClock(String valueClock) {
        this.valueClock = valueClock;
    }

    public String getQuantityPhoto() {
        return quantityPhoto;
    }

    public void setQuantityPhoto(String quantityPhoto) {
        this.quantityPhoto = quantityPhoto;
    }

    public int getImgPeople() {
        return imgPeople;
    }

    public void setImgPeople(int imgPeople) {
        this.imgPeople = imgPeople;
    }

    public String getQuantityPeople() {
        return quantityPeople;
    }

    public void setQuantityPeople(String quantityPeople) {
        this.quantityPeople = quantityPeople;
    }

    public String getVisitors() {
        return visitors;
    }

    public void setVisitors(String visitors) {
        this.visitors = visitors;
    }
}




