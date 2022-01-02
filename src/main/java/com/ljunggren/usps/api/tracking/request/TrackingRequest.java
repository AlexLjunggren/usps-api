package com.ljunggren.usps.api.tracking.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "TrackRequest")
public class TrackingRequest {

    @JacksonXmlProperty(isAttribute = true, localName = "USERID")
    private String userId;
    @JacksonXmlProperty(localName = "TrackID")
    private TrackId trackId;
    
}
