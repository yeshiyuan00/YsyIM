package com.ysy.ysy_im.entities;

import java.io.Serializable;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class Message implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public enum StatusType{ing,done,fail};
    public enum MessageType{plain,audio,image,location,emo};

    private String id;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private String content;
    private Attachment attachment;
    private StatusType status;
    private long timestamp;
    private boolean isRead;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSenderId() {
        return senderId;
    }
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
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
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public StatusType getStatus() {
        return status;
    }
    public void setStatus(StatusType status) {
        this.status = status;
    }

    public static Message test(String id,String senderId, String receiverId){
        Message message = new Message();
        message.setId(id);
        message.setContent("content:" + id);
        message.setStatus(StatusType.ing);
        message.setType(MessageType.plain);
        message.setTimestamp(System.currentTimeMillis());
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        return message;
    }

    @Override
    public String toString() {
        return senderId + " send "+content+" to " + receiverId +" " + status;
    }
    public Conversation copyTo() {
//		TODO
        return null;
    }
}
