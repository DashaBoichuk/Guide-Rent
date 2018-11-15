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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayDeque;
import java.util.Deque;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.up_site.guiderenttest.fragments.InstitutionFragment;
import ua.com.up_site.guiderenttest.fragments.LocationFragment;
import ua.com.up_site.guiderenttest.fragments.RouteFragment;
import ua.com.up_site.guiderenttest.map.MapGeneratePathFragment;
import ua.com.up_site.guiderenttest.map.MapSelectLocationFragment;
import ua.com.up_site.guiderenttest.place.PlaceEditFragment;
import ua.com.up_site.guiderenttest.place.PlaceFragment;
import ua.com.up_site.guiderenttest.test.NetworkingTestFragment;
import ua.com.up_site.guiderenttest.top_guides.GuideProfileFragment;
import ua.com.up_site.guiderenttest.top_guides.TopGuidesFragment;

public class MainActivity extends AppCompatActivity
        implements TopGuidesFragment.OnFragmentInteractionListener,
        GuideProfileFragment.OnFragmentInteractionListener,
        MapGeneratePathFragment.OnFragmentInteractionListener,
        PlaceFragment.OnFragmentInteractionListener,
        PlaceEditFragment.OnFragmentInteractionListener,
        NetworkingTestFragment.OnFragmentInteractionListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener,
        MapSelectLocationFragment.OnFragmentInteractionListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    public TextView toolbar_title;
   /* @BindView(R.id.tv_name)
    TextView tvName;*/


    private PlaceFragment mPlaceFragment;
    private TopGuidesFragment mTopGuidesFragment;
    private MapGeneratePathFragment mMapGeneratePathFragment;
    private PlaceEditFragment mPlaceEditFragment;
    private NetworkingTestFragment mNetworkingTestFragment;
    private InstitutionFragment institutionFragment;
    private LocationFragment locationFragment;
    private RouteFragment routeFragment;
    private FrameLayout content;
    ProfilePictureView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toast toast = Toast.makeText(getApplicationContext(), UserInfo.getName(), Toast.LENGTH_SHORT);
        toast.show();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // tvName.setText("ycfytu");

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        content = findViewById(R.id.content);

        mPlaceFragment = new PlaceFragment();
        mTopGuidesFragment = new TopGuidesFragment();
        mMapGeneratePathFragment = new MapGeneratePathFragment();
        mPlaceEditFragment = new PlaceEditFragment();
        mNetworkingTestFragment = new NetworkingTestFragment();
        institutionFragment = new InstitutionFragment();
        locationFragment = new LocationFragment();
        routeFragment = new RouteFragment();


        android.support.v4.app.FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.content, locationFragment);
        mFragmentTransaction.commit();

        pushFragmentIntoStack(R.id.navigation_location);
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    final String BACK_STACK_ROOT_TAG = "root_fragment";

                    android.support.v4.app.FragmentTransaction mFragmentTransaction;
                    checkStackLimit();
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();


                    switch (menuItem.getItemId()) {
                        case R.id.navigation_top:
                            if (!isBackPressed) {
                                pushFragmentIntoStack(R.id.navigation_top);
                            }
                            isBackPressed = false;
                            mFragmentTransaction.replace(R.id.content, mTopGuidesFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;

                        case R.id.navigation_route:
                            if (!isBackPressed) {
                                pushFragmentIntoStack(R.id.navigation_route);
                            }
                            isBackPressed = false;
                            mFragmentTransaction.replace(R.id.content, routeFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;

                        case R.id.navigation_location:
                            if (!isBackPressed) {
                                pushFragmentIntoStack(R.id.navigation_location);
                            }
                            isBackPressed = false;
                            mFragmentTransaction.replace(R.id.content, locationFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;
                        case R.id.navigation_add:
                            if (!isBackPressed) {
                                pushFragmentIntoStack(R.id.navigation_add);
                            }
                            isBackPressed = false;
                            mFragmentTransaction.replace(R.id.content, mPlaceEditFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;
                        case R.id.navigation_profile:
                            if (!isBackPressed) {
                                pushFragmentIntoStack(R.id.navigation_profile);
                            }
                            isBackPressed = false;
                            mFragmentTransaction.replace(R.id.content, mNetworkingTestFragment);
                            mFragmentTransaction.addToBackStack(null);
                            mFragmentTransaction.commit();
                            return true;
                        default:
                            return false;
                    }
                }


            };
    Deque<Integer> mStack = new ArrayDeque<>();
    boolean isBackPressed = false;

    private void checkStackLimit() {
        FragmentManager fm = this.getSupportFragmentManager();

        if(fm.getBackStackEntryCount() > 10) {

            fm.popBackStack(); // remove one (you can also remove more)
        }
    }

    private void pushFragmentIntoStack(int id) {
        if (mStack.size() <= 10) {
            mStack.push(id);
        } else {
            mStack.removeLast();
            mStack.push(id);
        }
    }

    @Override
    public void onBackPressed() {
        if (mStack.size() > 1) {
            isBackPressed = true;
            mStack.pop();
            bottomNavigationView.setSelectedItemId(mStack.peek());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.nav_draw_routes) {
            fragmentClass = MapGeneratePathFragment.class;
        } else if (id == R.id.nav_draw_locations) {
            fragmentClass = PlaceFragment.class;
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
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(UserInfo.getName() + " " + UserInfo.getLastName());
        TextView tvEmail = findViewById(R.id.tv_email);
        tvEmail.setText(UserInfo.getEmail());
        profilePic =  findViewById(R.id.myProfilePic);
        profilePic.setProfileId(UserInfo.getId());
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