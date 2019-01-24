package com.yunqilai.delivery.ui.presenter.infomanage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.manager.SharedPreferencesUtil;
import com.yunqilai.delivery.model.Bean.Info.InfoDetailsBean;
import com.yunqilai.delivery.model.Bean.Info.InfoListData;
import com.yunqilai.delivery.model.Bean.Info.PutInfoBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Tank;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.infomanage.ReplaceCodeActivity;
import com.yunqilai.delivery.ui.interlayer.infomanage.InfoManageListInterlayer;
import com.yunqilai.delivery.ui.interlayer.infomanage.SearchTankResultInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.ui.view.ToastUtil;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KK on 2017/6/12.
 */

public class SearchTankResultPresenter extends AbsPresenter<SearchTankResultInterlayer> {

    private String word ,accessToken;
    private Gson gson ;

    public SearchTankResultPresenter(Context context, SearchTankResultInterlayer interlayer, String keyWord) {
        super(context, interlayer);
        word = keyWord ;
        gson = new Gson();
        accessToken = (String) SharedPreferencesUtil.get(context,SharedPreferencesUtil.accessToken,"");
    }

    public void requestData(){

        String type = gson.toJson(new PutInfoBean(accessToken,null,1,40,word));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.GET_USER_MONEY_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("sssssv",response);
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        InfoListData mdata = gson.fromJson(gson.toJson(entity.getData()), InfoListData.class);
                        if (mdata.getTankList().size() > 0) {
                            interlayer.refreshDatas(mdata.getTankList());
                        } else {
                            Toast.makeText(mContext, "未搜索到相关瓶罐", Toast.LENGTH_SHORT).show();
                        }
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

    }

    public void toReplaceCode(InfoDetailsBean tankId){
        Intent intent = new Intent(mContext, ReplaceCodeActivity.class);
        intent.putExtra("tankId",tankId);
        mContext.startActivity(intent);
    }

}
