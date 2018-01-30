package com.mingbang.mingbang.mingbang.receiver;


import android.content.Context;
import android.util.Log;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

/**
 * TODO:消息接收
 *
 * @author: zhaojy
 * @data:On 2018/1/25.
 */

public class MessageReceiver extends XGPushBaseReceiver {

    private final String TAG = "MessageReceiver";

    @Override
    public void onRegisterResult(Context context, int i, XGPushRegisterResult xgPushRegisterResult) {
        Log.d(TAG, "onRegisterResult");
    }

    @Override
    public void onUnregisterResult(Context context, int i) {
        Log.d(TAG, "onUnregisterResult");
    }

    @Override
    public void onSetTagResult(Context context, int i, String s) {
        Log.d(TAG, "onSetTagResult");
    }

    @Override
    public void onDeleteTagResult(Context context, int i, String s) {
        Log.d(TAG, "onDeleteTagResult");
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage xgPushTextMessage) {
        String content = xgPushTextMessage.getContent();
        Log.d(TAG, content);
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult xgPushClickedResult) {

        Log.d(TAG, "onNotifactionClickedResult");
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult xgPushShowedResult) {
        Log.d(TAG, "onNotifactionShowedResult");
    }
}
