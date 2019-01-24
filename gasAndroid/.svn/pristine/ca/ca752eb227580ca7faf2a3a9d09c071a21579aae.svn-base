package com.yunqilai.delivery.ui.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.OrderDetailsBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.adapter.OrderListAdapter;
import com.yunqilai.delivery.ui.interlayer.order.SearchOrderResultInterlayer;
import com.yunqilai.delivery.ui.presenter.order.SearchOrderResultPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SearchOrderResultActivity extends BaseActivity<SearchOrderResultPresenter> implements SearchOrderResultInterlayer,OrderListAdapter.OrderListCallBack {
    private CommonTitle commonTitle;
    private SpringView springView;
    private ListView listView;
    private OrderListAdapter orderListAdapter;
    private List<OrderDetailsBean> orders;

    private String keyword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_search_order_result);

        keyword = getIntent().getStringExtra("desc");

        presenter = new SearchOrderResultPresenter(this,this,keyword);

        initView();
        initEvent();
        initData();

    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        springView = (SpringView)findViewById(R.id.spring_view);
        listView = (ListView)findViewById(R.id.lv_order_result_list);
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
                presenter.requestData();
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
        orders = new ArrayList<>();
        orderListAdapter = new OrderListAdapter(this,orders);
        listView.setAdapter(orderListAdapter);
        orderListAdapter.setCallBack(this);
        presenter.requestData();
    }

    @Override
    public void refreshDatas(List<OrderDetailsBean> orderList) {
        orders.clear();
        orders.addAll(orderList);
        orderListAdapter.notifyDataSetChanged();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void enterOrderDetail(String orderId) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("orderId",orderId);
        startActivity(intent);
    }

    @Override
    public void call(String phone) {
        presenter.callPhone(phone);
    }

    @Override
    public void surePickup(String orderId) {
        presenter.pickup(orderId);
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
