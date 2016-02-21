
package com.demo.chatbot.models.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BotResponse {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("message")
    @Expose
    private Message message;

    /**
     * 
     * @return
     *     The success
     */
    public Integer getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(Integer success) {
        this.success = success;
    }

    /**
     * 
     * @return
     *     The errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage
     *     The errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 
     * @return
     *     The message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(Message message) {
        this.message = message;
    }

}
