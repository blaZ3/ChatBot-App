
package com.demo.chatbot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Message {

    @SerializedName("chatBotName")
    @Expose
    private String chatBotName;
    @SerializedName("chatBotID")
    @Expose
    private Integer chatBotID;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("emotion")
    @Expose
    private Object emotion;

    /**
     * 
     * @return
     *     The chatBotName
     */
    public String getChatBotName() {
        return chatBotName;
    }

    /**
     * 
     * @param chatBotName
     *     The chatBotName
     */
    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * 
     * @return
     *     The chatBotID
     */
    public Integer getChatBotID() {
        return chatBotID;
    }

    /**
     * 
     * @param chatBotID
     *     The chatBotID
     */
    public void setChatBotID(Integer chatBotID) {
        this.chatBotID = chatBotID;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The emotion
     */
    public Object getEmotion() {
        return emotion;
    }

    /**
     * 
     * @param emotion
     *     The emotion
     */
    public void setEmotion(Object emotion) {
        this.emotion = emotion;
    }

}
