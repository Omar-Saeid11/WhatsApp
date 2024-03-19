package com.example.whatsappfirebase.viewpager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappfirebase.R;
import com.example.whatsappfirebase.databinding.ItemStatusBinding;
import com.example.whatsappfirebase.viewpager.data.Status;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    private final List<Status> statuses;

    public StatusAdapter(List<Status> statuses) {
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_status, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Status status = statuses.get(position);
        holder.binding.userName.setText(status.name);
        holder.binding.time.setText(status.time);
        holder.binding.imgUser.setImageResource(status.img);

    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemStatusBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemStatusBinding.bind(itemView);
        }
    }
}
