package com.alex.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertiesMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("conf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return (String) props.get(key);
    }

    ;
}
