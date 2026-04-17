package com.blackwater.entity.Percent;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Percent {
    private ArrayList<Double> Excellent;
    private ArrayList<Double> Good;
    private ArrayList<Double> Pollute;
    private ArrayList<Double> Serious;

}
