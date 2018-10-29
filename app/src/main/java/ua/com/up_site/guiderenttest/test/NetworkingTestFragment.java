package ua.com.up_site.guiderenttest.test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.api.APIWorker;
import ua.com.up_site.guiderenttest.place.PlaceInfo;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NetworkingTestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NetworkingTestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworkingTestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button generate;
    Button send;
    TextView lastResponse;
    PlaceInfo placeInfo;
    String response;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NetworkingTestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NetworkingTestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NetworkingTestFragment newInstance(String param1, String param2) {
        NetworkingTestFragment fragment = new NetworkingTestFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_networking_test, container, false);

        generate = rootView.findViewById(R.id.testButtonGenerate);
        send = rootView.findViewById(R.id.testButtonSend);
        lastResponse = rootView.findViewById(R.id.lastResponseTV);
        placeInfo = new PlaceInfo();

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeInfo = new PlaceInfo();
                GsonBuilder gsonRequestBuilder = new GsonBuilder();
                gsonRequestBuilder.setPrettyPrinting();
                final Gson gsonRequest = gsonRequestBuilder.create();

                //Java to JSON
                final String jsonPlace = gsonRequest.toJson(placeInfo);
                Toast.makeText(getActivity(), jsonPlace, Toast.LENGTH_SHORT).show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            response = APIWorker.createPlace(placeInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                lastResponse.setText(response);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
