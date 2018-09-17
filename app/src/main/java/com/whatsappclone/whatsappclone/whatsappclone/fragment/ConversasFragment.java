package com.whatsappclone.whatsappclone.whatsappclone.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.adapter.RecyclerViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversasFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter rv_adapter;

    public ConversasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversas, container, false);
        recyclerView = view.findViewById(R.id.rv_conversas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        rv_adapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rv_adapter);
        return view;
    }
}
