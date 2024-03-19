package com.example.whatsappfirebase.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.whatsappfirebase.databinding.ActivityHomeBinding;
import com.example.whatsappfirebase.model.ModelUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    ActivityHomeBinding binding;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ArrayList<ModelUser> list = new ArrayList<>();
    AdapterUser adapterUser = new AdapterUser();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityHomeBinding.inflate(inflater, container, false);
        adapterUser.setOnItemClick(modelUser -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra("senderId", modelUser.getuId());
            intent.putExtra("receiverId", FirebaseAuth.getInstance().getCurrentUser().getUid());
            intent.putExtra("name", modelUser.getName());
            intent.putExtra("image", modelUser.getImgUrl());
            startActivity(intent);
        });
        getData();
        return binding.getRoot();
    }

    private void getData() {
        ref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    ModelUser user = snapshot1.getValue(ModelUser.class);
                    assert user != null;
                    if (!user.getuId().equals(FirebaseAuth.getInstance().getUid())) {
                        list.add(user);
                    }
                    adapterUser.setList(list);
                    binding.recyclerUsers.setAdapter(adapterUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });
    }
}