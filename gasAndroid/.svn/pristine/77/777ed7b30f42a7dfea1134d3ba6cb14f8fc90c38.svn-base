package com.yunqilai.delivery.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wqd on 2017/3/6 0006.
 */
public class StringUtils {


    public static String attentionType(String type){
        if (type.equals("not")){
            return "未认证";
        }else if (type.equals("ing")){
            return "认证中";
        }else if (type.equals("failed")){
            return "认证失败";
        }else if (type.equals("success")){
            return "认证成功";
        }
        return "";
    }


    /**
     * 获取字符串的宽度
     * @param paint
     * @param str
     * @return
     */
    public static float getStringWidth(Paint paint, String str) {
        return paint.measureText(str);
    }

    public static String getLongestString(List<String> listDatas) {
        if (listDatas == null || listDatas.size() == 0) {
            return null;
        }

        String result = listDatas.get(0);
        for (String str: listDatas) {
            if (result.length() < str.length()) {
                result = str;
            }
        }

        return result;
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s){
        return s!=null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * 检测字符串是否为空(null,"","null")
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s){
        return s==null || "".equals(s) || "null".equals(s);
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     * @param mobile
     * @return
     */
    public static boolean checkMobileNumber(String mobile){
        boolean flag = false;
        try{
//            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            //Pattern regex = Pattern.compile("^(1\\d{10})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            String regexStr = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
            Pattern regex = Pattern.compile(regexStr);
            Matcher matcher = regex.matcher(mobile);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }


    /***
     * 验证电话
     * @param mobile
     * @return
     */
    public static boolean checkPhoneNumber(String mobile){
        boolean flag = false;
        try{
//            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            //Pattern regex = Pattern.compile("^(1\\d{10})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            //String regexStr = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}"
            String regexStr = "^0(10|2[0-5789]|\\d{3,4})-?\\d{7,8}$";
            Pattern regex = Pattern.compile(regexStr);
            Matcher matcher = regex.matcher(mobile);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /***
     * 20位中英文数字下划线
     * @param mobile
     * @return
     */
    public static boolean checkChar_CEN20(String mobile){
        boolean flag = false;
        try{
            String regexStr = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]{1,20}";
            Pattern regex = Pattern.compile(regexStr);
            Matcher matcher = regex.matcher(mobile);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /***
     * 30位中英文数字下划线
     * @param mobile
     * @return
     */
    public static boolean checkChar_CEN30(String mobile){
        boolean flag = false;
        try{
            String regexStr = "[\\u4e00-\\u9fa5_a-zA-Z0-9_]{1,30}";
            Pattern regex = Pattern.compile(regexStr);
            Matcher matcher = regex.matcher(mobile);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /***
     * 验证密码
     * @param password
     * @return
     */
    public static boolean checkPassword(String password){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$");
            Matcher matcher = regex.matcher(password);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /***
     * 验证邮编
     * @param zip
     * @return
     */
    public static boolean checkZip(String zip){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^\\d{6}$");
            Matcher matcher = regex.matcher(zip);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    public static boolean checkLength50(String feedback){
        boolean flag = false;
        try{
            int length = feedback.length();
            if(length<=50 && length>0){
                return true;
            }
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * 按长度截取字符串，后面加上“...”
     * @param str
     * @param length
     * @return
     */
    public static String subStrEndByLength(String str, int length){
        if(str != null && length >0 && str.length()>length){
            return str.substring(0,length)+"...";
        }else{
            return str;
        }
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

    public static String getPhotoImEi(Context context){
        TelephonyManager mTm = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = mTm.getDeviceId();
        String imsi = mTm.getSubscriberId();
        String mtype = android.os.Build.MODEL; // 手机型号
        String mtyb= android.os.Build.BRAND;//手机品牌
        String numer = mTm.getLine1Number(); // 手机号码，有的可得，有的不可得
        return imei ;
    }


    /***
     * 判断是否是中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str){
        boolean flag = false;
        try{
            String regexStr = "[\u4e00-\u9fa5]+";
            Pattern regex = Pattern.compile(regexStr);
            Matcher matcher = regex.matcher(str);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /***
     * 验证身份证 15或18位
     * @param str
     * @return
     */
    public static boolean isUserID(String str){
        boolean flag15 = false;
        boolean flag18 = false;
        try{
            String regexStr15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
            String regexStr18 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|[Xx])$";
            Pattern regex15 = Pattern.compile(regexStr15);
            Matcher matcher15 = regex15.matcher(str);
            flag15 = matcher15.matches();

            Pattern regex18 = Pattern.compile(regexStr18);
            Matcher matcher18 = regex18.matcher(str);
            flag18 = matcher18.matches();
        }catch(Exception e){
            flag15 = false;
            flag18 = false;
        }
        return flag15 || flag18;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

}
