package com.example.fungwah.campusgo;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.fungwahtools.activity.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView nav_img;
    private DrawerLayout drawerLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.homepage;
    }

    @Override
    protected void initView() {
        drawerLayout = findView(R.id.drawer_layout);
        toolbar = findView(R.id.toolbar);
        nav_img = findView(R.id.navigation_img);
    }

    @Override
    protected void setView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initListener() {
        nav_img.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_menu,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigation_img:
                if(drawerLayout.isDrawerOpen(Gravity.START)){
                    drawerLayout.closeDrawers();
                }else{
                    drawerLayout.openDrawer(Gravity.START);
                }
                break;
            default:
                break;
        }
    }
}
