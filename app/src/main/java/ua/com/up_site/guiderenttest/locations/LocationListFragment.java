package ua.com.up_site.guiderenttest.locations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import ua.com.up_site.guiderenttest.map.MapSelectLocationFragment;
import ua.com.up_site.guiderenttest.models.CommonData;

public class LocationListFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.recycler_location)
    RecyclerView recyclerView;
    @BindView(R.id.searchViewLocation)
    SearchView searchViewLocation;
    @BindView(R.id.fab_add_location)
    FloatingActionButton fab_location;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    private android.support.v4.app.FragmentTransaction mFragmentTransaction;
    List<CommonData> locationData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_content, container, false);
        unbinder = ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).toolbar_title.setText("Локации");

        final MapSelectLocationFragment mapSelectLocationFragment = new MapSelectLocationFragment();

        searchViewLocation.setBackgroundResource(R.drawable.frame);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 || dy < 0 && fab_location.isShown())
                    fab_location.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab_location.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

  /*     locationEditFragment = new LocationEditFragment();

        fab_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.content, locationEditFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });    */

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        fab_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.content, mapSelectLocationFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });

        initData();
        return view;
    }

    private void initData() {
        locationData = new ArrayList<>();

        locationData.add(new CommonData(R.drawable.morvokzal,"Заголовок", R.drawable.star, "5.4", R.drawable.photo_camera, "10:27", "Количество фото", R.drawable.people, "993", "Посещения"));
        locationData.add(new CommonData(R.drawable.opera_theater,"Заголовок", R.drawable.star, "3.2", R.drawable.photo_camera, "2:34", "Количество фото", R.drawable.people, "136", "Посещения"));
        locationData.add(new CommonData(R.drawable.memorial,"Заголовок", R.drawable.star, "9.6", R.drawable.photo_camera, "23:20", "Количество фото", R.drawable.people, "441", "Посещения"));
        locationData.add(new CommonData(R.drawable.philarmonia,"Заголовок", R.drawable.star, "6.0", R.drawable.photo_camera, "13:56", "Количество фото", R.drawable.people, "816", "Посещения"));
        locationData.add(new CommonData(R.drawable.alley,"Заголовок", R.drawable.star, "6.3", R.drawable.photo_camera, "7:39", "Количество фото", R.drawable.people, "188", "Посещения"));
        locationData.add(new CommonData(R.drawable.april,"Заголовок", R.drawable.star, "7.9", R.drawable.photo_camera, "8:15", "Количество фото", R.drawable.people, "854", "Посещения"));
        locationData.add(new CommonData(R.drawable.kinostudia,"Заголовок", R.drawable.star, "1.6", R.drawable.photo_camera, "16:09", "Количество фото", R.drawable.people, "833", "Посещения"));

        mAdapter = new LocationListAdapter(getContext(), locationData);
        recyclerView.setAdapter(mAdapter);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).toolbar_title.setText("Локации");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}