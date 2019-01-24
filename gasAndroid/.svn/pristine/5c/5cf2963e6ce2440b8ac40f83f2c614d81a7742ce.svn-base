package com.yunqilai.delivery.ui.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.AccesstokenBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.ParseReasionData;
import com.yunqilai.delivery.model.Bean.ReasonInfoBean;
import com.yunqilai.delivery.model.Bean.home.GetOrderBean;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.order.OrderDetailActivity;
import com.yunqilai.delivery.ui.interf.DeleteDialogInterface;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;
import com.yunqilai.delivery.utils.adapter.CommonAdapter;
import com.yunqilai.delivery.utils.adapter.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyuqi on 17-8-1.
 */

public class DialogReject extends Dialog {
    private TextView tvtitle,tvcontent;
    private TextView tv_ok,tv_no;
    private ImageView iv_show ;
    private Gson gson ;
    private String accesstoken ;
    private Spinner spinner ;

    private String strTitle,strContent,strConfirmbtn;

    private OrderDetailActivity context;

    private boolean isDelete = false;

    private String reasonId = "";
    private String orderId ;

    private DeleteDialogInterface deleteDialogInterface;

    private List<ReasonInfoBean> data = new ArrayList<>();
    private CommonAdapter<ReasonInfoBean> adapter ;

    public DialogReject(OrderDetailActivity context, String accesstokenn, String orderId) {
        super(context, R.style.MyDialogStyle);
        this.context = context;
        this.accesstoken = accesstokenn;
        this.orderId = orderId ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init() {
        gson = new Gson();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.confrim_dialog, null);
        setContentView(view);

        tv_no = (TextView) view.findViewById(R.id.tv_no);
        tv_ok = (TextView) view.findViewById(R.id.tv_ok);
        tvcontent = (TextView) view.findViewById(R.id.tv_content);
        iv_show = (ImageView) view.findViewById(R.id.iv_show);
        spinner = (Spinner) view.findViewById(R.id.spinner2);
        tv_no.setOnClickListener(new clickListener());
        tv_ok.setOnClickListener(new clickListener());
//        iv_show.setOnClickListener(new clickListener());
        adapter = new CommonAdapter<ReasonInfoBean>(context,data,R.layout.simple_spinner_item) {
            @Override
            public void convert(ViewHolder helper, ReasonInfoBean item) {
                helper.setText(R.id.tv_tv,item.getReason());
            }
        };
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                reasonId = data.get(position).getReasonId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.65); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);

        MyOkHttpClientManager.postAsynJson(gson.toJson(new AccesstokenBean(accesstoken)), UrlUtils.REJECT_REASON_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        ParseReasionData bean = gson.fromJson(gson.toJson(entity.getData()), ParseReasionData.class);
                        data.clear();
                        data.addAll(bean.getList());
                        adapter.setData(data);
                        adapter.notifyDataSetChanged();
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.tv_no:
                    dismiss();
                    break;
                case R.id.tv_ok:
                    if (reasonId.equals("")){
                        dismiss();
                    }else {
                        String type = gson.toJson(new GetOrderBean(accesstoken,orderId,"refuse_order",reasonId));
                        MyOkHttpClientManager.postAsynJson(type, UrlUtils.CHANGE_ORDER_STATUS_URL, new MyOkHttpClientManager.StringCallback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response) {
                                Log.e("ssssss",response);
                                try {
                                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                                    if (entity.getCode().equals(ResponseUtils.OK)) {
                                        EventBus.getDefault().post(true);
                                        context.finish();
                                        dismiss();
                                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                                    } else {
                                        Toast.makeText(context, entity.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    Toast.makeText(context,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    break;
                case R.id.iv_show:
                    break;
            }
        }
    };

    public void OnDeleteBtn(DeleteDialogInterface mdeleteDialogInterface){
        deleteDialogInterface = mdeleteDialogInterface;
    }

    @Override
    public void show() {
        super.show();
    }
}
