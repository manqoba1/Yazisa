package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;

/**
 * Created by CodeTribe1 on 2014-11-22.
 */
public class DeviceerrorDTO implements Serializable {

    private Integer deviceErrorID,schoolID;
    private long errorDate;
    private String packageName;
    private String appVersionName;
    private String appVersionCode;
    private String brand;
    private String phoneModel;
    private String androidVersion;
    private String stackTrace;
    private String logCat;
    private SchoolDTO school;

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getDeviceErrorID() {
        return deviceErrorID;
    }

    public void setDeviceErrorID(Integer deviceErrorID) {
        this.deviceErrorID = deviceErrorID;
    }

    public long getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(long errorDate) {
        this.errorDate = errorDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getLogCat() {
        return logCat;
    }

    public void setLogCat(String logCat) {
        this.logCat = logCat;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public void setSchool(SchoolDTO school) {
        this.school = school;
    }
}
