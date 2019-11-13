package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static String propFile = "db.properties";
    private static Properties properties;

    static {
        init();
    }

    private PropertyReader() {
    }

    private static void init() {
        properties = new Properties();
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFile)) {
            properties.load(inputStream);
        } catch (Exception e) {
            System.err.println("There is a problem loading properties file " + propFile);
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        String myProperty = properties.getProperty(property);
        if (myProperty == null) {
            System.err.println("There is no \"" + property + "\" field in \"" + propFile + "\"");
            return "";
        }
        return myProperty;
    }
}