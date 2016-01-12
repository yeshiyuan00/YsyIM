package com.ysy.ysy_im;

import android.content.Context;
import android.content.Intent;

import com.ysy.ysy_im.entities.Message;
import com.ysy.ysy_im.junit.ChatTest;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class PushManager {
    private static PushManager mInstance;
    private Context context;

    public PushManager(Context context) {

        this.context = context;
    }

    public static PushManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PushManager(context);
        }
        return mInstance;
    }

    public void handlePush(String content) {
        //TODO
        //Message message = new Message();
        Message message = Message.test("00001", ChatTest.TARGETID, ChatTest.SELFID);
        PushChanger.getInstace().notifyChanged(message);
    }

    public void SendMessage(Message message) {
//        Intent service=new Intent(context,PushService.class);
//        service.putExtra(Constants.KEY_MESSAGE,message);
//        context.startService(service);
        message.setStatus(Message.StatusType.ing);
        PushChanger.getInstace().notifyChanged(message);
        message.setStatus(Message.StatusType.done);
        PushChanger.getInstace().notifyChanged(message);

    }

    public void addObserver(PushWatcher watcher) {
        PushChanger.getInstace().addObserver(watcher);
    }

    public void removeObserver(PushWatcher watcher) {
        PushChanger.getInstace().deleteObserver(watcher);
    }

    public void removeObservers(PushWatcher watcher) {
        PushChanger.getInstace().deleteObservers();
    }

}
