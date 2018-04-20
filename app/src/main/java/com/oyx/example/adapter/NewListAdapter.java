package com.oyx.example.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.oyx.example.R;
import com.oyx.example.bean.NewsSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * des:新闻列表适配器
 * Created by xsf
 * on 2016.09.10:49
 */
public class NewListAdapter extends MultiItemRecycleViewAdapter<NewsSummary>
{
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM =1;

    public NewListAdapter(Context context, final List<NewsSummary> datas)
    {
        super(context, datas, new MultiItemTypeSupport<NewsSummary>()
        {

            @Override
            public int getLayoutId(int type) {
               if(type==TYPE_PHOTO_ITEM){
                   return R.layout.item_news_photo;
               }else{
                   return R.layout.item_news;
               }
            }

            @Override
            public int getItemViewType(int position, NewsSummary msg)
            {
                if (msg.getType()==1)
                {
                    return TYPE_ITEM;
                }
                return TYPE_PHOTO_ITEM;
            }
        });
    }

    @Override
    public void convert(ViewHolderHelper holder, NewsSummary newsSummary) {
        switch (holder.getLayoutId())
        {
            case R.layout.item_news:
                setItemValues(holder,newsSummary,getPosition(holder));
                break;
            case R.layout.item_news_photo:
                setPhotoItemValues(holder,newsSummary,getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     * @param holder
     * @param newsSummary
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final NewsSummary newsSummary, final int position) {
        String title = newsSummary.getTitle();

        String subtitle = newsSummary.getSubTitle();
        String imgSrc = newsSummary.getImgUrl();
//        holder.getConvertView().findViewById()
        holder.setText(R.id.news_summary_title_tv,title);
        holder.setText(R.id.news_summary_subtitle_tv,subtitle);
        holder.setImageUrl(R.id.news_summary_photo_iv,imgSrc);
    }


    /**
     * 图文样式
     * @param holder
     * @param position
     */
    private void setPhotoItemValues(ViewHolderHelper holder, final NewsSummary newsSummary, int position) {
        String imgurl = newsSummary.getImgUrl();
        holder.setImageUrl(R.id.photo_iv,imgurl);
    }


}
