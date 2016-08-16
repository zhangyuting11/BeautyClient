package com.yuting.beautyclient.bean;

import java.util.List;

/**
 * 图片分类--列表实体类
 * Created by yuting on 2016/8/5.
 */
public class GalleryBean extends ResultBean {
    public int total;//数据总数

    public List<Gallery> tngou;

    public class Gallery {
        public int id;
        public int galleryclass;//          图片分类
        public String title;//          标题
        public String img;//          图库封面
        public int count;//          访问数
        public int rcount;//           回复数
        public int fcount;//          收藏数
        public int size;//      图片多少张
    }
}
