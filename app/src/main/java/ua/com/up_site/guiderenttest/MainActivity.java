package ua.com.up_site.guiderenttest;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.up_site.guiderenttest.fragments.InstitutionFragment;
import ua.com.up_site.guiderenttest.fragments.LocationFragment;
import ua.com.up_site.guiderenttest.fragments.RouteFragment;
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
        NetworkingTestFragment.OnFragmentInteractionListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private PlaceFragment mPlaceFragment;
    private TopGuidesFragment mTopGuidesFragment;
    private MapFragmentTest mMapFragmentTest;
    private PlaceEditFragment mPlaceEditFragment;
    private NetworkingTestFragment mNetworkingTestFragment;
    private InstitutionFragment institutionFragment;
    private LocationFragment locationFragment;
    private RouteFragment routeFragment;
    private FrameLayout content;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content = findViewById(R.id.content);

        mPlaceFragment = new PlaceFragment();
        mTopGuidesFragment = new TopGuidesFragment();
        mMapFragmentTest = new MapFragmentTest();
        mPlaceEditFragment = new PlaceEditFragment();
        mNetworkingTestFragment = new NetworkingTestFragment();
        institutionFragment = new InstitutionFragment();
        locationFragment = new LocationFragment();
        routeFragment = new RouteFragment();


        android.support.v4.app.FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, mPlaceFragment);
        mFragmentTransaction.commit();
    }


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

                        case R.id.navigation_location:
                            mFragmentTransaction.replace(R.id.content, mPlaceFragment);
                            mFragmentTransaction.commit();
                            return true;
                        case R.id.navigation_add:
                            mFragmentTransaction.replace(R.id.content, mPlaceEditFragment);
                            mFragmentTransaction.commit();
                            return true;
                        case R.id.navigation_profile:
                            mFragmentTransaction.replace(R.id.content, mNetworkingTestFragment);
                            mFragmentTransaction.commit();


                        default:
                            return false;
                    }
                }
            };


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_draw_routes) {
            fragmentClass = RouteFragment.class;
        } else if (id == R.id.nav_draw_locations) {
            fragmentClass = LocationFragment.class;
        } else if (id == R.id.nav_draw_institutions) {
            fragmentClass = InstitutionFragment.class;

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}