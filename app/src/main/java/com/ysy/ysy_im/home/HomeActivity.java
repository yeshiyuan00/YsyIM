package com.ysy.ysy_im.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.ysy.ysy_im.R;
import com.ysy.ysy_im.utilities.Utils;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mHomeConversationBtn;
    private Button mHomeContactBtn;
    private Button mHomeProfileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY, Utils.getMetaValue(this, "api_key"));
//		PushManager.setTags(this, mCurrentUser.getPushTags());


        mHomeConversationBtn = (Button)findViewById(R.id.mHomeConversationBtn);
        mHomeContactBtn = (Button)findViewById(R.id.mHomeContactBtn);
        mHomeProfileBtn = (Button)findViewById(R.id.mHomeProfileBtn);
        mHomeConversationBtn.setOnClickListener(this);
        mHomeContactBtn.setOnClickListener(this);
        mHomeProfileBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mHomeConversationBtn:
//			TODO
                break;
            case R.id.mHomeContactBtn:

                break;
            case R.id.mHomeProfileBtn:

                break;

            default:
                break;
        }
    }
}
