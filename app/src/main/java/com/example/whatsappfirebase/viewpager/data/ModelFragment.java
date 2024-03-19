package com.example.whatsappfirebase.viewpager.data;

import androidx.fragment.app.Fragment;

public class ModelFragment {
    private Fragment fragment;
    public String title;

    public ModelFragment(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
