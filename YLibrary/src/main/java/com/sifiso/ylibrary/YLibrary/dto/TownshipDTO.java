package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class TownshipDTO implements Serializable{

    private Double latitude;
    private Double longitude;
    private static final long serialVersionUID = 1L;
    private Integer townshipID, provinceID;
    private String townshipName;
    private List<SchoolDTO> schoolList = new ArrayList<SchoolDTO>();
    private ProvinceDTO province;


    public TownshipDTO() {
    }

    public TownshipDTO(Integer townshipID) {
        this.townshipID = townshipID;
    }


    public Integer getTownshipID() {
        return townshipID;
    }

    public void setTownshipID(Integer townshipID) {
        this.townshipID = townshipID;
    }

    public List<SchoolDTO> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolDTO> schoolList) {
        this.schoolList = schoolList;
    }

    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
    }

    public ProvinceDTO getProvince() {
        return province;
    }

    public void setProvince(ProvinceDTO province) {
        this.province = province;
    }

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
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
}
