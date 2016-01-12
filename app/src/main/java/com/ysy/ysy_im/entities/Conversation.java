package com.ysy.ysy_im.entities;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class Conversation {
    private String messageId;
    private String content;
    private Message.MessageType type;
    private String TargetId;
    private int unreadNum;
    private Message.StatusType status;
    private long timestamp;
}
