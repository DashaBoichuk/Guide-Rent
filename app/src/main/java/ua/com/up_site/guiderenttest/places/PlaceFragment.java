package ua.com.up_site.guiderenttest.places;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.models.CommonData;

public class PlaceFragment extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.recycler_place)
    RecyclerView recyclerPlace;
    @BindView(R.id.searchViewPlace)
    SearchView searchViewPlace;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<CommonData> placeData;

    @BindView(R.id.fab_place)
    FloatingActionButton fab_place;
    PlaceEditFragment mPlaceEditFragment;

    private android.support.v4.app.FragmentTransaction mFragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place, container, false);
        unbinder = ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).toolbar_title.setText("Заведения");


        searchViewPlace.setBackgroundResource(R.drawable.frame);

        recyclerPlace.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 || dy < 0 && fab_place.isShown())
                    fab_place.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab_place.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        mPlaceEditFragment = new PlaceEditFragment();

        fab_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.content, mPlaceEditFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });

        recyclerPlace.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerPlace.setLayoutManager(layoutManager);

        initData();
        return view;

    }

    private void initData() {
        placeData = new ArrayList<>();

        placeData.add(new CommonData(R.drawable.alley,"Ресторан", "Интольже", R.drawable.star, "6.3",  "20 $", "Средний чек", R.drawable.people, "188", "Посещения"));
        placeData.add(new CommonData(R.drawable.april,"Ресторан", "Интольже", R.drawable.star, "7.9",  "20 $", "Средний чек", R.drawable.people, "854", "Посещения"));
        placeData.add(new CommonData(R.drawable.philarmonia,"Ресторан", "Интольже", R.drawable.star, "6.0", "20 $", "Средний чек", R.drawable.people, "816", "Посещения"));
        placeData.add(new CommonData(R.drawable.morvokzal,"Ресторан", "Интольже", R.drawable.star, "5.4",  "20 $", "Средний чек", R.drawable.people, "993", "Посещения"));
        placeData.add(new CommonData(R.drawable.kinostudia,"Ресторан", "Интольже", R.drawable.star, "1.6", "20 $", "Средний чек", R.drawable.people, "833", "Посещения"));
        placeData.add(new CommonData(R.drawable.opera_theater,"Ресторан", "Интольже", R.drawable.star, "3.2",  "20 $", "Средний чек", R.drawable.people, "136", "Посещения"));
        placeData.add(new CommonData(R.drawable.memorial,"Ресторан", "Интольже", R.drawable.star, "9.6",  "20 $", "Средний чек", R.drawable.people, "441", "Посещения"));

        mAdapter = new PlaceAdapter(getContext(), placeData);
        recyclerPlace.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}