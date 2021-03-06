package com.ysy.ysy_im.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ysy.ysy_im.Constants;
import com.ysy.ysy_im.entities.Message;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class IMPushService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message = (Message) intent.getSerializableExtra(Constants.KEY_MESSAGE);
        switch (message.getType()) {
            case txt:
                sendPlainMsg(message);
                break;

            default:
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendPlainMsg(Message message) {

    }
}
