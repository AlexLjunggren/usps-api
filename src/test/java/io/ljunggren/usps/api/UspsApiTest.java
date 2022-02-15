package io.ljunggren.usps.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.ljunggren.usps.api.tracking.response.ErrorResponse;
import io.ljunggren.usps.api.tracking.response.TrackingResponse;
import io.ljunggren.xmlUtils.XmlUtils;

@RunWith(MockitoJUnitRunner.class)
public class UspsApiTest {

    @Mock
    private CloseableHttpClient httpClient;
    
    @Mock
    private CloseableHttpResponse httpResponse;
    
    @Mock
    private HttpEntity httpEntity;
    
    @Mock
    private StatusLine statusLine;
    
    private String readFromResources(String file) throws IOException {
        return IOUtils.toString(this.getClass().getResourceAsStream(file), "UTF-8");
    }

    private void setup(String json, int responseCode) throws ClientProtocolException, IOException {
        InputStream content = new ByteArrayInputStream(json.getBytes());
        when(httpClient.execute(any(HttpPost.class))).thenReturn(httpResponse);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(responseCode);
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(content);
    }
    
    @Test
    public void trackTest() throws IOException {
        String xml = readFromResources("/trackingResponse.xml");
        setup(xml, 200);
        UspsEnvironment environment = UspsEnvironment.TESTING;
        UspsApi uspsApi = new UspsApi(environment, xml);
        TrackingResponse trackingResponse = uspsApi.track("12345", httpClient);
        assertNotNull(trackingResponse.getTrackInfo());
        assertNull(trackingResponse.getError());
    }
    
    @Test
    public void trackReturnedErrorTest() throws IOException {
        String xml = readFromResources("/authorizationErrorResponse.xml");
        setup(xml, 200);
        ErrorResponse errorResponse = XmlUtils.xmlToObject(xml, ErrorResponse.class);
        UspsEnvironment environment = UspsEnvironment.TESTING;
        UspsApi uspsApi = new UspsApi(environment, xml);
        TrackingResponse trackingResponse = uspsApi.track("12345", httpClient);
        assertEquals(errorResponse.getDescription(), trackingResponse.getError());
        assertNull(trackingResponse.getTrackInfo());
    }

    @Test
    public void trackNonOkResponseTest() throws ClientProtocolException, IOException {
        String xml = "notRealResponse";
        setup(xml, 407);
        UspsEnvironment environment = UspsEnvironment.TESTING;
        UspsApi uspsApi = new UspsApi(environment, xml);
        TrackingResponse trackingResponse = uspsApi.track("12345", httpClient);
        assertEquals("Response code 407", trackingResponse.getError());
        assertNull(trackingResponse.getTrackInfo());
    }
    
    @Test
    public void trackExceptionThrownTest() throws ClientProtocolException, IOException {
        String xml = "notRealResponse";
        setup(xml, 200);
        UspsEnvironment environment = UspsEnvironment.TESTING;
        UspsApi uspsApi = new UspsApi(environment, xml);
        TrackingResponse trackingResponse = uspsApi.track("12345", httpClient);
        assertEquals("Unable to parse response", trackingResponse.getError());
        assertNull(trackingResponse.getTrackInfo());
    }
    
}
