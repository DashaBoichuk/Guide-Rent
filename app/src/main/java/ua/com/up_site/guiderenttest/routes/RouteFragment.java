package ua.com.up_site.guiderenttest.routes;

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
import ua.com.up_site.guiderenttest.places.PlaceEditFragment;


public class RouteFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.recycler_route)
    RecyclerView recyclerViewRoute;
    @BindView(R.id.searchViewRoute)
    SearchView searchViewRoute;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<CommonData> routeData;

    @BindView(R.id.fab_route)
    FloatingActionButton fab_route;
    PlaceEditFragment mPlaceEditFragment;

    private android.support.v4.app.FragmentTransaction mFragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        unbinder = ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).toolbar_title.setText("Маршруты");

        searchViewRoute.setBackgroundResource(R.drawable.frame);


       recyclerViewRoute.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 || dy < 0 && fab_route.isShown())
                    fab_route.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab_route.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });


        mPlaceEditFragment = new PlaceEditFragment();

        fab_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.content, mPlaceEditFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });


        recyclerViewRoute.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewRoute.setLayoutManager(layoutManager);

        initData();
        return view;

    }

    private void initData() {
        routeData = new ArrayList<>();

        routeData.add(new CommonData(R.drawable.opera_theater,"Заголовок", "Оперный театр", R.drawable.star, "3.2", R.drawable.clock, "2:34", "Количество фото", R.drawable.people, "136", "Посещения"));
        routeData.add(new CommonData(R.drawable.alley,"Заголовок", "Аллея Славы", R.drawable.star, "6.3", R.drawable.clock, "7:39", "Количество фото", R.drawable.people, "188", "Посещения"));
        routeData.add(new CommonData(R.drawable.april,"Заголовок", "Площадь 10 апреля", R.drawable.star, "7.9", R.drawable.clock, "8:15", "Количество фото", R.drawable.people, "854", "Посещения"));
        routeData.add(new CommonData(R.drawable.memorial,"Заголовок", "Мемориал 411 бат.", R.drawable.star, "9.6", R.drawable.clock, "23:20", "Количество фото", R.drawable.people, "441", "Посещения"));
        routeData.add(new CommonData(R.drawable.philarmonia,"Заголовок", "Филармония", R.drawable.star, "6.0", R.drawable.clock, "13:56", "Количество фото", R.drawable.people, "816", "Посещения"));
        routeData.add(new CommonData(R.drawable.morvokzal,"Заголовок", "Морвокзал", R.drawable.star, "5.4", R.drawable.clock, "10:27", "Количество фото", R.drawable.people, "993", "Посещения"));
        routeData.add(new CommonData(R.drawable.kinostudia,"Заголовок", "Киностудия", R.drawable.star, "1.6", R.drawable.clock, "16:09", "Количество фото", R.drawable.people, "833", "Посещения"));

        mAdapter = new RouteAdapter(getContext(), routeData);
        recyclerViewRoute.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}






