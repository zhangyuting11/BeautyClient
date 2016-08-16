package com.yuting.beautyclient.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yuting.beautyclient.R;
import com.yuting.beautyclient.bean.GalleryBean;
import com.yuting.beautyclient.common.Constant;
import com.yuting.beautyclient.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MainListAdapter
 * Created by yuting on 2016/8/5.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListHolder> {

    private Context context;
    private List<GalleryBean.Gallery> galleries = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void goImageDetail(int id);
    }

    public MainListAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<GalleryBean.Gallery> galleries) {
        this.galleries = galleries;
        this.notifyDataSetChanged();
    }

    @Override
    public MainListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false);
        return new MainListHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainListHolder holder, int position) {
        GalleryBean.Gallery gallery = galleries.get(position);
        holder.gallery = gallery;
        Glide.with(context)
                .load(Constant.BASE_IMAGE_URL + gallery.img)
                .asBitmap()
                .thumbnail(0.1f)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        holder.ivItem.getLayoutParams().width = ScreenUtils.getScreenWidth(context);
                        holder.ivItem.getLayoutParams().height = ScreenUtils.getScreenWidth(context) * resource.getHeight() / resource.getWidth();
                        holder.ivItem.setImageBitmap(resource);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return galleries.size();
    }

    class MainListHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivItem)
        ImageView ivItem;
        @Bind(R.id.cardView)
        CardView cardView;

        GalleryBean.Gallery gallery;

        @OnClick(R.id.ivItem)
        void goImageDetail() {
            if (onItemClickListener != null) {
                onItemClickListener.goImageDetail(gallery.id);
            }
        }

        public MainListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
