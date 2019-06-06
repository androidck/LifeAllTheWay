package com.cloundwisdom.im.modules.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cloundwisdom.im.R;
import com.cloundwisdom.im.modules.entry.response.FindArticleEntity;
import com.hjq.base.view.BaseRecyclerViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends BaseRecyclerViewAdapter<ArticleAdapter.ViewHolder> {

    private List<FindArticleEntity> list;
    private Context context;

    public ArticleAdapter(Context context,List<FindArticleEntity> list) {
        super(context);
        this.context=context;
        this.list=list;
        if (this.list==null){
            this.list=new ArrayList<>();
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(viewGroup,R.layout.item_article);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(list.get(i).getCreateDate());
            viewHolder.itemTvTime.setText(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.itemTvReadCount.setText("阅读量："+list.get(i).getReadCount());
        viewHolder.itemTvTitle.setText(list.get(i).getTitile());
        Glide.with(context).load(list.get(i).getCoverPhoto()).into(viewHolder.itemImg);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder{

        TextView itemTvTitle,itemTvTime,itemTvReadCount;
        ImageView itemImg;

        public ViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
            itemTvTitle= (TextView) findViewById(R.id.item_tv_title);
            itemTvTime= (TextView) findViewById(R.id.item_tv_date_time);
            itemTvReadCount= (TextView) findViewById(R.id.item_tv_read_count);
            itemImg= (ImageView) findViewById(R.id.item_img);
        }
    }
}
