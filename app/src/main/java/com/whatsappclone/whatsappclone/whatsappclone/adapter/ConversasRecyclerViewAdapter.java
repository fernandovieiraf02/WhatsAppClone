package com.whatsappclone.whatsappclone.whatsappclone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.whatsappclone.whatsappclone.R;
import com.whatsappclone.whatsappclone.whatsappclone.activity.MainActivity;
import com.whatsappclone.whatsappclone.whatsappclone.fragment.ChatFragment;

public class ConversasRecyclerViewAdapter extends RecyclerView.Adapter<ConversasRecyclerViewAdapter.CustomViewHolder>{

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_card, viewGroup, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        customViewHolder.getTitulo().setText("Titulo de conversa");
        customViewHolder.getPreview().setText("Preview de conversa");
        customViewHolder.getHora().setText("09:31");
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo, preview, hora;
        private View itemView;
        private ChatFragment chatFragment;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.titulo = itemView.findViewById(R.id.tv_titulo);
            this.preview = itemView.findViewById(R.id.tv_preview);
            this.hora = itemView.findViewById(R.id.tv_hora);
            this.itemView = itemView;
            chatFragment = new ChatFragment();

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fl_frame, chatFragment);
                fragmentTransaction.commit();
                }
            });
        }

        public TextView getTitulo() {
            return titulo;
        }

        public void setTitulo(TextView titulo) {
            this.titulo = titulo;
        }

        public TextView getPreview() {
            return preview;
        }

        public void setPreview(TextView preview) {
            this.preview = preview;
        }

        public TextView getHora() {
            return hora;
        }

        public void setHora(TextView hora) {
            this.hora = hora;
        }
    }
}
