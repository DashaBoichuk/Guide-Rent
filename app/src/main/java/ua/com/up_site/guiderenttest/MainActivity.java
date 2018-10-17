package ua.com.up_site.guiderenttest;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import ua.com.up_site.guiderenttest.MapPackage.MapFragmentTest;
import ua.com.up_site.guiderenttest.TopGuidesPackage.GuideProfileFragment;

public class MainActivity extends AppCompatActivity
        implements TopGuidesFragment.OnFragmentInteractionListener,
        GuideProfileFragment.OnFragmentInteractionListener,
        MapFragmentTest.OnFragmentInteractionListener {
    private TopGuidesFragment mTopGuidesFragment;
    private MapFragmentTest mMapFragmentTest;

    private FrameLayout content;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    android.support.v4.app.FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();

                    switch (menuItem.getItemId()) {
                        case R.id.navigation_top:
                            mFragmentTransaction.replace(R.id.content, mTopGuidesFragment);
                            mFragmentTransaction.commit();
                            return true;

                        case R.id.navigation_route:
                            mFragmentTransaction.replace(R.id.content, mMapFragmentTest);
                            mFragmentTransaction.commit();
                            return true;
                        default:
                            return false;
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationDrawer = findViewById(R.id.navigation);
        bottomNavigationDrawer.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content = findViewById(R.id.content);

        mTopGuidesFragment = new TopGuidesFragment();
        mMapFragmentTest = new MapFragmentTest();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}