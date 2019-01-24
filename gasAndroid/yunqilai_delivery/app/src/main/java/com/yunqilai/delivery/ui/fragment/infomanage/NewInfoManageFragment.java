package com.yunqilai.delivery.ui.fragment.infomanage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Bean.Info.InfoDetailsBean;
import com.yunqilai.delivery.model.Bean.Info.InfoListData;
import com.yunqilai.delivery.model.Bean.Info.PutInfoBean;
import com.yunqilai.delivery.model.Bean.OrderDetailsBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.MainActivity;
import com.yunqilai.delivery.ui.activity.common.ScanCodeActivity;
import com.yunqilai.delivery.ui.activity.infomanage.SearchTankActivity;
import com.yunqilai.delivery.ui.activity.infomanage.TankDetailActivity;
import com.yunqilai.delivery.ui.fragment.order.BaseBaseFragment;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.ui.view.NoScrollListView;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;
import com.yunqilai.delivery.utils.adapter.CommonAdapter;
import com.yunqilai.delivery.utils.adapter.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.utils.ImageCaptureManager;
import rx.functions.Action1;

/**
 * Created by yangyuqi on 17-8-2.
 */

public class NewInfoManageFragment extends BaseBaseFragment {

    private View view ;

    private String role ;

    private CommonTitle commonTitle ;
    private SpringView springView;
    private TabLayout tabLayout ;
    private TextView countTv;
    private NoScrollListView listView;

    private List<InfoDetailsBean> in_deliverydata = new ArrayList<>();
    private List<InfoDetailsBean> in_usedata = new ArrayList<>();
    private List<InfoDetailsBean> has_relpacedata = new ArrayList<>();
    private List<InfoDetailsBean> in_depotdata = new ArrayList<>();
    private List<InfoDetailsBean> wait_checkdata = new ArrayList<>();
    private List<InfoDetailsBean> out_depotdata = new ArrayList<>();
    private List<InfoDetailsBean> in_checkdata = new ArrayList<>();
    private List<InfoDetailsBean> scrapdata = new ArrayList<>();
    private List<InfoDetailsBean> in_repairdata = new ArrayList<>();

    private int in_deliverypageNum = 1;
    private int in_usepageNum = 1;
    private int has_relpacepageNum = 1;
    private int in_depotpageNum = 1;
    private int wait_checkpageNum = 1;
    private int out_depotpageNum = 1;
    private int in_checkpageNum = 1;
    private int scrappageNum = 1;
    private int in_repairpageNum = 1;


    private CommonAdapter<InfoDetailsBean> adapter ;

    private int showCount = 30;

    private String current_place ;
    private View view_no_data ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        role = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.role,"");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_info_manger_layout,null);
        initView(view);
        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }

    private void initEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (role.equals("delivery")){
                    switch (tab.getPosition()){
                        case 0 : initData("in_delivery",1); current_place = "in_delivery" ;break;
                        case 1 : initData("in_use",1); current_place = "in_use" ;break;
                        case 2 : initData("has_relpace",1);current_place = "has_relpace" ;break;
                    }
                }else if (role.equals("manager")){
                    switch (tab.getPosition()){
                        case 0 : initData("in_depot",1);current_place = "in_depot" ;break;
                        case 1 : initData("wait_check",1);current_place = "wait_check" ;break;
                        case 2 : initData("out_depot",1);current_place = "out_depot" ;break;
                        case 3 : initData("in_check",1);current_place = "in_check" ;break;
                        case 4 : initData("scrap",1);current_place = "scrap" ;break;
                        case 5 : initData("in_repair",1);current_place = "in_repair" ;break;
                        case 6 : initData("has_relpace",1);current_place = "has_relpace" ;break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, TankDetailActivity.class);
                switch (current_place){
                    case "in_delivery":
                        intent.putExtra("id",in_deliverydata.get(position).getBarCode());
                        break;
                    case "in_use":
                        intent.putExtra("id",in_usedata.get(position).getBarCode());
                        break;
                    case "has_relpace":
                        intent.putExtra("id",has_relpacedata.get(position).getBarCode());
                        break;
                    case "in_depot":
                        intent.putExtra("id",in_depotdata.get(position).getBarCode());
                        break;
                    case "wait_check":
                        intent.putExtra("id",wait_checkdata.get(position).getBarCode());
                        break;
                    case "out_depot":
                        intent.putExtra("id",out_depotdata.get(position).getBarCode());
                        break;
                    case "in_check":
                        intent.putExtra("id",in_checkdata.get(position).getBarCode());
                        break;
                    case "scrap":
                        intent.putExtra("id",scrapdata.get(position).getBarCode());
                        break;
                    case "in_repair":
                        intent.putExtra("id",in_repairdata.get(position).getBarCode());
                        break;
                }

                intent.putExtra("from","1");
                intent.putExtra("orderId","");
                startActivity(intent);
            }
        });

        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {

                            Intent intent = new Intent(context, ScanCodeActivity.class);
                            context.startActivity(intent);
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(context, SearchTankActivity.class);
                context.startActivity(intent);
            }
        });

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                switch (current_place){
                    case "in_delivery":
                        in_deliverypageNum = 1;
                        initData(current_place,in_deliverypageNum);
                        break;
                    case "in_use":
                        in_usepageNum = 1;
                        initData(current_place,in_usepageNum);
                        break;
                    case "has_relpace":
                        has_relpacepageNum = 1;
                        initData(current_place,has_relpacepageNum);
                        break;
                    case "in_depot":
                        in_depotpageNum = 1;
                        initData(current_place,in_depotpageNum);
                        break;
                    case "wait_check":
                        wait_checkpageNum = 1;
                        initData(current_place,wait_checkpageNum);
                        break;
                    case "out_depot":
                        out_depotpageNum = 1;
                        initData(current_place,out_depotpageNum);
                        break;
                    case "in_check":
                        in_checkpageNum = 1;
                        initData(current_place,in_checkpageNum);
                        break;
                    case "scrap":
                        scrappageNum = 1;
                        initData(current_place,scrappageNum);
                        break;
                    case "in_repair":
                        in_repairpageNum = 1;
                        initData(current_place,in_repairpageNum);
                        break;
                }
            }

            @Override
            public void onLoadmore() {
                switch (current_place){
                    case "in_delivery":
                        ++in_deliverypageNum;
                        initData(current_place,in_deliverypageNum);
                        break;
                    case "in_use":
                        ++in_usepageNum;
                        initData(current_place,in_usepageNum);
                        break;
                    case "has_relpace":
                        ++has_relpacepageNum;
                        initData(current_place,has_relpacepageNum);
                        break;
                    case "in_depot":
                        ++in_depotpageNum;
                        initData(current_place,in_depotpageNum);
                        break;
                    case "wait_check":
                        ++wait_checkpageNum;
                        initData(current_place,wait_checkpageNum);
                        break;
                    case "out_depot":
                        ++out_depotpageNum;
                        initData(current_place,out_depotpageNum);
                        break;
                    case "in_check":
                        ++in_checkpageNum;
                        initData(current_place,in_checkpageNum);
                        break;
                    case "scrap":
                        ++scrappageNum;
                        initData(current_place,scrappageNum);
                        break;
                    case "in_repair":
                        ++in_repairpageNum;
                        initData(current_place,in_repairpageNum);
                        break;
                }
            }
        });

    }

    private void initView(View view) {
        commonTitle = (CommonTitle) view.findViewById(R.id.common_title);
        springView = (SpringView)view.findViewById(R.id.spring_view);
        countTv = (TextView)view.findViewById(R.id.tv_count);
        listView = (NoScrollListView)view.findViewById(R.id.lv_tank_list);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_info_manage);
        view_no_data = view.findViewById(R.id.rl_no_data);
        if (role.equals("delivery")){
            tabLayout.addTab(tabLayout.newTab().setText(R.string.head_in_delivery));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.head_in_use));
            tabLayout.addTab(tabLayout.newTab().setText("已补码"));
        }else if (role.equals("manager")){
            tabLayout.addTab(tabLayout.newTab().setText(R.string.head_in_depot));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.head_enter_depot));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.head_out_depot));
            tabLayout.addTab(tabLayout.newTab().setText(R.string.head_in_check));
            tabLayout.addTab(tabLayout.newTab().setText("报废"));
            tabLayout.addTab(tabLayout.newTab().setText("维修中"));
            tabLayout.addTab(tabLayout.newTab().setText("已补码"));
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        List<InfoDetailsBean> data = new ArrayList<>();
        adapter = new CommonAdapter<InfoDetailsBean>(context,data,R.layout.listview_info_manage_list) {
            @Override
            public void convert(ViewHolder helper, InfoDetailsBean item) {
                helper.setText(R.id.tv_bar_code,item.getBarCode());
                helper.setText(R.id.tv_tank_code,item.getTankNumber());
                helper.setText(R.id.tv_tank_model,item.getModel());
                helper.setText(R.id.tv_produce_time,item.getProduceTime());
            }
        };

        listView.setAdapter(adapter);

        listView.setEmptyView(view_no_data);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (role.equals("delivery")) {
            if(null == current_place || "".equals(current_place)){
                current_place = "in_delivery";
            }
            initData(current_place,1);
        }else if (role.equals("manager")){
            if(null == current_place || "".equals(current_place)){
                current_place = "in_depot";
            }
            initData(current_place,1);
        }
    }

    private void initData(String status, final int pagenum) {
        current_place = status;

        String type = gson.toJson(new PutInfoBean(getAccessToken(),status,pagenum,showCount,""));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.GET_USER_MONEY_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("ssssssss",response);
                springView.onFinishFreshAndLoad();
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        InfoListData mdata = gson.fromJson(gson.toJson(entity.getData()), InfoListData.class);
                        countTv.setText(String.valueOf(mdata.getCount()));
                        switch (current_place){
                            case "in_delivery":
                                if(pagenum == 1){
                                    in_deliverydata.clear();
                                }
                                break;
                            case "in_use":
                                if(pagenum == 1){
                                    in_usedata.clear();
                                }
                                break;
                            case "has_relpace":
                                if(pagenum == 1){
                                    has_relpacedata.clear();
                                }
                                break;
                            case "in_depot":
                                if(pagenum == 1){
                                    in_depotdata.clear();
                                }
                                break;
                            case "wait_check":
                                if(pagenum == 1){
                                    wait_checkdata.clear();
                                }
                                break;
                            case "out_depot":
                                if(pagenum == 1){
                                    out_depotdata.clear();
                                }
                                break;
                            case "in_check":
                                if(pagenum == 1){
                                    in_checkdata.clear();
                                }
                                break;
                            case "scrap":
                                if(pagenum == 1){
                                    scrapdata.clear();
                                }
                                break;
                            case "in_repair":
                                if(pagenum == 1){
                                    in_repairdata.clear();
                                }
                                break;
                        }
                        /*data.addAll(mdata.getTankList());
                        if (data.size() > 0) {
                            adapter.notifyDataSetChanged();
                        } else {
                            data.clear();
                            adapter.notifyDataSetChanged();
                        }*/

                        switch (current_place){
                            case "in_delivery":
                                    in_deliverydata.addAll(mdata.getTankList());
                                adapter.setData(in_deliverydata);
                                break;
                            case "in_use":
                                    in_usedata.addAll(mdata.getTankList());
                                adapter.setData(in_usedata);
                                break;
                            case "has_relpace":
                                    has_relpacedata.addAll(mdata.getTankList());
                                adapter.setData(has_relpacedata);
                                break;
                            case "in_depot":
                                    in_depotdata.addAll(mdata.getTankList());
                                adapter.setData(in_depotdata);
                                break;
                            case "wait_check":
                                    wait_checkdata.addAll(mdata.getTankList());
                                adapter.setData(wait_checkdata);
                                break;
                            case "out_depot":
                                    out_depotdata.addAll(mdata.getTankList());
                                adapter.setData(out_depotdata);
                                break;
                            case "in_check":
                                    in_checkdata.addAll(mdata.getTankList());
                                adapter.setData(in_checkdata);
                                break;
                            case "scrap":
                                    scrapdata.addAll(mdata.getTankList());
                                adapter.setData(scrapdata);
                                break;
                            case "in_repair":
                                    in_repairdata.addAll(mdata.getTankList());
                                adapter.setData(in_repairdata);
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}

