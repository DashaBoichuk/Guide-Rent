package ua.com.up_site.guiderenttest.locations;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.com.up_site.guiderenttest.MainActivity;
import ua.com.up_site.guiderenttest.R;

public class LocationTabFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.tab_layout_location)
    protected  TabLayout tabLayoutLocation;
    @BindView(R.id.viewPagerLocation)
    protected  ViewPager viewPagerLocation;
    public LocationTabAdapter locationTabAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_location, container, false);
        unbinder = ButterKnife.bind(this, view);

        locationTabAdapter = new LocationTabAdapter(getChildFragmentManager());
        viewPagerLocation.setAdapter(locationTabAdapter);

        viewPagerLocation.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutLocation));
        viewPagerLocation.setOffscreenPageLimit(0);

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

        setTabBG(R.drawable.tab_left_select, R.drawable.tab_right_unselect);
        tabLayoutLocation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerLocation.setCurrentItem(tab.getPosition());

                if (tabLayoutLocation.getSelectedTabPosition() == 0) {
                    setTabBG(R.drawable.tab_left_select, R.drawable.tab_right_unselect);
                    ((MainActivity) getActivity()).toolbar_title.setText("Локации");

                } else {
                    setTabBG(R.drawable.tab_left_unselect, R.drawable.tab_right_select);
                    ((MainActivity) getActivity()).toolbar_title.setText("Карта");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }
    private void setTabBG(int tab1, int tab2) {
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

    @Override
    public void onResume() {
        super.onResume();
        locationTabAdapter = new LocationTabAdapter(getChildFragmentManager());
        viewPagerLocation.setAdapter(locationTabAdapter);
        viewPagerLocation.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutLocation));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}