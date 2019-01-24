package com.yunqilai.delivery.ui.fragment.infomanage;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.ui.adapter.CommonFragmentPagerAdapter;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.interlayer.infomanage.InfoManageInterlayer;
import com.yunqilai.delivery.ui.presenter.infomanage.InfoManagePresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.ui.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoManageFragment extends BaseFragment<InfoManagePresenter> implements InfoManageInterlayer {

    private CommonTitle commonTitle;
    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private List<Fragment> fragements;
    private CommonFragmentPagerAdapter adapter;

    public InfoManageFragment() {
        // Required empty public constructor
    }

    public static InfoManageFragment newInstance() {
        InfoManageFragment fragment = new InfoManageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        presenter = new InfoManagePresenter(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_manage, container, false);

        initView(view);
        initEvent();
        initData();

        presenter.onCreateView();

        return view;
    }

    private void initView(View view){
        commonTitle = (CommonTitle) view.findViewById(R.id.common_title);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_info_manage);
        viewPager = (NoScrollViewPager) view.findViewById(R.id.vp_info_manage);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                presenter.scan();
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
    public void setDatas(List<Tank.STATU> datas) {
        fragements.clear();
        for(int i=0;i<datas.size();i++){
            fragements.add(InfoManageListFragment.newInstance(datas.get(i)));
        }
        adapter.notifyDataSetChanged();

        for(int i=0;i<tabLayout.getTabCount();i++){
            switch (datas.get(i)){
                case IN_DEPOT:
                    tabLayout.getTabAt(i).setText(R.string.head_in_depot);
                    break;
                case ENTER_DEPOT:
                    tabLayout.getTabAt(i).setText(R.string.head_enter_depot);
                    break;
                case OUT_DEPOT:
                    tabLayout.getTabAt(i).setText(R.string.head_out_depot);
                    break;
                case IN_CHECK:
                    tabLayout.getTabAt(i).setText(R.string.head_in_check);
                    break;
                case IN_DELIVERY:
                    tabLayout.getTabAt(i).setText(R.string.head_in_delivery);
                    break;
                case IN_USE:
                    tabLayout.getTabAt(i).setText(R.string.head_in_use);
                    break;
                case IN_CODE:
                    tabLayout.getTabAt(i).setText("已补码");
                    break;
            }
        }
        //TODO 后面通过登陆者的权限来区分

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setOffscreenPageLimit(fragements.size());

    }
}
