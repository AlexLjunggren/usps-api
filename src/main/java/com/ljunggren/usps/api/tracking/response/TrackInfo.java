package com.ljunggren.usps.api.tracking.response;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackInfo {

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    private String id;
    @JacksonXmlProperty(localName = "TrackSummary")
    private String summary;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "TrackDetail")
    private List<String> trackDetails;
    
}
