package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class ClazzDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer clazzID, schoolID;
    private String className;
    private Integer totalStudentsPerClazz;
    private Integer activeFlag;
    private List<ClazzstudentDTO> clazzstudentList = new ArrayList<ClazzstudentDTO>();
    private SchoolDTO school;
    private List<ClazzteacherDTO> clazzteacherList = new ArrayList<ClazzteacherDTO>();

    public ClazzDTO() {
    }


    public Integer getClazzID() {
        return clazzID;
    }

    public void setClazzID(Integer clazzID) {
        this.clazzID = clazzID;
    }

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getTotalStudentsPerClazz() {
        return totalStudentsPerClazz;
    }

    public void setTotalStudentsPerClazz(Integer totalStudentsPerClazz) {
        this.totalStudentsPerClazz = totalStudentsPerClazz;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public List<ClazzstudentDTO> getClazzstudentList() {
        return clazzstudentList;
    }

    public void setClazzstudentList(List<ClazzstudentDTO> clazzstudentList) {
        this.clazzstudentList = clazzstudentList;
    }

    public SchoolDTO getSchool() {
        return school;
    }

    public void setSchool(SchoolDTO school) {
        this.school = school;
    }

    public List<ClazzteacherDTO> getClazzteacherList() {
        return clazzteacherList;
    }

    public void setClazzteacherList(List<ClazzteacherDTO> clazzteacherList) {
        this.clazzteacherList = clazzteacherList;
    }
}
