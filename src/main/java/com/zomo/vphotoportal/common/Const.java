package com.zomo.vphotoportal.common;

import lombok.Getter;

public class Const {
    @Getter
    public enum ProjectStatus{
        ONLINE(0,"online"),
        OFFLINE(1,"offline");
        private Integer code;
        private String msg;

        ProjectStatus(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
