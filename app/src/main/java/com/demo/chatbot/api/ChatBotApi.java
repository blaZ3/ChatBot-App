package com.demo.chatbot.api;

/**
 * Created by root on 21/2/16.
 */
public class ChatBotApi {

    private ChatBotApi instance;


    private ChatBotApi(){

    }


    public ChatBotApi getInstance(){
        if (this.instance == null){
            this.instance = new ChatBotApi();
        }
        return instance;
    }

}
