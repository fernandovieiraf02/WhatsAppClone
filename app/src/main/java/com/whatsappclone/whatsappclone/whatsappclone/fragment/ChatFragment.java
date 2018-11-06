package com.whatsappclone.whatsappclone.whatsappclone.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.App;
import com.whatsappclone.whatsappclone.whatsappclone.activity.MainActivity;
import com.whatsappclone.whatsappclone.whatsappclone.adapter.ChatRecyclerViewAdapter;
import com.whatsappclone.whatsappclone.whatsappclone.threads.SendMessage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    public static ArrayList<String> chatList;
    private RecyclerView recyclerView;
    private ImageView arrowBack, sendButton;
    private EditText et_message;
    Thread thread;
    SendMessage sendTask;
    public static FragmentActivity fragmentActivity;
    public static ChatRecyclerViewAdapter chatAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        Tracker t = ((App) getActivity().getApplication()).getDefaultTracker();
        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder()
                .setCategory("TESTE")
                .setAction("OPEN CHAT")
                .setLabel("TEST")
                .build());

        fragmentActivity = getActivity();
        chatList = new ArrayList<>();
        sendTask = new SendMessage();
        thread = new Thread(sendTask);
        thread.start();

        arrowBack = view.findViewById(R.id.iv_back);
        backClickListener();

        sendButton = view.findViewById(R.id.id_send);
        sendAction();

        et_message = view.findViewById(R.id.et_message);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        chatAdapter = new ChatRecyclerViewAdapter(chatList);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_chat);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatAdapter);
        return view;
    }

    private void backClickListener() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
            fragmentTransaction.remove( MainActivity.fragmentManager.getFragments()
                    .get( MainActivity.fragmentManager.getFragments().size() - 1) );
            fragmentTransaction.commit();
            }
        });
    }

    private void sendAction() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_message.getText().toString();
                if(text != null && thread != null) {
                    sendTask.addPayload(text);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
