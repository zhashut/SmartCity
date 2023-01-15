package com.zhashut.smartcity.park.adapter;

import static com.zhashut.smartcity.park.constant.constant.PARK_CONSUME;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zhashut.smartcity.R;
import com.zhashut.smartcity.common.ReqCallback;
import com.zhashut.smartcity.common.ReqResult;
import com.zhashut.smartcity.common.ResultEntity;
import com.zhashut.smartcity.park.activity.ParkHomeActivity;
import com.zhashut.smartcity.park.entity.ParkProductList;

import org.json.JSONObject;

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
    private String token;
    ReqCallback callback = new ReqCallback();
    private Handler handler;

    public ParkProductAdapter(Context context, List<ParkProductList> productLists, String token, Handler handler) {
        this.context = context;
        this.productLists = productLists;
        this.token = token;
        this.handler = handler;
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
        ViewHandler viewhandler;
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(R.layout.park_product_list, null);
            viewhandler = new ViewHandler();
            viewhandler.tv_name = contentView.findViewById(R.id.tv_name);
            viewhandler.tv_price = contentView.findViewById(R.id.tv_price);
            viewhandler.tv_score = contentView.findViewById(R.id.tv_score);
            viewhandler.btn_exchange = contentView.findViewById(R.id.btn_exchange);
            contentView.setTag(viewhandler);
        } else {
            viewhandler = (ViewHandler) contentView.getTag();
        }

        ParkProductList info = productLists.get(i);
        viewhandler.tv_name.setText(info.name);
        viewhandler.tv_price.setText(info.price + "");
        viewhandler.tv_score.setText(info.score + "");
        viewhandler.btn_exchange.setOnClickListener(v -> {
            callback.CallBackPostByIDWithToken(PARK_CONSUME, token, info.id, new JSONObject(), handler, ResultEntity.class);
        });
        return contentView;
    }

    class ViewHandler {
        public TextView tv_name;
        public TextView tv_price;
        public TextView tv_score;
        public Button btn_exchange;
    }
}
