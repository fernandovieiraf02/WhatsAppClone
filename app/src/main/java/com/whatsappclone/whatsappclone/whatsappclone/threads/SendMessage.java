package com.whatsappclone.whatsappclone.whatsappclone.threads;

import com.whatsappclone.whatsappclone.whatsappclone.activity.MainActivity;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.ChatFragment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SendMessage implements Runnable {

    private static final int SERVERPORT = 3030;
    private static final String SERVER_IP = "192.168.2.88";
    private ArrayList<String> payloadList;
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;
    private BufferedReader buffer;

    public SendMessage() {
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
            buffer = null;
            os = new DataOutputStream(socket.getOutputStream());
            //is = new DataInputStream(socket.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
        String message = null;
        int sizeList = 0;
        int index = 0;
        try {
            while(true) {
                sizeList = payloadList.size();
                if(index < sizeList) {
                    message = payloadList.get(index);
                    index++;
                    if (socket != null && os != null) {
                        if (message != null) {
                            os.writeBytes(message);
                            os.flush();

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                try {
                                    buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                    while (true) {
                                        if (buffer.ready()) {
                                            ChatFragment.chatList.add(buffer.readLine());
                                            ChatFragment.fragmentActivity.runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    ChatFragment.chatAdapter.notifyDataSetChanged();
                                                }
                                            });
                                            break;
                                        }
                                    }
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                }
                            }).start();

                            /*getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    chatAdapter.notifyDataSetChanged();
                                }
                            });*/
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