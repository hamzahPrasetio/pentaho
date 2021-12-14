package com.example.pentaho.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class tested implements Serializable {
    @JsonProperty("testsconductedbyprivatelabs")
    private String testsConductedByPrivateLabs;

    @JsonProperty("totalsamplestested")
    private String totalSamplesTested;

    @JsonProperty("positivecasesfromsamplesreported")
    private String positiveCasesFromSamplesReported;

    @JsonProperty("samplereportedtoday")
    private String sampleReportedToday;

    @JsonProperty("source")
    private String source;

    @JsonProperty("updatetimestamp")
    private String updateTimeStamp;

    @JsonProperty("testpositivityrate")
    private String testPositivityRate;

    @JsonProperty("totalindividualstested")
    private String totalIndividualsTested;

    @JsonProperty("totalpositivecases")
    private String totalPositiveCases;
}