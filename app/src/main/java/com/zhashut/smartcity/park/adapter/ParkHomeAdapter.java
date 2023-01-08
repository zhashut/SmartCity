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
 * Time: 22:01
 * Description: No Description
 */
public class ParkHomeAdapter extends BaseAdapter {
    private Context context;
    private List<PressDetail> pressDetails;

    public ParkHomeAdapter(Context context, List<PressDetail> pressDetails) {
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
            contentView = LayoutInflater.from(context).inflate(R.layout.park_home_item, null);
            holder = new ViewHolder();
            holder.rl_press = contentView.findViewById(R.id.rl_press);
            holder.title = contentView.findViewById(R.id.tv_title);
            holder.readNum = contentView.findViewById(R.id.tv_read);
            holder.host = contentView.findViewById(R.id.iv_host);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        PressDetail info = pressDetails.get(i);
        String title = info.title.substring(0, 14) + "...";
        holder.title.setText(title);
        holder.readNum.setText(info.readNum + "");
        holder.host.setVisibility(info.hot.equals("Y") ? View.VISIBLE : View.VISIBLE);

        return contentView;
    }

    class ViewHolder {
        public RelativeLayout rl_press;
        public TextView title;
        public TextView readNum;
        public ImageView host;
    }
}
