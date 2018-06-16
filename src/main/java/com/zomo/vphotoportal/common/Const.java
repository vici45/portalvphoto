package com.zomo.vphotoportal.common;

import lombok.Getter;


public class Const {

    public static final String qiNiuCdnPrefix="http://image.zomo-studio.com/";
    public static final String qiNiuCompressCdnPrefix="http://image.compress.zomo-studio.com/";
    public static final String qiNiuSmallImageSuffix="?imageView2/1/w/450/h/450/";
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
