package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class ProvinceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer provinceID;

    private String provinceName;
    private Double latitude;
    private Double longitude;
    private Integer countryID;
    private List<TownshipDTO> townshipList = new ArrayList<TownshipDTO>();

    public ProvinceDTO() {
    }

    public ProvinceDTO(Integer provinceID) {
        this.provinceID = provinceID;
    }


    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getcountryID() {
        return countryID;
    }

    public void setcountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public List<TownshipDTO> getTownshipList() {
        return townshipList;
    }

    public void setTownshipList(List<TownshipDTO> townshipList) {
        this.townshipList = townshipList;
    }

}
