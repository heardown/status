package me.winds.test;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Author:  winds
 * Data:    2018/8/9
 * Version: 1.0
 * Desc:
 */


public abstract class WrapperStatusActivity extends AppCompatActivity {

    protected StatusManagerDelegate delegate;
    protected View content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        initView(savedInstanceState, getIntent());
    }

    protected ViewGroup initContentView() {
        FrameLayout container = new FrameLayout(this);
        content = View.inflate(this, getViewLayout(), null);
        container.addView(content);
        initStatusDelete(content);
        return container;
    }


    protected void initStatusDelete(View contentView) {
        delegate = new StatusManagerDelegate(contentView) {
            @Override
            public void load(String status) {
                shoToast("点击了状态" + status);
            }
        };
    }

    protected View getContentView() {
        return content;
    }

    private void shoToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected StatusManagerDelegate getStatusManagerDelegate() {
        return delegate;
    }

    protected abstract int getViewLayout();

    protected abstract void initView(Bundle savedInstanceState, Intent intent);
}
