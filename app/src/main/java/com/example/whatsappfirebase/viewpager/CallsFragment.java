package com.example.whatsappfirebase.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappfirebase.R;
import com.example.whatsappfirebase.databinding.ListOfCallsItemBinding;
import com.example.whatsappfirebase.viewpager.adapter.CallsAdapter;
import com.example.whatsappfirebase.viewpager.data.Calls;

import java.util.ArrayList;
import java.util.List;

public class CallsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ListOfCallsItemBinding binding = ListOfCallsItemBinding.inflate(inflater, container, false);

        List<Calls> calls = getCalls();
        RecyclerView recyclerView = binding.recyclerStatusList;
        recyclerView.setAdapter(new CallsAdapter(calls));

        return binding.getRoot();
    }

    private List<Calls> getCalls() {
        List<Calls> callsArrayList = new ArrayList<>();
        callsArrayList.add(new Calls("Omar Sa3id", "4:01 Am", R.drawable.ic_person, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Ali Sa3id", "3:05 Am", R.drawable.ic_person2, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Muhammed", "4:10 Pm", R.drawable.logo, R.drawable.ic_phone));
        callsArrayList.add(new Calls("My Sis", "5:15 Am", R.drawable.logo, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Mahdy", "10:55 Am", R.drawable.ic_person3, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Tony", "6:21 Am", R.drawable.ic_person2, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Tot's", "2:12 Am", R.drawable.ic_person, R.drawable.ic_phone));
        callsArrayList.add(new Calls("My", "5:11 Am", R.drawable.logo, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Bora3y", "6:01 Am", R.drawable.ic_person2, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Marzouk", "7:11 Am", R.drawable.ic_person, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Omar ", "2:19 Am", R.drawable.logo, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Mazen", "2:10 Am", R.drawable.ic_person2, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Eissa", "4:17 Am", R.drawable.ic_person3, R.drawable.ic_phone));
        callsArrayList.add(new Calls("7amdy", "3:11 Am", R.drawable.ic_person2, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Joumana", "1:11 Am", R.drawable.logo, R.drawable.ic_phone));
        callsArrayList.add(new Calls("Retal", "00:12 Am", R.drawable.ic_person, R.drawable.ic_phone));

        return callsArrayList;
    }
}

