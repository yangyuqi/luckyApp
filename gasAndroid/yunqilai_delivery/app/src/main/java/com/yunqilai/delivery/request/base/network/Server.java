package com.yunqilai.delivery.request.base.network;

/**
 * Created by KK on 2017/2/17.
 */
public class Server {

    public static final int CONNECT_TIMEOUT = 30;//网络超时时间

    public static final String IMG_HOST = "192.168.2.197:8180";
    public static final String HOST = "192.168.2.197:8180";
    public static final String APP = "/gas";
    public static final String HOST_VERSION = "/api/app/v1";

    public static String getUrl(String interfaceName) {
        String myInterfaceName;
        if (!interfaceName.substring(0, 1).equals("/")) {
            myInterfaceName = "/" + interfaceName;
        } else {
            myInterfaceName = interfaceName;
        }
        return "http://" + Server.HOST  + APP + HOST_VERSION +myInterfaceName;
    }
}
