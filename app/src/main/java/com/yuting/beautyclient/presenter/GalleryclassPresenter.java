package com.yuting.beautyclient.presenter;

import com.yuting.beautyclient.bean.GalleryclassBean;
import com.yuting.beautyclient.view.GalleryclassView;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * 图片分类presenter
 * Created by yuting on 2016/8/4.
 */
public class GalleryclassPresenter extends BasePresenter {
    protected GalleryclassView mGalleryclassView;
    protected Subscription subscription;

    public GalleryclassPresenter(GalleryclassView mGalleryclassView) {
        this.mGalleryclassView = mGalleryclassView;
    }

    @Override
    public void requestData(Object... o) {
        subscription = apiService
                .listGalleryList()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mGalleryclassView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GalleryclassBean>() {
                    @Override
                    public void onCompleted() {
                        mGalleryclassView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mGalleryclassView.showNetError();
                    }

                    @Override
                    public void onNext(GalleryclassBean galleryclassBean) {
                        if (galleryclassBean.status) {
                            mGalleryclassView.loadGalleryclass(galleryclassBean);
                        } else {
                            mGalleryclassView.showToastError(galleryclassBean.msg);
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
