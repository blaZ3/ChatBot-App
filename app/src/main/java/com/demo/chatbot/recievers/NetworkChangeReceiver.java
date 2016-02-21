package com.demo.chatbot.recievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.demo.chatbot.api.ChatBotApi;
import com.demo.chatbot.helpers.MessageHelper;
import com.demo.chatbot.models.ChatMessage;
import com.demo.chatbot.services.MessageService;

import java.util.ArrayList;

public class NetworkChangeReceiver extends BroadcastReceiver {
    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable()) {
            // Do something
            ArrayList<ChatMessage> messagesToSync = MessageHelper.getInstance(context).getMessagesToSync();
            Toast.makeText(context, "Network came back syncing unsent messages:" + messagesToSync.size(), Toast.LENGTH_SHORT).show();
            for (ChatMessage message :messagesToSync) {
                MessageService.startActionSync(context,
                        String.valueOf(message.getId()),
                        message.getMessage());
            }


            
        }
    }
}
