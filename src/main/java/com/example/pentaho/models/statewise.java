package com.example.pentaho.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class statewise implements Serializable {
    @JsonProperty("active")
    private String active;
    @JsonProperty("confirmed")
    private String confirmed;
    @JsonProperty("deaths")
    private String deaths;
    @JsonProperty("deltaconfirmed")
    private String deltaConfirmed;
    @JsonProperty("deltadeaths")
    private String deltaDeaths;
    @JsonProperty("deltarecovered")
    private String deltaRecovered;
    @JsonProperty("lastupdatedtime")
    private String lastUpdatedTime;
    @JsonProperty("migratedother")
    private String migratedOther;
    @JsonProperty("recovered")
    private String recovered;
    @JsonProperty("state")
    private String state;
    @JsonProperty("statecode")
    private String stateCode;
    @JsonProperty("statenotes")
    private String stateNotes;


}
