package com.zhashut.smartcity.park.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.entity.PressDetail;

import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/8
 * Time: 23:00
 * Description: No Description
 */
public class PressListAdapter extends BaseAdapter {
    private Context context;
    private List<PressDetail> pressDetails;

    public PressListAdapter(Context context, List<PressDetail> pressDetails) {
        this.context = context;
        this.pressDetails = pressDetails;
    }

    @Override
    public int getCount() {
        return pressDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return pressDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.press_list_item, null);
            holder = new ViewHolder();
            holder.tv_title = contentView.findViewById(R.id.tv_title);
            holder.tv_create = contentView.findViewById(R.id.tv_create);
            holder.tv_content = contentView.findViewById(R.id.tv_content);
            holder.tv_readNum = contentView.findViewById(R.id.tv_readNum);
            holder.tv_commentNum = contentView.findViewById(R.id.tv_commentNum);
            holder.tv_likeNum = contentView.findViewById(R.id.tv_likeNum);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        PressDetail info = pressDetails.get(i);
        holder.tv_title.setText("【" + info.title + "】");
        holder.tv_create.setText(info.createTime);
        holder.tv_content.setText(info.content.substring(0, 100) + "...");
        holder.tv_readNum.setText(info.readNum + "");
        holder.tv_commentNum.setText(info.commentNum + "");
        holder.tv_likeNum.setText(info.likeNum + "");
        return contentView;
    }

    class ViewHolder {
        public TextView tv_title;
        public TextView tv_create;
        public TextView tv_content;
        public TextView tv_readNum;
        public TextView tv_commentNum;
        public TextView tv_likeNum;
    }
}
