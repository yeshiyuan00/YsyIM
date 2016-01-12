package com.ysy.ysy_im.junit;


import android.test.AndroidTestCase;

import com.ysy.ysy_im.PushChanger;
import com.ysy.ysy_im.PushManager;
import com.ysy.ysy_im.PushWatcher;
import com.ysy.ysy_im.entities.Message;
import com.ysy.ysy_im.utilities.Trace;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class ChatTest extends AndroidTestCase {

    public static final String SELFID = "ysy";
    public static final String TARGETID = "zxg";

    private PushWatcher watcher1 = new PushWatcher() {
        @Override
        public void messageUpdate(Message message) {
//            System.out.println(message.toString());
            Trace.d(message.toString());
        }
    };
    private PushWatcher watcher2 = new PushWatcher();


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PushManager.getInstance(getContext()).addObserver(watcher1);
    }

    public void testSendMsg() throws Exception {
        Message message = Message.test("00001", SELFID, TARGETID);
        PushManager.getInstance(getContext()).SendMessage(message);
    }

    public void testReceiveMsg() throws Exception {
        PushManager.getInstance(getContext()).handlePush("");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        PushManager.getInstance(getContext()).removeObserver(watcher1);

    }
}
