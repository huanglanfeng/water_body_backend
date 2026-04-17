package com.blackwater.entity.ElementAndDissolve;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
public class ElementAndDissolve {

    private ArrayList<Double> Dissolve;
    private ArrayList<Double> Conductivity;
    private ArrayList<Double> Inorganic;
    private ArrayList<Double> Organic;
    private ArrayList<Double> Metal;
    private ArrayList<Double> Salt;
    private String site;

}
