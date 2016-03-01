package com.ysy.ysy_im;

import android.app.Activity;
import android.os.Bundle;

/**
 * Author: yeshiyuan
 * Date: 2/21/16.
 */
public abstract class BaseActivisty extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initializeView();
        initializeData();
    }

    protected abstract void setContentView();
    protected abstract void initializeView();
    protected abstract void initializeData();

    @Override
    protected void onResume() {
        super.onResume();
//		TODO umeng
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
