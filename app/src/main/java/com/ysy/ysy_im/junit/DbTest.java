package com.ysy.ysy_im.junit;

import android.test.AndroidTestCase;

import com.ysy.ysy_im.IMApplication;
import com.ysy.ysy_im.push.IMPushManager;
import com.ysy.ysy_im.push.PushChanger;
import com.ysy.ysy_im.push.PushWatcher;
import com.ysy.ysy_im.db.ConversationController;
import com.ysy.ysy_im.db.MessageController;
import com.ysy.ysy_im.entities.Conversation;
import com.ysy.ysy_im.entities.Message;
import com.ysy.ysy_im.utilities.Trace;

import java.util.ArrayList;

/**
 * Author: yeshiyuan
 * Date: 1/14/16.
 */
public class DbTest extends AndroidTestCase {
    public static final String SELFID = "Stay";
    public static final String TARGETID = "Will";
    public static final String OTHERID = "Other";

    PushWatcher watcher=new PushWatcher(){
        @Override
        public void messageUpdate(Message message) {
            if(message.getSenderId().equals(SELFID)){
                Trace.d("i sent a message to " + message.getReceiverId());
            }else {
                if(message.getSenderId().equals(TARGETID)){
                    // show on list
                    Trace.d("new message sent by " + message.getSenderId());
                } else {
                    Trace.d("new message sent by others");
                }
            }
        }
    };

    PushWatcher conversationWatcher =new PushWatcher(){
        @Override
        public void messageUpdate(Message message) {
            updateConversation();
        }
    };

    protected void updateConversation(){
        ArrayList<Conversation> conversations = ConversationController.queryAllByTimeDesc();
        if(conversations!=null&&conversations.size()>0){
            for(Conversation conversation:conversations){
                Trace.d(conversation.toString());
            }
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //
        // IMPushManager.getInstance(getContext()).addObserver(conversationWatcher);
        //IMApplication.gContext = getContext();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        //
        // IMPushManager.getInstance(getContext()).removeObserver(conversationWatcher);
    }

    public void testChat() throws Exception{
        IMPushManager.getInstance(getContext()).addObserver(watcher);
        // TODO query db
        ArrayList<Message> messages = MessageController.queryAllByTimeAsc(SELFID, TARGETID);
        if (messages != null && messages.size() > 0) {
            for (Message message : messages) {
                Trace.d(message.toString());
            }
        }
        // TODO new message
        Message message = Message.test("00003", OTHERID, ChatTest.SELFID);
        IMPushManager.getInstance(getContext()).handlePush(message);
        // TODO notify
        IMPushManager.getInstance(getContext()).removeObserver(watcher);
    }

    public void testConversation() throws Exception{
        IMPushManager.getInstance(getContext()).addObserver(conversationWatcher);
        updateConversation();
        Message message = Message.test("00002", OTHERID, ChatTest.SELFID);
        IMPushManager.getInstance(getContext()).handlePush(message);
        IMPushManager.getInstance(getContext()).removeObserver(conversationWatcher);
    }

    public void testBack() throws Exception {
        IMPushManager.getInstance(getContext()).addObserver(watcher);
        ArrayList<Message> messages = MessageController.queryAllByTimeAsc(SELFID, OTHERID);
        if (messages != null && messages.size() > 0) {
            for (Message message : messages) {
                Trace.d(message.toString());
            }
        }

        Message message = Message.test("00001", SELFID, OTHERID);
        message.setStatus(Message.StatusType.ing);
        PushChanger.getInstance().notifyChanged(message);
        IMPushManager.getInstance(getContext()).removeObserver(watcher);

        ConversationController.markAsRead(OTHERID);

        IMPushManager.getInstance(getContext()).addObserver(conversationWatcher);
        updateConversation();

        message.setStatus(Message.StatusType.done);
        PushChanger.getInstance().notifyChanged(message);

        IMPushManager.getInstance(getContext()).removeObserver(conversationWatcher);

    }

}
