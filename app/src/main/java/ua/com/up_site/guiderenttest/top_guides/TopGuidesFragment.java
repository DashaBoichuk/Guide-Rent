package ua.com.up_site.guiderenttest.top_guides;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.user_model.GuideInfo;


public class TopGuidesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<GuideInfo> guideInfoList;

    private GridLayoutManager mLayoutManager;

    private android.support.v4.app.FragmentTransaction mFragmentTransaction;

    private OnFragmentInteractionListener mListener;

    public TopGuidesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopGuidesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopGuidesFragment newInstance(String param1, String param2) {
        TopGuidesFragment fragment = new TopGuidesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_guides, container, false);

        ((MainActivity) getActivity()).toolbar_title.setText("Top Guides");



        RecyclerView rvRecyclerView = rootView.findViewById(R.id.guides_recycler_view);
        //Создаём двадцать гидов
        guideInfoList = GuideInfo.createGuideList(20);
        //Из них создаём адаптер, назначаем ему OnClickListener, интерфейс которого я описал внутри адаптера...
        final GuidesAdapter adapter = new GuidesAdapter(guideInfoList, new GuidesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(GuideInfo item) {
                GuideProfileFragment mGuideProfileFragment = new GuideProfileFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("GuideInfoItem", item);
                mGuideProfileFragment.setArguments(bundle);

                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.content, mGuideProfileFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });
        //...и прикрепляем его
        rvRecyclerView.setAdapter(adapter);

        // (least common multiple of 1 and 2)
        mLayoutManager = new GridLayoutManager(rootView.getContext(), 2, GridLayoutManager.VERTICAL, false);

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 3 is the sum of items in one repeated section
                switch (position % 3) {
                    // first two items span 2 columns each
                    case 0:
                    case 1:
                        return 1;
                    // next one item span in 1 columns

                    case 2:
                        return 2;
                }
                throw new IllegalStateException("internal error");
            }
        });

        rvRecyclerView.setLayoutManager(mLayoutManager);

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
