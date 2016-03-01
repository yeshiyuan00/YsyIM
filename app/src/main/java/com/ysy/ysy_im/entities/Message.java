package com.ysy.ysy_im.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.ysy.ysy_im.IMApplication;
import com.ysy.ysy_im.db.ConversationController;

import java.io.Serializable;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
@DatabaseTable(tableName = "message")
public class Message implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String TIMESTAMP = "timestamp";
    public static final String SENDERID = "senderId";
    public static final String RECEIVERID = "receiverId";

    public enum StatusType {ing, done, fail}

    ;

    public enum MessageType {txt, multimedia}

    ;

    @DatabaseField(id = true)
    private String _id;
    @DatabaseField
    private String senderId;
    @DatabaseField
    private String sender_name;
    @DatabaseField
    private String sender_picture;
    @DatabaseField
    private String receiverId;
    @DatabaseField
    private String receiver_name;
    @DatabaseField
    private String receiver_picture;
    @DatabaseField
    private MessageType type;
    @DatabaseField
    private String content;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attachment attachment;
    @DatabaseField
    private StatusType status;
    @DatabaseField
    private long timestamp;
    @DatabaseField
    private boolean isRead;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }


    public String getSender_name() {
        return sender_name;
    }


    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_picture() {
        return sender_picture;
    }


    public void setSender_picture(String sender_picture) {
        this.sender_picture = sender_picture;
    }

    public String getReceiverId() {
        return receiverId;
    }


    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_picture() {
        return receiver_picture;
    }

    public void setReceiver_picture(String receiver_picture) {
        this.receiver_picture = receiver_picture;
    }


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }


    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public static Message test(String id, String senderId, String receiverId) {
        Message message = new Message();
        message.set_id(id);
        message.setContent("content:" + id);
        message.setStatus(StatusType.ing);
        message.setType(MessageType.txt);
        message.setTimestamp(System.currentTimeMillis());
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        return message;
    }

    @Override
    public String toString() {
        return sender_name + " send " + content + " to " + receiver_name + " " + status;
    }

    public Conversation copyTo() {
        Conversation conversation=new Conversation();
        conversation.setContent(getContent());
        conversation.setStatus(getStatus());
        conversation.setTimestamp(getTimestamp());
        conversation.setType(getType());
        if(!getSenderId().equals(IMApplication.selfId)){
            conversation.setTargetId(getSenderId());
            conversation.setTargetName(getSender_name());
            conversation.setTargetPicture(getSender_picture());
            Conversation tmp= ConversationController.querybyId(conversation.getTargetId());
            conversation.setUnreadNum(tmp==null?1:tmp.getUnreadNum()+1);
        }else{
            conversation.setTargetId(getReceiverId());
            conversation.setTargetName(getReceiver_name());
            conversation.setTargetPicture(getReceiver_picture());
            Conversation tmp= ConversationController.querybyId(conversation.getTargetId());
            conversation.setUnreadNum(tmp == null ? 1 : tmp.getUnreadNum() + 1);
        }
        return conversation;
    }
}
