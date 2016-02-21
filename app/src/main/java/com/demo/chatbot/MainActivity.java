package com.demo.chatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.demo.chatbot.api.ChatBotApi;
import com.demo.chatbot.helpers.MessageHelper;
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

    MessageHelper messageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageHelper = MessageHelper.getInstance(MainActivity.this);

        recyclerChat = (RecyclerView)findViewById(R.id.recycler_chat);
        editTextMessage = (EditText) findViewById(R.id.editTxtMessage);
        btnSend = (ImageButton) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(doSend);

        recyclerChat.setHasFixedSize(true);
        recyclerChat.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        messages = messageHelper.getMessages();
        chatMessageAdapter = new ChatMessageAdapter(messages);
        recyclerChat.setAdapter(chatMessageAdapter);
        if(messages.size()>0){
            recyclerChat.smoothScrollToPosition(messages.size()-1);
        }
    }

    View.OnClickListener doSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextMessage.getText().toString().length() > 0){
                ChatMessage msg = new ChatMessage(
                        ChatMessage.MSG_SENT,
                        editTextMessage.getText().toString()
                );

                //add message
                messages.add(msg);
                msg.setId(messageHelper.save(msg));

                //update chat list
                chatMessageAdapter.notifyDataSetChanged();
                recyclerChat.smoothScrollToPosition(messages.size()-1);

                //clear edit text
                editTextMessage.setText("");

                //send to server
                ChatBotApi.getInstance(MainActivity.this).sendMessage(msg);
            }else{
                Log.d(TAG, "Empty message");
            }
        }
    };


    @Override
    public void gotResponse(int id, BotResponse response) {
        ChatMessage msg = new ChatMessage(
                ChatMessage.MSG_RECIEVED,
                response.getMessage().getMessage()
        );
        messageHelper.setAsSynced(id);
        try{
            getSupportActionBar().setTitle(response.getMessage().getChatBotName());
        }catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }

        //add message
        messages.add(msg);
        messageHelper.save(msg);


        //update chat list
        chatMessageAdapter.notifyDataSetChanged();
        recyclerChat.smoothScrollToPosition(messages.size()-1);
    }

    @Override
    public void onError(ChatMessage msg) {
        Toast.makeText(getApplicationContext(), "Failed to send message", Toast.LENGTH_SHORT).show();
    }
}
