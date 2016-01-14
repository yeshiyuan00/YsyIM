package com.ysy.ysy_im.db;

import com.ysy.ysy_im.IMApplication;

/**
 * Author: yeshiyuan
 * Date: 1/14/16.
 */
public class DBController {
    private static DBController instance;
    private OrmDBHelper mDBHelper;

    private DBController(){
        mDBHelper=new OrmDBHelper(IMApplication.gContext);
        mDBHelper.getWritableDatabase();
    }

    public static DBController getInstance(){
        if(instance==null){
            instance=new DBController();
        }
        return instance;
    }

    public OrmDBHelper getDB(){
        return mDBHelper;
    }
}
