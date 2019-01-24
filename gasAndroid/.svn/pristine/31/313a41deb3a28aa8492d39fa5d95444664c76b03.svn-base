package com.yunqilai.consumer.luckyapp.Common.Ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.GoodsData;
import com.yunqilai.consumer.luckyapp.ChoicePage.Bean.GoodsToCartBean;
import com.yunqilai.consumer.luckyapp.Common.Model.AllGoodsCountBean;
import com.yunqilai.consumer.luckyapp.Common.Model.CloseLoginBean;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.LoginActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.AddCartBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.NewCartBeanData;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SpecBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.SpecGoodsBean;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.GoodsDetailsActivity;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.ReasionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class BottomDialog extends Dialog {

    DecimalFormat df   = new DecimalFormat("######0.00");
    private int selectPosition = -1;//用于记录用户选择的变量
    private Context context;

    private String comm_price ,goods_id ="" ,style_id ="" ,style_name = "",icon_path="";
    private int layout ;
    private int max_num ,max_weight;
    View view ;

    private View rl_add ,rl_reduce ;
    private TextView tv_price , tv_num ,tv_nu ,tv_confrim ,tv_content;
    private ImageView iv_close ,iv_goods_icon;
    private GridView gv ;
    private CommonAdapter<SpecGoodsBean> adapter ;
    private List<SpecGoodsBean> data = new ArrayList<>();
    private ImageView iv_add_icon ,iv_reduce_icon ;

    private MyAdapter my_adapter ;
    private List<NewCartBeanData> datas =new ArrayList<>();

    private String type ;

    public BottomDialog(Context context ,int mlayout ,String mtype) {
        super( context , R.style.BottomDialog);
        this.context = context;
        layout = mlayout ;
        type = mtype ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        EventBus.getDefault().register(this);
    }

    public View getView() {
        return view;
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
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_num = (TextView) view.findViewById(R.id.tv_num_count);
        iv_close = (ImageView) view.findViewById(R.id.btn_delete_iv);
        rl_add = view.findViewById(R.id.rl_add);
        rl_reduce = view.findViewById(R.id.rl_test_1);
        tv_content = (TextView) view.findViewById(R.id.tv_content_title);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        gv = (GridView) view.findViewById(R.id.gv_color);
        tv_nu = (TextView) view.findViewById(R.id.tv_tv_num);
        tv_confrim = (TextView) view.findViewById(R.id.tv_confrim_true);
        iv_add_icon = (ImageView) view.findViewById(R.id.iv_add_icon);
        iv_reduce_icon = (ImageView) view.findViewById(R.id.iv_reduce_icon);
        my_adapter = new MyAdapter(data);
        iv_goods_icon = (ImageView) view.findViewById(R.id.iv_goods_icon);
        gv.setAdapter(my_adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_nu.setText("1");
                selectPosition = position;
                my_adapter.notifyDataSetChanged();
                style_id = data.get(position).getId();
                max_num = Integer.parseInt(data.get(position).getCount());
            }
        });

        rl_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tv_nu.getText().toString())<max_num){
                    iv_add_icon.setImageResource(R.drawable.btn_add);
                    int nn = Integer.parseInt(tv_nu.getText().toString()) +1 ;
                    tv_nu.setText(String.valueOf(nn));
                    if (Integer.parseInt(tv_nu.getText().toString())!=0){
                        double price = Double.parseDouble(comm_price)*(Integer.parseInt(tv_nu.getText().toString()));
//                        tv_price.setText("￥"+ String.valueOf(price));
                    }
                }else {
                    iv_add_icon.setImageResource(R.drawable.btn_add_hl);
                    tv_nu.setText(String.valueOf(max_num));
                    Toast.makeText(context,"数量不要超过最大值",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rl_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tv_nu.getText().toString())>0){
                    int nn = Integer.parseInt(tv_nu.getText().toString()) - 1 ;
                    if (nn>=1) {
                        if (nn==1){
                            iv_reduce_icon.setImageResource(R.drawable.btn_reduce_hl);
                        }else {
                            iv_reduce_icon.setImageResource(R.drawable.btn_reduce);
                        }
                        tv_nu.setText(String.valueOf(nn));
                    }
                    if (Integer.parseInt(tv_nu.getText().toString())!=0) {
                        double price = Double.parseDouble(comm_price) * (Integer.parseInt(tv_nu.getText().toString()));
//                        tv_price.setText("￥" + String.valueOf(price));
                    }
                }
            }
        });

        tv_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accessToken = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.access_token,"");
                if (!accessToken.equals("")) {
                    if (!goods_id.equals("") && !style_id.equals("")) {
                        if (!StringUtils.getNumFromChar(tv_num.getText().toString()).startsWith("0")) {
                            if (type.equals("add")) {
                                GoodsToCartBean bean = new GoodsToCartBean(accessToken, new GoodsData(goods_id, style_id, Integer.parseInt(tv_nu.getText().toString())));
                                final Gson gson = new Gson();
                                String type = gson.toJson(bean);
                                OkHttpClientManager.postAsynJson(type, UrlUtils.ADD_TOCART_URL, new OkHttpClientManager.StringCallback() {
                                    @Override
                                    public void onFailure(Request request, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                        if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                            EventBus.getDefault().post(new NewCartBeanData("", goods_id, style_id, Integer.parseInt(tv_nu.getText().toString()), icon_path, tv_content.getText().toString(), "￥" + comm_price, style_name));
                                            dismiss();
                                        } else {
                                            Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                EventBus.getDefault().post(new NewCartBeanData("", goods_id, style_id, Integer.parseInt(tv_nu.getText().toString()), icon_path, tv_content.getText().toString(), "￥" + comm_price, style_name));
                                dismiss();
                            }
                        }else {
                            Toast.makeText(context, "商品库存不足", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "请选商品规格", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    EventBus.getDefault().post(new CloseLoginBean("close"));
                    dismiss();
                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void onEvent(AddCartBean bean){
        Gson gson = new Gson();
        if (bean.getSpec_bean().size()>0) {
            comm_price = df.format(Double.parseDouble(bean.getSpec_bean().get(0).getPrice())) ;
            tv_price.setText("价格 :" + "￥" + comm_price);
            data.addAll(bean.getSpec_bean());
            my_adapter.setData(data);
            max_num = Integer.parseInt(bean.getSpec_bean().get(0).getCount());
            goods_id = bean.getGoodsId();
            tv_content.setText(bean.getContent());
            icon_path = bean.getGoodsPhoto();
            Glide.with(context).load(UrlUtils.URL_BASE+bean.getGoodsPhoto()).into(iv_goods_icon);
        }
    }


    public class MyAdapter extends BaseAdapter {
        List<SpecGoodsBean> list;
        public MyAdapter(List<SpecGoodsBean> mlist) {
            list = mlist ;
        }

        public void setData(List<SpecGoodsBean> mlist){
            list = mlist ;
            notifyDataSetChanged();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.color_bg_goods, null);
                viewHolder = new MyViewHolder();
                viewHolder.rb = (RadioButton) convertView.findViewById(R.id.cb_checked);
//                viewHolder.tv = (TextView) convertView.findViewById(R.id.id_name);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (MyViewHolder) convertView.getTag();
            }
            viewHolder.rb.setText(list.get(position).getName());
            if(selectPosition == position){
                viewHolder.rb.setChecked(true);
                style_name = list.get(position).getName();
                viewHolder.rb.setBackgroundResource(R.drawable.round_blue_bg);
                viewHolder.rb.setTextColor(context.getResources().getColor(R.color.text_blue));
                comm_price = list.get(position).getPrice();
                tv_price.setText("价格 :"+"￥"+df.format(Double.parseDouble(list.get(position).getPrice())));
                tv_num.setText("库存"+list.get(position).getCount()+"件");
            }
            else{
                viewHolder.rb.setChecked(false);
                viewHolder.rb.setBackgroundResource(R.drawable.round_black_mid_bg);
                viewHolder.rb.setTextColor(context.getResources().getColor(R.color.text_black));
            }
            return convertView;
        }
    }

    public class MyViewHolder{
        RadioButton rb ;
        TextView tv ;
    }

    @Subscribe
    public void onEvent(SpecBean specBean){
        if (specBean!=null){
            if (data.size()>0){
                for (int i = 0 ;i<data.size();i++){
                    if (specBean.getSpecId().equals(data.get(i).getId())){
                        selectPosition = i;
                        my_adapter.notifyDataSetChanged();
                        style_id = data.get(i).getId();
                    }
                }

            }
        }
    }

    @Subscribe
    public void onEvent(AllGoodsCountBean goodsCount){
        tv_num.setText("总库存"+goodsCount.getGoodsCount()+"件");
    }

}
