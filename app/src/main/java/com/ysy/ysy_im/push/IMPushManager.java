package com.ysy.ysy_im.push;

import android.content.Context;

import com.google.gson.Gson;
import com.ysy.ysy_im.entities.Message;
import com.ysy.ysy_im.junit.ChatTest;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class IMPushManager {
    private static IMPushManager mInstance;
    private Context context;
    private Gson gson=new Gson();

    public IMPushManager(Context context) {

        this.context = context;
    }

    public static IMPushManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IMPushManager(context);
        }
        return mInstance;
    }


    public void handlePush(Message message) {
//		TODO parse content to Message/Notice
        PushChanger.getInstance().notifyChanged(message);
    }


    public void handlePush(String content) {
        Message message = gson.fromJson(content,Message.class);
        PushChanger.getInstance().notifyChanged(message);
    }

    public void SendMessage(Message message) {
//        Intent service=new Intent(context,IMPushService.class);
//        service.putExtra(Constants.KEY_MESSAGE,message);
//        context.startService(service);
        message.setStatus(Message.StatusType.ing);
        PushChanger.getInstance().notifyChanged(message);
        message.setStatus(Message.StatusType.done);
        PushChanger.getInstance().notifyChanged(message);

    }

    public void addObserver(PushWatcher watcher) {
        PushChanger.getInstance().addObserver(watcher);
    }

    public void removeObserver(PushWatcher watcher) {
        PushChanger.getInstance().deleteObserver(watcher);
    }

    public void removeObservers(PushWatcher watcher) {
        PushChanger.getInstance().deleteObservers();
    }

}
