package com.example.whatsappfirebase.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsappfirebase.model.ModelUser;
import com.example.whatsappfirebase.databinding.ItemUserBinding;

import java.util.ArrayList;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.Holder> {

    public void setList(ArrayList<ModelUser> list) {
        this.list = list;
    }

    private ArrayList<ModelUser> list;
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;

        public Holder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClick != null)
                        onItemClick.onClick(list.get(getLayoutPosition()));
                }
            });
        }

        public void bind(ModelUser modelUser) {
            binding.username.setText(modelUser.getName());
            Glide.with(binding.getRoot().getContext()).load(modelUser.getImgUrl()).into(binding.imgProfile);
        }
    }

    interface OnItemClick {
        void onClick(ModelUser modelUser);
    }
}
