package com.ysy.ysy_im;

import com.ysy.ysy_im.entities.Message;

import java.util.Observable;
import java.util.Observer;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class PushWatcher implements Observer {
    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof Message) {
            messageUpdate((Message) data);
        }
    }

    public void messageUpdate(Message message) {

    }
}
