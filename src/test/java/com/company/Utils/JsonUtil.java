package com.company.Utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonUtil {
    /**
     * Object Mapper is a class from Jackson library
     * Methods are:
     * .writeValueAsString(Object object)
     *      -> In serialization converts Class object to Json String
     * .writeValue
     *      -> Used to write value to Json file
     */

    public static ObjectMapper mapper;

    /*
     *This method is used for just copy from Json and Paste to Body as String
     * Accepts filePath location as string parameter of method.
     */
    public static String readFromJsonFile(String filePath) throws IOException {
        String payload = new String(Files.readAllBytes(Paths.get(filePath)));
        return payload;
    }

    /**
     * This method is used after creating object of Pojo class, in order to
     * convert created  Java object to JSON format String to use it in body of payload.
     */
    public static String convertJavaToJson(Object object) throws IOException {
        mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(object);
        return payload;
    }

    //Not using
//    public static <T> T convertJsonToJava(String jsonString, Class<T> cls) throws IOException {
//        mapper = new ObjectMapper();
//        T result = mapper.readValue(jsonString, cls);
//        return result;
//    }


}






