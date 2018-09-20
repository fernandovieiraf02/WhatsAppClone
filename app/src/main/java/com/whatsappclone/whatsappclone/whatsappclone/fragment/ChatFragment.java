package com.whatsappclone.whatsappclone.whatsappclone.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private static final int SERVERPORT = 3030;
    private static final String SERVER_IP = "192.168.2.88";
    private ArrayList<String> chatList;
    private Socket socket;
    private RecyclerView recyclerView;
    private ChatRecyclerViewAdapter chatAdapter;
    private ImageView arrowBack, sendButton;
    private EditText et_message;
    Thread thread;
    SendTask sendTask;
    BufferedReader bufferedReader;
    PrintWriter writer;
    boolean started;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatList = new ArrayList<>();
        sendTask = new SendTask();
        thread = new Thread(sendTask);
        thread.start();

        arrowBack = view.findViewById(R.id.iv_back);
        backClickListener();

        sendButton = view.findViewById(R.id.id_send);
        sendMessage();

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

    private void sendMessage() {
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

    private class SendTask implements Runnable {

        private ArrayList<String> payloadList;
        private Socket socket;
        private DataInputStream is;
        private DataOutputStream os;

        public SendTask() {
            payloadList = new ArrayList<>();
        }

        public ArrayList<String> getPayloadList() {
            return payloadList;
        }

        public void addPayload(String payload) {
            this.payloadList.add(payload);
        }

        @Override
        public void run() {
            try {
                socket = new Socket(SERVER_IP, SERVERPORT);
                os = null;
                is = null;
                os = new DataOutputStream(socket.getOutputStream());
                is = new DataInputStream(socket.getInputStream());
            } catch(IOException e) {
                e.printStackTrace();
            }
            String message = null;
            int sizeList = 0;
            int index = 0;
            try {
                while(true) {
                    sizeList = payloadList.size();
                    if(sizeList > 0) {
                        message = payloadList.get(index);
                        index++;
                        if (socket != null && os != null && is != null) {
                            if (message != null) {
                                os.writeBytes(message);
                                os.flush();
                                chatList.add(is.readLine());
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    }
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
