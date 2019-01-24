package com.yunqilai.delivery.ui.presenter.infomanage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.Info.GetTankBean;
import com.yunqilai.delivery.model.Bean.Info.PutBarCodeBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.interlayer.infomanage.TankDetailInterlayer;
import com.yunqilai.delivery.ui.presenter.AbsPresenter;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by KK on 2017/6/7.
 */

public class TankDetail2Presenter extends AbsPresenter<TankDetailInterlayer> {

    private String accesstoken ;
    private String id ;
    private Gson gson ;

    public TankDetail2Presenter(Context context, TankDetailInterlayer interlayer , String accessToken , String mid) {
        super(context, interlayer);
        accesstoken = accessToken ;
        id = mid ;
        gson = new Gson();
    }

    public void requestData(){

        String type = gson.toJson(new PutBarCodeBean(accesstoken,id));
        MyOkHttpClientManager.postAsynJson(type, UrlUtils.GET_CODE_DETAILS_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                Log.e("ssssssssssss",response);
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        GetTankBean bean = gson.fromJson(gson.toJson(entity.getData()), GetTankBean.class);
                        interlayer.refreshDatas(bean);
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    } /*else {
                        Toast.makeText(mContext, entity.getMsg(), Toast.LENGTH_SHORT).show();
                    }*/
                }catch (Exception e){
                    Toast.makeText(mContext, R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
