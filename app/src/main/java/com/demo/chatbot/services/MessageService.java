package com.demo.chatbot.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.demo.chatbot.api.ChatBotApi;
import com.demo.chatbot.helpers.MessageHelper;
import com.demo.chatbot.models.ChatMessage;

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


    private void handleActionSync(String msg_id, String msg) {
        ChatMessage message = new ChatMessage();
        message.setId(Integer.parseInt(msg_id));
        message.setMessage(msg);

        ChatBotApi.getInstance(this).sendMessage(message);
        MessageHelper.getInstance(this).setAsSynced(Integer.parseInt(msg_id));
    }
}
