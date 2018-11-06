package ua.com.up_site.guiderenttest.map;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.api.APIWorker;

public class LoginDialogFragment extends DialogFragment {

    EditText mEditText;

    public LoginDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static LoginDialogFragment newInstance(String title, LatLng markerPoint) {
        LoginDialogFragment frag = new LoginDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putDouble("latitude", markerPoint.latitude);
        args.putDouble("longitude", markerPoint.longitude);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_create_location_confirm_dialog, container);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = view.findViewById(R.id.enter_name_dialog);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }




    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.map_create_location_confirm_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Dialog dialogView = Dialog.class.cast(dialog);
                                EditText editText = dialogView.findViewById(R.id.enter_name_dialog);
                                LocationData locationData = new LocationData(
                                        new LatLng(getArguments().getDouble("latitude"),
                                                getArguments().getDouble("longitude")),
                                                //TODO: make this possible
                                                editText.getText().toString()
                                                );

                                try {
                                    //String response =
                                    APIWorker.addLocation(locationData);
                                    //Log.d("Location", "message: " + response);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
