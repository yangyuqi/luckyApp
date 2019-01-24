package com.yunqilai.consumer.luckyapp.Common.View;

import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.ChoicePageFragment;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Administrator on 2017/5/23.
 */

public class MainView implements Vu {

    protected View view ;
    protected FrameLayout containerView ;
    protected RadioGroup rg;
    VuCallback<Integer> vuCallback ;
    public RadioButton rb ;
    Integer index ;



    public RadioGroup getRg() {
        return rg;
    }

    public RadioButton getRb() {
        return rb;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_main,null);
        containerView = (FrameLayout) view.findViewById(R.id.fragment_container);
        rg = (RadioGroup) view.findViewById(R.id.group);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.foot_bar_home : index = 0 ; vuCallback.execute(index);break;
                    case R.id.foot_bar_im : index = 1 ; vuCallback.execute(index);break;
                    case R.id.foot_bar_im2 : index = 2 ; vuCallback.execute(index);break;
                    case R.id.foot_bar_interest : index = 3 ; vuCallback.execute(index);break;
                    case R.id.main_footbar_user : index = 4 ; vuCallback.execute(index);break;
                }
            }
        });
        rb = (RadioButton) view.findViewById(R.id.foot_bar_interest);
    }

    @Override
    public View getView() {
        return view;
    }

    public int getContainerId(){
        return containerView.getId();
    }

    public void setOnRadioGroupCheckedListener(VuCallback<Integer> callback){
        vuCallback = callback ;
    }
}
