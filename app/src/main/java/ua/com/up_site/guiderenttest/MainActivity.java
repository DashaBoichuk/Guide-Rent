package ua.com.up_site.guiderenttest;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import ua.com.up_site.guiderenttest.TopGuidesPackage.GuideProfileFragment;

public class MainActivity extends AppCompatActivity
        implements TopGuidesFragment.OnFragmentInteractionListener,
        GuideProfileFragment.OnFragmentInteractionListener {
    private FragmentTransaction mFragmentTransaction;
    private TopGuidesFragment mTopGuidesFragment;

    private FrameLayout content;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    mFragmentTransaction = getFragmentManager().beginTransaction();

                    switch (menuItem.getItemId()) {
                        case R.id.navigation_top:
                            mFragmentTransaction.replace(R.id.content, mTopGuidesFragment);
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
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
