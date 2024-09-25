package io.ljunggren.usps.api.tracking.request;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "TrackFieldRequest")
public class TrackFieldRequest {

    @JacksonXmlProperty(isAttribute = true, localName = "USERID")
    private String userId;
    @JacksonXmlProperty(localName = "Revision")
    private int revision = 1;
    @JacksonXmlProperty(localName = "ClientIp")
    private String clientIp = "127.0.0.1";
    @JacksonXmlProperty(localName = "SourceId")
    private String sourceId = "Default";
    @JacksonXmlProperty(localName = "TrackID")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TrackId> trackIds;
    
    @JsonIgnore
    public void addTrackingNumber(String trackingNumber) {
        if (trackingNumber == null) {
            return;
        }
        TrackId trackId = new TrackId(trackingNumber);
        if (trackIds == null) {
            trackIds = new ArrayList<>();
        }
        trackIds.add(trackId);
    }

}
