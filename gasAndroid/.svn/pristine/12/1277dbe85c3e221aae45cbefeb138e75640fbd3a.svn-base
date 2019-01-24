package com.yunqilai.consumer.luckyapp;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.ChoicePage.ChoicePageFragment;
import com.yunqilai.consumer.luckyapp.Common.Base.BasePresenterActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.ConfirmUserActivity;
import com.yunqilai.consumer.luckyapp.Common.Presenter.LoginActivity;
import com.yunqilai.consumer.luckyapp.Common.Utils.ScreenUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.TimeUtils;
import com.yunqilai.consumer.luckyapp.Common.View.AttestationDialog;
import com.yunqilai.consumer.luckyapp.Common.View.MainView;
import com.yunqilai.consumer.luckyapp.Common.View.VuCallback;
import com.yunqilai.consumer.luckyapp.HomePage.HomePageFragment;
import com.yunqilai.consumer.luckyapp.SafeKnowledge.SafeKnowledgeFragment;
import com.yunqilai.consumer.luckyapp.ShoppingCart.ShoppingCartFragment;
import com.yunqilai.consumer.luckyapp.UserCenter.StringUtils;
import com.yunqilai.consumer.luckyapp.UserCenter.UserCenterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends BasePresenterActivity<MainView> {
    private static final String CURR_INDEX = "currIndex";
    private ArrayList<String> fragmentTags;
    private static int currIndex = 2;

    private String userRole;
    private String tipDate;
    private AttestationDialog attestationDialog;

    @Override
    protected Class<MainView> getViewClass() {
        return MainView.class;
    }

    @Override
    protected void onBindView() {
        super.onBindView();

        EventBus.getDefault().register(this);

        showFragment();
        vu.setOnRadioGroupCheckedListener(new VuCallback<Integer>() {
            @Override
            public void execute(Integer result) {
                switch (result) {
                    case 0 : currIndex = 0 ;break;
                    case 1 : currIndex = 1;break;
                    case 2 : currIndex = 2;break;
                    case 3 :
                        if (!getAccessToken().equals("")) {
                            currIndex = 3;
                            break;
                        }else {
                            startActivity(new Intent(context, LoginActivity.class));
                        }
                    case 4 : currIndex = 4;break;
                    default: break;
                }
                showFragment();
            }
        });
        userRole = (String)SharedPreferencesUtils.getParam(MainActivity.this,SharedPreferencesUtils.role,"");
        tipDate = (String)SharedPreferencesUtils.getParam(MainActivity.this,SharedPreferencesUtils.attestationTipDate,"");
//        if(userRole != null && !TimeUtils.getYMD().equals(tipDate)){
//            attestationDialog = new AttestationDialog(this, new AttestationDialog.DialogTouchhListener() {
//                @Override
//                public void onUp() {
//                    attestationDialog.dismiss();
//                }
//
//                @Override
//                public void onDown() {
//                    switch (userRole){
//                        case "not"://“not”未认证
//                        case "failed"://“failed“认证失败
//                            //去新增
//                            Intent in = new Intent(context,ConfirmUserActivity.class);
//                            in.putExtra("flag","2");
//                            startActivity(in);
//                            break;
////                        case "failed"://“failed“认证失败
////                            //去修改
////                            break;
//                    }
//                    attestationDialog.dismiss();
//                }
//            });
//            switch (userRole){
//                case "not"://“not”未认证
//                    attestationDialog.setTipImgRes(R.drawable.icon_attestation);
//                    attestationDialog.show();
//                    SharedPreferencesUtils.setParam(MainActivity.this,SharedPreferencesUtils.attestationTipDate,TimeUtils.getYMD());
//                    break;
//                case "failed"://“failed“认证失败
//                    attestationDialog.setTipImgRes(R.drawable.icon_attestation_failure);
//                    attestationDialog.show();
//                    SharedPreferencesUtils.setParam(MainActivity.this,SharedPreferencesUtils.attestationTipDate,TimeUtils.getYMD());
//                    break;
//            }
//        }

    }

    private void showFragment() {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment == null) {
            fragment = instantFragment(currIndex);
        }
        for (int i = 0; i < fragmentTags.size(); i++) {
            Fragment f = fm.findFragmentByTag(fragmentTags.get(i));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(vu.getContainerId(), fragment, fragmentTags.get(currIndex));
        }
        fragmentTransaction.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURR_INDEX, currIndex);
    }

    @Override
    protected void onBindData(Bundle savedInstanceState) {
        super.onBindData(savedInstanceState);
        initData(savedInstanceState);
    }

    private void initData(Bundle savedInstanceState) {
        fragmentTags = new ArrayList<>(Arrays.asList("ChoicePageFragment", "SafeKnowledgeFragment", "HomePageFragment", "ShoppingCartFragment","UserCenterFragment"));
        currIndex = 2;
        if(savedInstanceState != null) {
            currIndex = savedInstanceState.getInt(CURR_INDEX);
            hideSavedFragment();
        }
    }

    private void hideSavedFragment() {
        Fragment fragment = fm.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment != null) {
            fm.beginTransaction().hide(fragment).commit();
        }
    }

    private Fragment instantFragment(int currIndex) {
        switch (currIndex) {
            case 0 :
                return new ChoicePageFragment();
            case 1:
                return new SafeKnowledgeFragment();
            case 2:
                return new HomePageFragment();
            case 3:
                return new ShoppingCartFragment();
            case 4:
                return new UserCenterFragment();

            default:
                return null;
        }
    }

    @Override
    protected void onDestroyVu() {
        super.onDestroyVu();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Integer index){
        if (index==3) {
            currIndex = 3;
            showFragment();
            vu.getRb().setChecked(true);
        }else if (index == 6){
            finish();
        }
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getApplicationContext(),
                            R.string.tip_exit, Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
