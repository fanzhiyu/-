package com.easy.work.weather.controller;


import com.alibaba.fastjson.JSONObject;
import com.easy.core.util.ResponseUtil;
import com.easy.core.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by apple on 18/3/29.
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    /**
     * 获取天气情况
     * @return
     */
    @RequestMapping(value = "/getWeather", method = RequestMethod.GET)
    public ResponseEntity getWeather() throws UnsupportedEncodingException, UnknownHostException {
        JSONObject res = null;
        String ipUrl = "http://www.3322.org/dyndns/getip";
        String ip = sendGetRequest(ipUrl);
        if(StringUtils.isNotNull(ip)){
            String cityUrl = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
            JSONObject cityStr = JSONObject.parseObject(sendGetRequest(cityUrl));
            if(StringUtils.isNotNull(cityStr)){
                JSONObject data = cityStr.getJSONObject("data");
                String city = data.getString("city");
                String url = "http://v.juhe.cn/weather/index?cityname="+city+"&format=2&key=f80b130b641a28704e087ceda41df4ef";
                res = JSONObject.parseObject(sendGetRequest(url));
            }
        }
        return ResponseUtil.success(res);
    }

    /**
     * 发送get请求
     * @param getUrl
     * @return
     */
    private String sendGetRequest(String getUrl) {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try
        {
            URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }  finally{
            try {
                isr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
