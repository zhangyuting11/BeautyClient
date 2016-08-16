package com.yuting.beautyclient.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yuting.beautyclient.R;
import com.yuting.beautyclient.bean.GalleryclassBean;
import com.yuting.beautyclient.presenter.GalleryclassPresenter;
import com.yuting.beautyclient.view.GalleryclassView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MainFragment
 * Created by yuting on 2016/8/5.
 */
public class MainFragment extends BaseFragment implements GalleryclassView {

    @Bind(R.id.tableLayout)
    TabLayout tableLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private GalleryclassPresenter galleryclassPresenter;
    private PagerAdapter adapter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_main_layout;
    }

    @Override
    public void getBundle(Bundle bundle) {
    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        galleryclassPresenter = new GalleryclassPresenter(this);
        adapter = new PagerAdapter(getChildFragmentManager());
    }

    @Override
    public void initData() {
        galleryclassPresenter.requestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void loadGalleryclass(GalleryclassBean galleryclassBean) {
        for (GalleryclassBean.Galleryclass galleryclass : galleryclassBean.tngou) {
            tableLayout.addTab(tableLayout.newTab().setText(galleryclass.title));
            adapter.addFragment(MainListFragment.newInstance(galleryclass.id), galleryclass.title);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        galleryclassPresenter.destroy();
    }
}
