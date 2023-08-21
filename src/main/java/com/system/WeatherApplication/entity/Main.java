package com.system.WeatherApplication.entity;

import lombok.Data;

 @Data
 public class Main {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;

 }
