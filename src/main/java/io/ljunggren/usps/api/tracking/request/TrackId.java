package io.ljunggren.usps.api.tracking.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "TrackID")
public class TrackId {

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    private String id;
    
}
