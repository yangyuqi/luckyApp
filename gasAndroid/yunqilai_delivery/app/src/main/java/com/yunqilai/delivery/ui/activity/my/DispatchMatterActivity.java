package com.yunqilai.delivery.ui.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liaoinstan.springview.widget.SpringView;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.my.ArticleBean;
import com.yunqilai.delivery.model.Matter;
import com.yunqilai.delivery.ui.activity.BaseActivity;
import com.yunqilai.delivery.ui.adapter.DispatchMatterListAdapter;
import com.yunqilai.delivery.ui.interlayer.my.DispatchMatterInterlayer;
import com.yunqilai.delivery.ui.presenter.my.DispatchMatterPresenter;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class DispatchMatterActivity extends BaseActivity<DispatchMatterPresenter> implements DispatchMatterInterlayer{

    private CommonTitle commonTitle;
    private SpringView springView;
    private ListView listView;
    private DispatchMatterListAdapter adapter;
    private List<ArticleBean> matters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_dispatch_matter);

        presenter = new DispatchMatterPresenter(this,this);

        initView();
        initEvent();
        initData();
    }

    private void initView(){
        commonTitle = (CommonTitle)findViewById(R.id.common_title);
        springView = (SpringView)findViewById(R.id.spring_view);
        listView = (ListView)findViewById(R.id.lv_matter_list);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onItemClick(matters.get(i));
            }
        });
    }

    private void initData(){
        matters = new ArrayList<>();
        adapter = new DispatchMatterListAdapter(this,matters);
        listView.setAdapter(adapter);

        presenter.requestData();
    }

    @Override
    public void refreshDatas(List<ArticleBean> matterList) {
        matters.clear();
        matters.addAll(matterList);
        adapter.notifyDataSetChanged();

        springView.onFinishFreshAndLoad();
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
