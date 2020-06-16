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

    private static ObjectMapper mapper = new ObjectMapper();


    /**
     * Accepting the object of pojo class and converting to Json (String for body)
     * Store returned data in String
     * Returns String
     */
    public static String convertJavaToJson(Object object) throws IOException {
        String jsonResult = mapper.writeValueAsString(object);
        return jsonResult;
    }

    /**
     * Accepts Json String and Pojo class as parameter, returns Java object.
     * Returns Java object, Must be stored in Pojo's class object.
     * Using that object we call getter methods to get the value for certain variables.
     */

    public static <T> T convertJsonToJava(String jsonString, Class<T> cls) throws IOException {
        T result = mapper.readValue(jsonString, cls);
        return result;
    }

    /**
     * This method accepts path of Json file,
     * Using this method we can read data from Json file and use as Payload
     */
    public static String readFromJsonFile(String filePath) throws IOException {
        String payload = new String(Files.readAllBytes(Paths.get(filePath)));
        return payload;
    }
}






