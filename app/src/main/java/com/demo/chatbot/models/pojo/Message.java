
package com.demo.chatbot.models.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Message{

    @SerializedName("chatBotName")
    @Expose
    protected String chatBotName;
    @SerializedName("chatBotID")
    @Expose
    protected Integer chatBotID;
    @SerializedName("message")
    @Expose
    protected String message;
    @SerializedName("emotion")
    @Expose
    protected Object emotion;

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
