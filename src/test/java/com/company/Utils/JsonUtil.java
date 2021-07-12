package com.company.Utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {


    public static ObjectMapper mapper;

    /*
     * Converts Json file to Json String for payload.
     */
    public static String readFromJsonFile(String filePath) {
        String payload = "";
        try {
            payload = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    /**
     * Converts Pojo class's object to Json String.
     */
    public static String convertJavaToJson(Object object) {
        String payload = "";
        try {
                payload = new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    //Not using
//    public static <T> T convertJsonToJava(String jsonString, Class<T> cls) throws IOException {
//        mapper = new ObjectMapper();
//        T result = mapper.readValue(jsonString, cls);
//        return result;
//    }


}






