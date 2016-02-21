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
import com.demo.chatbot.interfaces.ChatBotInterface;
import com.demo.chatbot.models.BotResponse;

public class MainActivity extends AppCompatActivity implements ChatBotInterface {

    String TAG = "MainActivity";

    RecyclerView recyclerChat;

    EditText editTextMessage;
    ImageButton btnSend;

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

    }

    View.OnClickListener doSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextMessage.getText().toString().length() > 0){
                ChatBotApi.getInstance(MainActivity.this).sendMessage(editTextMessage.getText().toString());
            }else{
                Log.d(TAG, "Empty message");
            }
        }
    };

    @Override
    public void gotResponse(BotResponse response) {
        Toast.makeText(getApplicationContext(), response.getMessage().getMessage(), Toast.LENGTH_SHORT).show();
    }
}
