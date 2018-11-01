package ua.com.up_site.guiderenttest;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import ua.com.up_site.guiderenttest.top_guides.TopGuidesFragment;
import ua.com.up_site.guiderenttest.place.PlaceEditFragment;
import ua.com.up_site.guiderenttest.place.PlaceFragment;
import ua.com.up_site.guiderenttest.map.MapFragmentTest;
import ua.com.up_site.guiderenttest.test.NetworkingTestFragment;
import ua.com.up_site.guiderenttest.top_guides.GuideProfileFragment;

public class MainActivity extends AppCompatActivity
        implements TopGuidesFragment.OnFragmentInteractionListener,
        GuideProfileFragment.OnFragmentInteractionListener,
        MapFragmentTest.OnFragmentInteractionListener,
        PlaceFragment.OnFragmentInteractionListener,
        PlaceEditFragment.OnFragmentInteractionListener,
        NetworkingTestFragment.OnFragmentInteractionListener {

    private PlaceFragment mPlaceFragment;
    private TopGuidesFragment mTopGuidesFragment;
    private MapFragmentTest mMapFragmentTest;
    private PlaceEditFragment mPlaceEditFragment;
    private NetworkingTestFragment mNetworkingTestFragment;

    private FrameLayout content;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    android.support.v4.app.FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();

                    switch (menuItem.getItemId()) {
                        case R.id.navigation_top:
                            mFragmentTransaction.replace(R.id.content, mTopGuidesFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;

                        case R.id.navigation_route:
                            mFragmentTransaction.replace(R.id.content, mMapFragmentTest);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;

                        case R.id.navigation_location:
                            mFragmentTransaction.replace(R.id.content, mPlaceFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;
                        case R.id.navigation_add:
                            mFragmentTransaction.replace(R.id.content, mPlaceEditFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;
                        case R.id.navigation_profile:
                            mFragmentTransaction.replace(R.id.content, mNetworkingTestFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                        default:
                            return false;
                    }
                }
            };
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationDrawer = findViewById(R.id.navigation);
        bottomNavigationDrawer.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content = findViewById(R.id.content);

        mPlaceFragment = new PlaceFragment();
        mTopGuidesFragment = new TopGuidesFragment();
        mMapFragmentTest = new MapFragmentTest();
        mPlaceEditFragment = new PlaceEditFragment();
        mNetworkingTestFragment = new NetworkingTestFragment();

        android.support.v4.app.FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mPlaceFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
