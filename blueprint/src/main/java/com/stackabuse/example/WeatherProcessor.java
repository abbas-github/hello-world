package com.stackabuse.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String json = exchange.getIn().getBody(String.class);
        JSONObject obj = new JSONObject(json);
        JSONArray list = obj.getJSONArray("list");
        JSONObject day = list.getJSONObject(1);
        double dayTemp = day.getJSONObject("temp").getDouble("day");
        
        Long timestampLong = day.getLong("dt");
        Date date = new Date(timestampLong * 1000);
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        
        exchange.getOut().setBody(dateString + ": " + dayTemp + " Celsius", String.class);
    }

}
