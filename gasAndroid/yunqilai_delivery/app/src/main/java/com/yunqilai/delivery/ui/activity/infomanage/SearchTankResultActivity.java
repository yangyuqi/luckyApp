package com.yunqilai.delivery.ui.activity.infomanage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.Info.InfoDetailsBean;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.adapter.InfoManageAdapter;
import com.yunqilai.delivery.ui.interlayer.infomanage.SearchTankResultInterlayer;
import com.yunqilai.delivery.ui.presenter.infomanage.SearchTankResultPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SearchTankResultActivity extends BaseActivity<SearchTankResultPresenter> implements SearchTankResultInterlayer,InfoManageAdapter.InfoManageListCallback {

    private CommonTitle commonTitle;
    private SpringView springView;
    private ListView listView;
    private InfoManageAdapter adapter;
    private List<InfoDetailsBean> tanks;

    private String keyWord ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_search_tank_result);

        keyWord = getIntent().getStringExtra("keyword");
        presenter = new SearchTankResultPresenter(this,this ,keyWord);

        initView();
        initEvent();
        initData();

        presenter.requestData();
    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        springView = (SpringView) findViewById(R.id.spring_view);
        listView = (ListView)findViewById(R.id.lv_tank_result_list);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });

    }

    private void initData(){
        tanks = new ArrayList<>();
        adapter = new InfoManageAdapter(this,tanks);
        adapter.setCallback(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void refreshDatas(List<InfoDetailsBean> tankList) {
        tanks.clear();
        tanks.addAll(tankList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void enterTankDetail(String tankId) {
        Intent intent = new Intent(this, TankDetailActivity.class);
        intent.putExtra("id",tankId);
        intent.putExtra("from","3");
        intent.putExtra("orderId","");
        startActivity(intent);
    }

    @Override
    public void replaceCode(InfoDetailsBean tankId) {
        presenter.toReplaceCode(tankId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event event){
        if (event == Event.EXITANDLOGIN) {
            finish();
        }
    }
}
