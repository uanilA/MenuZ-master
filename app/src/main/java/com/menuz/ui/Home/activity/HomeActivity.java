package com.menuz.ui.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.menuz.R;
import com.menuz.Utils.MySnackBar;
import com.menuz.application.MenuZ;
import com.menuz.base.BaseActivity;
import com.menuz.session.Session;
import com.menuz.ui.GalleryActivity;
import com.menuz.ui.Home.adapter.NavigationMenuAdapter;
import com.menuz.ui.Home.fragment.HomeFragment;
import com.menuz.ui.Home.model.NavigationModel;
import com.menuz.ui.language.ChangeLanguageActivity;
import com.menuz.ui.language.Language;
import com.menuz.ui.setting.SettingActivity;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationMenuAdapter.Listener {
    private DrawerLayout drawer;

    private ArrayList<NavigationModel> arrayList = new ArrayList<>();
    private NavigationView navigation_view;
    private boolean doubleBackToExitPressedOnce;
    private Runnable runnable;
    private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLanguage();
        setContentView(R.layout.activity_home);
        initView();
        addFragment(HomeFragment.newInstance(), false);


    }

    public void initView() {
        drawer = findViewById(R.id.drawer);
        navigation_view = findViewById(R.id.navigation_view);
        navigation_view.setOnClickListener(this);
        ImageView ivMenu = findViewById(R.id.ivMenu);
        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(this);

        RecyclerView recyclerMenu = findViewById(R.id.recyclerMenu);
        drawer.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                // mainView.setTranslationX(slideOffset * drawerView.getWidth());
                drawer.bringChildToFront(drawerView);
                drawer.requestLayout();
                //   rlContent.setTranslationX(-slideX);
            }
        });

        addItems();
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerMenu.setLayoutManager(layoutManager);
        NavigationMenuAdapter navigationAdapter = new NavigationMenuAdapter(HomeActivity.this, arrayList, this);
        recyclerMenu.setAdapter(navigationAdapter);

    }


    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 700) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.ivMenu:

                if (drawer.isDrawerOpen(navigation_view)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(navigation_view);
                }
                break;


            case R.id.ivBack:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }


                break;
        }
    }

    private void addItems() {
        NavigationModel item;
        for (int i = 0; i < 6; i++) {
            item = new NavigationModel();
            switch (i) {
                case 0:
                    item.itemName = getString(R.string.home);
                    item.itemImg = R.drawable.home_inactive;
                    item.isSelect = true;
                    item.itemImgActive = R.drawable.home_active;

                    break;
                case 1:
                    item.itemName = getString(R.string.gallery);
                    item.isSelect = false;
                    item.itemImg = R.drawable.gallery_inactive;
                    item.itemImgActive = R.drawable.gallery_active;

                    break;

                case 2:
                    item.itemName = getString(R.string.language);
                    item.isSelect = false;
                    item.itemImg = R.drawable.language_inactive;
                    item.itemImgActive = R.drawable.language_active;
                    break;

                case 3:
                    item.itemName = getString(R.string.logut);
                    item.isSelect = false;
                    item.itemImg = R.drawable.logout_inactive;
                    item.itemImgActive = R.drawable.logout_active;
                    break;

                case 4:
                    item.itemName = getString(R.string.action_settings);
                    item.isSelect = false;
                    item.itemImg = R.drawable.inactive_setting_icon;
                    item.itemImgActive = R.drawable.active_setting_icon;
                    break;

                case 5:
                    item.itemName = "V 1.0";
                    item.isSelect = false;
                    break;


            }
            arrayList.add(item);
        }
    }

    @Override
    public void pos(int pos) {
        switch (pos) {
            case 0:
                addFragment(HomeFragment.newInstance(), true);
                drawer.closeDrawers();
                break;

            case 1:
                drawer.closeDrawers();
                Intent gallery=new Intent(this,GalleryActivity.class);
                startActivity(gallery);

                break;

            case 2:
                drawer.closeDrawers();
                Intent intent = new Intent(HomeActivity.this, ChangeLanguageActivity.class);
                startActivity(intent);
                break;


            case 3:
                drawer.closeDrawers();
                MenuZ.getInstance().getSessionManager().logout();
                new Thread(() -> getDataManager().clearAllTable()).start();
                break;

            case 4:
                drawer.closeDrawers();
                Intent intentSetting = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intentSetting);
                break;

            default:
                addFragment(HomeFragment.newInstance(), true);
                break;

        }
    }


    @Override
    public void onBackPressed() {
        /* Handle double click to finish activity*/
        Handler handler = new Handler();
        FragmentManager fm = getSupportFragmentManager();
        int i = fm.getBackStackEntryCount();
        if (i > 0) {
            fm.popBackStack();
        } else if (!doubleBackToExitPressedOnce) {

            doubleBackToExitPressedOnce = true;
            //Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT).show();
            MySnackBar.showSnackbar(this, findViewById(R.id.lyCoordinatorLayout), "Click again to exit");
            handler.postDelayed(runnable = () -> doubleBackToExitPressedOnce = false, 2000);

        } else {
            handler.removeCallbacks(runnable);
            finishAffinity();
        }
    }

    public void checkLanguage() {
        Session session = new Session(HomeActivity.this);
        String userselectedlanguage = session.getLanguage();
        switch (userselectedlanguage) {
            case "en":
                Language.SetLanguage(HomeActivity.this, "en");
                break;
            case "iw":
                Language.SetLanguage(HomeActivity.this, "iw");
                break;
            case "":
                Language.SetLanguage(HomeActivity.this, "en");
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        checkLanguage();
    }
}
