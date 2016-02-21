package com.demo.chatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.demo.chatbot.api.ChatBotApi;
import com.demo.chatbot.interfaces.ChatBotInterface;
import com.demo.chatbot.models.ChatMessage;
import com.demo.chatbot.models.pojo.BotResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChatBotInterface {

    String TAG = "MainActivity";

    RecyclerView recyclerChat;

    EditText editTextMessage;
    ImageButton btnSend;

    ArrayList<ChatMessage> messages;

    ChatMessageAdapter chatMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerChat = (RecyclerView)findViewById(R.id.recycler_chat);
        editTextMessage = (EditText) findViewById(R.id.editTxtMessage);
        btnSend = (ImageButton) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(doSend);

        recyclerChat.setHasFixedSize(true);
        recyclerChat.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        messages = new ArrayList<>();
        chatMessageAdapter = new ChatMessageAdapter(messages);
        recyclerChat.setAdapter(chatMessageAdapter);
    }

    View.OnClickListener doSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextMessage.getText().toString().length() > 0){
                ChatMessage msg = new ChatMessage(
                        ChatMessage.MSG_SENT,
                        editTextMessage.getText().toString()
                );
                messages.add(msg);
                chatMessageAdapter.notifyDataSetChanged();
                recyclerChat.smoothScrollToPosition(messages.size()-1);
                editTextMessage.setText("");
                ChatBotApi.getInstance(MainActivity.this).sendMessage(msg);
            }else{
                Log.d(TAG, "Empty message");
            }
        }
    };

    @Override
    public void gotResponse(BotResponse response) {
        ChatMessage msg = new ChatMessage(
                ChatMessage.MSG_RECIEVED,
                response.getMessage().getMessage()
        );
        try{
            getSupportActionBar().setTitle(response.getMessage().getChatBotName());
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
        messages.add(msg);
        chatMessageAdapter.notifyDataSetChanged();
        recyclerChat.smoothScrollToPosition(messages.size()-1);
    }
}
