package com.jikai.standard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jikai.standard.R;
import com.jikai.standard.event.MessageEvent;
import com.jikai.standard.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.btn_send_msg)
    Button btnSendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe()
    public void onStringMessageEvent(String event) {
        tvMsg.setText(event);
        LogUtil.logEv("DEFAULT p0" + event);
    }

    @Subscribe
    public void onMessageEvent0(MessageEvent event) {
        tvMsg.setText(event.message);
        LogUtil.logEv("DEFAULT p0" + event.message);
    }

    @Subscribe(priority = 1)
    public void onMessageEvent1(MessageEvent event) {
        LogUtil.logEv("DEFAULT p1" + event.message);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 1)
    public void onMessageEvent2(MessageEvent event) {
        LogUtil.logEv("ASYNC p1" + event.message);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 2)
    public void onMessageEvent3(MessageEvent event) {
        LogUtil.logEv("ASYNC p2" + event.message);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 1)
    public void onMessageEvent12(MessageEvent event) {
        LogUtil.logEv("BACKGROUND p1" + event.message);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, priority = 2)
    public void onMessageEvent13(MessageEvent event) {
        LogUtil.logEv("BACKGROUND p2" + event.message);
    }

    @OnClick(R.id.btn_send_msg)
    void sendMsg() {
        EventBus.getDefault().postSticky(new MessageEvent("Hello event bus!"));
    }

    @OnClick(R.id.btn_next)
    void next() {
        startActivity(new Intent(this, SecondActivity.class));
    }

// BACKGROUND p2Hello event bus!
// DEFAULT p1Hello event bus!
// BACKGROUND p1Hello event bus!
// ASYNC p2Hello event bus!
// DEFAULT p0Hello event bus!
// ASYNC p1Hello event bus!
// 优先级只有在同一种模式下才有意义
}
