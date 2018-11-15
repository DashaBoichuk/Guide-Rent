package ua.com.up_site.guiderenttest.places;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;

import static android.app.Activity.RESULT_OK;


public class PlaceEditFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int PLACE_PICKER_REQUEST = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Unbinder unbinder;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @BindView(R.id.fabMapPlaceEdit)
    FloatingActionButton fabMapPlaceEdit;

    @BindView(R.id.placeEditName)
    EditText placeEditName;

    @BindView(R.id.placeEditImage)
    ImageView placeEditImage;

    private GeoDataClient mGeoDataClient;


    List<String> categories = new ArrayList<>();

    String googlePlaceID;

    ImageView imageView;
    com.seatgeek.placesautocomplete.PlacesAutocompleteTextView addressACTV;

    private OnFragmentInteractionListener mListener;

    String address = null;

    public PlaceEditFragment() {
        // Required empty public constructor
    }

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
        final View rootView = inflater.inflate(R.layout.fragment_place_edit, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        ((MainActivity) getActivity()).toolbar_title.setText("Place Edit");

        initializeCategories();

        mGeoDataClient = Places.getGeoDataClient(getContext());

        addressACTV = rootView.findViewById(R.id.placeAutoComplete);
        imageView = rootView.findViewById(R.id.placeEditImage);

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
                                        }, 500);


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

        Spinner spinner = rootView.findViewById(R.id.placeCategorySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        return rootView;
    }

    @OnClick(R.id.fabMapPlaceEdit)
    void fabMapPlaceEditOnClick(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(), data);
                addressACTV.setText(place.getAddress());
                placeEditName.setText(place.getName());
                googlePlaceID = place.getId();
                mGeoDataClient.getPlacePhotos(googlePlaceID).addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
                        PlacePhotoMetadataResponse response = task.getResult();
                        PlacePhotoMetadataBuffer buffer = response.getPhotoMetadata();
                        PlacePhotoMetadata photoMetadata = null;
                        try {
                            photoMetadata = buffer.get(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (photoMetadata != null) {
                            CharSequence attribution = photoMetadata.getAttributions();
                            Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photoMetadata);
                            photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
                                @Override
                                public void onComplete(@NonNull Task<PlacePhotoResponse> task) {
                                    PlacePhotoResponse photo = task.getResult();
                                    Bitmap bitmap = photo.getBitmap();
                                    placeEditImage.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                });

                String toastMsg = String.format("PlaceId: %s", googlePlaceID);
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
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
        Double D0 = Double.valueOf(stringD[0]);
        Double D1 = Double.valueOf(stringD[1]);
        Double FloatD = D0 / D1;

        String[] stringM = DMS[1].split("/", 2);
        Double M0 = Double.valueOf(stringM[0]);
        Double M1 = Double.valueOf(stringM[1]);
        Double FloatM = M0 / M1;

        String[] stringS = DMS[2].split("/", 2);
        Double S0 = Double.valueOf(stringS[0]);
        Double S1 = Double.valueOf(stringS[1]);
        Double FloatS = S0 / S1;

        result = FloatD + (FloatM / 60) + (FloatS / 3600);

        return result;
    }
}
