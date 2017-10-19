package com.example.fungwah.campusgo.command.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fungwahtools.fragment.BaseFragment;

import java.util.List;

/**
 * Created by FungWah on 2017/10/19.
 */

public class FragmentPageAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;

    public FragmentPageAdapter(List<BaseFragment> list, FragmentManager fm) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
