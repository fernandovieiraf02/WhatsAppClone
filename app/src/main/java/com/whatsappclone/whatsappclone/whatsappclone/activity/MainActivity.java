package com.whatsappclone.whatsappclone.whatsappclone.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.adapter.TabsPageAdapter;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.ChatFragment;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.ConversasFragment;

public class MainActivity extends AppCompatActivity {

    private ConversasFragment conversasFragment, chamadasFragment, statusFragment;
    private TabItem tb_conversas, tb_status, tb_chamadas;
    private TabLayout tab;
    private ViewPager viewPager;
    public static FragmentManager fragmentManager;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = getApplicationContext();
        fragmentManager = getSupportFragmentManager();

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
    }

    @Override
    public void onBackPressed() {
       /* if (fragmentManager.isDestroyed()) {
            super.onBackPressed();
        } else {
            fragmentTransaction.remove(new ChatFragment());
            fragmentTransaction.commit();
        }*/
    }
}