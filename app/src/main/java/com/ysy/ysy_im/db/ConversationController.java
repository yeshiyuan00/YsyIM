package com.ysy.ysy_im.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.ysy.ysy_im.IMApplication;
import com.ysy.ysy_im.entities.Conversation;
import com.ysy.ysy_im.entities.Message;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Author: yeshiyuan
 * Date: 1/14/16.
 */
public class ConversationController {
    public static Dao<Conversation, String> getDao() throws SQLException {
        return DBController.getInstance().getDB().getDao(Conversation.class);
    }

    public static void addOrUpdata(Conversation conversation) {
        try {
            getDao().createOrUpdate(conversation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Conversation conversation) {
        try {
            getDao().delete(conversation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conversation querybyId(String id) {
        try {
            return getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Conversation> queryAllByTimeDesc() {
        ArrayList<Conversation> conversations = new ArrayList<Conversation>();
        try {
            QueryBuilder<Conversation, String> queryBuilder = getDao().queryBuilder();
            queryBuilder.orderBy(Conversation.TIMESTAMP, false);
            conversations = (ArrayList<Conversation>) getDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conversations;
    }

    public static void syncMessage(Message message) {
        addOrUpdata(message.copyTo());
        }

    public static void markAsRead(String targetId){
        try {
            getDao().executeRaw("UPDATE conversation SET"+
                    Conversation.UNREADNUM+" =0 WHERE "+Conversation.TARGETID+"=?",targetId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
