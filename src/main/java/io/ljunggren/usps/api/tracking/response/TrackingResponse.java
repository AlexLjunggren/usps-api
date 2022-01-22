package io.ljunggren.usps.api.tracking.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "TrackResponse")
public class TrackingResponse {

    @JacksonXmlProperty(localName = "TrackInfo")
    private TrackInfo trackInfo;
    @JsonIgnore
    private String error;
    
}
