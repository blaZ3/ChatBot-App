package com.demo.chatbot.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.chatbot.api.ChatBotApi;
import com.demo.chatbot.helpers.ChatBotStatics;
import com.demo.chatbot.helpers.MessageHelper;
import com.demo.chatbot.models.ChatMessage;
import com.demo.chatbot.models.pojo.BotResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MessageService extends IntentService {

    private static final String ACTION_SYNC = "com.demo.chatbot.services.action.sync";


    private static final String EXTRA_MSG_ID = "com.demo.chatbot.services.extra.MSGID";
    private static final String EXTRA_MSG = "com.demo.chatbot.services.extra.MSG";

    public MessageService() {
        super("MessageService");
    }


    public static void startActionSync(Context context, String msg_id, String msg) {
        Intent intent = new Intent(context, MessageService.class);
        intent.setAction(ACTION_SYNC);
        intent.putExtra(EXTRA_MSG_ID, msg_id);
        intent.putExtra(EXTRA_MSG, msg);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SYNC.equals(action)) {
                final String id = intent.getStringExtra(EXTRA_MSG_ID);
                final String msg = intent.getStringExtra(EXTRA_MSG);
                handleActionSync(id, msg);
            }
        }
    }


    private void handleActionSync(final String msg_id, String msg) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());;
        String url = ChatBotStatics.getUrl(msg);
        // Request a string response from the provided URL.
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MessageHelper.getInstance(getApplicationContext()).setAsSynced(Integer.parseInt(msg_id));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(request);

    }
}


