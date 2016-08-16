package com.yuting.beautyclient;

import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.yuting.beautyclient.ui.BaseActivity;
import com.yuting.beautyclient.ui.MainFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout container;


    @Override
    public int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
