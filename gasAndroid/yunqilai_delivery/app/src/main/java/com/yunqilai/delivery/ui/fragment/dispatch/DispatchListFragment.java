package com.yunqilai.delivery.ui.fragment.dispatch;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.OrderAllBean;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.request.base.network.OkHttpClientManager;
import com.yunqilai.delivery.ui.activity.order.OrderDetailActivity;
import com.yunqilai.delivery.ui.adapter.OrderListAdapter;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchListInterlayer;
import com.yunqilai.delivery.ui.presenter.dispatch.DispatchListPresenter;
import com.yunqilai.delivery.ui.view.NoScrollListView;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.UrlUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表fragment
 */
public class DispatchListFragment extends BaseFragment<DispatchListPresenter> implements DispatchListInterlayer,OrderListAdapter.OrderListCallBack {

    private static final String ARG_ORDER_STATU = "ARG_ORDER_STATU";

    private String statu = null;

    private SpringView springView;

    private TextView orderCountTv;
    private NoScrollListView listView;
    private OrderListAdapter orderListAdapter;
    private List<Order> orders;

    public DispatchListFragment() {
        // Required empty public constructor
    }

    /**
     * @param statu 订单的状态
     */
    public static DispatchListFragment newInstance(String statu) {
        DispatchListFragment fragment = new DispatchListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ORDER_STATU, statu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            statu = getArguments().getString(ARG_ORDER_STATU);
        }

        presenter = new DispatchListPresenter(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispatch_list, container, false);

        Log.e("ssss",statu);

        initView(view);
        initEvent();
        initData();

        return view;
    }

    private void initView(View view){
        springView = (SpringView)view.findViewById(R.id.spring_view);
        orderCountTv = (TextView)view.findViewById(R.id.tv_order_count);
        listView = (NoScrollListView)view.findViewById(R.id.lv_order_list);
    }

    private void initEvent(){
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
//        orderListAdapter = new OrderListAdapter(getActivity(),orders);
        listView.setAdapter(orderListAdapter);
        orderListAdapter.setCallBack(this);

//        Log.e("sssssss", String.valueOf(statu));
//        presenter.requestData();
        String type = gson.toJson(new OrderAllBean(getAccessToken(),"wait_order",1,20));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.GET_ALL_ORDER_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("ssssss",response);
            }
        });
    }

    @Override
    public void refreshDatas(List<Order> orderList) {
        orders.clear();
        orders.addAll(orderList);
        orderListAdapter.notifyDataSetChanged();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void enterOrderDetail(String orderId) {
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
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
}
