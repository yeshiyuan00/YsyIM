package com.ysy.ysy_im.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.ysy.ysy_im.BaseActivisty;
import com.ysy.ysy_im.IMApplication;
import com.ysy.ysy_im.R;
import com.ysy.ysy_im.db.ConversationController;
import com.ysy.ysy_im.entities.Conversation;
import com.ysy.ysy_im.entities.Message;
import com.ysy.ysy_im.net.AppException;
import com.ysy.ysy_im.net.Request;
import com.ysy.ysy_im.net.callback.JsonArrayCallback;
import com.ysy.ysy_im.net.callback.JsonCallback;
import com.ysy.ysy_im.push.IMPushManager;
import com.ysy.ysy_im.push.PushWatcher;
import com.ysy.ysy_im.utilities.UrlHelper;
import com.ysy.ysy_im.utilities.Utils;

import java.util.ArrayList;

/**
 * Author: yeshiyuan
 * Date: 3/1/16.
 */
public class ConversationActivity extends BaseActivisty {
    private ListView mConversationLsv;
    private ConversationAdapter adapter;
    private ArrayList<Conversation> mConversationList;
    private PushWatcher watcher = new PushWatcher() {
        @Override
        public void messageUpdate(Message message) {
            Conversation conversation = message.copyTo();
            mConversationList.remove(conversation);
            mConversationList.add(0, conversation);
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_conversation);
    }

    @Override
    protected void initializeView() {
        mConversationLsv = (ListView) findViewById(R.id.mConversationLsv);
    }

    @Override
    protected void initializeData() {
        PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(this, "api_key"));
        adapter = new ConversationAdapter();
        mConversationLsv.setAdapter(adapter);
        loadDataFromDB();
        loadDataListFromServer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IMPushManager.getInstance(getApplicationContext()).addObserver(watcher);
    }

    @Override
    protected void onPause() {
        super.onPause();
        IMPushManager.getInstance(getApplicationContext()).removeObserver(watcher);
    }

    private void loadDataFromDB() {
        mConversationList = ConversationController.queryAllByTimeDesc();
        adapter.notifyDataSetChanged();
    }

    private void loadDataListFromServer() {
        Request request = new Request(UrlHelper.loadConversation(), Request.RequestMethod.GET,
                Request.RequestTool.HTTPURLCONNECTION);
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization", IMApplication.getToken());
        request.setCallback(new JsonCallback<ArrayList<Message>>() {
            @Override
            public void onSuccess(ArrayList<Message> messages) {

                for (Message message : messages) {
                    ConversationController.syncMessage(message);
                }
                loadDataFromDB();
            }

            @Override
            public void onFailure(AppException exception) {
                exception.printStackTrace();
            }
        });
        request.execute();
    }

    class ConversationAdapter extends BaseAdapter {
        private ViewHolder mViewHolder;

        @Override
        public int getCount() {
            return mConversationList == null ? 0 : mConversationList.size();
        }

        @Override
        public Object getItem(int position) {
            return mConversationList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = LayoutInflater.from(ConversationActivity.this)
                        .inflate(R.layout.activity_conversation_item, null);
                mViewHolder.mConversationAvatarImg = (ImageView) convertView.findViewById(R.id.mConversationAvatarImg);
                mViewHolder.mConversationNumTip = (TextView) convertView.findViewById(R.id.mConversationNumTip);
                mViewHolder.mConversationTimestampLabel = (TextView) convertView.findViewById(R.id.mConversationTimestampLabel);
                mViewHolder.mConversationUsernameLabel = (TextView) convertView.findViewById(R.id.mConversationUsernameLabel);
                mViewHolder.mConversationContentLabel = (TextView) convertView.findViewById(R.id.mConversationContentLabel);
                mViewHolder.mConversationStatusLabel = (TextView) convertView.findViewById(R.id.mConversationStatusLabel);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            Conversation conversation = mConversationList.get(position);
            mViewHolder.mConversationUsernameLabel.setText(conversation.getTargetName());
            mViewHolder.mConversationContentLabel.setText(conversation.getContent());
            return convertView;
        }
    }

    static class ViewHolder {
        TextView mConversationStatusLabel;
        TextView mConversationContentLabel;
        TextView mConversationUsernameLabel;
        TextView mConversationTimestampLabel;
        TextView mConversationNumTip;
        ImageView mConversationAvatarImg;
    }
}
