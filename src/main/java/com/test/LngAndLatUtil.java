package com.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class LngAndLatUtil {

    public static void main(String[] args) {
        System.out.print(LngAndLatUtil.getLngAndLatAndAddress("杭州市"));
    }

    public static String getLngAndLatAndAddress(String address) {
        Map<String, Double> map = getLngAndLat(address);
        String info = "";
        if (map.size() != 0) {
            String addressInfo = getAddress(String.valueOf(map.get("lng")), String.valueOf(map.get("lat")));
            //String psotCode = getPostCode(addressInfo);
            info = map.get("lng") + "," + map.get("lat") + "," + addressInfo;
        }
        return info;
    }

    /**
     * 通过模糊地址获取经纬度
     *
     * @param address
     * @return
     */
    public static Map<String, Double> getLngAndLat(String address) {
        Map<String, Double> map = new HashMap<>();
        String url = "http://api.map.baidu.com/location/ip?ip=&ak=LANCZEa6OYhoNKdTgm58clg1fBaHx8GR";
        String json = loadJSON(url);
        JSONObject obj;
        try {
            obj = JSONObject.fromObject(json);
            if (obj.get("status").toString().equals("0")) {
                double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
                double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
                map.put("lng", lng);
                map.put("lat", lat);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();

    }

    public static String getAddress(String lng, String lat) {
        String add = getAdd(lng, lat);
        JSONObject jsonObject = JSONObject.fromObject(add);
        JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addrList"));
        JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String address_road = "";
        if (j_2.containsKey("addr")) {
            address_road = j_2.getString("addr");
        }
        String addressInfo = allAdd.replaceAll(",", "") + address_road;
        return addressInfo;
    }

    public static String getAdd(String log, String lat) {
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        return res;
    }
}
