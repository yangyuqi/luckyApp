package com.yunqilai.consumer.luckyapp.ShoppingCart;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterFragment;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.LoginActivity;
import com.yunqilai.consumer.luckyapp.Common.Ui.DeleteDialog;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.NewCartBeanData;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.GoodsDetailsActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.OrderInfoActivity;
import com.yunqilai.consumer.luckyapp.MainActivity;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.CartBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.CartUserBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.DeleteCartBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.EditCartNumBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.ShoppingCartUserBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.UserTokenBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.View.ShoppingCartView;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.DeleteDialogInterface;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.UserAttentionBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/22.
 */

public class ShoppingCartFragment extends BasePresenterFragment<ShoppingCartView> {

    double allMoney ;
    private String attention ;
    DecimalFormat df   = new DecimalFormat("######0.00");

    StringBuilder allshopCartId =new StringBuilder() ;
    private List<CartUserBean> num_data = new ArrayList<>();
    private List<NewCartBeanData> new_data = new ArrayList<>();

    @Override
    public Class<ShoppingCartView> getViewClass() {
        return ShoppingCartView.class;
    }

    @Override
    public void bindView() {
        super.bindView();

        String role = (String) SharedPreferencesUtils.getParam(context,SharedPreferencesUtils.role,"");
        if (role.equals("manager")||role.equals("delivery")){

        }else if (!getAccessToken().equals("")) {
            getAttention();
        }

        EventBus.getDefault().register(this);

        adapter = new ShoppingCartAdapter(data);
        vu.getLs().setAdapter(adapter);

        vu.getCb().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                allMoney = 0 ;
                for (int i = 0 ;i <adapter.getCount() ; i++) {
                    if (isChecked) {
                        adapter.map.put(i, (CartBean) adapter.getItem(i));
                        allMoney = allMoney + Float. parseFloat(((CartBean) adapter.getItem(i)).getPrice())*((CartBean) adapter.getItem(i)).getCount();
                    } else {
                        adapter.map.remove(i);
                        allMoney = 0 ;
                    }
                    vu.getTv_all_money().setText("￥"+df.format(allMoney));
                }
                adapter.notifyDataSetChanged();
            }
        });

        vu.getTv_edit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vu.getTv_edit().getText().toString().equals("编辑")){
                    vu.getTv_edit().setText("完成");
                    adapter.setEdsit(true);
                }else {
                    vu.getTv_edit().setText("编辑");
                    adapter.setEdsit(false);
                }
            }
        });

        vu.getTv_pay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!getAccessToken().equals("")) {
                    new_data.clear();
                    for (CartBean bean : adapter.map.values()) {
                        NewCartBeanData data = new NewCartBeanData(bean.getShopCartId(), null, null, bean.getCount(), bean.getIcon(), bean.getGoodsName(), "￥" + Float.parseFloat(bean.getPrice()) * bean.getCount(), String.valueOf(bean.getSpec()));
                        new_data.add(data);
                    }
                    if (new_data.size()>0) {
                        startActivity(new Intent(context, OrderInfoActivity.class));
                    }else {
                        Toast.makeText(context,"请选择商品",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Intent intent = new Intent(context,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        vu.getTv_go().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,ConfirmUserActivity.class);
                in.putExtra("flag","2");
                startActivity(in);
            }
        });


    }

    private void getAttention() {
        String s = gson.toJson(new AccessTokenBean(getAccessToken()));
        OkHttpClientManager.postAsynJson(s, UrlUtils.MY_ATTENTION_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                    UserAttentionBean bean = gson.fromJson(gson.toJson(entity.getData()), UserAttentionBean.class);
                    attention = bean.getStatus();
                    if (bean.getStatus().equals("not")){
                        vu.getView_show().setVisibility(View.VISIBLE);
                        vu.getView_show().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, ConfirmUserActivity.class);
                                intent.putExtra("flag","2");
                                startActivity(intent);
                            }
                        });
                    }else {
                        vu.getView_show().setVisibility(View.GONE);
                    }
                }else {
                    vu.getView_show().setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        String type = gson.toJson(new UserTokenBean(getAccessToken()));
        OkHttpClientManager.postAsynJson(type, UrlUtils.SHOPPING_CART_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                ShoppingCartUserBean bean = gson.fromJson(response,ShoppingCartUserBean.class);
                if (bean.getCode().equals(ResponseCodeUtils.OK)){
                    if (bean.getData().getList().size()>0){
                        vu.getTv_edit().setVisibility(View.VISIBLE);
                        vu.getRl_ls().setVisibility(View.VISIBLE);
                        adapter.setData(bean.getData().getList());
                    }else {
                        vu.getTv_edit().setVisibility(View.GONE);
                        List<CartBean> mm = new ArrayList<CartBean>();
                        adapter.setData(mm);
                        vu.getRl_ls().setVisibility(View.GONE);
                        vu.getView_no_data().setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private List<CartBean> data = new ArrayList<>();
    private ShoppingCartAdapter adapter ;

    public class ShoppingCartAdapter extends BaseAdapter{

        List<CartBean> data ;
        boolean editAll = false ;


        private HashMap<Integer,CartBean> map= new HashMap<>();// 存放已被选中的CheckBox


        public ShoppingCartAdapter(List<CartBean> mdata ){
            data = mdata ;
        }

        public void setData(List<CartBean> mdata){
            data = mdata ;
            notifyDataSetChanged();
        }

        public void setEdsit(boolean is){
            editAll = is ;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyViewHolder holder = null ;
            if (convertView == null){
                holder = new MyViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_ls_item,null);
                holder.writeView = convertView.findViewById(R.id.rl_test_q);
                holder.editView = convertView.findViewById(R.id.rl_test_w);
                holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
                holder.cb = (CheckBox) convertView.findViewById(R.id.cart_rb_q);
                holder.iv_edit = (ImageView) convertView.findViewById(R.id.iv_write);
                holder.tv_finish = (TextView) convertView.findViewById(R.id.tv_finish);
                holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
                holder.tv_goods_num = (EditText) convertView.findViewById(R.id.tv_num_goods);
                holder.iv_add =  convertView.findViewById(R.id.iv_add);
                holder.iv_reduce =  convertView.findViewById(R.id.iv_reduce);
                holder.deleteView = convertView.findViewById(R.id.ll_delete);
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content_cart);
                holder.tv_spec = (TextView) convertView.findViewById(R.id.tv_style_cart);
                convertView.setTag(holder);
            }else {
                holder = (MyViewHolder) convertView.getTag();
            }
            final MyViewHolder finalHolder = holder;
            Glide.with(context).load(UrlUtils.URL_BASE+data.get(position).getIcon()).into(finalHolder.iv_icon);
            finalHolder.tv_num.setText("×"+String.valueOf(data.get(position).getCount()));
            finalHolder.tv_content.setText(data.get(position).getGoodsName());
            finalHolder.tv_spec.setText("规格 : "+String.valueOf(data.get(position).getSpec()));
            finalHolder.tv_money.setText("￥"+String.valueOf(df.format(Double.parseDouble(data.get(position).getPrice()))));
            finalHolder.iv_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalHolder.writeView.setVisibility(View.GONE);
                    finalHolder.editView.setVisibility(View.VISIBLE);
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,GoodsDetailsActivity.class);
                    intent.putExtra("id",data.get(position).getGoodsId());
                    startActivity(intent);
                }
            });

            finalHolder.tv_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalHolder.editView.setVisibility(View.GONE);
                    finalHolder.writeView.setVisibility(View.VISIBLE);
                    final int current_num = data.get(position).getCount();
                    if (current_num -Integer.parseInt(finalHolder.tv_goods_num.getText().toString())>0) {//减少数量
                        if (Integer.parseInt(finalHolder.tv_goods_num.getText().toString())==0) {
                            Toast.makeText(context,"数量不能为0件",Toast.LENGTH_SHORT).show();
                        }else {
                            if (map.containsKey(position)) {
                                int current = current_num -Integer.parseInt(finalHolder.tv_goods_num.getText().toString());
                                allMoney = allMoney - current * Double.parseDouble(data.get(position).getPrice());
                                EventBus.getDefault().post(allMoney);
                        }
                            changeNum();
                        }
                    }else { //添加数量
                        if (Integer.parseInt(finalHolder.tv_goods_num.getText().toString())<=data.get(position).getStock()){
                                if (map.containsKey(position)) {
                                    int current = Integer.parseInt(finalHolder.tv_goods_num.getText().toString()) - current_num;
                                    allMoney = allMoney + current * Double.parseDouble(data.get(position).getPrice());
                                    EventBus.getDefault().post(allMoney);
                                }
                                changeNum();
                        }else {
                            Toast.makeText(context,"数量不要超过最大值",Toast.LENGTH_SHORT).show();
                            finalHolder.tv_goods_num.setText(""+data.get(position).getStock());
                            finalHolder.tv_num.setText("×"+data.get(position).getStock());
                        }
                    }
                }

                private void changeNum() {
                    CartUserBean bean = new CartUserBean(data.get(position).getShopCartId(),Integer.parseInt(finalHolder.tv_goods_num.getText().toString()));
                    num_data.add(bean);
                    String type = gson.toJson(new EditCartNumBean(getAccessToken(),num_data));
                    OkHttpClientManager.postAsynJson(type, UrlUtils.EDIT_NUMBER_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseCodeUtils.OK)){
                                finalHolder.tv_num.setText("×"+finalHolder.tv_goods_num.getText().toString());
                                initData();
                            }else {
                                Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            if (editAll){
                finalHolder.writeView.setVisibility(View.GONE);
                finalHolder.editView.setVisibility(View.VISIBLE);
            }else {
                finalHolder.editView.setVisibility(View.GONE);
                finalHolder.writeView.setVisibility(View.VISIBLE);
            }

            finalHolder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (map.containsKey(position)){
                        finalHolder.cb.setChecked(false);
                        map.remove(position);
                        allMoney = allMoney - Double.parseDouble(finalHolder.tv_money.getText().toString().substring(1))*data.get(position).getCount();
                    }else {
                        finalHolder.cb.setChecked(true);
                        map.put(position,data.get(position));
                        allMoney = allMoney + Double.parseDouble(finalHolder.tv_money.getText().toString().substring(1))*data.get(position).getCount();
                    }
                    EventBus.getDefault().post(allMoney);
                }
            });

            final MyViewHolder finalHolder1 = holder;
            finalHolder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(finalHolder.tv_goods_num.getText().toString());
                    int all = Integer.parseInt(finalHolder.tv_goods_num.getText().toString())+1;
                    if (all>=data.get(position).getStock()) {
                        finalHolder.tv_goods_num.setText(""+data.get(position).getStock());
                        Toast.makeText(context,"数量不要超过最大值",Toast.LENGTH_SHORT).show();
                    }else {
                        finalHolder.tv_goods_num.setText(String.valueOf(all));
                    }
                }
            });

            //全選取消選全
            if(map!=null&&map.containsKey(position)){
                finalHolder.cb.setChecked(true);
            }else {
                finalHolder.cb.setChecked(false);
            }


            finalHolder.tv_goods_num.setText(String.valueOf(data.get(position).getCount()));

            finalHolder.iv_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(finalHolder.tv_goods_num.getText().toString());
                    int all = Integer.parseInt(finalHolder.tv_goods_num.getText().toString())-1;
                    if (all>0) {
                        finalHolder.tv_goods_num.setText(String.valueOf(all));
                    }
                }
            });

            finalHolder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteDialog dialog = new DeleteDialog(context,"提示","确认要删除宝贝吗","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            if (isdelete){
                                vu.getCb().setChecked(false);
                                if (map.containsKey(position)) {
                                    allMoney = allMoney - Double.parseDouble(data.get(position).getPrice()) * data.get(position).getCount();
                                    EventBus.getDefault().post(allMoney);
                                }
                                deleteCartId();
                                data.remove(position);
                                map.remove(position);
                                initData();
                            }
                        }
                    });
                }

                private void deleteCartId() {
                    allshopCartId.append(data.get(position).getShopCartId() + ",");
                    final String type = gson.toJson(new DeleteCartBean(getAccessToken(), allshopCartId.toString()));
                    OkHttpClientManager.postAsynJson(type, UrlUtils.DELETE_CART_URL, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                            if (entity.getCode().equals(ResponseCodeUtils.OK)) {
                                notifyDataSetChanged();
                            } else {
                                Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            return convertView;
        }


        public class MyViewHolder{
            View writeView , editView ,deleteView;
            CheckBox cb ;
            TextView tv_num ,tv_finish ,tv_money  ,tv_content ,tv_spec;
            ImageView iv_edit ,iv_icon;
            View iv_add ,iv_reduce;
            EditText tv_goods_num ;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            initData();
        }else {
            vu.getCb().setChecked(false);
        }
    }

    @Subscribe
    public void onEvent(Double price){
        vu.getTv_all_money().setText("￥"+df.format(price));
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().post(new_data);
        vu.getCb().setChecked(true);
        vu.getCb().setChecked(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
