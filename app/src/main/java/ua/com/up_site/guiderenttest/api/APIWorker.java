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

import ua.com.up_site.guiderenttest.place.PlaceInfo;

import static android.content.ContentValues.TAG;

public class APIWorker {

    static String rootURL = "http://www.youinvest.com.ua/android_guide_api/";

    public static String createPlace(PlaceInfo place) throws IOException {

        Log.e(TAG, "createPlace: INIT");
        //Для сериализации обьекта сontact в формат JSON при запросе
        GsonBuilder gsonRequestBuilder = new GsonBuilder();
        gsonRequestBuilder.setPrettyPrinting();
        final Gson gsonRequest = gsonRequestBuilder.create();

        //Java to JSON
        final String jsonPlace = gsonRequest.toJson(place);


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
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Принимаем response
        responseCode = con.getResponseCode();
        Log.e("Networking","\nSending 'POST' request to URL : " + obj.toString() );
        Log.e("Networking","Response Code : " + responseCode);

        try {
            BufferedReader in = new BufferedReader
                    (new InputStreamReader(con.getInputStream()));
            String inputLine;
            // TODO: Replaced StringBuffer with StringBuilder, test it!
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            responseData = response.toString();
            Log.w("Networking", responseData);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        int responseCodeFirstNumber = responseCode / 100;

       return responseData;
    }

    public static PlaceInfo getPlaceInfo() {

        return null;
    }

}
