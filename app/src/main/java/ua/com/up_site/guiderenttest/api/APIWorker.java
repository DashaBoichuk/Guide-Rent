package ua.com.up_site.guiderenttest.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ua.com.up_site.guiderenttest.map.LocationData;
import ua.com.up_site.guiderenttest.map.RouteData;
import ua.com.up_site.guiderenttest.place.PlaceInfo;


public class APIWorker {




    private static String rootURL = "http://edu.lt.ua/android_guide_api/";
    private static String TAG = "Networking";

    public static boolean validateGoogleToken(String token) throws IOException {

        TokenModel tokenModel = new TokenModel(token);

        Log.e(TAG, "validateGoogleToken: INIT");
        //Для сериализации обьекта сontact в формат JSON при запросе
        GsonBuilder gsonRequestBuilder = new GsonBuilder();
        gsonRequestBuilder.setPrettyPrinting();
        final Gson gsonRequest = gsonRequestBuilder.create();

        //Java to JSON
        final String jsonPlace = gsonRequest.toJson(tokenModel);

        Log.w(TAG, jsonPlace);

        URL obj = new URL(rootURL + "check_google_token.php");
        String responseData = "";
        int responseCode;

        //Открываем соединение
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //POST request
        con.setRequestMethod("POST");

        //Для будущих токенов
        //con.setRequestProperty("Authorization", token);
        //con.setRequestProperty("Api", apiKey);

        //Отправление POST request
        con.setDoOutput(true);

        try {
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(con.getOutputStream()));
            out.write(jsonPlace);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Принимаем response
        responseCode = con.getResponseCode();
        Log.e(TAG, "\nSending 'POST' request to URL : " + obj.toString());
        Log.e(TAG, "Response Code : " + responseCode);

        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseData = response.toString();
            Log.w("Networking", responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ValidateResponseModel validateResponseModel = gsonRequest.fromJson(responseData, ValidateResponseModel.class);

        Log.d(TAG, "validateGoogleToken: " + gsonRequest.toJson(validateResponseModel));

        return validateResponseModel.error;
    }

    public static String createPlace(PlaceInfo place) throws IOException {

        Log.e(TAG, "createPlace: INIT createPlace");
        //Для сериализации обьекта сontact в формат JSON при запросе
        GsonBuilder gsonRequestBuilder = new GsonBuilder();
        gsonRequestBuilder.setPrettyPrinting();
        final Gson gsonRequest = gsonRequestBuilder.create();

        //Java to JSON
        final String jsonPlace = gsonRequest.toJson(place);

        Log.w("Networking", jsonPlace);

        URL obj = new URL(rootURL + "createplace.php");
        String responseData = "";
        int responseCode;

        //Открываем соединение
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //POST request
        con.setRequestMethod("POST");

        //Для будущих токенов
        //con.setRequestProperty("Authorization", token);
        //con.setRequestProperty("Api", apiKey);

        //Отправление POST request
        con.setDoOutput(true);

        try {
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(con.getOutputStream()));
            out.write(jsonPlace);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Принимаем response
        responseCode = con.getResponseCode();
        Log.e("Networking", "\nSending 'POST' request to URL : " + obj.toString());
        Log.e("Networking", "Response Code : " + responseCode);

        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseData = response.toString();
            Log.w("Networking", responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;
    }

    public static ArrayList<PlaceInfo> getPlacesInfo(final int sortIndex, final int portion) throws IOException {
        Log.e(TAG, "createPlace: INIT createPlace");

        URL obj = new URL(rootURL + "getplaces.php");
        String responseData = "";
        int responseCode;

        //Открываем соединение
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //POST request
        con.setRequestMethod("POST");

        //Параметры
        con.setRequestProperty("SortIndex", String.valueOf(sortIndex));
        con.setRequestProperty("Portion", String.valueOf(portion));

        con.setDoOutput(true);


        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(con.getInputStream()));
            String inputLine;
            // TODO: Replaced StringBuffer with StringBuilder, test it!
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseData = response.toString();
            Log.w("Networking", responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //TODO: json error and data handling


        return null;
    }

    public static PlaceInfo getSinglePlaceInfo(int google_id) throws IOException {
        return null;
    }

    /**
     * addroute.php is not ready!
     */
    public static void addRoute(RouteData route) throws IOException {
        //TODO: add route class (and id variable to it) to define routes in database
        Log.e(TAG, "addRoute: INIT addroute");
        //Для сериализации обьекта сontact в формат JSON при запросе
        GsonBuilder gsonRequestBuilder = new GsonBuilder();
        gsonRequestBuilder.setPrettyPrinting();
        final Gson gsonRequest = gsonRequestBuilder.create();

        //Java to JSON
        final String jsonRoute = gsonRequest.toJson(route);

        Log.w("Networking", jsonRoute);

        URL obj = new URL(rootURL + "addroute.php");
        String responseData = "";
        int responseCode;

        //Открываем соединение
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //POST request
        con.setRequestMethod("POST");

        //Для будущих токенов
        //con.setRequestProperty("Authorization", token);
        //con.setRequestProperty("Api", apiKey);

        //Отправление POST request
        con.setDoOutput(true);

        try {
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(con.getOutputStream()));
            out.write(jsonRoute);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Принимаем response
        responseCode = con.getResponseCode();
        Log.e("Networking", "\nSending 'POST' request to URL : " + obj.toString());
        Log.e("Networking", "Response Code : " + responseCode);

        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseData = response.toString();
            Log.w("Networking", responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String addLocation(LocationData locationData) throws IOException {
        Log.e(TAG, "addLocation: INIT");
        //Для сериализации обьекта сontact в формат JSON при запросе
        GsonBuilder gsonRequestBuilder = new GsonBuilder();
        gsonRequestBuilder.setPrettyPrinting();
        final Gson gsonRequest = gsonRequestBuilder.create();

        //Java to JSON
        final String jsonPlace = gsonRequest.toJson(locationData);

        Log.w(TAG, jsonPlace);

        URL obj = new URL(rootURL + "createlocation.php");
        String responseData = "";
        int responseCode;

        //Открываем соединение
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //POST request
        con.setRequestMethod("POST");

        //Для будущих токенов
        //con.setRequestProperty("Authorization", token);
        //con.setRequestProperty("Api", apiKey);

        //Отправление POST request
        con.setDoOutput(true);

        try {
            BufferedWriter out = new BufferedWriter
                    (new OutputStreamWriter(con.getOutputStream()));
            out.write(jsonPlace);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Принимаем response
        responseCode = con.getResponseCode();
        Log.e(TAG, "\nSending 'POST' request to URL : " + obj.toString());
        Log.e(TAG, "Response Code : " + responseCode);

        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseData = response.toString();
            Log.w(TAG, responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;

    }

}
