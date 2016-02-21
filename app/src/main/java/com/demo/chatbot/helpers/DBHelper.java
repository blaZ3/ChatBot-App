package com.demo.chatbot.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 19/9/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DB_Helper";
    private static final String DATABASE_NAME = "chatbot.db";
    private static final int CURR_DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, CURR_DB_VERSION);
    }

    public interface Tables {
        String MESSAGES = "messages";
    }

    public interface BaseColumns {
        String _ID = "_id";
    }

    public interface MessageColumns {
        String MSG_TYPE = "message_type";
        String MSG_MESSAGE = "message";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(
                    "CREATE TABLE " + Tables.MESSAGES + " ("
                            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + MessageColumns.MSG_TYPE + " INTEGER NOT NULL,"
                            + MessageColumns.MSG_MESSAGE + " TEXT NOT NULL)"
            );
        }catch (Exception ex){}

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);

        //check and make and schema changes

        onCreate(db);
    }
}
