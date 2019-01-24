package com.yunqilai.consumer.luckyapp.ShoppingCart.Bean;

/**
 * Created by yangyuqi on 17-7-27.
 */

public class FloorBean {
    private String floorId ;
    private String lift ;
    private String title ;

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getLift() {
        return lift;
    }

    public void setLift(String lift) {
        this.lift = lift;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FloorBean(String floorId, String lift, String title) {

        this.floorId = floorId;
        this.lift = lift;
        this.title = title;
    }
}
