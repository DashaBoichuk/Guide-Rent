package ua.com.up_site.guiderenttest.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class RouteData {
    private List<LatLng> pointsList;
    private LatLng firstLocation;
    private LatLng lastLocation;
   // private String name;

    public RouteData(List<LatLng> pointsList) {
        this.pointsList = pointsList;

    }
}
