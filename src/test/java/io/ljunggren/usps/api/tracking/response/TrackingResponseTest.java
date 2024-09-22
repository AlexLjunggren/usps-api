package io.ljunggren.usps.api.tracking.response;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import io.ljunggren.xml.utils.XmlUtils;

public class TrackingResponseTest {

    private String readFromResources(String file) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(file), "UTF-8");
    }

    @Test
    public void serializeTest() throws IOException {
        String xml = readFromResources("/trackingResponse.xml");
        TrackingResponse response = XmlUtils.xmlToObject(xml, TrackingResponse.class);
        String serializedXml = XmlUtils.objectToXml(response);
        assertTrue(XmlUtils.areEqual(xml, serializedXml));
    }
    
}
