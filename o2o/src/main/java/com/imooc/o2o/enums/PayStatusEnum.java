package com.imooc.o2o.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 17:16
 */
@Getter
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),

    ;

    private int state;
    private String stateInfo;

    PayStatusEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static PayStatusEnum stateOf(int index) {
        for (PayStatusEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
