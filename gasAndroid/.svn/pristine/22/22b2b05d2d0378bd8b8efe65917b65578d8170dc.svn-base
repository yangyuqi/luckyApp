package com.yunqilai.consumer.luckyapp.HomePage.Presenter;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.CaptureActivity;
import com.yunqilai.consumer.luckyapp.HomePage.View.SearchGoodsView;

/**
 * Created by Administrator on 2017/6/5.
 */

public class SearchGoodsActivity extends BasePresenterActivity<SearchGoodsView> {
    @Override
    protected Class<SearchGoodsView> getViewClass() {
        return SearchGoodsView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        vu.getIv_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vu.getTv_search().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = vu.getEdt_search().getText().toString();
                if(key.length() == 0){
                    Toast.makeText(SearchGoodsActivity.this, R.string.please_input_search_msg, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(SearchGoodsActivity.this,SearchResultActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }
        });
    }
}
