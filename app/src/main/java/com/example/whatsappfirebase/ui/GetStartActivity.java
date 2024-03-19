package com.example.whatsappfirebase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappfirebase.databinding.ActivityGetStartBinding;
import com.example.whatsappfirebase.ui.auth.LoginActivity;

public class GetStartActivity extends AppCompatActivity {

    ActivityGetStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GetStartActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}