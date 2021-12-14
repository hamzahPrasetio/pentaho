package com.example.pentaho.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.example.pentaho.models.*;

public interface dataservices {
    void fetchAllData() throws IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException;
    List<statewise> getStatewiseList();
    List<tested> getTestedList();
    List<cases_time_series> getCaseTimeSeriesList();
}
