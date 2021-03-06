package com.lht.justdraw.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lht.justdraw.R;
import com.lht.justdraw.view.NativeProView;
import com.lht.justdraw.view.OnDrawFinishedListener;

public class NativeProActivity extends AppCompatActivity {

    NativeProView mView;
    TextView mTvMsg;

    int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_native_pro);
        setContentView(R.layout.activity_native_pro);

        mCount = getIntent().getIntExtra("count", 1000);

        mView = (NativeProView)findViewById(R.id.sv_canvas);
        mView.setOnDrawFinishedListener(new OnDrawFinishedListener() {
            @Override
            public void onDrawFinished(String msg) {
                mTvMsg.setText(msg);
            }
        });

        mTvMsg = (TextView)findViewById(R.id.tv_msg);

        draw(null);
    }

    public void draw(View v) {
        new Thread(runnable).start();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mView.setCount(mCount);
            handler.sendEmptyMessage(0);
        }
    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            mView.invalidate();
        }
    };
}
