package com.example.whatsappfirebase.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.whatsappfirebase.databinding.ActivityChatBinding;
import com.example.whatsappfirebase.model.ModelMessage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    ActivityChatBinding binding;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelMessage> list = new ArrayList<>();
    AdapterReyclerMessages adapterReyclerMessages = new AdapterReyclerMessages();

    private String senderId;
    private String receiverId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.username.setText(intent.getStringExtra("name"));
        Glide.with(binding.getRoot().getContext()).load(intent.getStringExtra("image")).into(binding.imgUser);

        senderId = intent.getStringExtra("senderId");
        receiverId = intent.getStringExtra("receiverId");

        binding.recyclerViewChats.setAdapter(adapterReyclerMessages);

        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = Objects.requireNonNull(binding.message.getEditText()).getText().toString().trim();
                if (!message.isEmpty()) {
                    sendMessage(message);
                    binding.message.getEditText().setText("");
                }
            }
        });

        getData();
    }

    private void getData() {
        ref.child("messages")
                .orderByChild("timestamp")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        ModelMessage modelMessage = snapshot.getValue(ModelMessage.class);
                        if (modelMessage != null
                                && ((modelMessage.getSenderId().equals(senderId) && modelMessage.getReceiverId().equals(receiverId))
                                || (modelMessage.getSenderId().equals(receiverId) && modelMessage.getReceiverId().equals(senderId)))) {
                            adapterReyclerMessages.addItem(modelMessage);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        ModelMessage updatedMessage = snapshot.getValue(ModelMessage.class);
                        if (updatedMessage != null && ((updatedMessage.getSenderId().equals(senderId) && updatedMessage.getReceiverId().equals(receiverId))
                                || (updatedMessage.getSenderId().equals(receiverId) && updatedMessage.getReceiverId().equals(senderId)))) {
                            adapterReyclerMessages.changeItem(updatedMessage);
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        ModelMessage removedMessage = snapshot.getValue(ModelMessage.class);
                        if (removedMessage != null && ((removedMessage.getSenderId().equals(senderId) && removedMessage.getReceiverId().equals(receiverId))
                                || (removedMessage.getSenderId().equals(receiverId) && removedMessage.getReceiverId().equals(senderId)))) {
                            adapterReyclerMessages.removeItem(removedMessage);
                        }
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        // Not implemented
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Not implemented
                    }
                });
    }

    private void sendMessage(String message) {
        String messageId = ref.child("messages").push().getKey();
        if (messageId != null) {
            ModelMessage modelMessage = new ModelMessage(senderId, receiverId, message, messageId, System.currentTimeMillis());
            ref.child("messages").child(messageId).setValue(modelMessage)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // Message sent successfully
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChatActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}