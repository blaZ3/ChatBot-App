package com.demo.chatbot.helpers;

/**
 * Created by root on 21/2/16.
 */
public class ChatBotStatics {

    public static String API_ROOT = "http://www.personalityforge.com/api";
    public static String API_ENDPOINT_CHAT = "/chat";


    public static String API_KEY = "6nt5d1nJHkqbkphe";
    public static String CHAT_BOT_ID = "63906";
    public static String EXTERNAL_ID = "abc";

    public static String getUrl(String message){
        String url ="http://www.personalityforge.com/api/chat?" +
                "apiKey=_API_KEY_&" +
                "message=_MESSAGE_&" +
                "chatBotID=_CHAT_BOT_ID_&" +
                "externalID=_EXT_ID_";

        url = url.replace("_API_KEY_", ChatBotStatics.API_KEY);
        url = url.replace("_MESSAGE_", message);
        url = url.replace("_CHAT_BOT_ID_", ChatBotStatics.CHAT_BOT_ID);
        url = url.replace("_EXT_ID_", ChatBotStatics.EXTERNAL_ID);

        return url;
    }

}
