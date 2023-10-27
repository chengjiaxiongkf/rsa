package com.rsa;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SortUtils {
//    public static void main(String[] args) {
//        String jsonStr = "{ \"partner_code\": \"KDPCTF\", \"kdp_member_number\": \"00002472166\", \"partner_serial_number\": \"PointAsCash-20231027161327-1698394407812\", \"partner_order_number\": \"ORDER-20231027161327-1698394407812\", \"partner_member_number\": \"123456789\", \"partner_member_tier_code\": \"金卡\", \"deduct_type\": \"3\", \"deduct_value\": \"\", \"point_delta\": \"-1000\", \"transaction_at\": \"20231027161327\", \"transaction_type\": 1, \"key_version\": \"v3\", \"sign\": \"JeCfGhGef5jX3XqmBMWLeta1lVqXKiSAuS8YFR0DWwNSCx4qjDyfYOC4Z9xKRdcvAOxWU+cv8emiY35FIy0SaVe2ppoyn5hJ9gF8F2CsF9Trb0t5LNMCkhFcNihEAphbDzSLc9qXIef7zciPta3qVa+0Jf0jDAkNCQkhgw92M1Y=\", \"transaction_desc\": { \"partner_campaign_code\": \"201704001\", \"partner_store_code\": \"STORE-001\", \"partner_store_name\": { \"en\": \"jichang\", \"zh-cn\": \"jichang\", \"ZH-HK\": \"jichang\" }, \"currency\": \"2\", \"amount\": \"2500.00\", \"payment\": [ { \"method\": \"UnionPay\", \"amount\": \"1500.00\" }, { \"method\": \"Coupon\", \"amount\": \"1000.00\" } ], \"skus\": [ { \"sku_code\": \"SKU-1234\", \"sku_name\": \"钛合金宝石\", \"sku_category\": \"SKU-CAT-A\", \"sku_brand\": \"Disney\", \"sku_tag\": \"5A1\", \"quantity\": 1, \"list_unit_price\": \"128.00\", \"actual_unit_price\": \"100.00\", \"additional_info\": { \"sku_subbrand\": \"abc\" } }, { \"sku_code\": \"SKU-1235\", \"sku_name\": \"动感扭片银925手链\", \"sku_category\": \"SKU-CAT-B\", \"sku_brand\": \"Disney\", \"sku_tag\": \"5A1\", \"quantity\": 1, \"list_unit_price\": \"1280.00\", \"actual_unit_price\": \"1000.00\", \"additional_info\": { \"sku_subbrand\": \"abc\" } } ], \"description\": \"积分抵现扣分\" } }";
//
//        JSONObject json = new JSONObject(jsonStr);
//        Map<String, Object> data = json.toBean(Map.class);
//
//        String queryData = parseParam(jsonSort(data));
//        System.out.println(queryData);
//    }

    public static String parseParam(Map<String, Object> param) {
        StringBuilder paramStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String || value instanceof Number || value instanceof Boolean) {
                if (!"sign".equals(key)) {
                    try{
                        paramStr.append("&").append(key).append("=").append(URLEncoder.encode(value.toString(),"UTF-8"));
                    }catch (Exception e){

                    }
                }
            } else if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> subMap = (Map<String, Object>) value;
                for (Map.Entry<String, Object> subEntry : subMap.entrySet()) {
                    String subKey = subEntry.getKey();
                    Object subValue = subEntry.getValue();
                    String k = key + "[" + subKey + "]";
                    paramStr.append("&").append(parseParam(createMap(k, subValue)));
                }
            } else if (value instanceof List) {
                @SuppressWarnings("unchecked")
                List<Object> list = (List<Object>) value;
                for (int i = 0; i < list.size(); i++) {
                    String k = key + "[" + i + "]";
                    paramStr.append("&").append(parseParam(createMap(k, list.get(i))));
                }
            }
        }
        return paramStr.substring(1);
    }

    public static Map<String, Object> jsonSort(Map<String, Object> obj) {
        Map<String, Object> list = new TreeMap<>();
        Map<String, Object> jsonObj = objKeySort(obj);
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                list.put(key, jsonSort((Map<String, Object>) value));
            } else if (value instanceof List) {
                list.put(key, jsonSort(arrayToMap((List<Object>) value)));
            } else {
                list.put(key, value);
            }
        }
        return list;
    }

    public static Map<String, Object> objKeySort(Map<String, Object> obj) {
        Map<String, Object> newObj = new TreeMap<>();
        newObj.putAll(obj);
        return newObj;
    }

    public static Map<String, Object> arrayToMap(List<Object> array) {
        Map<String, Object> map = new TreeMap<>();
        for (int i = 0; i < array.size(); i++) {
            map.put(String.valueOf(i), array.get(i));
        }
        return map;
    }

    public static Map<String, Object> createMap(String key, Object value) {
        key = Pattern.compile("\\d").matcher(key).replaceAll("");
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
