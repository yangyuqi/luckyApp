package com.yunqilai.consumer.luckyapp.UserCenter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangyuqi on 17-7-19.
 */

public class StringUtils {
    public static String getName(String type){
        if (type.equals("wait_pay")){
            return "待付款";
        }else if (type.equals("wait_skip")){
            return "待发货";
        }else if (type.equals("wait_receive")){
            return "待收货";
        }else if (type.equals("wait_evaluate")){
            return "交易成功";
        }else if (type.equals("refunding")){
            return "买家已付款";
        }else if (type.equals("refunded")){
            return "交易关闭";
        }else if (type.equals("cancel")){
            return "交易关闭";
        }else if (type.equals("shipped")){
            return "订单已发货";
        }else if (type.equals("delivered")){
            return "已送达" ;
        }else if (type.equals("has_evaluate")){
            return "交易成功";
        }else if(type.equals("refuseRefund")){
            return "拒绝退款";
        }
        return "已取消";
    }

    public static String payType(String payType ){
        if (payType.equals("0")){
            return "在线支付";
        }
        return "货到付款";
    }

    public static String sendType(String payType ){
        if (payType.equals("0")){
            return "运气来配送";
        }
        return "自提";
    }

    public static String attentionType(String type){
        if (type.equals("not")){
            return "未认证";
        }else if (type.equals("ing")){
            return "审核中";
        }else if (type.equals("failed")){
            return "认证失败";
        }else if (type.equals("success")){
            return "认证成功";
        }
        return "未认证,前往认证";
    }

    public static String ORDER_CANCEL = "cancel";
    public static String ORDER_WAIT_PAY = "wait_pay";
    public static String ORDER_WAIT_SKIP = "wait_skip";
    public static String ORDER_WAIT_RECEIVE = "wait_receive";
    public static String ORDER_WAIT_EVALUTE = "wait_evaluate";
    public static String ORDER_REFUNDING = "refunding";
    public static String ORDEEER_REFUNDED = "refunded";
    public static String HAS_EVALUTE = "has_evaluate";
    public static String REFUND_FAILED_URL = "refuseRefund";

    public static String getRefundStatus(String content){
        if (content.equals("review")){
            return "平台审核中";
        }else if (content.equals("refuse")){
            return "refuse";
        }else if (content.equals("")){
            return "complete";
        }
        return "";
    }


    public static String getPhotoImEi(Context context){
        TelephonyManager mTm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        String imsi = mTm.getSubscriberId();
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return imei ;
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "" ;
        int versioncode ;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static DecimalFormat getMoney() {
        DecimalFormat    df   = new DecimalFormat("######0.00");
        return df ;
    }


    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isCorrectPwd(String str){
        Pattern pattern = Pattern.compile("[0-9A-Za-z]{6,16}$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static boolean checkApkExist(Context context, String packageName){
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 根据日期获得所在周的日期
     * @param mdate
     * @return
     */
    @SuppressWarnings("deprecation")
    public static List<Date> dateToWeek(Date mdate) {
        int b = mdate.getDay();
        Date fdate;
        List<Date> list = new ArrayList<Date>();
        Long fTime = mdate.getTime() - b * 24 * 3600000;
        for (int a = 1; a <= 7; a++) {
            fdate = new Date();
            fdate.setTime(fTime + (a * 24 * 3600000));
            list.add(a-1, fdate);
        }
        return list;
    }

    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getNumFromChar(String str){
        String refex = "[^0-9]";;
        Pattern p = Pattern.compile(refex);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 获取过去或者未来 任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static ArrayList<String> testDay(int intervals ) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        ArrayList<String> fetureDaysList = new ArrayList<>();
        for (int i = 0; i <intervals; i++) {
            pastDaysList.add(getPastDate(i));
            fetureDaysList.add(getFetureDate(i));
        }
        return fetureDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        Log.e(null, result);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        Log.e(null, result);
        return result;
    }

}
