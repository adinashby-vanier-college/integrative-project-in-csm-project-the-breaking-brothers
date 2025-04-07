package com.example.chemistryapp.Controller;

public class ElementData {
    private String name;
    private String symbol;
    private int atomicNumber;
    private String density;
    private String meltingPoint;
    private String boilingPoint;
    private String group;
    private String period;
    private String electronConfiguration;
    private String technicalData;
    private String description;
    private String atomicWeight;

    public ElementData(String name, String symbol, int atomicNumber) {
        this.name = name;
        this.symbol = symbol;
        this.atomicNumber = atomicNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public void setAtomicNumber(int atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getMeltingPoint() {
        return meltingPoint;
    }

    public void setMeltingPoint(String meltingPoint) {
        this.meltingPoint = meltingPoint;
    }

    public String getBoilingPoint() {
        return boilingPoint;
    }

    public void setBoilingPoint(String boilingPoint) {
        this.boilingPoint = boilingPoint;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getElectronConfiguration() {
        return electronConfiguration;
    }

    public void setElectronConfiguration(String electronConfiguration) {
        this.electronConfiguration = electronConfiguration;
    }

    public String getTechnicalData() {
        return technicalData;
    }

    public void setTechnicalData(String technicalData) {
        this.technicalData = technicalData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAtomicWeight() {
        return atomicWeight;
    }

    public void setAtomicWeight(String atomicWeight) {
        this.atomicWeight = atomicWeight;
    }
}