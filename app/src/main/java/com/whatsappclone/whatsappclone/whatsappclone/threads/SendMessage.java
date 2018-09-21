package com.whatsappclone.whatsappclone.whatsappclone.threads;

import com.whatsappclone.whatsappclone.whatsappclone.fragment.ChatFragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SendMessage implements Runnable {

    private static final int SERVERPORT = 3030;
    private static final String SERVER_IP = "192.168.2.88";
    private ArrayList<String> payloadList;
    private Socket socket;
    private InputMessage inputTask;
    private RefleshUI refleshTask;
    private DataOutputStream os;
    private BufferedReader buffer;
    private Semaphore semaphore;

    public SendMessage() {
        payloadList = new ArrayList<>();
        os = null;
        inputTask = null;
        buffer = null;
        refleshTask = new RefleshUI();
        semaphore = new Semaphore(1);
    }

    public ArrayList<String> getPayloadList() {
        return payloadList;
    }

    public void addPayload(String payload) {
        this.payloadList.add(payload);
    }

    private void refleshUI() {
        ChatFragment.fragmentActivity.runOnUiThread(refleshTask);
    }

    @Override
    public void run() {
        try {
            socket = new Socket(SERVER_IP, SERVERPORT);
            os = new DataOutputStream(socket.getOutputStream());
            inputTask = new InputMessage(socket);
            new Thread(inputTask).start();
        } catch (IOException e) {
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
                        }
                    }
                } else if (sizeList == 0) {
                    index = 0;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class RefleshUI implements Runnable {
        @Override
        public void run() {
            ChatFragment.chatAdapter.notifyDataSetChanged();
        }
    }

    private class InputMessage implements Runnable {
        private Socket socket;

        public InputMessage(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    if (buffer.ready()) {
                        ChatFragment.chatList.add(buffer.readLine());
                        refleshUI();
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}