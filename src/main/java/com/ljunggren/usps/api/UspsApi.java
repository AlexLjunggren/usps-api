package com.ljunggren.usps.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.ljunggren.usps.api.tracking.request.TrackId;
import com.ljunggren.usps.api.tracking.request.TrackingRequest;
import com.ljunggren.usps.api.tracking.response.ErrorResponse;
import com.ljunggren.usps.api.tracking.response.TrackingResponse;
import com.ljunggren.xmlUtils.XmlUtils;

public class UspsApi {

    private String username;
    private UspsProperties properties;
    
    public UspsApi(UspsEnvironment environment, String username) {
        this.username = username;
        this.properties = new UspsProperties(environment);
    }
    
    public TrackingResponse track(String trackingNumber) throws IOException {
        return track(trackingNumber, HttpClients.createDefault());
    }
    
    // package private for unit testing
    TrackingResponse track(String trackingNumber, CloseableHttpClient httpClient) throws IOException {
        String url = properties.getTrackingUrl();
        HttpPost post = new HttpPost(url);
        try {
            String xmlRequest = XmlUtils.objectToXml(generateTrackingRequest(trackingNumber));
            List<NameValuePair> params = Arrays.asList(new NameValuePair[] {
                    new BasicNameValuePair("api", "trackV2"),
                    new BasicNameValuePair("xml", xmlRequest)
            });
            post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == HttpStatus.SC_OK) {
                String xml = getResult(httpResponse);
                return parseTrackingResponse(xml);
            }
            return new TrackingResponse(null, String.format("Response code %d", responseCode));
        } catch (Exception e) {
            return new TrackingResponse(null, e.getMessage());
        } finally {
            httpClient.close();
        }
    }
    
    private TrackingRequest generateTrackingRequest(String trackingNumber) {
        TrackId trackId = new TrackId(trackingNumber);
        return new TrackingRequest(username, trackId);
    }
    
    private TrackingResponse parseTrackingResponse(String xml) throws Exception {
        try {
            return parse(xml, TrackingResponse.class);
        }
        catch (Exception e) {
            ErrorResponse errorResponse = parse(xml, ErrorResponse.class);
            return new TrackingResponse(null, errorResponse.getDescription());
        }
    }
    
    private <T> T parse(String xml, Class<T> clazz) throws Exception {
        try {
            return XmlUtils.xmlToObject(xml, clazz);
        } catch (Exception e) {
            throw new Exception("Unable to parse response");
        }
    }
    
    private static String getResult(HttpResponse response) throws UnsupportedOperationException, IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder result = new StringBuilder();
        String line = new String();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
    
}
