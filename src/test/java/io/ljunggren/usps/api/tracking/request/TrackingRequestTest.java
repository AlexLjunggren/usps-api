package io.ljunggren.usps.api.tracking.request;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import io.ljunggren.xmlUtils.XmlUtils;

public class TrackingRequestTest {

    private String readFromResources(String file) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(file), "UTF-8");
    }

    @Test
    public void serializeTest() throws IOException {
        String xml = readFromResources("/trackingRequest.xml");
        TrackingRequest errorResponse = XmlUtils.xmlToObject(xml, TrackingRequest.class);
        String serializedXml = XmlUtils.objectToXml(errorResponse);
        assertTrue(XmlUtils.areEqual(xml, serializedXml));
    }

}
