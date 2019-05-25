package com.imooc.o2o.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 17:12
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    SUCCESS_GET(1006,"成功获取"),
    INNER_ERROR(-1001, "内部系统错误"), NULL_ORDERID(-1002, "orderId为空"), NULL_ORDER(-1003, "order信息为空"),
    NULL_CONDITION(-1004,"缺少查询条件"),
    NULL_ORDERDETAIL(-1005,"未查询到订单");


    private int state;
    private String stateInfo;

    OrderStatusEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static OrderStatusEnum stateOf(int index) {
        for (OrderStatusEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(OrderStatusEnum.stateOf(3).getStateInfo());
    }
}
