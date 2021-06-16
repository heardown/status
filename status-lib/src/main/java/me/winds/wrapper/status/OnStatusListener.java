package me.winds.wrapper.status;

import android.view.View;

/**
 * Author:  winds
 * Data:    2018/1/17
 * Version: 1.0
 * Desc:
 */
public interface OnStatusListener {
    /**
     * when status View on create callback
     *
     * @param status     status name
     * @param statusView status view
     */
    void onStatusViewCreate(String status, View statusView);

    /**
     * when status View on show callback
     *
     * @param status     status name
     * @param statusView status view
     */
    void onStatusViewShow(String status, View statusView);

    /**
     * when status View on hide callback
     *
     * @param status     status name
     * @param statusView status view
     */
    void onStatusViewHide(String status, View statusView);

    /**
     * when status View on remove callback
     *
     * @param status     status name
     * @param statusView status view
     */
    void onStatusViewRemove(String status, View statusView);
}
