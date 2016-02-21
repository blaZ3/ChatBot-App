package com.demo.chatbot.api;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.demo.chatbot.helpers.ChatBotStatics;
import com.demo.chatbot.interfaces.ChatBotInterface;
import com.demo.chatbot.models.pojo.BotResponse;
import com.demo.chatbot.models.ChatMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by root on 21/2/16.
 */
public class MessageApi {

    ChatBotInterface callback;

    String TAG = "MessageApi";

    public MessageApi(){
    }

    public void sendMessage(final ChatMessage message){
        // Instantiate the RequestQueue.
        RequestQueue queue = ChatBotApi.queue;

        String url ="http://www.personalityforge.com/api/chat?" +
                "apiKey=_API_KEY_&" +
                "message=_MESSAGE_&" +
                "chatBotID=_CHAT_BOT_ID_&" +
                "externalID=_EXT_ID_";

        url = url.replace("_API_KEY_", ChatBotStatics.API_KEY);
        url = url.replace("_MESSAGE_", message.getMessage());
        url = url.replace("_CHAT_BOT_ID_", ChatBotStatics.CHAT_BOT_ID);
        url = url.replace("_EXT_ID_", ChatBotStatics.EXTERNAL_ID);


        // Request a string response from the provided URL.
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.replace("\n\n\n\n", "");
                        Gson gson = new GsonBuilder().serializeNulls().create();
                        BotResponse botResponse = gson.fromJson(response, BotResponse.class);
                        if (callback != null){
                            callback.gotResponse(Integer.parseInt(String.valueOf(message.getId())),
                                    botResponse);
                        }
                        Log.d(TAG, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work!");
                if(callback != null){
                    callback.onError(message);
                }
            }
        });

        // Add the request to the RequestQueue.
        queue.add(request);
    }


    public ChatBotInterface getCallback() {
        return callback;
    }

    public void setCallback(ChatBotInterface callback) {
        this.callback = callback;
    }
}
