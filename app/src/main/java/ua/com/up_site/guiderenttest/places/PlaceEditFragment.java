package ua.com.up_site.guiderenttest.places;

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


public class PlaceEditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> categories = new ArrayList<>();

    ImageView imageView;
    com.seatgeek.placesautocomplete.PlacesAutocompleteTextView addressACTV;

    private OnFragmentInteractionListener mListener;

    String address = null;

    public PlaceEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlaceEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceEditFragment newInstance(String param1, String param2) {
        PlaceEditFragment fragment = new PlaceEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    //В будущем в этом классе нужно будет определять категории (из БД?)
    private void initializeCategories() {
        if (categories.isEmpty()) {
            categories.add("Cafe");
            categories.add("Restaurant");
            categories.add("Monument");
            categories.add("Beach");
            categories.add("Park");
            categories.add("Other");
        }
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_place_edit, container, false);


        ((MainActivity) getActivity()).toolbar_title.setText("Place Edit");

        initializeCategories();

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

        Spinner spinner = rootview.findViewById(R.id.placeCategorySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        return rootview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
