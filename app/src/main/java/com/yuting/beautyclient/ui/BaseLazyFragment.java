package com.yuting.beautyclient.ui;

import android.os.Bundle;
import android.view.View;

/**
 * BaseLazyFragment
 * Created by yuting on 2016/8/5.
 */
public abstract class BaseLazyFragment extends BaseFragment {
    private boolean isFirstLoad = true;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getBundle(getArguments());
        initUI(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        }
    }

    private void onVisible() {
        if (isFirstLoad && isPrepare) {
            initData();
            isFirstLoad = false;
        }
    }
}
