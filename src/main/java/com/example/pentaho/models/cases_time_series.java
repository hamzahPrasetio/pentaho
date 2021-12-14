package com.example.pentaho.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class cases_time_series implements Serializable {
    @JsonProperty ("date")
    private String date;

    @JsonProperty("dailyrecovered")
    private String dailyRecovered;

    @JsonProperty("totalconfirmed")
    private String totalConfirmed;

    @JsonProperty("totaldeceased")
    private String totalDeceased;

    @JsonProperty("dailydeceased")
    private String dailyDeceased;

    @JsonProperty("totalrecovered")
    private String totalRecovered;

    @JsonProperty("dailyconfirmed")
    private String dailyConfirmed;

}
