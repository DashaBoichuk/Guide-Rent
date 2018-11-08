package ua.com.up_site.guiderenttest.locations;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;


public class LocationEditFragment extends Fragment {

    ImageView imageView;
    com.seatgeek.placesautocomplete.PlacesAutocompleteTextView addressACTV;

    String address = null;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootview = inflater.inflate(R.layout.location_edit, container, false);

        ((MainActivity) getActivity()).toolbar_title.setText("Location Edit");

        addressACTV = rootview.findViewById(R.id.placeAutoComplete);
        imageView = rootview.findViewById(R.id.guideProfileImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                imageView.setImageBitmap(r.getBitmap());
                                String imagefile = r.getPath();
                                try {
                                    //TODO: violation of separation of concerns principle
                                    Geocoder geocoder;
                                    geocoder = new Geocoder(getActivity(), Locale.getDefault());
                                    ExifInterface exifInterface = new ExifInterface(imagefile);
                                    boolean valid = false;
                                    Double Latitude = null,
                                            Longitude = null;

                                    String attrLATITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
                                    String attrLATITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
                                    String attrLONGITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                                    String attrLONGITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

                                    if ((attrLATITUDE != null)
                                            && (attrLATITUDE_REF != null)
                                            && (attrLONGITUDE != null)
                                            && (attrLONGITUDE_REF != null)) {
                                        valid = true;

                                        if (attrLATITUDE_REF.equals("N")) {
                                            Latitude = convertToDegree(attrLATITUDE);
                                        } else {
                                            Latitude = 0 - convertToDegree(attrLATITUDE);
                                        }

                                        if (attrLONGITUDE_REF.equals("E")) {
                                            Longitude = convertToDegree(attrLONGITUDE);
                                        } else {
                                            Longitude = 0 - convertToDegree(attrLONGITUDE);
                                        }

                                    }

                                    try {
                                        List<Address> addresses = geocoder.getFromLocation(Latitude, Longitude, 1);
                                        address = addresses.get(0).getAddressLine(0);

                                        addressACTV.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                addressACTV.showDropDown();
                                                addressACTV.setText(address);
                                                addressACTV.setSelection(addressACTV.getText().length());
                                            }
                                        },500);


                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setOnPickCancel(new IPickCancel() {
                            @Override
                            public void onCancelClick() {
                                //TODO: do what you have to if user clicked cancel
                            }
                        }).show(getFragmentManager());
            }
        });


        return rootview;
    }

    private Double convertToDegree(String stringDMS) {
        Double result = null;
        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split("/", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0 / D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0 / M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0 / S1;

        result = new Double(FloatD + (FloatM / 60) + (FloatS / 3600));

        return result;
    }
}
