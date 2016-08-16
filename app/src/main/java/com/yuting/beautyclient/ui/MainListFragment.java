package com.yuting.beautyclient.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuting.beautyclient.R;
import com.yuting.beautyclient.bean.GalleryBean;
import com.yuting.beautyclient.common.Constant;
import com.yuting.beautyclient.presenter.GalleryPresenter;
import com.yuting.beautyclient.view.GalleryView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MainListFragment
 * Created by yuting on 2016/8/5.
 */
public class MainListFragment extends BaseFragment implements GalleryView, SwipeRefreshLayout.OnRefreshListener {
    public final static String ID = "id";

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    public static MainListFragment newInstance(int id) {
        Bundle args = new Bundle();
        MainListFragment fragment = new MainListFragment();
        args.putInt(ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    private MainListAdapter adapter;
    private GalleryPresenter mGalleryPresenter;
    private int page = 1;
    private int id = -1;

    @Override
    public int initContentView() {
        return R.layout.fragment_main_list_layout;
    }

    @Override
    public void getBundle(Bundle bundle) {
        id = bundle.getInt(ID, -1);
    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        mGalleryPresenter = new GalleryPresenter(this);
        adapter = new MainListAdapter(getActivity());
        swipeRefreshLayout.setColorSchemeColors(Constant.colors);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        mGalleryPresenter.requestData(id, page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void loadGallery(GalleryBean galleryBean) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.setData(galleryBean.tngou);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        page = 1;
        initData();
    }

    @Override
    public void onReloadClicked() {
        super.onReloadClicked();
    }
}
