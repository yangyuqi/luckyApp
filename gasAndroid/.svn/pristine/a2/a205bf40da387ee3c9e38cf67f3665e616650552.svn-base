package com.yunqilai.consumer.luckyapp.Common.Ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ChangeOrderStatusBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ParseReasonData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ReasionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.GoodsOrderDetailsActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Presenter.OrderAllInfoActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class BottomDialogUI extends Dialog {
    private int selectPosition = -1;//用于记录用户选择的变量
    private Context context;
    private int layout ;

    private View view ;
    private String accessToken ;
    private Gson gson ;

    private List<ReasionBean> data = new ArrayList<>();

    private ListView rg ;
    private MyAdapter adapter ;
    private String orderId ;
    private ReasionBean bean ;

    private TextView tv_cancel ,tv_confirm ;

    private GoodsOrderDetailsActivity orderDetailsActivity ;
    private OrderAllInfoActivity allInfoActivity ;
    private String T_type ;

    public BottomDialogUI(Context context , int mlayout , String accesstoken, String morderId , Activity activity , String mtype) {
        super( context , R.style.BottomDialog);
        this.context = context;
        layout = mlayout ;
        accessToken = accesstoken ;
        gson = new Gson();
        orderId = morderId ;
        T_type = mtype ;
        if (T_type.equals("1")) {
            orderDetailsActivity = (GoodsOrderDetailsActivity) activity;
        }else if (T_type.equals("2")){
            allInfoActivity = (OrderAllInfoActivity) activity;
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater =LayoutInflater.from(context);
        view =inflater.inflate(layout, null);
        setContentView(view);
        initView(view);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        params.width = d.widthPixels;
        params.height = d.heightPixels;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

    private void initView(View view) {

        rg = (ListView) view.findViewById(R.id.rg);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_confirm = (TextView) view.findViewById(R.id.tv_confrim);
        initData();

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean==null){
                    Toast.makeText(context,"选择原因",Toast.LENGTH_SHORT).show();
                }else {
                    changOrdersState("cancel",orderId,accessToken,bean.getReasonId());
                }
            }
        });
    }

    private void initData() {
        data.clear();
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean(accessToken)), UrlUtils.CANCEL_ORDER_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    ParseReasonData reasonData = gson.fromJson(gson.toJson(entity.getData()),ParseReasonData.class);
                    data.addAll(reasonData.getList());
                    if (data.size()>0){
                        adapter = new MyAdapter(data);
                        rg.setAdapter(adapter);
                        rg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                selectPosition = position;
                                adapter.notifyDataSetChanged();
                                bean = data.get(position);
                            }
                        });
                    }
                }
            }
        });
    }

    public class MyAdapter extends BaseAdapter {
        List<ReasionBean> list;
        public MyAdapter(List<ReasionBean> mlist) {
            list = mlist ;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            MyViewHolder viewHolder = null ;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.radio_button_item, null);
                viewHolder = new MyViewHolder();
                viewHolder.rb = (RadioButton) convertView.findViewById(R.id.rb_rb);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.id_name);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (MyViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(list.get(position).getReason());
            if(selectPosition == position){
                viewHolder.rb.setChecked(true);
            }
            else{
                viewHolder.rb.setChecked(false);
            }
            return convertView;
        }
    }

    public class MyViewHolder{
        RadioButton rb ;
        TextView tv ;
    }

    private void changOrdersState(String cancel, String orderId, String accessToken,String reasionId) {
        final String type = gson.toJson(new ChangeOrderStatusBean(accessToken,orderId,cancel,reasionId));
        OkHttpClientManager.postAsynJson(type, UrlUtils.CHANGE_ORDER_INFO_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){
                    dismiss();
                    if (T_type.equals("1")) {
                        EventBus.getDefault().post("refresh");
                    }else if (T_type.equals("2")){
                        EventBus.getDefault().post("refresh");
                    }
                }
            }
        });
    }
}
