package com.yunqilai.delivery.ui.activity.my;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.yunqilai.delivery.R;
import com.yunqilai.delivery.model.Bean.AccesstokenBean;
import com.yunqilai.delivery.model.Bean.ParseBaseEntity;
import com.yunqilai.delivery.model.Bean.my.StaticBean;
import com.yunqilai.delivery.model.Bean.my.StaticData;
import com.yunqilai.delivery.request.base.ErrorCode;
import com.yunqilai.delivery.ui.activity.BaseFragmentActivity;
import com.yunqilai.delivery.ui.view.ChartView;
import com.yunqilai.delivery.ui.view.CommonTitle;
import com.yunqilai.delivery.utils.Event;
import com.yunqilai.delivery.utils.MyOkHttpClientManager;
import com.yunqilai.delivery.utils.ResponseUtils;
import com.yunqilai.delivery.utils.UrlUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class DispatchStatisticsActivity extends BaseFragmentActivity {

    private LineChartView chartViewWeek;
    private LineChartView chartViewMonth;

    private CommonTitle title ;

    private TextView tv_day ,tv_week ,tv_month ,tv_all ;

    private List<String> week_x = new ArrayList<>();
    private List<Integer> week_num = new ArrayList<>();

    private List<String> month_x = new ArrayList<>();
    private List<Integer> month_num = new ArrayList<>();

    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


    private List<PointValue> mPointValues_two = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues_two = new ArrayList<AxisValue>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_dispatch_statistics);

        title = (CommonTitle) findViewById(R.id.common_title);
        chartViewWeek = (LineChartView) findViewById(R.id.chart_view_one);
        chartViewMonth = (LineChartView) findViewById(R.id.chart_view_two);
        tv_day = (TextView) findViewById(R.id.tv_today);
        tv_week = (TextView) findViewById(R.id.tv_week);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_all = (TextView) findViewById(R.id.tv_all);

        title.setOnTitleClickListener(new CommonTitle.OnTitleClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        initData();
    }

    private void initLineChart() {

            Line line = new Line(mPointValues).setColor(getResources().getColor(R.color.text_blue));  //折线的颜色
            List<Line> lines = new ArrayList<Line>();
            line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
            line.setCubic(false);//曲线是否平滑
        line.setColor(getResources().getColor(R.color.text_blue));
//	    line.setStrokeWidth(3);//线条的粗细，默认是3
            line.setFilled(false);//是否填充曲线的面积
            line.setHasLabels(true);//曲线的数据坐标是否加上备注
//		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
            line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
            line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
            lines.add(line);
            LineChartData data = new LineChartData();
            data.setLines(lines);

            //坐标轴
            Axis axisX = new Axis(); //X轴
            axisX.setHasTiltedLabels(true);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
            axisX.setTextColor(getResources().getColor(R.color.text_blue));//灰色
            axisX.setTextSize(11);//设置字体大小
            axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
            axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
            data.setAxisXBottom(axisX); //x 轴在底部
            axisX.setHasLines(true); //x 轴分割线
        axisX.setLineColor(getResources().getColor(R.color.text_blue));


            Axis axisY = new Axis();  //Y轴
            axisY.setName("");//y轴标注
            axisY.setTextSize(11);//设置字体大小
        axisY.setLineColor(getResources().getColor(R.color.text_blue));
        axisY.setTextColor(getResources().getColor(R.color.text_blue));
            data.setAxisYLeft(axisY);  //Y轴设置在左边
            //data.setAxisYRight(axisY);  //y轴设置在右边
            //设置行为属性，支持缩放、滑动以及平移
            chartViewWeek.setInteractive(true);
            chartViewWeek.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
            chartViewWeek.setMaxZoom((float) 3);//缩放比例
            chartViewWeek.setLineChartData(data);
            chartViewWeek.setVisibility(View.VISIBLE);
            Viewport v = new Viewport(chartViewWeek.getMaximumViewport());
            v.left = 0;
            v.right= 7;
            chartViewWeek.setCurrentViewport(v);
        }



    private void initData() {
        MyOkHttpClientManager.postAsynJson(gson.toJson(new AccesstokenBean(getAccessToken())), UrlUtils.STATISTICS_URL, new MyOkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(DispatchStatisticsActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                try {
                    ParseBaseEntity entity = gson.fromJson(response, ParseBaseEntity.class);
                    if (entity.getCode().equals(ResponseUtils.OK)) {
                        StaticData data = gson.fromJson(gson.toJson(entity.getData()), StaticData.class);
                        tv_day.setText(String.valueOf(data.getToday()));
                        tv_week.setText(String.valueOf(data.getWeek()));
                        tv_month.setText(String.valueOf(data.getMonth()));
                        tv_all.setText(String.valueOf(data.getTotal()));
                        testChartView1(data.getWeekList());
                        testChartView2(data.getMonthList());
                    }else if(entity.getCode().equals(ErrorCode.ERR_ACCESS_TOKEN_INVALID) || entity.getCode().equals(ErrorCode.ERR_REFRESH_TOKEN_INVALID)){
                        EventBus.getDefault().post(Event.EXITANDLOGIN);
                    }
                }catch (Exception e){
                    Toast.makeText(DispatchStatisticsActivity.this,R.string.service_error_500,Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }


    private void testChartView1(List<StaticBean> weekList){
        week_x.clear();
        week_num.clear();
        for (int i = 0 ;i <weekList.size() ;i++){
            if(weekList.get(i).getTime().length() == 10){
                weekList.get(i).setTime(weekList.get(i).getTime().substring(5,weekList.get(i).getTime().length()));
            }
            week_x.add(weekList.get(i).getTime());
            week_num.add(Integer.parseInt(weekList.get(i).getCount()));
        }


        for (int i = 0; i < week_x.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(week_x.get(i)));
        }

        for (int i = 0; i < week_num.size(); i++) {
            mPointValues.add(new PointValue(i, week_num.get(i)));
        }
        initLineChart();

    }
    private void testChartView2(List<StaticBean> monthList){
        month_x.clear();
        month_num.clear();
        for (int i = 0 ;i <monthList.size() ;i++){
            if(monthList.get(i).getTime().length() == 10){
                monthList.get(i).setTime(monthList.get(i).getTime().substring(5,monthList.get(i).getTime().length()));
            }
            month_x.add(monthList.get(i).getTime());
            month_num.add(Integer.parseInt(monthList.get(i).getCount()));
        }


        for (int i = 0; i < month_x.size(); i++) {
            mAxisXValues_two.add(new AxisValue(i).setLabel(month_x.get(i)));
        }

        for (int i = 0; i < month_num.size(); i++) {
            mPointValues_two.add(new PointValue(i, month_num.get(i)));
        }
        initLineChart2();
    }

    private void initLineChart2() {
        Line line = new Line(mPointValues_two).setColor(getResources().getColor(R.color.text_blue));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
//	    line.setStrokeWidth(3);//线条的粗细，默认是3
        line.setFilled(false);//是否填充曲线的面积
        line.setColor(getResources().getColor(R.color.text_blue));
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(getResources().getColor(R.color.text_blue));//灰色
        axisX.setLineColor(getResources().getColor(R.color.text_blue));
        axisX.setTextSize(11);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues_two);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        axisX.setHasLines(true); //x 轴分割线


        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(11);//设置字体大小
        axisY.setLineColor(getResources().getColor(R.color.text_blue));
        axisY.setTextColor(getResources().getColor(R.color.text_blue));
        data.setAxisYLeft(axisY);  //Y轴设置在左边

        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        chartViewMonth.setInteractive(true);
        chartViewMonth.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
        chartViewMonth.setMaxZoom((float) 3);//缩放比例
        chartViewMonth.setLineChartData(data);
        chartViewMonth.setVisibility(View.VISIBLE);
        Viewport v = new Viewport(chartViewMonth.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        chartViewMonth.setCurrentViewport(v);
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
