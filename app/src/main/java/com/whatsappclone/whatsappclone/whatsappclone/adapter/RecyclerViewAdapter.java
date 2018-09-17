package com.whatsappclone.whatsappclone.whatsappclone.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whatsappclone.whatsappclone.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>{

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_card, viewGroup, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.titulo.setText("Titulo de conversa");
        customViewHolder.preview.setText("Preview de conversa");
        customViewHolder.hora.setText("09:31");
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView titulo, preview, hora;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.tv_titulo);
            this.preview = itemView.findViewById(R.id.tv_preview);
            this.hora = itemView.findViewById(R.id.tv_hora);
        }
    }
}
