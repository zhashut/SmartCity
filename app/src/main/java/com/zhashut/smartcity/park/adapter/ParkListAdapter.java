package com.zhashut.smartcity.park.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.activity.ParkDetailActivity;
import com.zhashut.smartcity.park.entity.ParkListField;

import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/12/29
 * Time: 21:35
 * Description: No Description
 */
public class ParkListAdapter extends BaseAdapter {

    private Context context;
    private List<ParkListField> parkListFields;

    public ParkListAdapter(Context context, List<ParkListField> parkListFields) {
        this.context = context;
        this.parkListFields = parkListFields;
    }

    @Override
    public int getCount() {
        return parkListFields == null ? 0 : parkListFields.size();
    }

    @Override
    public Object getItem(int position) {
        return parkListFields.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.park_list_item, null);
            holder = new ViewHolder();
            holder.ll_park = convertView.findViewById(R.id.ll_park);
            holder.parkName = convertView.findViewById(R.id.tv_parkName);
            holder.distance = convertView.findViewById(R.id.tv_distance);
            holder.address = convertView.findViewById(R.id.tv_address);
            holder.vacancy = convertView.findViewById(R.id.tv_vacancy);
            holder.rates = convertView.findViewById(R.id.tv_rates);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ParkListField info = parkListFields.get(position);
        holder.parkName.setText(info.parkName);
        holder.distance.setText("距离" + info.distance + "米");
        holder.address.setText(info.address);
        holder.vacancy.setText(info.vacancy);
        holder.rates.setText(info.rates + "/h");
        holder.ll_park.setOnClickListener(view -> {
            Intent intent = new Intent(context, ParkDetailActivity.class);
            intent.putExtra("id", info.id);
            context.startActivity(intent);
        });
        return convertView;
    }

    class ViewHolder {
        public LinearLayout ll_park;
        public TextView parkName;
        public TextView distance;
        public TextView address;
        public TextView vacancy;
        public TextView rates;
    }

}
