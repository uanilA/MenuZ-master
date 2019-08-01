package com.menuz.ui.Order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.menuz.Demo.DemoAddonFragment;
import com.menuz.Demo.DemoPreprationFragment;

public class ViewPagerAdapter  extends FragmentPagerAdapter {

    public DemoAddonFragment demoAddonFragment;
    public DemoPreprationFragment demoPreprationFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            demoAddonFragment = new DemoAddonFragment();
            fragment = demoAddonFragment;
        } else if (position == 1) {
            demoPreprationFragment = new DemoPreprationFragment();
            fragment = demoPreprationFragment;
        }
    /*    else if (position == 2)
        {
            fragment=new CartFragment();
        }*/
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Addon";

        } else if (position == 1) {
            title = "Preparation";
        }
       /* else if (position == 2)
        {
            title = "Cart";
        }*/
        return title;
    }
}

