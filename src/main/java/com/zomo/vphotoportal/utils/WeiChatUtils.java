package com.zomo.vphotoportal.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Slf4j
public class WeiChatUtils {
    public JSONObject getAccessToken (String appId,String appSecret,String accessTokenUrl){
        String requestUrl=accessTokenUrl.replace("APPID",appId).replace("APPSECRET",appSecret);
        log.info("getAccessToken.requestUrl=====>"+requestUrl);
        JSONObject result=HttpUtils.doGet(requestUrl);
        return result;
    }

    private static String createNonceStr(){
        return UUID.randomUUID().toString();
    }
    private static String createTimeStamp(){
        return Long.toString(System.currentTimeMillis()/1000);
    }

    public JSONObject getJsApTicket(String accessToken,String jsApTicketUrl){
        String requestUrl=jsApTicketUrl.replace("ACCESS_TOKEN",accessToken);
        log.info("getJsApTicket.requestUrl=====>"+requestUrl);
        JSONObject result=HttpUtils.doGet(requestUrl);
        return result;

    }

    public Map<String,String> makeWeiChatParam(String jsApTicket,String url,String appId){
        Map<String,String> map=new HashMap();
        String timestamp=createTimeStamp();
        String nonceStr=createNonceStr();
        String combo;
        String signature="";
        combo="jsApTicket"+jsApTicket+"&nonceStr"+nonceStr+"&timestamp"+timestamp+"&url"+url;
        log.info("combo=====>"+combo);
        try {
            MessageDigest sha1=MessageDigest.getInstance("SHA-1");
            sha1.reset();
            sha1.update(combo.getBytes("UTF-8"));
            signature=byteToHex(sha1.digest());
            log.info("signature=====>"+signature);
        } catch (NoSuchAlgorithmException e) {
            log.error("weiChatUtils.makeWeiChatParam=====>begin");
            log.error(e.getMessage(),e);
            log.error("weiChatUtils.makeWeiChatParam=====>end");
        } catch (UnsupportedEncodingException e) {
            log.error("weiChatUtils.makeWeiChatParam=====>begin");
            log.error(e.getMessage(),e);
            log.error("weiChatUtils.makeWeiChatParam=====>end");
        }
        //封装数据
        map.put("timestamp",timestamp);
        map.put("nonceStr",nonceStr);
        map.put("signature",signature);
        map.put("url",url);
        map.put("jsApTicket",jsApTicket);
        map.put("appId",appId);
        return map;
    }

    private static String byteToHex(final byte[] hash){
        Formatter formatter=new Formatter();
        for(byte b:hash){
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
