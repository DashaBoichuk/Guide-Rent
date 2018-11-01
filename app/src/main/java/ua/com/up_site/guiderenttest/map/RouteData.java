package ua.com.up_site.guiderenttest.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class RouteData {
    private List<LatLng> pointsList;
    private int length;
   // private String name;

    public RouteData(List<LatLng> pointsList) {
        this.pointsList = pointsList;
        this.length = pointsList.size();

    }
}
