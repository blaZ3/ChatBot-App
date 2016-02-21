package com.demo.chatbot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.chatbot.models.ChatMessage;

import java.util.ArrayList;

/**
 * Created by root on 21/2/16.
 */
public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {


    ArrayList<ChatMessage> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtRecieved, txtSent;
        public LinearLayout layoutSent,layoutRecieved;
        public ViewHolder(View v) {
            super(v);
            txtRecieved = (TextView) v.findViewById(R.id.txt_recieved);
            txtSent = (TextView) v.findViewById(R.id.txt_sent);

            layoutRecieved = (LinearLayout) v.findViewById(R.id.layout_recieved);
            layoutSent = (LinearLayout) v.findViewById(R.id.layout_sent);

        }
    }

    public ChatMessageAdapter(@NonNull ArrayList<ChatMessage> _list){
        this.items = _list;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_chat_bubble, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage msg = items.get(position);
        if (msg.getType() == ChatMessage.MSG_RECIEVED){
            holder.layoutSent.setVisibility(View.GONE);
            holder.layoutRecieved.setVisibility(View.VISIBLE);

            holder.txtRecieved.setText(msg.getMessage());
        }else{
            holder.layoutRecieved.setVisibility(View.GONE);
            holder.layoutSent.setVisibility(View.VISIBLE);

            holder.txtSent.setText(msg.getMessage());
        }
    }
}
