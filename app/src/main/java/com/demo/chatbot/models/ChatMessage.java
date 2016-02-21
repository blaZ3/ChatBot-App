package com.demo.chatbot.models;

/**
 * Created by root on 21/2/16.
 */
public class ChatMessage {

    public static int MSG_SENT = 1;
    public static int MSG_RECIEVED = 2;

    private long id;
    private int type;
    private String message;
    private String senderName;
    private int senderId;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
