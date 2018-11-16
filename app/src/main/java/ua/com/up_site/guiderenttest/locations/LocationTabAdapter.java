package ua.com.up_site.guiderenttest.locations;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ua.com.up_site.guiderenttest.map.MapSelectLocationFragment;

public class LocationTabAdapter extends FragmentStatePagerAdapter {

    public LocationTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new LocationListFragment();
            case 1:
                return new MapSelectLocationFragment();
            default: return null;
        }
    }
        @Override
        public int getCount() {
            return 2;
        }
}



