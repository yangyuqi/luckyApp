package com.yunqilai.consumer.luckyapp.Common.Utils;

/**
 * Created by Administrator on 2017/5/31.
 */

public class UrlUtils {
    //    public static String BASE_URL = "http://192.168.2.197:8180/gas/" ;
    public static String BASE_URL = "http://121.43.184.167:9527/gas/";
    public static String BASE_BASE_URL = "http://121.43.184.167:8080/GAS/";//图片地址

    public static String URL_BASE = "http://121.43.184.167:8080";//图片端口

    public static String LOGIN_USER_URL = BASE_URL + "api/app/v1/user/login";//登录
    public static String CODE_SEND_URL = BASE_URL + "api/app/v1/msgSet/sendMsg";//验证码
    public static String REGISTER_AUTHON_URL = BASE_URL + "api/app/v1/user/register";//注册
    public static String USER_PROTOCOL_URL = BASE_URL + "api/app/v1/article/Protocol";//用户协议
    public static String BANNER_HOME_URL = BASE_URL + "api/app/v1/index/homepage";//首页banner


    public static String SHOPPING_CART_URL = BASE_URL + "api/app/v1/goodsCart/selectGoodsCartAll";//购物车
    public static String ADD_TOCART_URL = BASE_URL + "api/app/v1/goodsCart/addGoodsCart";//加入购物车
    public static String EDIT_NUMBER_URL = BASE_URL + "api/app/v1/goodsCart/editGoodsCart";//修改购物车数量
    public static String DELETE_CART_URL = BASE_URL + "api/app/v1/goodsCart/deleteGoodsCart";//删除购物车
    public static String CHOICE_LIST_URL = BASE_URL + "api/app/v1/channel/chosen"; //精选商品
    public static String GOODS_DETAILS_URL = BASE_URL + "api/app/v1/channel/getGoodsById";//商品详情
    public static String KEY_SEARCH_URL = BASE_URL + "api/app/v1/search/getGoodsSearchByKeyWord";//关键字搜索
    public static String FRIST_MESSAGE_URL = BASE_URL + "api/app/v1/message/getMessageRoot";//消息第一级列表
    public static String SECOND_MESSAGE_URL = BASE_URL + "api/app/v1/message/getMessageList";//消息二级列表
    public static String SCAN_CODER_URL = BASE_URL + "api/app/v1/tank/selectTankByCode";//扫一扫

    public static String CHANNEL_GOODS_ONE_URL = BASE_URL + "api/app/v1/channel/getGoodsListById";//商品列表
//    public static String CHANNEL_GOODS_TWO_URL = BASE_URL + "api/app/v1/channel/getChannelTwo";//频道二
    public static String SOFT_PIC_URL = BASE_URL + "api/app/v1/channel/getAdvertorialById";//软文

    public static String GET_USER_ALL_ADDRESS = BASE_URL + "api/app/v1/address/selectAddressAll";//获取用户所有地址
    public static String ADD_NEW_ORDER_URL = BASE_URL + "api/app/v1/order/addOrder";//新增订单
    public static String COMPUTER_ORDER_MONEYY_URL = BASE_URL + "api/app/v1/order/orderCalculate";//订单计算
    public static String GET_ORDER_INFO_URL = BASE_URL + "api/app/v1/order/selectOrderFormAll";//获取订单列表
    public static String CHANGE_ORDER_INFO_URL = BASE_URL + "api/app/v1/order/editOrderStatus";//修改订单状态
    public static String ORDER_DETAILS_URL = BASE_URL + "api/app/v1/order/selectOrderById";//订单详情
    public static String PAY_MONEY_URL = BASE_URL +"api/app/v1/pay/appPay";//支付
    public static String GET_EVERY_ORDER_URL = BASE_URL+"api/app/v1/order/selectOrderFormCount";//查询各状态下的订单数量
    public static String GET_ADDERESS_BASE_URL = BASE_URL+"api/app/v1/area/selectAreaAll";
    public static String SEARCH_ORDER_DICSUSS = BASE_URL +"api/app/v1/evaluate/selectEvaluateAll";//查询订单评论
    public static String FIND_PWD_URL = BASE_URL + "api/app/v1/user/getBackPassWord";//找回密码
    public static String EDIT_NICKNAME_URL = BASE_URL+"api/app/v1/user/editUnickName";//修改昵称
    public static String EDIT_PWD_URL = BASE_URL+"api/app/v1/user/alertPassWord";//修改密码
    public static String DELETE_ADDRESS_URL = BASE_URL+"api/app/v1/address/deleteAddress";//删除地址
    public static String ABOUT_US_URL = BASE_URL+"api/app/v1/article/AboutUS";//关于我们
    public static String GET_SUPPORT_FLAT_URL=BASE_URL+"api/app/v1/address/getFloor";//获取支持楼层
    public static String ADD_NEW_ADDRESS_URL = BASE_URL+"api/app/v1/address/addAddress";//新增地址
    public static String EDIT_ADRRRESS_URL = BASE_URL+"api/app/v1/address/editAddress";//编辑地址
    public static String GET_ADDRESS_DETAILS = BASE_URL+"api/app/v1/address/selectAddressById";//获取地址详情
    public static String CHANGE_YES_ADDRESS_URL = BASE_URL+"api/app/v1/address/editDefault";//修改为默认地址
    public static String GET_DEFAULT_ADDRESS_URL = BASE_URL+"api/app/v1/address/getDefaultAddress";//获取用户默认地址
    public static String SAFE_KNOWLEDGE_URL = BASE_URL+"api/app/v1/article/getAboutGasAll";//燃气事项
    public static String GET_SELF_ADDRESS_URL = BASE_URL+"api/app/v1/address/getPickPlace";//获取自提点
    public static String CHANGE_SEND_PAY_URL = BASE_URL+"api/app/v1/order/editOrderDeliverPayType";//修改订单支付方式和配送方式
    public static String CANCEL_ORDER_URL = BASE_URL+"api/app/v1/order/cancelOrderReason";//取消订单原因
    public static String REFUND_ORDER_URL = BASE_URL+"api/app/v1/refund/getRefundPrice";//退款
    public static String REFUND_REASON_URL = BASE_URL+"api/app/v1/refund/getRefundReason";//退款原因
    public static String APPLAY_REFUND_URL = BASE_URL+"api/app/v1/refund/applyRefund";//申请退款
    public static String UPLOAD_PHOTO_URL = BASE_BASE_URL +"api/app/v1/uploadFile";//上传图片
    public static String ATTENTION_NEW_URL = BASE_URL + "api/app/v1/attestation/addAttestation";//新增或修改认证
    public static String MY_ATTENTION_URL = BASE_URL+"api/app/v1/attestation/getMyAttestation";//我的认证
    public static String EDIT_USER_PHOTO_URL =BASE_URL+"api/app/v1/user/editUserIcon";//修改头像
    public static String INTRODUCE_GOODS_URL = BASE_URL+"api/app/v1/channel/getRecommendGoodsList";//推荐
    public static String UPDATE_APP_URL = BASE_URL+"api/app/v1/index/getVersion";//版本更新
    public static String COMMENT_ORDER_URL = BASE_URL+"api/app/v1/evaluate/addEvaluate";//新增订单评论
    public static String PUSH_INFO_URL = BASE_URL  +"api/app/v1/message/pushMessage";//推送
    public static String REFUNDED_DETAILS_URL = BASE_URL+"api/app/v1/refund/refundDetail";//退款详情
    public static String DISCUSS_HISTROY_URL = BASE_URL+"api/app/v1/refund/consultHistory";//协商历史
    public static String REFRESH_TOKEN = BASE_URL+"user/getAccessToken";//根据refreshToken申请accessToken
}