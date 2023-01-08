package com.zhashut.smartcity.park.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.park.entity.FeedbackDetail;

import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2023/1/9
 * Time: 2:17
 * Description: No Description
 */
public class FeedbackListAdapter extends BaseAdapter {
    private Context context;
    private List<FeedbackDetail> feedbackDetails;

    public FeedbackListAdapter(Context context, List<FeedbackDetail> feedbackDetails) {
        this.context = context;
        this.feedbackDetails = feedbackDetails;
    }

    @Override
    public int getCount() {
        return feedbackDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return feedbackDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View contentView, ViewGroup viewGroup) {
        ViewHandler handler;
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.park_feedback_item, null);
            handler = new ViewHandler();
            handler.tv_createTime = contentView.findViewById(R.id.tv_createTime);
            handler.tv_title = contentView.findViewById(R.id.tv_title);
            handler.tv_content = contentView.findViewById(R.id.tv_content);
            contentView.setTag(handler);
        } else {
            handler = (ViewHandler) contentView.getTag();
        }

        FeedbackDetail info = feedbackDetails.get(i);
        handler.tv_createTime.setText(info.createTime);
        handler.tv_title.setText(info.title);
        handler.tv_content.setText(info.content);

        return contentView;
    }

    class ViewHandler {
        public TextView tv_createTime;
        public TextView tv_title;
        public TextView tv_content;
    }
}
