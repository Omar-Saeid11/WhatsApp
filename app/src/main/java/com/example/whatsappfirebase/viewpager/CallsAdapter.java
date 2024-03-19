package com.example.whatsappfirebase.viewpager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappfirebase.R;
import com.example.whatsappfirebase.databinding.ItemCallsBinding;
import com.example.whatsappfirebase.viewpager.data.Calls;

import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.ViewHolder> {

    private final List<Calls> callsList;

    public CallsAdapter(List<Calls> calls) {
        this.callsList = calls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_calls, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Calls calls = callsList.get(position);
        holder.binding.userName.setText(calls.name);
        holder.binding.time.setText(calls.time);
        holder.binding.imgUser.setImageResource(calls.img_user);
        holder.binding.imgPhone.setImageResource(calls.img_phone);

    }

    @Override
    public int getItemCount() {
        return callsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemCallsBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemCallsBinding.bind(itemView);
        }
    }
}
