package com.whatsappclone.whatsappclone.whatsappclone.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.activity.MainActivity;
import com.whatsappclone.whatsappclone.whatsappclone.adapter.ChatRecyclerViewAdapter;
import com.whatsappclone.whatsappclone.whatsappclone.threads.SendMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatRecyclerViewAdapter chatAdapter;
    private ImageView arrowBack, sendButton;
    private EditText et_message;
    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        arrowBack = view.findViewById(R.id.iv_back);
        backClickListener();

        sendButton = view.findViewById(R.id.id_send);
        sendMessage();

        et_message = view.findViewById(R.id.et_message);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        chatAdapter = new ChatRecyclerViewAdapter();
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

    private void sendMessage() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.send(et_message.getText().toString());
            }
        });
    }

}
