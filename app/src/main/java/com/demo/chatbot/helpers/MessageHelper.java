package com.demo.chatbot.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.demo.chatbot.models.ChatMessage;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by root on 19/9/15.
 */
public class MessageHelper {

    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private static MessageHelper instance;
    Context context;

    public static MessageHelper getInstance(Context _context) {
        if (instance == null) {
            instance = new MessageHelper(_context);
        }
        return instance;
    }

    private MessageHelper(Context _context){
        context = _context;
        dbHelper = new DBHelper(_context);
        try{
            this.open();
        }catch (Exception ex){}
    }

    public void open() throws SQLException {
        if (db == null || !db.isOpen()) {
            db = dbHelper.getWritableDatabase();
        }
    }

    public boolean isOpen() {
        return db != null && db.isOpen();
    }

    public long save(ChatMessage msg){
        ContentValues values = new ContentValues();
        values.put(DBHelper.MessageColumns.MSG_TYPE, msg.getType());
        values.put(DBHelper.MessageColumns.MSG_MESSAGE, msg.getMessage());
        values.put(DBHelper.MessageColumns.MSG_SENT, 0);

        try{
            long insertId = db.insert(DBHelper.Tables.MESSAGES, null, values);
            if (insertId == -1) {
                return -1;
            }else {
                return insertId;
            }
        }catch (Exception ex){
            return -1;
        }
    }

    public boolean setAsSynced(int id){
        ContentValues values = new ContentValues();
        values.put(DBHelper.MessageColumns.MSG_SENT, 1);

        try{
            int rows_affected = db.update(DBHelper.Tables.MESSAGES,
                    values,
                    DBHelper.BaseColumns._ID+"=" + id, null);
            if (rows_affected > 0) {
                return true;
            }else {
                return false;
            }
        }catch (Exception ex){
            return false;
        }
    }

    public ArrayList<ChatMessage> getMessages(){
        ArrayList<ChatMessage> messages = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.Tables.MESSAGES
                + " ORDER BY "
                + DBHelper.BaseColumns._ID + " ASC", null);
        while (cursor.moveToNext()){
            ChatMessage message = new ChatMessage();
            message.setId(cursor.getInt(0));
            message.setType(cursor.getInt(1));
            message.setMessage(cursor.getString(3));

            messages.add(message);
        }

        return messages;
    }

    public ArrayList<ChatMessage> getMessagesToSync(){
        ArrayList<ChatMessage> messages = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.Tables.MESSAGES
                + " WHERE "+DBHelper.MessageColumns.MSG_SENT+" = 0 ", null);
        while (cursor.moveToNext()){
            ChatMessage message = new ChatMessage();
            message.setId(cursor.getInt(0));
            message.setType(cursor.getInt(1));
            message.setMessage(cursor.getString(3));

            messages.add(message);
        }

        return messages;
    }

}
