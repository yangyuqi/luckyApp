package com.yunqilai.delivery.ui.fragment.attestation;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Attestation;
import com.yunqilai.delivery.model.Bean.attention.ParseAttestation;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.adapter.AttestationAdapter;
import com.yunqilai.delivery.ui.adapter.OrderListAdapter;
import com.yunqilai.delivery.ui.fragment.BaseFragment;
import com.yunqilai.delivery.ui.interlayer.attestation.AttestationInterlayer;
import com.yunqilai.delivery.ui.presenter.attestation.AttestationPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.ui.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证列表
 */
public class AttestationFragment extends BaseFragment<AttestationPresenter> implements AttestationInterlayer {

    private CommonTitle commonTitle;
    private SpringView springView;
    private TextView countTv;
    private NoScrollListView listView;
    private AttestationAdapter adapter;
    private List<ParseAttestation> attestations;
    private View view_nod_data ;

    private int pageNum = 1;

    public AttestationFragment() {
        // Required empty public constructor
    }

    public static AttestationFragment newInstance() {
        AttestationFragment fragment = new AttestationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        presenter = new AttestationPresenter(getActivity(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attestation, container, false);

        initView(view);
        initEvent();
        initData();

        return view;
    }

    private void initView(View view){
        commonTitle = (CommonTitle)view.findViewById(R.id.common_title);
        springView = (SpringView)view.findViewById(R.id.spring_view);
        countTv = (TextView)view.findViewById(R.id.tv_count);
        listView = (NoScrollListView)view.findViewById(R.id.lv_attestation_list);
        view_nod_data = view.findViewById(R.id.rl_no_data);
    }

    private void initEvent(){
        commonTitle.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                presenter.addAttestation();
            }
        });
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                presenter.requestData(pageNum);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                        ++pageNum;
                        presenter.requestData(pageNum);
                    }
                }, 1000);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onItemClick(attestations.get(i).getAttestationId());
            }
        });
    }

    private void initData(){
        attestations = new ArrayList<>();
        adapter = new AttestationAdapter(getActivity(),attestations);
        listView.setAdapter(adapter);
//
//        presenter.requestData();
    }

    @Override
    public void refreshDatas(List<ParseAttestation> attestationList ,int count,int thisPage) {
        countTv.setText(String.valueOf(count));
        if(thisPage == 1) {
            attestations.clear();
        }
        if (attestationList.size()>0 || attestations.size()>0) {
            view_nod_data.setVisibility(View.GONE);
            attestations.addAll(attestationList);
            adapter.notifyDataSetChanged();
        }else {
            view_nod_data.setVisibility(View.VISIBLE);
            attestations.addAll(attestationList);
            adapter.notifyDataSetChanged();
        }
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.requestData(pageNum);
    }
}
