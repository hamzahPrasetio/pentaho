package com.example.pentaho.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class statistics implements Serializable {

    @JsonProperty("cases_time_series")
    private cases_time_series[] caseTimeSeries;

    @JsonProperty("tested")
    private tested[] tested;

    @JsonProperty("statewise")
    private statewise[] stateWise;
}
