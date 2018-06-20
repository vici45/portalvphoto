package com.zomo.vphotoportal.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class HttpUtils {
    public static JSONObject doGet(String url){
        CloseableHttpClient client=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=null;
        String responseContent=null;
        JSONObject result=null;
        try {
            response=client.execute(httpGet);
            HttpEntity entity=response.getEntity();
            responseContent=EntityUtils.toString(entity,"UTF-8");
            result=JSON.parseObject(responseContent);

        } catch (IOException e) {
            log.error("httpUtils------------begin");
            log.error(e.getMessage(),e);
            log.error("httpUtils------------end");
        }
        return result;
    }

}
