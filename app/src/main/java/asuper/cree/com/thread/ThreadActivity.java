package asuper.cree.com.thread;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import asuper.cree.com.demo.adapter.ThreadAdapter;
import asuper.cree.com.superdelegateadapter.R;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/23  14:43
 *
 * @author luyongjiang
 * @version 1.0
 */
public class ThreadActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ThreadAdapter mThreadAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        mRecyclerView = findViewById(R.id.rv_thread);
        mThreadAdapter = new ThreadAdapter();
        mThreadAdapter.setAdapter(mRecyclerView);
        startRequest();

    }

    public void onClick(View view) {
        startRequest();
    }

    private void startRequest() {
        for (int i = 0; i < 3; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(j * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    start();
                }
            }).start();
        }
    }

    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadAdapter.addData1();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mThreadAdapter.addData2();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                });

            }
        }).start();
    }


}
