package com.yuting.beautyclient.bean;

import java.util.List;

/**
 * 图片分类实体类
 * Created by yuting on 2016/8/4.
 */
public class GalleryclassBean extends ResultBean {
    public List<Galleryclass> tngou;

    public class Galleryclass {
        public int id;
        public String name;
        public String title;
        public String keywords;
        public String description;
        public int seq;//排序 从0。。。。10开始
    }
}
