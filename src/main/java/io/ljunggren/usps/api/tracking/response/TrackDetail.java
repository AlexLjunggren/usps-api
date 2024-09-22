package io.ljunggren.usps.api.tracking.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class TrackDetail {

    @JacksonXmlProperty(localName = "EventTime")
    private String eventTime;
    @JacksonXmlProperty(localName = "EventDate")
    private String eventDate;
    @JacksonXmlProperty(localName = "Event")
    private String event;
    @JacksonXmlProperty(localName = "EventCity")
    private String eventCity;
    @JacksonXmlProperty(localName = "EventState")
    private String eventState;
    @JacksonXmlProperty(localName = "EventZIPCode")
    private String eventZipCode;
    @JacksonXmlProperty(localName = "EventCountry")
    private String eventCountry;
    @JacksonXmlProperty(localName = "FirmName")
    private String firmName;
    @JacksonXmlProperty(localName = "Name")
    private String name;
    @JacksonXmlProperty(localName = "AuthorizedAgent")
    private boolean authorizedAgent;
    @JacksonXmlProperty(localName = "EventCode")
    private String eventCode;
    @JacksonXmlProperty(localName = "DeliveryAttributeCode")
    private String deliveryAttributeCode;
    @JacksonXmlProperty(localName = "GMT")
    private String gmt;
    @JacksonXmlProperty(localName = "GMTOffset")
    private String gmtOffset;


}
