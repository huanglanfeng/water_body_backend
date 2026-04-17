package com.blackwater.entity;

import lombok.Data;

@Data
public class DissolvedOxygen {

    private double sulfate;
    private double nitrate;
    private double permanganate;
    private double ph;
    private double conductivity;

    public double getSulfate() {
        return sulfate;
    }

    public void setSulfate(double sulfate) {
        this.sulfate = sulfate;
    }

    public double getNitrate() {
        return nitrate;
    }

    public void setNitrate(double nitrate) {
        this.nitrate = nitrate;
    }

    public double getPermanganate() {
        return permanganate;
    }

    public void setPermanganate(double permanganate) {
        this.permanganate = permanganate;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    @Override
    public String toString() {
        return "DissolvedOxygen{" +
                "sulfate=" + sulfate +
                ", nitrate=" + nitrate +
                ", permanganate=" + permanganate +
                ", ph=" + ph +
                ", conductivity=" + conductivity +
                '}';

    }
}
