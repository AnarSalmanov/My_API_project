package com.company.JSON;

import com.github.javafaker.Faker;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONArray_ {
    /**
     * JSONArray can have list of Strings, numbers,booleans, null and Objects.
     * Note: Object itself has multiple values
     */
    /**
     *
     */


    public static void main(String[] args) throws IOException {
//        JSONObject main = new JSONObject();
//        main.put("name","sample");
//
//        // internal elements
//        JSONObject arrElem1 = new JSONObject();
//        arrElem1.put("name", "ABC");
//        arrElem1.put("type", "STRING");
//        JSONObject arrElem2 = new JSONObject();
//        arrElem2.put("name", "XYZ");
//        arrElem2.put("type", "STRING");
//        JSONArray setDef1 = new JSONArray();
//        setDef1.add(arrElem1);
//        setDef1.add(arrElem2);
//        JSONObject def1 = new JSONObject();
//        def1.put("setId",1);
//        def1.put("setDef",setDef1);
//
//        JSONObject arrElem3 = new JSONObject();
//        arrElem3.put("name", "abc");
//        arrElem3.put("type", "STRING");
//        JSONObject arrElem4 = new JSONObject();
//        arrElem4.put("name", "xyz");
//        arrElem4.put("type", "STRING");
//        JSONArray setDef2 = new JSONArray();
//        setDef2.add(arrElem3);
//        setDef2.add(arrElem4);
//        JSONObject def2 = new JSONObject();
//        def2.put("setId",2);
//        def2.put("setDef",setDef2);
//
//        //placing to main objects
//        JSONArray def = new JSONArray();
//        def.add(def1);
//        def.add(def2);
//
//        main.put("def",def);
//
//        System.out.println(main.toJSONString());
//        System.out.println(last("AABBAACCDD"));
        System.out.println(mix("AABBAACCDD"));
    }

    public static String last(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character ch = Character.valueOf(str.charAt(i));
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        return map.toString().replaceAll("\\W", "");
    }

    public static String mix(String str) {
        String build = "";
        for (int i = 0; i < str.length() - 1; i++) {
            String temp = String.valueOf(str.charAt(i));
            if (str.charAt(i)!=str.charAt(i+1)) {
                temp = temp + ",";
            }
            build += temp;
        }
        build += str.charAt(str.length() - 1);
        String[] arr = build.split(",");
        String last = "";
        for (String s : arr) {
            last += s.charAt(0) +""+ s.length();
        }
        return last;
    }


}
