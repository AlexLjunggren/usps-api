package io.ljunggren.usps.api.tracking.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
@JacksonXmlRootElement(localName = "TrackResponse")
public class TrackingResponse {

    @JacksonXmlProperty(localName = "TrackInfo")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TrackInfo> trackInfos;
    
    @JsonIgnore
    public void addTrackInfo(TrackInfo trackInfo) {
        if (trackInfo == null) {
            return;
        }
        if (trackInfos == null) {
            trackInfos = new ArrayList<>();
        }
        trackInfos.add(trackInfo);
    }
    
}
