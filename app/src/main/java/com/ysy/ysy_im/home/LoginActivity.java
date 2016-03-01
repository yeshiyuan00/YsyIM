package com.ysy.ysy_im.home;

import android.content.Intent;
import android.preference.PreferenceScreen;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ysy.ysy_im.BaseActivisty;
import com.ysy.ysy_im.IMApplication;
import com.ysy.ysy_im.R;
import com.ysy.ysy_im.entities.Profile;
import com.ysy.ysy_im.net.AppException;
import com.ysy.ysy_im.net.Request;
import com.ysy.ysy_im.net.callback.JsonCallback;
import com.ysy.ysy_im.utilities.Constants;
import com.ysy.ysy_im.utilities.PrefsAccessor;
import com.ysy.ysy_im.utilities.Trace;
import com.ysy.ysy_im.utilities.UrlHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivisty implements View.OnClickListener {


    private EditText mLoginPwdEdt;
    private EditText mLoginAccountEdt;
    private Button mLoginSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initializeView() {
        mLoginAccountEdt = (EditText) findViewById(R.id.mLoginAccountEdt);
        mLoginPwdEdt = (EditText) findViewById(R.id.mLoginPwdEdt);
        mLoginSubmitBtn = (Button) findViewById(R.id.mLoginSubmitBtn);
    }

    @Override
    protected void initializeData() {
        mLoginAccountEdt.setText(PrefsAccessor.getInstance(LoginActivity.this)
                .getString(Constants.KEY_ACCOUNT));
        mLoginPwdEdt.setText(PrefsAccessor.getInstance(LoginActivity.this)
                .getString(Constants.KEY_PASSWORD));
        mLoginSubmitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLoginSubmitBtn:
                // TODO check valid
                String account = mLoginAccountEdt.getText().toString();
                String pwd = mLoginPwdEdt.getText().toString();
                doLogin(account, pwd);
                break;
            default:
                break;
        }
    }

    private void doLogin(final String account, final String pwd) {
        Request request = new Request(UrlHelper.loadLogin(),
                Request.RequestMethod.POST, Request.RequestTool.HTTPURLCONNECTION);
        JSONObject json = new JSONObject();
        try {
            json.put("account", account);
            json.put("password", pwd);
            json.put("clientId", "android");
            json.put("clientVersion", "1.0.0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        request.addHeader("content-type", "application/json");
        request.postContent = json.toString();

        request.setCallback(new JsonCallback<Profile>() {
            @Override
            public void onSuccess(Profile result) {
                Trace.d(result.toString());
                IMApplication.setProfile(result);
                PrefsAccessor.getInstance(LoginActivity.this).saveString(Constants.KEY_ACCOUNT, account);
                PrefsAccessor.getInstance(LoginActivity.this).saveString(Constants.KEY_PASSWORD, pwd);
                goHome();
            }

            @Override
            public void onFailure(AppException exception) {

            }

        });

        request.execute();

    }

    protected void goHome() {
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
    }

}
