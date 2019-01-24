package com.yunqilai.delivery.ui.fragment.dispatch;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Order;
import com.yunqilai.delivery.ui.adapter.CommonFragmentPagerAdapter;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.interlayer.dispatch.DispatchInterlayer;
import com.yunqilai.delivery.ui.presenter.dispatch.DispatchPresenter;
import com.yunqilai.delivery.ui.view.NoScrollViewPager;
import com.yunqilai.delivery.utils.ComStringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DispatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DispatchFragment extends BaseFragment<DispatchPresenter> implements DispatchInterlayer {

    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private List<Fragment> fragements;
    private CommonFragmentPagerAdapter adapter;


    public DispatchFragment() {
        // Required empty public constructor
    }

    public static DispatchFragment newInstance() {
        DispatchFragment fragment = new DispatchFragment();
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

        presenter = new DispatchPresenter(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispatch, container, false);

        initView(view);
        initData();


        presenter.onCreateView();

        initEvent();

        return view;
    }

    private void initEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(View view){
        tabLayout = (TabLayout) view.findViewById(R.id.tab_dispatch);
        viewPager = (NoScrollViewPager) view.findViewById(R.id.vp_dispatch);
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

        for(int i=0;i<datas.size();i++){
            switch (datas.get(i)){
                case SELF_WAIT_EXTRACT:
                    tabLayout.getTabAt(i).setText("自提订单待提取");
                    break;
                case SELF_COMPLETE:
                    tabLayout.getTabAt(i).setText("自提订单已完成");
                    break;

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
            }
        }

        viewPager.setOffscreenPageLimit(fragements.size());
    }
}
