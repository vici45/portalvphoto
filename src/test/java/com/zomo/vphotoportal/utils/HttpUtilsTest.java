package com.zomo.vphotoportal.utils;

import com.alibaba.fastjson.JSONObject;
import com.zomo.vphotoportal.ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class HttpUtilsTest extends ApplicationTests {

    @Test
    public void doGet(){
        JSONObject jsonObject=HttpUtils.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxad117f6cb8c8c8d1&secret=26d37eb3901089fdaccba0b8e1c6605a");
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("access_token"));
    }

}