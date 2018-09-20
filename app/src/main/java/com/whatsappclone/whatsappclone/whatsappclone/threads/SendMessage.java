package com.whatsappclone.whatsappclone.whatsappclone.threads;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.whatsappclone.whatsappclone.whatsappclone.fragment.ChatFragment;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SendMessage {


    private String message;
}