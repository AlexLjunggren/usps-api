package io.ljunggren.usps.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UspsProperties {

    private static Properties properties = new Properties();
    
    private static final String API_TRACKING_URL = "api.tracking.url";

    public UspsProperties(UspsEnvironment environment) {
        Map<UspsEnvironment, String> fileMap = generateFileMap();
        setProperties(fileMap.get(environment));
    }
    
    private static Map<UspsEnvironment, String> generateFileMap() {
        Map<UspsEnvironment, String> fileMap = new HashMap<UspsEnvironment, String>();
        fileMap.put(UspsEnvironment.TESTING, "/properties/testing/usps.properties");
        fileMap.put(UspsEnvironment.PRODUCTION, "/properties/production/usps.properties");
        return fileMap;
    }
    
    private static void setProperties(String file) {
        try {
            InputStream is = UspsProperties.class.getResourceAsStream(file);
            properties.load(is);
            is.close();
        } catch(IOException e) {
            throw new RuntimeException("Error loading properties", e);
        }
    }
    
    public String getTrackingUrl() {
        return properties.getProperty(API_TRACKING_URL);
    }
    
}
