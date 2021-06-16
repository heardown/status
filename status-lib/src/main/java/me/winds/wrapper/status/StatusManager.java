package me.winds.wrapper.status;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author:  winds
 * Data:    2018/1/17
 * Version: 1.0
 * Desc:
 */
public class StatusManager {
    public static final String TAG = "StatusManager";

    private Map<String, StatusProvider> map = new HashMap<>();
    private StatusProvider currentStatusProvider;
    protected View contentView;     //content view
    protected ViewGroup containerView; //parent view
    public boolean clearStatusFlag;

    /**
     * @param contentView current content view object, must have a parent view, that can add status view
     */
    public StatusManager(View contentView) {
        this.contentView = contentView;
        ViewParent parent = this.contentView.getParent();
        if (parent != null) {
            if (parent instanceof ViewGroup) {
                containerView = (ViewGroup) parent;
            } else {
                throw new RuntimeException(contentView.getClass().getName() + " must be ViewGroup' child");
            }
        } else {
            throw new RuntimeException(contentView.getClass().getName() + " must have a ViewParent");
        }
    }

    /**
     * 加入对应的StatusProvider
     *
     * @param p
     */
    public void addStatusProvider(StatusProvider p) {
        map.put(p.getStatus(), p);
    }


    public void show(StatusProvider provider, OnStatusListener listener) {
        map.put(provider.getStatus(), provider);
        show(provider.getStatus(), listener);
    }

    public void show(StatusProvider provider) {
        show(provider, null);
    }

    /**
     * 展示对应的状态布局
     *
     * @param status
     */
    public void show(String status) {
        show(status, null);
    }


    /**
     * show current status
     *
     * @param status   status key
     * @param listener status life listener
     */
    public void show(String status, OnStatusListener listener) {
        if (currentStatusProvider != null) {
            currentStatusProvider.removeStatusView();
        }
        StatusProvider provider = map.get(status);
        if (provider != null) {
            if (listener != null) {
                provider.setOnStatusListener(listener);
            }
            provider.showStatusView(containerView);
            currentStatusProvider = provider;
        } else {
            switch (status) {
                case DefaultStatus.STATUS_LOADING:
                    show(new DefaultStatusProvider.DefaultLoadingStatusProvider(), listener);
                    break;
                case DefaultStatus.STATUS_EMPTY:
                    show(new DefaultStatusProvider.DefaultEmptyStatusProvider(), listener);
                    break;
                case DefaultStatus.STATUS_NO_NETWORK:
                    show(new DefaultStatusProvider.DefaultNoNetWorkStatusProvider(), listener);
                    break;
                case DefaultStatus.STATUS_NOT_LOGIN:
                    show(new DefaultStatusProvider.DefaultNotLoginStatusProvider(), listener);
                    break;
                case DefaultStatus.STATUS_LOAD_ERROR:
                    show(new DefaultStatusProvider.DefaultLoadErrorStatusProvider(), listener);
                    break;
                default:
                    Log.e(TAG, "Please add StatusProvider");
                    break;
            }
        }
    }

    /**
     * show content view
     * before use, must be sure remove or hide other status
     */
    protected void showContentView() {
        contentView.setVisibility(View.VISIBLE);
        contentView.bringToFront();
    }

    /**
     * get current status
     *
     * @return
     */
    public String getCurrentStatus() {
        return currentStatusProvider == null ? "" : currentStatusProvider.getStatus();
    }


    public StatusProvider getCurrentStatusProvider() {
        return currentStatusProvider;
    }

    /**
     * hide current status
     */
    public void hideStatus() {
        removeStatus(false);
    }


    /**
     * remove current status
     */
    public void removeStatus() {
        removeStatus(true);
    }


    /**
     * remove named status
     *
     * @param status
     */
    public void removeStatus(String status) {
        StatusProvider provider = map.get(status);
        if (provider != null) {
            if (provider == currentStatusProvider) {
                removeStatus();
            } else {
                provider.removeStatusView();
            }
            map.remove(status);
        }
    }

    /**
     * remove current status
     *
     * @param full true depth remove  false only hide
     */
    public void removeStatus(boolean full) {
        if (currentStatusProvider != null) {
            if (full) {
                currentStatusProvider.removeStatusView();
                map.remove(currentStatusProvider.getStatus());
                if (clearStatusFlag) {
                    currentStatusProvider = null;
                }
            } else {
                currentStatusProvider.hideStatusView();
            }
            showContentView();
        }
    }

    /**
     * remove named status
     *
     * @param status
     * @param full   true depth remove  false only hide
     */
    public void removeStatus(String status, boolean full) {
        StatusProvider provider = map.get(status);
        if (provider != null) {
            if (provider == currentStatusProvider) {
                removeStatus(full);
            } else {
                if (full) {
                    provider.removeStatusView();
                    map.remove(status);
                } else {
                    provider.hideStatusView();
                }
            }
        }
    }

    /**
     * remove all status
     */
    public void removeAllStatus() {
        if (map.size() > 0) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String status = iterator.next();
                removeStatus(status);
            }
        }
    }

    /**
     * set this param, at show status, do not record last time status
     *
     * @param clearStatusFlag
     */
    public void setClearStatusFlag(boolean clearStatusFlag) {
        this.clearStatusFlag = clearStatusFlag;
    }
}
