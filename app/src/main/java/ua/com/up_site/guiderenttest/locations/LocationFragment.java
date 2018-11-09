package ua.com.up_site.guiderenttest.locations;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.models.CommonData;

public class LocationFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.recycler_location)
    RecyclerView recyclerView;
    @BindView(R.id.searchViewLocation)
    SearchView searchViewLocation;
    @BindView(R.id.fab_location)
    FloatingActionButton fab_location;
    @BindView(R.id.tabs_location)
    TabLayout tabLayoutLocation;
   // @BindView(R.id.viewPager)
  //  public ViewPager viewPager;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<CommonData> locationData;
   LocationEditFragment locationEditFragment;

    private android.support.v4.app.FragmentTransaction mFragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        unbinder = ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).toolbar_title.setText("Локации");

        tabLayoutLocation.addTab(tabLayoutLocation.newTab().setText("список"));
        tabLayoutLocation.addTab(tabLayoutLocation.newTab().setText("карта"));

        View root = tabLayoutLocation.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(android.R.color.black));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        setTabBG(R.drawable.tab_left_select,R.drawable.tab_right_unselect);
        tabLayoutLocation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                if(tabLayoutLocation.getSelectedTabPosition()==0) {
                    setTabBG(R.drawable.tab_left_select,R.drawable.tab_right_unselect);
                }
                else {
                    setTabBG(R.drawable.tab_left_unselect,R.drawable.tab_right_select);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

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

        locationEditFragment = new LocationEditFragment();

        fab_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.content, locationEditFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

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

        mAdapter = new LocationAdapter(getContext(), locationData);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void setTabBG(int tab1, int tab2){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            ViewGroup tabStrip = (ViewGroup) tabLayoutLocation.getChildAt(0);
            View tabView1 = tabStrip.getChildAt(0);
            View tabView2 = tabStrip.getChildAt(1);
            if (tabView1 != null) {
                int paddingStart = tabView1.getPaddingStart();
                int paddingTop = tabView1.getPaddingTop();
                int paddingEnd = tabView1.getPaddingEnd();
                int paddingBottom = tabView1.getPaddingBottom();
                ViewCompat.setBackground(tabView1, AppCompatResources.getDrawable(tabView1.getContext(), tab1));
                ViewCompat.setPaddingRelative(tabView1, paddingStart, paddingTop, paddingEnd, paddingBottom);
            }

            if (tabView2 != null) {
                int paddingStart = tabView2.getPaddingStart();
                int paddingTop = tabView2.getPaddingTop();
                int paddingEnd = tabView2.getPaddingEnd();
                int paddingBottom = tabView2.getPaddingBottom();
                ViewCompat.setBackground(tabView2, AppCompatResources.getDrawable(tabView2.getContext(), tab2));
                ViewCompat.setPaddingRelative(tabView2, paddingStart, paddingTop, paddingEnd, paddingBottom);
            }
        }
    }


}