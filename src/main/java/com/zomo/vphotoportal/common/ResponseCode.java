package com.zomo.vphotoportal.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(0,"success"),
    ERROR(1,"error");
    private Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
