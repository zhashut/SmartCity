package com.zhashut.smartcity.citysubway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.citysubway.entity.FeedbackListField;

import java.util.List;

public class ListFeedbackAdapter extends BaseAdapter {

    private Context context;

    private List<FeedbackListField> listFields;

    public ListFeedbackAdapter(Context context, List<FeedbackListField> listFields) {
        this.context = context;
        this.listFields = listFields;
    }

    @Override
    public int getCount() {
        return listFields.size();
    }

    @Override
    public Object getItem(int position) {
        return listFields.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.feedback_list_item,null);
            holder = new ViewHolder();
            holder.tv_title = view.findViewById(R.id.tv_title);
            holder.tv_content = view.findViewById(R.id.tv_content);
            holder.tv_time = view.findViewById(R.id.tv_time);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        FeedbackListField feedback = listFields.get(position);
        holder.tv_title.setText(feedback.title);
        holder.tv_content.setText(feedback.content);
        holder.tv_time.setText(feedback.createTime);
        return view;
    }

    class ViewHolder {
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_time;
    }
}
