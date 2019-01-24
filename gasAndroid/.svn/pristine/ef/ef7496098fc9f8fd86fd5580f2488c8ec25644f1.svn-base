package com.yunqilai.delivery.ui.fragment.infomanage;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.Info.InfoDetailsBean;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.activity.infomanage.TankDetailActivity;
import com.yunqilai.delivery.ui.adapter.InfoManageAdapter;
import com.yunqilai.delivery.ui.adapter.OrderListAdapter;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.interlayer.infomanage.InfoManageListInterlayer;
import com.yunqilai.delivery.ui.presenter.infomanage.InfoManageListPresenter;
import com.yunqilai.delivery.ui.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

public class InfoManageListFragment extends BaseFragment<InfoManageListPresenter> implements InfoManageListInterlayer,InfoManageAdapter.InfoManageListCallback {

    private static final String ARG_TANK_STATU = "ARG_TANK_STATU";

    private Tank.STATU statu = Tank.STATU.NULL;

    private SpringView springView;

    private TextView countTv;
    private NoScrollListView listView;
    private InfoManageAdapter adapter;
    private List<Tank> tanks;

    public InfoManageListFragment() {
        // Required empty public constructor
    }

    public static InfoManageListFragment newInstance(Tank.STATU statu) {
        InfoManageListFragment fragment = new InfoManageListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TANK_STATU, statu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            statu = (Tank.STATU)getArguments().getSerializable(ARG_TANK_STATU);
        }
        presenter = new InfoManageListPresenter(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_manage_list, container, false);

        initView(view);
        initEvent();
        initData();

        return view;
    }

    private void initView(View view){
        springView = (SpringView)view.findViewById(R.id.spring_view);
        countTv = (TextView)view.findViewById(R.id.tv_count);
        listView = (NoScrollListView)view.findViewById(R.id.lv_tank_list);
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
        tanks = new ArrayList<>();
//        adapter = new InfoManageAdapter(getActivity(),tanks);
        listView.setAdapter(adapter);
        adapter.setCallback(this);

        presenter.requestData();
    }

    @Override
    public void refreshDatas(List<Tank> tankList) {
        tanks.clear();
        tanks.addAll(tankList);
        adapter.notifyDataSetChanged();
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void enterTankDetail(String tankId) {
        Intent intent = new Intent(getActivity(), TankDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void replaceCode(InfoDetailsBean tankId) {
    }
}
