package me.winds.test;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Author:  winds
 * Data:    2018/8/9
 * Version: 1.0
 * Desc:
 */


public abstract class WrapperStatusFragment extends Fragment {

    protected StatusManagerDelegate delegate;

    /**
     * 为status页面提供父布局 非一般情况 请避免重写此方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout parent = new FrameLayout(getActivity());
        View view = inflater.inflate(getViewLayout(), container, false);
        parent.addView(view);
        return parent;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onCreateStatus(view);
        super.onViewCreated(view, savedInstanceState);
    }

    protected void onCreateStatus(View view) {
        delegate = new StatusManagerDelegate(view) {
            @Override
            public void load(String status) {

            }
        };
    }

    protected abstract int getViewLayout();
}
