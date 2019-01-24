package com.yunqilai.delivery.utils;

/**
 * Created by yangyuqi on 17-7-25.
 */

public class UrlUtils {

    public static String BASE_PHOTO_URL = "http://121.43.184.167:8080/GAS/";//图片地址
    public static String PHOTO_URL_BASE = "http://121.43.184.167:8080";//图片端口

    public static String BASE_URL = "http://121.43.184.167:9527/gas/";
    public static String LOGIN_URL = BASE_URL+"api/app/v1/user/login";//登录
    public static String GET_ALL_ORDER_URL = BASE_URL+"api/app/v1/deliveryOrder/selectOrderAll";//获取所有订单信息
    public static String ORDER_DETAILS_URL = BASE_URL + "api/app/v1/deliveryOrder/selectOrderById";//订单详情
    public static String REJECT_REASON_URL = BASE_URL +"api/app/v1/deliveryOrder/refuseOrderReason";//拒单原因查询
    public static String CHANGE_ORDER_STATUS_URL =BASE_URL+"api/app/v1/deliveryOrder/editOrderStatusById";//订单状态修改
    public static String ATTENTION_LIST_URL = BASE_URL +"api/app/v1/attestation/selectAttestationAll";//认证列表
    public static String ATTENTION_DETAILS_URL = BASE_URL+ "api/app/v1/attestation/selectAttestationById";//认证详情
    public static String ATTENTION_NEW_URL = BASE_URL + "api/app/v1/attestation/addAttestation";//新增或修改认证
    public static String UPLOAD_PHOTO_URL = BASE_PHOTO_URL +"api/app/v1/uploadFile";//上传图片
    public static String GET_USER_MONEY_URL = BASE_URL+"api/app/v1/tank/selectAssetAll";//获取资产列表
    public static String GET_CODE_DETAILS_URL = BASE_URL+"api/app/v1/tank/selectTankByCode";//根据条码获取资产详情
    public static String CHANGE_STATUS_GAS_URL = BASE_URL+"api/app/v1/tank/editTankStatusById";//瓶罐状态修改
    public static String EXPRESS_THING_URL = BASE_URL+"api/app/v1/article/getAboutExpressAll";//配送事项

    public static String REPLACE_CODE_URL = BASE_URL +"api/app/v1/tank/replaceBarCodeById";//补码
    public static String CHECK_FREE_BAR_CODE = BASE_URL + "api/app/v1/tank/checkFreeBarCode";//检查条码是否空闲
    public static String STATISTICS_URL = BASE_URL + "api/app/v1/delivery/deliveryStatistics";//配送统计
    public static String EDIT_NICKNAME_URL = BASE_URL + "api/app/v1/user/editUnickName";//修改昵称
    public static String EDIT_NEW_PASSWORD = BASE_URL + "api/app/v1/user/alertPassWord";//修改密码
    public static String ABOUT_US_URL = BASE_URL+"api/app/v1/article/AboutUS";//关于我们
    public static String UPDATE_APP_URL = BASE_URL+"api/app/v1/index/getVersion";//版本更新
    public static String EDIT_USER_PHOTO_URL =BASE_URL+"api/app/v1/user/editUserIcon";//修改头像
    public static String FIND_PWD_NEW_URL = BASE_URL+"api/app/v1/user/getBackPassWord";//找回密码
    public static String SENF_PWD_CODE_URL = BASE_URL+"api/app/v1/msgSet/sendMsg";//获取短信验证码
}