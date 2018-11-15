package ua.com.up_site.guiderenttest.map;

import com.google.android.gms.maps.model.LatLng;

public class LocationData {
    private double lat;
    private double lng;
    private String name;

    public LocationData(LatLng coordinates, String name) {
        this.lat = coordinates.latitude;
        this.lng = coordinates.longitude;
        this.name = name;
    }
}
