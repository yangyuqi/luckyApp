package com.yunqilai.consumer.luckyapp.SafeKnowledge.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.View.Vu;

/**
 * Created by Administrator on 2017/5/24.
 */

public class SafeKnowledgeView implements Vu {

    private View view ;
    private TextView tv_title ;
    private ListView ls ;

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.safe_knowledge_layout,null);
        tv_title = (TextView) view.findViewById(R.id.textHeadTitle);
        tv_title.setText("安全知识");
        ls = (ListView) view.findViewById(R.id.safe_know_ls);
    }

    @Override
    public View getView() {
        return view;
    }

    public ListView getLs(){
        return ls;
    }
}
