package com.jikai.standard.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.jikai.standard.R;
import com.jikai.standard.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jikai on 16/3/14.
 */
public class SecondActivity extends AppCompatActivity {

    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.btn_send_msg)
    Button btnSendMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
        // Better check that an event was actually posted before
        if (stickyEvent != null) {
            // do something
            tvMsg.setText(stickyEvent.message);
        }
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    @Subscribe 必须加上 sticky = true, 才能收到消息
    public void onMessageEvent(MessageEvent event) {
        tvMsg.setText(event.message);
        EventBus.getDefault().cancelEventDelivery(event) ;
    }

    @OnClick(R.id.btn_back)
    void back() {
        finish();
    }

    @OnClick(R.id.btn_next)
    void next() {

    }

}
