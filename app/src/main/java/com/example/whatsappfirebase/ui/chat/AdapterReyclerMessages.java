package com.example.whatsappfirebase.ui.chat;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappfirebase.databinding.ItemMessageBinding;
import com.example.whatsappfirebase.model.ModelMessage;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AdapterReyclerMessages extends RecyclerView.Adapter<AdapterReyclerMessages.Holder> {


    //    ArrayList<ModelMessage> list = new ArrayList<>();
    OnItemLongClick onItemLongClick;

    public void setOnItemLongClick(OnItemLongClick onItemLongClick) {
        this.onItemLongClick = onItemLongClick;
    }

    private List<ModelMessage> list;

    public AdapterReyclerMessages() {
        this.list = new ArrayList<>();
    }

    public void addItem(ModelMessage item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void changeItem(ModelMessage updatedItem) {
        for (int i = 0; i < list.size(); i++) {
            ModelMessage item = list.get(i);
            if (item.getMessageId().equals(updatedItem.getMessageId())) {
                list.set(i, updatedItem);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void removeItem(ModelMessage removedItem) {
        for (int i = 0; i < list.size(); i++) {
            ModelMessage item = list.get(i);
            if (item.getMessageId().equals(removedItem.getMessageId())) {
                list.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        ItemMessageBinding binding =
                ItemMessageBinding.inflate(LayoutInflater.from(parent.getContext())
                        , parent, false);

        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder {


        ItemMessageBinding binding;

        public Holder(ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    onItemLongClick.onLongClick(list.get(getLayoutPosition()));
                    return false;
                }
            });


        }

        public void bind(ModelMessage message) {
            binding.messageTxt.setText(message.getMessage());


            if (message.getSenderId().equals(FirebaseAuth.getInstance().getUid())) {
                binding.message.setGravity(Gravity.START);
            } else {
                binding.message.setGravity(Gravity.END);
            }
        }
    }


    interface OnItemLongClick {
        void onLongClick(ModelMessage message);
    }
}
