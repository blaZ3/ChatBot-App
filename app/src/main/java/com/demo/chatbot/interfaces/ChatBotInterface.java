package com.demo.chatbot.interfaces;

import com.demo.chatbot.models.ChatMessage;
import com.demo.chatbot.models.pojo.BotResponse;

/**
 * Created by root on 21/2/16.
 */
public interface ChatBotInterface {
    public void gotResponse(int id, BotResponse response);
    public void onError(ChatMessage msg);
}
