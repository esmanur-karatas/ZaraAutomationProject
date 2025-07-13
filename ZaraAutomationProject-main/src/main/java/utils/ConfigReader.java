package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // ClassLoader ile classpath'ten (resources) dosyayı oku
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("configuration.properties");

            if (input == null) {
                throw new RuntimeException("configuration.properties dosyası bulunamadı (classpath içinde olmalı)");
            }

            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException("configuration.properties dosyası okunamadı!", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

