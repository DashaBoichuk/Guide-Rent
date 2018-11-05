package ua.com.up_site.guiderenttest;

import com.facebook.drawee.view.SimpleDraweeView;

public final class UserInfo {

    static String id = "";
    static String name = "";
    static String lastName = "";
    static String email = "";
    static SimpleDraweeView mProfilePhotoView = null;


    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        UserInfo.lastName = lastName;
    }



    static public String getName() {
        return name;
    }

    static public void setName(String _name) {
        name = _name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserInfo.email = email;
    }

    public static SimpleDraweeView getmProfilePhotoView() {
        return mProfilePhotoView;
    }

    public static void setmProfilePhotoView(SimpleDraweeView mProfilePhotoView) {
        UserInfo.mProfilePhotoView = mProfilePhotoView;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        UserInfo.id = id;
    }
}
