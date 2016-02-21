package com.demo.chatbot.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.demo.chatbot.interfaces.ChatBotInterface;
import com.demo.chatbot.models.BotResponse;
import com.demo.chatbot.models.ChatMessage;

/**
 * Created by root on 21/2/16.
 */
public class ChatBotApi {

    static ChatBotApi instance;
    static RequestQueue queue;

    MessageApi messageApi;

    private ChatBotApi(){
        messageApi = new MessageApi();
    }


    public static ChatBotApi getInstance(Context _context){
        if (instance == null){
            instance = new ChatBotApi();
            instance.messageApi.setCallback((ChatBotInterface)_context);
            queue = Volley.newRequestQueue(_context);
        }
        return instance;
    }


    public BotResponse sendMessage(String _msg){
        messageApi.sendMessage(new ChatMessage(
                ChatMessage.MSG_SENT,
                _msg
        ));
        return null;
    }

}
