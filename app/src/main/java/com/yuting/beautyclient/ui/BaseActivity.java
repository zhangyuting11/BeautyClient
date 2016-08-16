package com.yuting.beautyclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.yuting.beautyclient.help.AppManager;
import com.yuting.beautyclient.view.BaseView;

/**
 * 基类Activity
 * Created by yuting on 2016/8/4.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected final String TAG = this.getClass().getSimpleName();
    protected Toast toast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        initUiAndListener();
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 设置主题
     */
    private void initTheme() {

    }

    /**
     * 设置view
     */
    public abstract int initContentView();

    /**
     * init UI && Listener
     */
    public abstract void initUiAndListener();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void hasNoMoreData() {

    }

    @Override
    public void showToastError(String msg) {
        showShortToast(msg);
    }

    //Toast
    protected void showShortToast(int msgID) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msgID, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msgID);
        }
        toast.show();
    }

    protected void showShortToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    protected void showLongToast(int msgID) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msgID, Toast.LENGTH_LONG);
        } else {
            toast.setText(msgID);
        }
        toast.show();
    }

    protected void showLongToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
