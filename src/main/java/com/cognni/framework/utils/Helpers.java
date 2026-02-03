package com.cognni.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Helpers {

    // Load properties from config.properties file
    private static Properties properties;
    private static final String FILE_PATH = "src/main/resources/config.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream(FILE_PATH);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get value by key from properties
    public static String getValue(String key) {
        return properties.getProperty(key);
    }
}
