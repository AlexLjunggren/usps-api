package io.ljunggren.usps.api.tracking.request;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import io.ljunggren.xml.utils.XmlUtils;

public class TrackFieldRequestTest {

    private String readFromResources(String file) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(file), "UTF-8");
    }

    @Test
    public void serializeTest() throws IOException {
        String xml = readFromResources("/trackFieldRequest.xml");
        TrackFieldRequest request = XmlUtils.xmlToObject(xml, TrackFieldRequest.class);
        String serializedXml = XmlUtils.objectToXml(request);
        assertTrue(XmlUtils.areEqual(xml, serializedXml));
    }

}
