package com.yunqilai.delivery.ui.fragment.order;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.adapter.CommonFragmentPagerAdapter;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.fragment.dispatch.DispatchListFragment;
import com.yunqilai.delivery.ui.interlayer.order.OrderInterlayer;
import com.yunqilai.delivery.ui.presenter.order.OrderPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.ui.view.NoScrollViewPager;
import com.yunqilai.delivery.utils.ComStringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends BaseFragment<OrderPresenter> implements OrderInterlayer {
    private CommonTitle commonTitle;
    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private List<Fragment> fragements;
    private CommonFragmentPagerAdapter adapter;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        presenter = new OrderPresenter(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        initView(view);
        initEvent();
        initData();


        presenter.onCreateView();

        return view;
    }

    private void initView(View view){
        commonTitle = (CommonTitle)view.findViewById(R.id.common_title);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_order);
        viewPager = (NoScrollViewPager) view.findViewById(R.id.vp_order);
    }
    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                presenter.search();
            }
        });
    }

    private void initData(){
        fragements = new ArrayList<>();
        adapter = new CommonFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragements);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setDatas(List<Order.STATU> datas) {
        fragements.clear();
        for(int i=0;i<datas.size();i++){
            fragements.add(DispatchListFragment.newInstance(ComStringUtils.getStatus(datas.get(i))));
        }
        adapter.notifyDataSetChanged();

        for(int i=0;i<tabLayout.getTabCount();i++){
            switch (datas.get(i)){
                case WAIT_ORDER:
                    tabLayout.getTabAt(i).setText(R.string.head_wait_order);
                    break;
                case WAIT_PICKUP:
                    tabLayout.getTabAt(i).setText(R.string.head_wait_pickup);
                    break;
                case WAIT_DELIVERY:
                    tabLayout.getTabAt(i).setText(R.string.head_wait_delivery);
                    break;
                case COMPLETE:
                    tabLayout.getTabAt(i).setText(R.string.head_complete);
                    break;
                case SELF_WAIT_EXTRACT:
                    tabLayout.getTabAt(i).setText(R.string.head_wait_extract);
                    break;
                case SELF_COMPLETE:
                    tabLayout.getTabAt(i).setText(R.string.head_complete);
                    break;
            }
        }

        viewPager.setOffscreenPageLimit(fragements.size());
    }
}
