package com.zomo.vphotoportal.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;
import com.zomo.vphotoportal.utils.HttpUtils;
import com.zomo.vphotoportal.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class WeiChatController {
    //application.properties param
    @Value("${weichat.appId}")
    private String appId;
    @Value("${weichat.appSecret}")
    private String appSecret;
    @Value("${weichat.jsApiTicketUrl}")
    private String jsApiTicketUrl;
    @Value("${weichat.accessTokenUrl}")
    private String accessTokenUrl;
    //weiChat param
    private String accessToken;
    private String jsApiTicket;
    private Long tokenExpireTime=0L;
    private Long ticketExpireTime=0L;

    //get weiChatParam token and ticket
    @RequestMapping("/weiChatParam")
    @ResponseBody
    public Map<String,String> makeWeiChatParam(String url){
        JSONObject tokenInfo=getAccessToken();
        if (tokenInfo!=null){
            log.info("tokenInfo======"+tokenInfo.toJSONString());
            accessToken=tokenInfo.getString("access_token");
            tokenExpireTime=tokenInfo.getLong("expires_in");
        }else {
            log.error("=====>tokenInfo is null");
            log.error("=====>failure to getting tokenInfo,please check ");
        }
        JSONObject ticketInfo=getJsApiTicket();
        if (ticketInfo != null) {
            log.info("ticketInfo======"+ticketInfo.toJSONString());
            jsApiTicket=ticketInfo.getString("ticket");
            ticketExpireTime=ticketInfo.getLong("expires_in");
        }else {
            log.error("=====>ticketInfo is null");
            log.error("=====>failure to getting ticketInfo,please check ");
        }
        Map<String,String> weiChatParam=assembleWeiChatParam(url);
        return weiChatParam;
    }

    public Map<String,String> assembleWeiChatParam(String url){
        Map<String,String> paramMap=Maps.newHashMap();
        String timestamp=createTimestamp();
        String nonceStr=createNonceStr();
        String str;
        String signature="";
        str="jsapi_ticket="+jsApiTicket
                +"&noncestr="+nonceStr+
                "&timestamp="+timestamp+
                "&url="+url;
        log.info("str======"+str);

//        try {
//            MessageDigest digest=MessageDigest.getInstance("SHA1");
//            digest.reset();
//            digest.update(str.getBytes("UTF-8"));
//            signature=btye2Hex(digest.digest());
//            log.info("signature======"+signature);
//
//        } catch (NoSuchAlgorithmException e) {
//            log.error("WeiChatController.assembleWeiChatParam===========>begin");
//            log.error(e.getMessage(),e);
//            log.error("WeiChatController.assembleWeiChatParam===========>end");
//        } catch (UnsupportedEncodingException e) {
//            log.error("WeiChatController.assembleWeiChatParam===========>begin");
//            log.error(e.getMessage(),e);
//            log.error("WeiChatController.assembleWeiChatParam===========>end");
//        }
        signature=SHA1.encode(str);
        log.info("signature======"+signature);

        paramMap.put("url",url);
        paramMap.put("signature",signature);
        paramMap.put("timestamp",timestamp);
        paramMap.put("noncestr",nonceStr);
        paramMap.put("appid",appId);
        paramMap.put("jsapi_ticket",jsApiTicket);


        return paramMap;
    }
    //get accessToken From weiChat
    private JSONObject getAccessToken() {
        String tokenUrl = accessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);
        log.info("accessTokenUrl=======>"+tokenUrl);
        JSONObject jsonObject = HttpUtils.doGet(tokenUrl);
        return jsonObject;
    }
    //get jsApiTicket from weiChat by accessToken
    private JSONObject getJsApiTicket(){
        String ticketUrl=jsApiTicketUrl.replace("ACCESS_TOKEN",accessToken);
        log.info("ticketUrl============>"+ticketUrl);
        JSONObject jsonObject=HttpUtils.doGet(ticketUrl);
        return jsonObject;
    }
    //create timestamp
    private String createTimestamp(){
        return Long.toString(System.currentTimeMillis()/1000);
    }
    //create random 32 String
    private String createNonceStr(){
        return UUID.randomUUID().toString();
    }

    private static String btye2Hex(final byte[] hash){
        Formatter formatter=new Formatter();
        for (byte b :hash){
            formatter.format("%02x",b);
        }
        String result=formatter.toString();
        formatter.close();
        return result;
    }

}
