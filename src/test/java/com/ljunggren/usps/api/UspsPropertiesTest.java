package com.ljunggren.usps.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class UspsPropertiesTest {

    @Test
    public void getTrackingUrlTestingTest() {
        UspsProperties properties = new UspsProperties(UspsEnvironment.TESTING);
        String url = properties.getTrackingUrl();
        assertNotNull(url);
    }

    @Test
    public void getTrackingUrlProductionTest() {
        UspsProperties properties = new UspsProperties(UspsEnvironment.PRODUCTION);
        String url = properties.getTrackingUrl();
        assertNotNull(url);
    }

}
