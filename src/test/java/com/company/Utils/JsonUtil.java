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

    public static String readFromJsonFile(String filePath) throws IOException {
        String payload = new String(Files.readAllBytes(Paths.get(filePath)));
        return payload;
    }

    public static String convertJavaToJson(Object object) throws IOException {
        String jsonResult = mapper.writeValueAsString(object);
        return jsonResult;
    }


    public static <T> T convertJsonToJava(String jsonString, Class<T> cls) throws IOException {
        T result = mapper.readValue(jsonString, cls);
        return result;
    }


}






