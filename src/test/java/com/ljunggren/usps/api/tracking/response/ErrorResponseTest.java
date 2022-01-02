package com.ljunggren.usps.api.tracking.response;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.ljunggren.xmlUtils.XmlUtils;

public class ErrorResponseTest {

    private String readFromResources(String file) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(file), "UTF-8");
    }

    @Test
    public void serializeTest() throws IOException {
        String xml = readFromResources("/authorizationErrorResponse.xml");
        ErrorResponse errorResponse = XmlUtils.xmlToObject(xml, ErrorResponse.class);
        String serializedXml = XmlUtils.objectToXml(errorResponse);
        assertTrue(XmlUtils.areEqual(xml, serializedXml));
    }

}
