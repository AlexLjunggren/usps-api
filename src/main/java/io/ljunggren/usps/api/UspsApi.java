package io.ljunggren.usps.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import io.ljunggren.usps.api.tracking.request.TrackFieldRequest;
import io.ljunggren.usps.api.tracking.response.Error;
import io.ljunggren.usps.api.tracking.response.TrackInfo;
import io.ljunggren.usps.api.tracking.response.TrackingResponse;
import io.ljunggren.xml.utils.XmlUtils;

public class UspsApi {

    private String username;
    private UspsProperties properties;
    
    public UspsApi(UspsEnvironment environment, String username) {
        this.properties = new UspsProperties(environment);
        this.username = username;
    }
    
    public TrackingResponse track(String... trackingNumbers) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = properties.getTrackingUrl();
        HttpPost post = new HttpPost(url);
        try {
            String xmlRequest = XmlUtils.objectToXml(generateTrackingRequest(trackingNumbers));
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
            return generateError(String.format("Response code %d", responseCode));
        } catch (Exception e) {
            return generateError(e.getMessage());
        } finally {
            httpClient.close();
        }
    }
    
    private TrackFieldRequest generateTrackingRequest(String... trackingNumbers) {
        TrackFieldRequest request = new TrackFieldRequest();
        request.setUserId(username);
        Stream.of(trackingNumbers).forEach(trackingNumber -> request.addTrackingNumber(trackingNumber));
        return request;
    }
    
    private TrackingResponse parseTrackingResponse(String xml) throws Exception {
        try {
            return parse(xml, TrackingResponse.class);
        }
        catch (Exception e) {
            return generateError(e.getMessage());
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
    
    private TrackingResponse generateError(String message) {
        TrackingResponse response = new TrackingResponse();
        TrackInfo trackInfo = new TrackInfo();
        Error error = new Error(0, message);
        trackInfo.setError(error);
        response.addTrackInfo(trackInfo);
        return response;
    }
    
}
