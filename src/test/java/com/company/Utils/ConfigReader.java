package com.company.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {


    public static String getProperty(String key) {
        Properties prop = new Properties(); //outside of block
        try {
            FileInputStream fis = new FileInputStream("configuration.properties");
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }


}
