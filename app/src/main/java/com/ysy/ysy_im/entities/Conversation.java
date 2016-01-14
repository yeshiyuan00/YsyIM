package com.ysy.ysy_im.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.ysy.ysy_im.entities.Message.MessageType;
import com.ysy.ysy_im.entities.Message.StatusType;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
@DatabaseTable(tableName = "conversation")
public class Conversation {
    public static final String TIMESTAMP="timestamp";
    public static final String UNREADNUM = "unreadNum";
    public static final String TARGETID = "targetId";
    @DatabaseField(id = true)
    private String targetId;
    @DatabaseField
    private String content;
    @DatabaseField
    private MessageType type;
    @DatabaseField
    private int unreadNum;
    @DatabaseField
    private StatusType status;
    @DatabaseField
    private long timestamp;


    public StatusType getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public String getTargetId() {
        return targetId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public MessageType getType() {
        return type;
    }

    public int getUnreadNum() {
        return unreadNum;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }

    @Override
    public String toString() {
        return targetId + " msg " + content +" unread "+ unreadNum + " status: " + status;
    }
}
