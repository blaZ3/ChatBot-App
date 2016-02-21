package com.demo.chatbot.models;

/**
 * Created by root on 21/2/16.
 */
public class ChatMessage extends Message {

    public static int MSG_SENT = 1;
    public static int MSG_RECIEVED = 1;

    private int type;


    public ChatMessage(){

    }

    public ChatMessage(int _type, String _msg){
        this.type = _type;
        this.message = _msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
