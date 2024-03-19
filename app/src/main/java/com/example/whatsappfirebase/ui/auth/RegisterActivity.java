package com.example.whatsappfirebase.ui.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappfirebase.R;
import com.example.whatsappfirebase.databinding.ActivityRegisterBinding;
import com.example.whatsappfirebase.model.ModelUser;
import com.example.whatsappfirebase.viewpager.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    Uri fileImgProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Objects.requireNonNull(binding.name.getEditText()).getText().toString().trim();
                String email = Objects.requireNonNull(binding.email.getEditText()).getText().toString().trim();
                String password = Objects.requireNonNull(binding.password.getEditText()).getText().toString();
                validate(name, email, password);
            }
        });
        binding.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 200);
            }
        });
    }

    private void validate(String name, String email, String password) {
        if (name.isEmpty()) {
            binding.name.setError(getString(R.string.required));
        } else if (email.isEmpty()) {
            binding.email.setError((getString(R.string.required)));
        } else if (password.isEmpty() || password.length() < 6) {
            binding.password.setError((getString(R.string.required)));
        } else if (fileImgProfile == null) {
            Toast.makeText(RegisterActivity.this, "Please select photo", Toast.LENGTH_LONG).show();
        } else {
            register(name, email, password);
        }
    }

    private void register(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        sendImgToStorage(name, email, Objects.requireNonNull(authResult.getUser()).getUid());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void sendImgToStorage(String name, String email, String uId) {
        if (fileImgProfile != null) {
            StorageReference reference = storageReference.child("images/").child(uId + System.currentTimeMillis());
            reference.putFile(fileImgProfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            sendDataToRealTime(name, email, uId, uri.toString());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void sendDataToRealTime(String name, String email, String uId, String imgUrl) {
        ref.child("users").child(uId).setValue(new ModelUser(name, email, uId, imgUrl)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null && requestCode == 200) {
            fileImgProfile = data.getData();
            binding.imgProfile.setImageURI(fileImgProfile);
            binding.imgProfile.setPadding(0, 0, 0, 0);
        }
    }
}