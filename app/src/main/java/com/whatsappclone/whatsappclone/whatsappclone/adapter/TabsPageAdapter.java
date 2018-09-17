package com.whatsappclone.whatsappclone.whatsappclone.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabsPageAdapter extends FragmentStatePagerAdapter {

    private HashMap<String, Fragment> listFragments;
    private List<String> listTitle;


    public TabsPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        listFragments = new HashMap<>();
        listTitle = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        return listFragments.get( listTitle.get(i) );
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        listFragments.put(title, fragment);
        listTitle.add(title);
    }
}
