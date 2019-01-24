package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Model.ParseBaseEntity;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.StringUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.HomePage.View.AddLocationView;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AccessTokenBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AddressBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AreaInfoBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.AreaInfoChildBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.EditAddressBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.FlatBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.ParseAddressDetailsBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.ParseFlatBean;
import com.yunqilai.consumer.luckyapp.ShoppingCart.Bean.PostPositionBean;
import com.yunqilai.consumer.luckyapp.UserCenter.Model.LocationBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public class AddLocationActivity extends BasePresenterActivity<AddLocationView> {

    LocationBean bean ;

    String flag ;

    private List<AreaInfoChildBean> options1Items = new ArrayList<>();
    private List<List<AreaInfoChildBean>> options2Items = new ArrayList<>();
    private List<List<List<AreaInfoChildBean>>> options3Items = new ArrayList<>();

    private List<String> list_data = new ArrayList<>();
    private List<List<String>> list_data_two = new ArrayList<>();
    private List<List<List<String>>> list_data_third = new ArrayList<>();


    private OptionsPickerView pvCustomTime ;

    private ArrayList<String> flat = new ArrayList<>();
    private ArrayList<ArrayList<String>> floor = new ArrayList<>();
    private ArrayList<String> test_one = new ArrayList<>();
    private ArrayList<String> test_two = new ArrayList<>();

    private ArrayList<FlatBean> flat_data = new ArrayList<>();
    private ArrayList<FlatBean> flat_data_one = new ArrayList<>();
    private ArrayList<ArrayList<FlatBean>> floor_data = new ArrayList<>();


    private String areaId ;
    private String address_str ;
    private String  liftId ,lftName;
    private String defaultVal = "0" ;
    ProgressDialog dialog2 ;

    @Override
    protected Class<AddLocationView> getViewClass() {
        return AddLocationView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

//        initArea(0);
        flag = getIntent().getStringExtra("flag");
        if (flag.equals("1")){
            vu.getTv_title().setText("新增地址");
        }else {
            vu.getTv_title().setText("编辑地址");
            bean = (LocationBean) getIntent().getSerializableExtra("info");
            vu.getEdt_name().setText(bean.getTrueName());
            vu.getEdt_phone().setText(bean.getMobile());
            vu.getTv_address().setText(bean.getShengName()+bean.getShiName()+bean.getQuName());
            vu.getEdt_details().setText(bean.getAreaInfo());
            defaultVal = bean.getDefaultVal();
            areaId = bean.getQuId();
            OkHttpClientManager.postAsynJson(gson.toJson(new AddressBean(getAccessToken(), bean.getId())), UrlUtils.GET_ADDRESS_DETAILS, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                        ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                        if (entity.getCode().equals(ResponseCodeUtils.OK)){
                            ParseAddressDetailsBean bean = gson.fromJson(gson.toJson(entity.getData()),ParseAddressDetailsBean.class);
                            liftId = bean.getFloor().getFloorId();
                            String ceng = null ;
                            if (bean.getFloor().getLift().equals("0")){
                                ceng = "电梯房";
                            }else {
                                ceng = "非电梯房";
                            }
                            vu.getTv_flat().setText(ceng+bean.getFloor().getTitle()+"层");
                        }
                }
            });
        }

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getAdd_addr().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInput(AddLocationActivity.this,vu.getEdt_phone());
                initArea(1);
            }
        });
        vu.getView_flat().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hideInput(AddLocationActivity.this,vu.getEdt_phone());
                getflat();
            }
        });

        vu.getBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAddress();
            }
        });

        vu.getCb().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    defaultVal = "1";
                }else {
                    defaultVal = "0";
                }
            }
        });
    }

    private void hideInput(Context context, View view){
        InputMethodManager inputMethodManager =
                (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    private void postAddress() {
        String url = null ;
        String type = null;

        if (vu.getEdt_name().getText().toString().length() == 0) {
            Toast.makeText(context,"收货人不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!StringUtils.checkChar_CEN50(vu.getEdt_name().getText().toString())){
            Toast.makeText(context,"收货人只可是中文、字母、数字",Toast.LENGTH_SHORT).show();
            return;
        }
        if (vu.getEdt_phone().getText().toString().length() == 0) {
            Toast.makeText(context,"手机号不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!StringUtils.checkMobileNumber(vu.getEdt_phone().getText().toString())){
            Toast.makeText(context,"手机号格式错误",Toast.LENGTH_SHORT).show();
            return;
        }
        if (vu.getEdt_details().getText().toString().length() == 0) {
            Toast.makeText(context,"地址不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if (vu.getEdt_details().getText().toString().length()<5) {
            Toast.makeText(context,"收货人地址长度不能大于50个字符,小于5个字符",Toast.LENGTH_SHORT).show();
            return;
        }

        if(areaId == null || "".equals(areaId)){
            Toast.makeText(context,"请选择所在地区",Toast.LENGTH_SHORT).show();
            return;
        }
        if(liftId == null || "".equals(liftId)){
            Toast.makeText(context,"请选择户型",Toast.LENGTH_SHORT).show();
            return;
        }

        if (flag.equals("1")) {
            url = UrlUtils.ADD_NEW_ADDRESS_URL;
            type = gson.toJson(new PostPositionBean(getAccessToken(), vu.getEdt_name().getText().toString(), vu.getEdt_phone().getText().toString(), areaId, vu.getEdt_details().getText().toString(), liftId, defaultVal));
        } else {
            url = UrlUtils.EDIT_ADRRRESS_URL;
            type = gson.toJson(new EditAddressBean(getAccessToken(), vu.getEdt_name().getText().toString(), vu.getEdt_phone().getText().toString(), areaId, vu.getEdt_details().getText().toString(), liftId, defaultVal, bean.getId()));
        }
        OkHttpClientManager.postAsynJson(type, url, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                    ParseBaseEntity entity = gson.fromJson(response,ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseCodeUtils.OK)){
                        finish();
                    }else {
                        Toast.makeText(context,entity.getMsg(),Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void getflat() {
//        final ProgressDialog dialog2 = ProgressDialog.show(this, "提示", "正在加载数据");
//        dialog2.show();
        OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean(getAccessToken())), UrlUtils.GET_SUPPORT_FLAT_URL, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
//                dialog2.dismiss();
                flat.clear();
                floor.clear();test_one.clear();test_two.clear();flat_data.clear();flat_data_one.clear();floor_data.clear();
                ParseFlatBean entity = gson.fromJson(response,ParseFlatBean.class);
                if (entity.getCode().equals(ResponseCodeUtils.OK)){

                    if (entity.getData().getHasLift().size()>0){
                        flat.add("电梯房");
                        flat_data_one.addAll(entity.getData().getHasLift());
                    }

                    if (entity.getData().getNoLift().size()>0){
                        flat.add("非电梯房");
                        flat_data.addAll(entity.getData().getNoLift());
                    }

                    floor_data.add(flat_data_one);
                    floor_data.add(flat_data);
                    for (int i = 0 ;i<flat_data.size();i++){
                        test_one.add(flat_data.get(i).getTitle()+"层");
                    }
                    for (int j = 0 ;j<flat_data_one.size();j++){
                        test_two.add(flat_data_one.get(j).getTitle()+"层");
                    }
                    floor.add(test_two);
                    floor.add(test_one);


                    pvCustomTime = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {

                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {

                            liftId = floor_data.get(options1).get(options2).getFloorId();
                            vu.getTv_flat().setText(flat.get(options1)+floor_data.get(options1).get(options2).getTitle()+"层");
                        }
                    }).setTitleText("选择电梯").build();
                    pvCustomTime.setPicker(flat, floor);
                    pvCustomTime.show();
                }
            }
        });
    }

    private void initDialog() {
        OptionsPickerView pickerView = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getAreaName()+
                        options2Items.get(options1).get(options2).getAreaName()+
                        options3Items.get(options1).get(options2).get(options3).getAreaName();

                areaId = options3Items.get(options1).get(options2).get(options3).getId();

                if (tx!=null&&!tx.equals(""))
                {
                    vu.getTv_address().setText(tx);
                    address_str = tx ;
                }

                Toast.makeText(context,tx,Toast.LENGTH_SHORT).show();
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        pickerView.setPicker(list_data, list_data_two,list_data_third);//三级选择器
        pickerView.show();
    }

    private void initArea(int type) {
        if(type == 1){
            dialog2 = ProgressDialog.show(this, "提示", "正在加载数据");
            String areaall = (String) SharedPreferencesUtils.getParam(this,SharedPreferencesUtils.AREAALL,"");
            if("".equals(areaall) || null == areaall){
                OkHttpClientManager.postAsynJson(gson.toJson(new AccessTokenBean("")), UrlUtils.GET_ADDERESS_BASE_URL, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        dialog2.dismiss();
                    }

                    @Override
                    public void onResponse(String response) {
                        SharedPreferencesUtils.setParam(AddLocationActivity.this,SharedPreferencesUtils.AREAALL,response);
                        initAddress();
                    }
                });
            }else{
                initAddress();
            }
        }


    }



    private void initAddress() {
        dialog2.dismiss();
        String areaall = (String) SharedPreferencesUtils.getParam(this,SharedPreferencesUtils.AREAALL,"");
        AreaInfoBean bean = gson.fromJson(areaall,AreaInfoBean.class);
        if (bean.getCode().equals(ResponseCodeUtils.OK)){
            options1Items.addAll(bean.getData().get("areaList"));

            for (int q = 0 ; q<options1Items.size();q++){
                list_data.add(options1Items.get(q).getAreaName());
            }


            for (int i = 0 ;i<options1Items.size();i++){
                List<AreaInfoChildBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
                List<List<AreaInfoChildBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                List<String> c_city = new ArrayList<>();
                List<List<String>> p_city = new ArrayList<>();


                for (int c = 0 ;c<options1Items.get(i).getChilds().size();c++) {
                    AreaInfoChildBean childBean = options1Items.get(i).getChilds().get(c);
                    CityList.add(childBean);//添加城市

                    c_city.add(options1Items.get(i).getChilds().get(c).getAreaName());


                    List<AreaInfoChildBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                    List<String> c_list = new ArrayList<>();

                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (options1Items.get(i).getChilds().get(c).getAreaName() == null
                            || options1Items.get(i).getChilds().get(c).getChilds().size() == 0) {
                        City_AreaList.add(new AreaInfoChildBean("", "", null));
                        c_list.add("");
                    } else {
                        for (int d = 0; d < options1Items.get(i).getChilds().get(c).getChilds().size(); d++) {//该城市对应地区所有数据
                            AreaInfoChildBean AreaName = options1Items.get(i).getChilds().get(c).getChilds().get(d);

                            City_AreaList.add(AreaName);//添加该城市所有地区数据
                            c_list.add(AreaName.getAreaName());
                        }
                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                        p_city.add(c_list);
                    }
                }

                /**
                 * 添加城市数据
                 */
                options2Items.add(CityList);

                /**
                 * 添加地区数据
                 */
                options3Items.add(Province_AreaList);

                list_data_two.add(c_city);
                list_data_third.add(p_city);
            }

            if (options2Items.size()>0&&options3Items.size()>0&&options1Items.size()>0){
                initDialog();
            }
        }
    }

}

