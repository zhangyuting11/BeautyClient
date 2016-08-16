package com.yuting.beautyclient.presenter;

import com.yuting.beautyclient.bean.GalleryBean;
import com.yuting.beautyclient.view.GalleryView;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 图片分类---列表presenter
 * Created by yuting on 2016/8/4.
 */
public class GalleryPresenter extends BasePresenter {
    protected GalleryView mGalleryView;
    protected Subscription subscription;

    public GalleryPresenter(GalleryView mGalleryView) {
        this.mGalleryView = mGalleryView;
    }


    @Override
    public void requestData(Object... o) {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(o[0]));
        map.put("page", String.valueOf(o[1]));
        subscription = apiService
                .listGalleries(map)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mGalleryView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GalleryBean>() {
                    @Override
                    public void onCompleted() {
                        mGalleryView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mGalleryView.showNetError();
                    }

                    @Override
                    public void onNext(GalleryBean galleryBean) {
                        if (galleryBean.status) {
                            if (galleryBean.tngou==null){
                                mGalleryView.showEmptyView();
                                return;
                            }
                            mGalleryView.loadGallery(galleryBean);
                        } else {
                            mGalleryView.showToastError(galleryBean.msg);
                        }
                    }
                });
    }

    @Override
    public void destroy() {
        if (subscription != null)
            subscription.unsubscribe();
    }

}
