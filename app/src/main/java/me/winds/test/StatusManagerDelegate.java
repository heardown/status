package me.winds.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import me.winds.wrapper.status.DefaultStatus;
import me.winds.wrapper.status.OnDefaultStatusListener;
import me.winds.wrapper.status.OnStatusListener;
import me.winds.wrapper.status.StatusManager;
import me.winds.wrapper.status.StatusProvider;


/**
 * Auther:  winds
 * Data:    2018/3/12
 * Version: 1.0
 * Desc:
 */


public abstract class StatusManagerDelegate {

    public StatusManager statusManager;

    public StatusManagerDelegate(View view) {
        if (view != null) {
            init(view);
        }
    }

    /**
     * 初始化StatusManager
     *
     * @param view
     */
    public void init(View view) {
        statusManager = new StatusManager(view);
    }

    /**
     * 展示StatusManager加载页面
     */
    public void initView() {
        showStatus(DefaultStatus.STATUS_LOADING);
    }


    /**
     * 展示对应的状态布局
     *
     * @param status
     */
    public void showStatus(String status) {
        if (statusManager != null) {
            statusManager.show(status);
        }
    }


    public void showLoadingStatus() {
        statusManager.show(DefaultStatus.STATUS_LOADING);
    }

    public void showNetErrorStatus() {
        statusManager.show(DefaultStatus.STATUS_NO_NETWORK);
    }

    public void showEmptyStatus() {
        statusManager.show(DefaultStatus.STATUS_EMPTY);
    }

    /**
     * 展示对应StatusProvider的状态布局
     *
     * @param provider
     * @param listener
     */
    public void showStatus(StatusProvider provider, OnStatusListener listener) {
        if (statusManager != null) {
            statusManager.show(provider, listener);
        }
    }

    public StatusManager getStatusManager() {
        return statusManager;
    }

    /**
     * 移除当前的状态布局
     */
    public void removeStatus() {
        removeStatus(null);
    }

    /**
     * 移除对应的状态布局
     *
     * @param status
     */
    public void removeStatus(String status) {
        if (statusManager != null) {
            if (TextUtils.isEmpty(status)) {
                statusManager.removeStatus();
            } else {
                statusManager.removeStatus(status);
            }
        }
    }


    /**
     * 此处理方法仅供参考  如使用 请覆盖重写此方法
     */
    public void showLoadErrorStaus() {
        /**
         * 从loadData进入的方法
         * @see #loadData(Bundle)
         */
        statusManager.show(DefaultStatus.STATUS_LOAD_ERROR, new OnDefaultStatusListener() {
            @Override
            public void onStatusViewCreate(String status, View statusView) {
                statusView.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusManager.show(DefaultStatus.STATUS_LOADING);
                        load(DefaultStatus.STATUS_LOAD_ERROR);
                    }
                });
            }
        });
    }

    /**
     * 处理网络异常问题时的状态显示
     * 此方法的回调仅实现重新初始化
     * 此处理方法仅供参考  如使用 请覆盖重写此方法
     */
    public void showNoNetworkStatus() {
        /**
         * 从loadData进入的方法
         * @see #loadData(Bundle)
         */
        statusManager.show(DefaultStatus.STATUS_NO_NETWORK, new OnDefaultStatusListener() {
            @Override
            public void onStatusViewCreate(String status, View statusView) {
                statusView.findViewById(R.id.btn_retry).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusManager.show(DefaultStatus.STATUS_LOADING);
                        load(DefaultStatus.STATUS_NO_NETWORK);
                    }
                });
            }
        });
    }

    /**
     * 此方法用于在点击重试时重新加载数据
     */
    public abstract void load(String status);
}
