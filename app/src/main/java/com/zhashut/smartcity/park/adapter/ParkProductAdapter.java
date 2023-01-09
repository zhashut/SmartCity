package com.zhashut.smartcity.park.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.entity.ParkProductList;

import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/9
 * Time: 12:52
 * Description: No Description
 */
public class ParkProductAdapter extends BaseAdapter {
    private Context context;
    private List<ParkProductList> productLists;

    public ParkProductAdapter(Context context, List<ParkProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
    }

    @Override
    public int getCount() {
        return productLists.size();
    }

    @Override
    public Object getItem(int i) {
        return productLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        ViewHandler handler;
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.park_product_list, null);
            handler = new ViewHandler();
            handler.tv_name = contentView.findViewById(R.id.tv_name);
            handler.tv_price = contentView.findViewById(R.id.tv_price);
            handler.tv_score = contentView.findViewById(R.id.tv_score);
            handler.tv_createTime = contentView.findViewById(R.id.tv_createTime);
            contentView.setTag(handler);
        } else {
            handler = (ViewHandler) contentView.getTag();
        }

        ParkProductList info = productLists.get(i);
        handler.tv_name.setText(info.name);
        handler.tv_price.setText(info.price + "");
        handler.tv_score.setText(info.score + "");
        handler.tv_createTime.setText(info.createTime);

        return contentView;
    }

    class ViewHandler {
        public TextView tv_name;
        public TextView tv_price;
        public TextView tv_score;
        public TextView tv_createTime;
    }
}
