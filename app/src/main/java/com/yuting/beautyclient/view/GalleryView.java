package com.yuting.beautyclient.view;

import com.yuting.beautyclient.bean.GalleryBean;

/**
 * 图片分类---列表view
 * Created by yuting on 2016/8/4.
 */
public interface GalleryView extends BaseView {
    void loadGallery(GalleryBean galleryBean);
}
