package com.example.pentaho.services.impl;

import com.example.pentaho.models.cases_time_series;
import com.example.pentaho.models.statewise;
import com.example.pentaho.models.statistics;
import com.example.pentaho.models.tested;
import com.example.pentaho.services.dataservices;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class dataservices_impl implements dataservices {
    private static String DATA_URL = "https://data.covid19india.org/data.json";
    DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat dateFormat2 = new SimpleDateFormat("hh:mm aa");


    public List<statewise> statewiseList = new ArrayList<>();
    public List<tested> testedList = new ArrayList<>();
    public List<cases_time_series> cases_time_seriesList = new ArrayList<>();
    public String timeUpdated;

    public List<statewise> getStatewiseList(){
        return statewiseList;
    }

    public List<tested> getTestedList(){
        return testedList;
    }

    public List<cases_time_series> getCaseTimeSeriesList(){
        return  cases_time_seriesList;
    }

    @Override
    @PostConstruct
    @Scheduled(cron = "0 */30 * * * *")
    public void fetchAllData() throws IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {
        dateFormat1.setTimeZone(TimeZone.getTimeZone("IST"));
        dateFormat2.setTimeZone(TimeZone.getTimeZone("IST"));
        this.timeUpdated = dateFormat1.format(new Date())+" at "+dateFormat2.format(new Date());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONParser jsonParser = new JSONParser();
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) jsonParser.parse(httpResponse.body());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        statistics statistics = objectMapper.readValue(jsonObject.toString(),statistics.class);
        this.cases_time_seriesList = Arrays.asList(statistics.getCaseTimeSeries());
        this.statewiseList = Arrays.asList(statistics.getStateWise());
        this.testedList = Arrays.asList(statistics.getTested());
    }
}
