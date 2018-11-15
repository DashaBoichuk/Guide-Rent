package ua.com.up_site.guiderenttest.top_guides;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.user_model.GuideInfo;

public class GuideProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GuideInfo guideInfoItem;


    CalendarView calendarView;

    public GuideProfileFragment() {
    }

    public static GuideProfileFragment newInstance(String param1, String param2) {
        GuideProfileFragment fragment = new GuideProfileFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((MainActivity) getActivity()).toolbar_title.setText("Guide Profile");

        Bundle bundle = this.getArguments();
        guideInfoItem = bundle.getParcelable("GuideInfoItem");

        View rootView = inflater.inflate(R.layout.fragment_guide_profile, container, false);

        RecyclerView rvRecyclerViewService = rootView.findViewById(R.id.profileServiceRV);
        RecyclerView rvRecyclerViewInterests = rootView.findViewById(R.id.profileInterestsRV);

        GuideProfileTagAdapter interestsAdapter = new GuideProfileTagAdapter(guideInfoItem.getInterests());
        Toast.makeText(getActivity(), guideInfoItem.getInterests().toString(), Toast.LENGTH_SHORT).show();
        GuideProfileTagAdapter serviceAdapter = new GuideProfileTagAdapter(guideInfoItem.getService());

        rvRecyclerViewService.setAdapter(serviceAdapter);
        rvRecyclerViewInterests.setAdapter(interestsAdapter);

        RecyclerView.LayoutManager mLayoutManagerService = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager mLayoutManagerInterests = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);

        rvRecyclerViewInterests.setLayoutManager(mLayoutManagerInterests);
        rvRecyclerViewService.setLayoutManager(mLayoutManagerService);


        calendarView = rootView.findViewById(R.id.GuideProfileCV);
        calendarView.setBackgroundColor(Color.WHITE);
        //final GuideProfileTagAdapter serviceAdapter = new GuideProfileTagAdapter();

        return rootView;
    }

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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
