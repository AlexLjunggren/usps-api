package io.ljunggren.usps.api.tracking.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class TrackInfo {

    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    private String id;
    @JacksonXmlProperty(localName = "Class")
    private String clazz;
    @JacksonXmlProperty(localName = "ClassOfMailCode")
    private String classOfMailcode;
    @JacksonXmlProperty(localName = "DestinationCity")
    private String destinationCidy;
    @JacksonXmlProperty(localName = "DestinationState")
    private String destinationState;
    @JacksonXmlProperty(localName = "DestinationZip")
    private String destinationZip;
    @JacksonXmlProperty(localName = "EmailEnabled")
    private Boolean emailEnabled;
    @JacksonXmlProperty(localName = "KahalaIndicator")
    private Boolean kahalaIndicator;
    @JacksonXmlProperty(localName = "MailTypeCode")
    private String mailTypeCode;
    @JacksonXmlProperty(localName = "MPDATE")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSSSSS")
    private Date mpDate;
    @JacksonXmlProperty(localName = "MPSUFFIX")
    private Date mpSuffix;
    @JacksonXmlProperty(localName = "OriginCity")
    private String originCity;
    @JacksonXmlProperty(localName = "OriginState")
    private String originState;
    @JacksonXmlProperty(localName = "OriginZip")
    private String originZip;
    @JacksonXmlProperty(localName = "PodEnabled")
    private Boolean podEnabled;
    @JacksonXmlProperty(localName = "TPodEnabled")
    private Boolean tPodEnabled;
    @JacksonXmlProperty(localName = "RestoreEnabled")
    private Boolean restoreEnabled;
    @JacksonXmlProperty(localName = "RramEnabled")
    private Boolean rramEnabled;
    @JacksonXmlProperty(localName = "RreEnabled")
    private Boolean rreEnabled;
    @JacksonXmlProperty(localName = "Service")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> services;
    @JacksonXmlProperty(localName = "ServiceTypeCode")
    private String serviceTypeCode;
    @JacksonXmlProperty(localName = "Status")
    private String status;
    @JacksonXmlProperty(localName = "StatusCategory")
    private String statusCategory;
    @JacksonXmlProperty(localName = "StatusSummary")
    private String statusSummary;
    @JacksonXmlProperty(localName = "TABLECODE")
    private String tableCode;
    @JacksonXmlProperty(localName = "TrackSummary")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TrackDetail> trackSummaries;
    @JacksonXmlProperty(localName = "TrackDetail")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<TrackDetail> trackDetails;
    @JacksonXmlProperty(localName = "Error")
    private Error error;
}
