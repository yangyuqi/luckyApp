package com.yunqilai.delivery.utils;

import java.text.DecimalFormat;

/**
 * Created by wangjun on 2016/6/12.
 */
public class NumberUtils {

    public static String formatToDouble(String dob){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Double.parseDouble(dob));
    }

    public static int getIntFromString(String str){
        int result = 0;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                result = (int) Double.parseDouble(str);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

    public static double getDoubleFromString(String str){
        double result = 0;
        try {
            result =  Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
