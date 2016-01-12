package com.ysy.ysy_im;

import com.ysy.ysy_im.entities.Message;

import java.util.Observable;

/**
 * Author: yeshiyuan
 * Date: 12/2/15.
 */
public class PushChanger extends Observable {
    private static PushChanger mInstance;

    public static PushChanger getInstace(){
        if(mInstance==null){
            mInstance=new PushChanger();

        }
        return  mInstance;
    }

    public void notifyChanged(Message message) {

        //
        setChanged();
        notifyObservers(message);
    }
}
