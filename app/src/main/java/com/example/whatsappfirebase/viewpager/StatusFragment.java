package com.example.whatsappfirebase.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappfirebase.R;
import com.example.whatsappfirebase.databinding.ListOfStatusItemBinding;
import com.example.whatsappfirebase.viewpager.data.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListOfStatusItemBinding binding = ListOfStatusItemBinding.inflate(inflater, container, false);

        List<Status> statuses = getStatuses();
        RecyclerView recyclerView = binding.recyclerStatusList;
        recyclerView.setAdapter(new StatusAdapter(statuses));

        return binding.getRoot();
    }

    private List<Status> getStatuses() {
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Status("My status", "2:11 Am", R.drawable.logo));
        statuses.add(new Status("Ali Sa3id", "3:00 Am", R.drawable.ic_person));
        statuses.add(new Status("Muhammed", "4:00 Pm", R.drawable.logo));
        statuses.add(new Status("Retal", "2:11 Am", R.drawable.logo));
        statuses.add(new Status("Mahdy", "10:11 Am", R.drawable.ic_person2));
        statuses.add(new Status("Tony", "2:11 Am", R.drawable.logo));
        statuses.add(new Status("Tot's", "2:12 Am", R.drawable.ic_person));
        statuses.add(new Status("Jumana", "5:11 Am", R.drawable.ic_person2));
        statuses.add(new Status("Bora3y", "6:01 Am", R.drawable.logo));
        statuses.add(new Status("Marzouk", "7:11 Am", R.drawable.logo));
        statuses.add(new Status("Omar ", "2:19 Am", R.drawable.ic_person2));
        statuses.add(new Status("Mazen", "2:10 Am", R.drawable.ic_person3));
        statuses.add(new Status("Eissa", "4:17 Am", R.drawable.logo));
        statuses.add(new Status("7amdy", "3:11 Am", R.drawable.ic_person3));
        statuses.add(new Status("Son", "1:11 Am", R.drawable.ic_person2));
        statuses.add(new Status("Leo", "00:12 Am", R.drawable.logo));

        return statuses;
    }
}

