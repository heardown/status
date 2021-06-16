package me.winds.wrapper.status;

import android.view.View;
import android.view.ViewGroup;

import me.winds.wrapper.R;

/**
 * Author:  winds
 * Data:    2018/1/17
 * Version: 1.0
 * Desc:
 */
public class DefaultStatusProvider {

    /**
     * loading status default
     */
    public static class DefaultLoadingStatusProvider extends StatusProvider {

        public DefaultLoadingStatusProvider() {
            super(DefaultStatus.STATUS_LOADING);
        }

        @Override
        public View getStatusView(ViewGroup containerView) {
            return View.inflate(containerView.getContext(), R.layout.layout_status_loading, null);
        }
    }

    /**
     * net error status default
     */
    public static class DefaultNoNetWorkStatusProvider extends StatusProvider {

        public DefaultNoNetWorkStatusProvider() {
            super(DefaultStatus.STATUS_NO_NETWORK);
        }

        @Override
        public View getStatusView(ViewGroup containerView) {
            return View.inflate(containerView.getContext(), R.layout.layout_status_no_network, null);
        }
    }

    /**
     * empty status default
     */
    public static class DefaultEmptyStatusProvider extends StatusProvider {

        public DefaultEmptyStatusProvider() {
            super(DefaultStatus.STATUS_EMPTY);
        }

        @Override
        public View getStatusView(ViewGroup containerView) {
            return View.inflate(containerView.getContext(), R.layout.layout_status_empty, null);
        }
    }

    /**
     * no login default
     */
    public static class DefaultNotLoginStatusProvider extends StatusProvider {

        public DefaultNotLoginStatusProvider() {
            super(DefaultStatus.STATUS_NO_NETWORK);
        }

        @Override
        public View getStatusView(ViewGroup containerView) {
            return View.inflate(containerView.getContext(), R.layout.layout_status_not_login, null);
        }
    }


    /**
     * load error status default
     */
    public static class DefaultLoadErrorStatusProvider extends StatusProvider {

        public DefaultLoadErrorStatusProvider() {
            super(DefaultStatus.STATUS_LOAD_ERROR);
        }

        @Override
        public View getStatusView(ViewGroup containerView) {
            return View.inflate(containerView.getContext(), R.layout.layout_status_load_error, null);
        }
    }
}
