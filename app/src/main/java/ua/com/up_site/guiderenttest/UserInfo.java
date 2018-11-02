package ua.com.up_site.guiderenttest;

public final class UserInfo {

    static String name = "";
    static String lastName = "";
    static String email = "";


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
}
