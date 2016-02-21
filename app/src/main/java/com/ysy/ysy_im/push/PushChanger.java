package com.ysy.ysy_im.push;

import com.ysy.ysy_im.db.MessageController;
import com.ysy.ysy_im.entities.Message;

import java.util.Observable;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class PushChanger extends Observable {
    private static PushChanger mInstance;

    public static PushChanger getInstance(){
        if(mInstance==null){
            mInstance=new PushChanger();

        }
        return  mInstance;
    }

    public void notifyChanged(Message message) {

        //
        MessageController.addOrUpdata(message);
        setChanged();
        notifyObservers(message);
    }
}
