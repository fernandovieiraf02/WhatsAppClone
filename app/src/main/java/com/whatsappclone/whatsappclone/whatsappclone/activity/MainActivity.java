package com.whatsappclone.whatsappclone.whatsappclone.activity;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.adapter.TabsPageAdapter;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.ConversasFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConversasFragment conversasFragment, chamadasFragment, statusFragment;
    private TabItem tb_conversas, tb_status, tb_chamadas;
    private TabLayout tab;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conversasFragment = new ConversasFragment();
        chamadasFragment = new ConversasFragment();
        statusFragment = new ConversasFragment();

        tab = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_frame);

        TabsPageAdapter tabsAdapter = new TabsPageAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(conversasFragment, "CONVERSAS");
        tabsAdapter.addFragment(statusFragment, "STATUS");
        tabsAdapter.addFragment(chamadasFragment, "CHAMADAS");

        viewPager.setAdapter(tabsAdapter);
        tab.setupWithViewPager(viewPager);

        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame, conversasFragment);
        fragmentTransaction.commit();*/
    }
}