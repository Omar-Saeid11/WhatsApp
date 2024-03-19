package com.example.whatsappfirebase.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.whatsappfirebase.viewpager.data.ModelFragment;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    private ArrayList<ModelFragment> list;

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ModelFragment> list) {
        super(fragmentActivity);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position).getFragment();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<ModelFragment> getList() {
        return list;
    }

    public void setList(ArrayList<ModelFragment> list) {
        this.list = list;
    }
}


