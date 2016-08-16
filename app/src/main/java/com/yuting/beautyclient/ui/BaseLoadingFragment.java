package com.yuting.beautyclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.yuting.beautyclient.R;

/**
 * 基类BaseLoadingFragment
 * Created by yuting on 2016/8/5.
 */
public abstract class BaseLoadingFragment extends Fragment {

    protected Toast toast;
    protected View mContentView;

    protected boolean isPrepare;
    protected boolean isLoadingCompleted;

    private Animation mAnimIn, mAnimOut;

    /**
     * 得到Activity传进来的值
     */
    public abstract void getBundle(Bundle bundle);

    /**
     * 初始化控件
     */
    public abstract void initUI(View view);

    /**
     * 在监听器之前把数据准备好
     */
    public abstract void initData();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup main = (ViewGroup) inflater.inflate(R.layout.base_loading_layout, container, false);

        View content = onCreateContentView(inflater);
        View error = onCreateContentErrorView(inflater);
        View empty = onCreateContentEmptyView(inflater);
        View progress = onCreateProgressView(inflater);

        replaceViewById(main, R.id.tvContent, content);
        replaceViewById(main, R.id.tvError, error);
        replaceViewById(main, R.id.tvEmpty, empty);
        replaceViewById(main, R.id.llProgress, progress);

        mAnimIn = onCreateAnimationIn();
        mAnimOut = onCreateAnimationOut();

        mContentView = main;
        isPrepare = true;

        return main;
    }

    private void replaceViewById(ViewGroup container, int viewId, View newView) {
        if (newView == null) {
            return;
        }
        newView.setId(viewId);
        View oldView = container.findViewById(viewId);
        int index = container.indexOfChild(oldView);
        container.removeView(oldView);
        container.addView(newView, index);
        newView.setVisibility(View.GONE);
    }


    //Override this method to change content view
    public View onCreateContentView(LayoutInflater inflater) {
        return null;
    }

    //Override this method to change error view
    public View onCreateContentErrorView(LayoutInflater inflater) {
        return null;
    }

    //Override this method to change empty view
    public View onCreateContentEmptyView(LayoutInflater inflater) {
        return null;
    }

    //Override this method to change progress view
    public View onCreateProgressView(LayoutInflater inflater) {
        return null;
    }


    public Animation onCreateAnimationIn() {
        return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
    }

    public Animation onCreateAnimationOut() {
        return AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
    }

    protected void showViewById(int viewId, boolean animate) {
        View content = mContentView.findViewById(viewId);
        if (animate) {
            mAnimIn.reset();
            content.startAnimation(mAnimIn);
        } else {
            content.clearAnimation();
        }
        content.setVisibility(View.VISIBLE);
    }

    protected void dismissViewById(int viewId, boolean animate) {
        View content = mContentView.findViewById(viewId);
        if (animate) {
            mAnimOut.reset();
            content.startAnimation(mAnimOut);
        } else {
            content.clearAnimation();
        }
        content.setVisibility(View.GONE);
    }

    //Toast
    protected void showShortToast(int msgID) {
        if (toast == null) {
            toast = Toast.makeText(getActivity().getApplicationContext(), msgID, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msgID);
        }
        toast.show();
    }

    protected void showShortToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    protected void showLongToast(int msgID) {
        if (toast == null) {
            toast = Toast.makeText(getActivity().getApplicationContext(), msgID, Toast.LENGTH_LONG);
        } else {
            toast.setText(msgID);
        }
        toast.show();
    }

    protected void showLongToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
