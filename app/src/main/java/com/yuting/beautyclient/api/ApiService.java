package com.yuting.beautyclient.api;

import com.yuting.beautyclient.bean.GalleryBean;
import com.yuting.beautyclient.bean.GalleryclassBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Retrofit API接口
 * Created by yuting on 2016/8/4.
 */
public interface ApiService {

    @GET("tnfs/api/classify")
    Observable<GalleryclassBean> listGalleryList();//图片分类

    @GET("tnfs/api/list")
    Observable<GalleryBean> listGalleries(@QueryMap Map<String, String> options);//图片列表


}
