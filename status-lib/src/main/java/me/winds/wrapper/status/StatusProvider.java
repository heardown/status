package me.winds.wrapper.status;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Author:  winds
 * Data:    2018/1/17
 * Version: 1.0
 * Desc:
 */
public abstract class StatusProvider {

    protected View statusView;      //current status view
    protected String status;        //current status name
    protected OnStatusListener listener;

    public StatusProvider(String status) {
        this.status = status;
    }

    /**
     * show status view
     */
    public void showStatusView(ViewGroup container) {
        if (statusView == null) {
            statusView = getStatusView(container);
            if (listener != null) {
                listener.onStatusViewCreate(status, statusView);
            }
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(statusView, lp);
        }
        statusView.setVisibility(View.VISIBLE);
        statusView.bringToFront();
        if (listener != null) {
            listener.onStatusViewShow(status, statusView);
        }
    }


    /**
     * hide status view
     */
    public void hideStatusView() {
        if (statusView != null) {
            statusView.setVisibility(View.GONE);
        }
        if (listener != null) {
            listener.onStatusViewHide(status, statusView);
        }
    }

    /**
     * judge status is hide
     *
     * @return
     */
    public boolean isHide() {
        if (statusView != null && statusView.getVisibility() == View.VISIBLE) {
            return false;
        }
        return true;
    }

    /**
     * judge status is show
     *
     * @return
     */
    public boolean isShow() {
        if (statusView != null && statusView.getVisibility() == View.VISIBLE) {
            return true;
        }
        return false;
    }

    /**
     * remove current status view
     */
    public void removeStatusView() {
        if (statusView != null) {
            ViewParent parent = statusView.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(statusView);
            }
            statusView = null;
        }
        if (listener != null) {
            listener.onStatusViewRemove(status, statusView);
        }
    }

    /**
     * get status name
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    public StatusProvider setOnStatusListener(OnStatusListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * get current status view
     *
     * @return
     */
    public View getView() {
        return statusView;
    }

    public abstract View getStatusView(ViewGroup containerView);
}
