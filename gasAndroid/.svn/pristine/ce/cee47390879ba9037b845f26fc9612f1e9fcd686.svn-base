package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Ui.DeleteDialog;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.CommonAdapter;
import com.yunqilai.consumer.luckyapp.Common.Utils.adapter.ViewHolder;
import com.yunqilai.consumer.luckyapp.HomePage.Model.GetPositionBean;
import com.yunqilai.consumer.luckyapp.HomePage.View.LocationManagerView;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AddressBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Interface.DeleteDialogInterface;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.LocationBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.LocationManagerBean;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public class LocationManagerActivity extends BasePresenterActivity<LocationManagerView> {

    private List<LocationBean> data = new ArrayList<>();
    private CommonAdapter<LocationBean> adapter ;
    private String type ;

    @Override
    protected Class<LocationManagerView> getViewClass() {
        return LocationManagerView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();


        type = getIntent().getStringExtra("flag");

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("1")){
                    setResult(2);
                    finish();
                }else {
                    finish();
                }
            }
        });


        adapter = new CommonAdapter<LocationBean>(context,data, R.layout.location_manager_layout_ls_item) {
            @Override
            public void convert(final ViewHolder helper, final LocationBean item) {
                final Map<Integer,Boolean> map = new HashMap<>();
                final TextView tv = (TextView) helper.getConvertView().findViewById(R.id.tv_change_name);
                final CheckBox box = (CheckBox) helper.getConvertView().findViewById(R.id.cart_rb_q);
                helper.setText(R.id.tv_name,item.getTrueName());
                helper.setText(R.id.tv_phone,item.getMobile());
                helper.setText(R.id.tv_address,item.getShengName()+item.getShiName()+item.getQuName()+item.getAreaInfo());
                helper.getView(R.id.location_manager_ls_item_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteDialog deleteDialog = new DeleteDialog(context,"提示","确定要删除该收货地址吗?","确定");
                        deleteDialog.show();
                        deleteDialog.OnDeleteBtn(new DeleteDialogInterface() {
                            @Override
                            public void isDelete(boolean isdelete) {
                                if (isdelete){
                                    data.remove(item);
                                    notifyDataSetChanged();
                                    deleteAddress(item.getId());
                                }
                            }

                            private void deleteAddress(String areaId) {
                                    String type = gson.toJson(new AddressBean(getAccessToken(),areaId));
                                    OkHttpClientManager.postAsynJson(type, UrlUtils.DELETE_ADDRESS_URL, new OkHttpClientManager.StringCallback() {
                                        @Override
                                        public void onFailure(Request request, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                                            Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            }
                        });
                    }
                });

                helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,AddLocationActivity.class);
                        intent.putExtra("info",item);
                        intent.putExtra("flag","2");
                        startActivity(intent);
                    }
                });

                box.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (map.get(helper.getPosition())){
                            tv.setText("设为默认");
                            box.setChecked(false);
                            map.put(helper.getPosition(),false);
                        }else {
                            tv.setText("默认地址");
                            box.setChecked(true);
                            map.put(helper.getPosition(),true);
                            changeCheckBox(item.getId());
                        }

                    }
                });

                if (item.getDefaultVal().equals("1")){
                    tv.setText("默认地址");
                    box.setChecked(true);
                    map.put(helper.getPosition(),true);
                }else {
                    tv.setText("设为默认");
                    box.setChecked(false);
                    map.put(helper.getPosition(),false);
                }
            }

            private void changeCheckBox(final String id) {
                OkHttpClientManager.postAsynJson(gson.toJson(new AddressBean(getAccessToken(), id)), UrlUtils.CHANGE_YES_ADDRESS_URL, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                        if (entity.getCode().equals(ResponseCodeUtils.OK)){
                            initData();
                            if (type.equals("1")){
                                Intent intent = new Intent();
                                intent.putExtra("addressId",id);
                                setResult(2,intent);
                                finish();
                            }
                        }
                    }
                });
            }
        };
        vu.getLs().setAdapter(adapter);
        vu.getLs().setEmptyView(vu.getLl_show());

        vu.getTv_add().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddLocationActivity.class);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void afterResume() {
        super.afterResume();
        initData();
    }

    private void initData() {
        data.clear();
        String type = gson.toJson(new GetPositionBean(getAccessToken(),1,8));
        OkHttpClientManager.postAsynJson(type, UrlUtils.GET_USER_ALL_ADDRESS, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                LocationManagerBean bean = gson.fromJson(response,LocationManagerBean.class);
                if (bean.getCode().equals(ResponseCodeUtils.OK)){
                    if (bean.getData().get("list").size()>0) {
                        data.addAll(bean.getData().get("list"));
                        adapter.setData(data);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }



    public class AddId{
        public String id ;
        public AddId(String string){
            id = string ;
        }
    }
}
