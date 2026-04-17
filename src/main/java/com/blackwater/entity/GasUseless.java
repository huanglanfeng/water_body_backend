package com.blackwater.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class GasUseless {
    @ApiModelProperty("氨气")
    private double ammonia;
    @ApiModelProperty("二氧化硫")
    private double sulfur_dioxide;
    @ApiModelProperty("硫化氢")
    private double hydrogen_sulfide;
    @ApiModelProperty("二氧化氮")
    private double nitrogen_dioxide;
    @ApiModelProperty("一氧化碳")
    private double carbon_monoxide;
    @ApiModelProperty("地区")
    private String address;

    public double getAmmonia() {
        return ammonia;
    }

    public void setAmmonia(double ammonia) {
        this.ammonia = ammonia;
    }

    public double getSulfur_dioxide() {
        return sulfur_dioxide;
    }

    public void setSulfur_dioxide(double sulfur_dioxide) {
        this.sulfur_dioxide = sulfur_dioxide;
    }

    public double getHydrogen_sulfide() {
        return hydrogen_sulfide;
    }

    public void setHydrogen_sulfide(double hydrogen_sulfide) {
        this.hydrogen_sulfide = hydrogen_sulfide;
    }

    public double getNitrogen_dioxide() {
        return nitrogen_dioxide;
    }

    public void setNitrogen_dioxide(double nitrogen_dioxide) {
        this.nitrogen_dioxide = nitrogen_dioxide;
    }

    public double getCarbon_monoxide() {
        return carbon_monoxide;
    }

    public void setCarbon_monoxide(double carbon_monoxide) {
        this.carbon_monoxide = carbon_monoxide;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Gas{" +
                "ammonia=" + ammonia +
                ", sulfur_dioxide=" + sulfur_dioxide +
                ", hydrogen_sulfide=" + hydrogen_sulfide +
                ", nitrogen_dioxide=" + nitrogen_dioxide +
                ", carbon_monoxide=" + carbon_monoxide +
                ", address='" + address + '\'' +
                '}';
    }
}
