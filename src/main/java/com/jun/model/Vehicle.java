package com.jun.model;

public class Vehicle {
    private Integer id;
    private String licensePlate;
    private Float weight;
    private Integer personCarriedNumber;
    private Integer companyId;
    private Integer transportationId;
    private Integer providerId;
    private Integer locationId;
    private String unKnown;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getPersonCarriedNumber() {
        return personCarriedNumber;
    }

    public void setPersonCarriedNumber(Integer personCarriedNumber) {
        this.personCarriedNumber = personCarriedNumber;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getTransportationId() {
        return transportationId;
    }

    public void setTransportationId(Integer transportationId) {
        this.transportationId = transportationId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getUnKnown() {
        return unKnown;
    }

    public void setUnKnown(String unKnown) {
        this.unKnown = unKnown;
    }
}
