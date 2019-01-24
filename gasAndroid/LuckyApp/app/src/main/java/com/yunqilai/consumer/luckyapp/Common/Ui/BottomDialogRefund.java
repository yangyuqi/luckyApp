package com.yunqilai.consumer.luckyapp.Common.Ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ParseReasonData;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ReasionBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyuqi on 17-7-28.
 */

public class BottomDialogRefund extends Dialog {

    private int selectPosition = -1;//用于记录用户选择的变量
    private Context context;
    private int layout ;

    private View view ;
    private String accessToken ;
    private Gson gson ;

    private ReasionBean reasonId = null ;

    private List<ReasionBean> data = new ArrayList<>();

    private ListView rg ;
    private MyAdapter adapter ;

    private TextView tv_cancel , tv_finish ;

    public BottomDialogRefund(Context context , int mlayout ,String accesstoken) {
        super( context , R.style.BottomDialog);
        this.context = context;
        layout = mlayout ;
        accessToken = accesstoken ;
        gson = new Gson();
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
        tv_finish = (TextView) view.findViewById(R.id.tv_confrim);
        initData();
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reasonId==null){
                    Toast.makeText(context,"请选择原因",Toast.LENGTH_SHORT).show();
                }else {
                    EventBus.getDefault().post(reasonId);
                    dismiss();
                }
            }
        });
    }



    private void initData() {
        data.clear();
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean(accessToken)), UrlUtils.REFUND_REASON_URL, new OkHttpClientManager.StringCallback() {
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
                                reasonId = data.get(position);
                            }
                        });
                    }
                }
            }
        });
    }

    public class MyAdapter extends BaseAdapter{
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
}
