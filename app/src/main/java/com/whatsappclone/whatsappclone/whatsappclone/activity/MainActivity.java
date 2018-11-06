package com.whatsappclone.whatsappclone.whatsappclone.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.adapter.TabsPageAdapter;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.ConversasFragment;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {

    private ConversasFragment conversasFragment, chamadasFragment, statusFragment;
    private TabLayout tab;
    private ViewPager viewPager;
    public static FragmentManager fragmentManager;
    public static MainActivity instance;
    private HomeFragment homeFragment;
    public static Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();
        this.instance = this;
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, homeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment lastFragment = fragmentManager.getFragments().get(fragmentManager.getFragments().size() - 1);
        if (fragmentManager.getFragments().size() <= 0) {
            return;
        }

        if (lastFragment.getClass().equals(ConversasFragment.class)) {
            super.onBackPressed();
        }

        fragmentTransaction.remove(lastFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}