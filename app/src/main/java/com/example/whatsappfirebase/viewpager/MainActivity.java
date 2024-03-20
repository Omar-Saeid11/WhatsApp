package com.example.whatsappfirebase.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.whatsappfirebase.ui.chat.ChatFragment;
import com.example.whatsappfirebase.ui.auth.LoginActivity;
import com.example.whatsappfirebase.R;
import com.example.whatsappfirebase.databinding.ActivityMainBinding;
import com.example.whatsappfirebase.viewpager.adapter.MyViewPagerAdapter;
import com.example.whatsappfirebase.viewpager.data.ModelFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    StatusFragment statusFragment;
    ChatFragment chatFragment;
    CallsFragment callsFragment;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ArrayList<ModelFragment> list = new ArrayList<>();
    MyViewPagerAdapter myViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        statusFragment = new StatusFragment();
        chatFragment = new ChatFragment();
        callsFragment = new CallsFragment();
        myViewPagerAdapter = new MyViewPagerAdapter(this, list);
        list.add(new ModelFragment(chatFragment, "Chats"));
        list.add(new ModelFragment(statusFragment, "Status"));
        list.add(new ModelFragment(callsFragment, "Calls"));

        binding.viewPager.setAdapter(myViewPagerAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(list.get(position).getTitle());
            }
        }).attach();

        binding.AppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.group) {

                    return true;
                } else if (id == R.id.broadcast) {

                    return true;
                } else if (id == R.id.link) {

                    return true;
                } else if (id == R.id.logout) {
                    auth.signOut();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    return true;
                } else if (id == R.id.search) {
                    return true;
                } else return id == R.id.camera;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (!chatFragment.isVisible()) {
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
        } else {
            super.onBackPressed();
        }
    }

}
