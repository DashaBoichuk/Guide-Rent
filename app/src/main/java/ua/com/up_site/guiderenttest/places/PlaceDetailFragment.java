package ua.com.up_site.guiderenttest.place;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlaceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ViewFlipper simpleViewFlipper;
    // private ViewFlipper TruitonFlipper;
    RatingBar ratingBar;
    RatingBar oneStar;
    // private float initialX;
    int[] images = {R.drawable.first, R.drawable.second, R.drawable.third};     // array of images


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceFragment newInstance(String param1, String param2) {
        PlaceFragment fragment = new PlaceFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_place, container, false);

        ((MainActivity) getActivity()).toolbar_title.setText("Place");

      /*  view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float finalX = event.getX();
                        if (initialX > finalX) {
                            if (TruitonFlipper.getDisplayedChild() == 1)
                                break;

				*//*TruitonFlipper.setInAnimation(this, R.anim.in_right);
				TruitonFlipper.setOutAnimation(this, R.anim.out_left);*//*

                            TruitonFlipper.showNext();
                        } else {
                            if (TruitonFlipper.getDisplayedChild() == 0)
                                break;

				*//*TruitonFlipper.setInAnimation(this, R.anim.in_left);
				TruitonFlipper.setOutAnimation(this, R.anim.out_right);*//*

                            TruitonFlipper.showPrevious();
                        }
                        break;
                }
                return false;
            }
        });*/

     /*   TruitonFlipper = view.findViewById(R.id.flipper);
        TruitonFlipper.setInAnimation(getActivity(), android.R.anim.fade_in);
        TruitonFlipper.setOutAnimation(getActivity(), android.R.anim.fade_out);*/
        final ua.com.up_site.guiderenttest.place.PlaceInfo placeInfo = new ua.com.up_site.guiderenttest.place.PlaceInfo("+38 066 121 12 12", "", "Г.ОДЕССА. УЛ. Б.АРНАУТСКАЯ 198", "10:00-23:00", "ТИП КУХНИ: ЕВРОПЕЙСКАЯ", 4.5, 234);

        TextView phone_tv = view.findViewById(R.id.phonetv3);
        phone_tv.setText("ТЕЛ: " + placeInfo.getPhone());
        TextView website_tv = view.findViewById(R.id.websitetv3);
        website_tv.setText("ВЕБ-САЙТ" + placeInfo.getWebSite());
        TextView address_tv = view.findViewById(R.id.addresstv3);
        address_tv.setText("АДРЕСС: " + placeInfo.getAddress());
        TextView hours_tv = view.findViewById(R.id.hourstv3);
        hours_tv.setText("ОТКРЫТО: " + placeInfo.getHoursOfWork());
        TextView description_tv = view.findViewById(R.id.descriptiontv3);
        description_tv.setText("" + placeInfo.getComment());
        TextView review_tv =view.findViewById(R.id.reviewtv);
        review_tv.setText("ОТЗЫВЫ " + placeInfo.getCountOfMarks().toString());
        simpleViewFlipper = view.findViewById(R.id.simpleViewFlipper2);

        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images[i]);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
            roundedBitmapDrawable.setCircular(true);
            imageView.setImageDrawable(roundedBitmapDrawable);
            simpleViewFlipper.addView(imageView);
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(3000);
        // set auto start for flipping between views
        simpleViewFlipper.setAutoStart(true);
        //  flipperLayout = (FlipperLayout)findViewById(R.id.flipper_layout);
        //setLayout();



        ratingBar = view.findViewById(R.id.ratingBar2);
        //   ratingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });

        oneStar = view.findViewById(R.id.oneStar);
        oneStar.setRating(5f);

        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#fcfcfc"), PorterDuff.Mode.SRC_ATOP);

        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}