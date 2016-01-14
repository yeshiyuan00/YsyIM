package com.ysy.ysy_im.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ysy.ysy_im.entities.Conversation;
import com.ysy.ysy_im.entities.Message;

import java.sql.SQLException;

/**
 * Author: yeshiyuan
 * Date: 1/14/16.
 */
public class OrmDBHelper extends OrmLiteSqliteOpenHelper {
    public static final String DB_NAME = "stay4it";
    public static final int DB_VERSION = 1;

    public OrmDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    public OrmDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Message.class);
            TableUtils.createTable(connectionSource, Conversation.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
