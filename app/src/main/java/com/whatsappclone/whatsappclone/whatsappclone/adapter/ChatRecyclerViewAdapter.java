package com.whatsappclone.whatsappclone.whatsappclone.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whatsappclone.whatsappclone.R;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.CustomViewHolder>{

    @NonNull
    @Override
    public ChatRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item_view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view_chat, viewGroup, false);
        return new CustomViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerViewAdapter.CustomViewHolder customViewHolder, int i) {
        customViewHolder.getNameChat().setText("Fulano");
        customViewHolder.getNumberChat().setText("(67)99665-3345");
        customViewHolder.getTextChat().setText("Olá, tudo bem ?");
        customViewHolder.getTimeChat().setText("09:34");
    }

    @Override
    public int getItemCount() {
        return 17;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textChat, numberChat, timeChat, nameChat;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.nameChat = itemView.findViewById(R.id.tv_user_chat);
            this.numberChat = itemView.findViewById(R.id.tv_number);
            this.timeChat = itemView.findViewById(R.id.tv_time_chat);
            this.textChat = itemView.findViewById(R.id.tv_chat);
        }

        public TextView getTextChat() {
            return textChat;
        }

        public void setTextChat(TextView textChat) {
            this.textChat = textChat;
        }

        public TextView getNumberChat() {
            return numberChat;
        }

        public void setNumberChat(TextView numberChat) {
            this.numberChat = numberChat;
        }

        public TextView getTimeChat() {
            return timeChat;
        }

        public void setTimeChat(TextView timeChat) {
            this.timeChat = timeChat;
        }

        public TextView getNameChat() {
            return nameChat;
        }

        public void setNameChat(TextView nameChat) {
            this.nameChat = nameChat;
        }
    }
}
