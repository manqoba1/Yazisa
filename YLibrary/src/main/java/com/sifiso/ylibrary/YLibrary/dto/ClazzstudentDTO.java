package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class ClazzstudentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clazzStudentID, clazzID, studentID;
    private long clazzYear;
    private ClazzDTO clazz;
    private StudentDTO student;

    public ClazzstudentDTO() {
    }


    public Integer getClazzStudentID() {
        return clazzStudentID;
    }

    public void setClazzStudentID(Integer clazzStudentID) {
        this.clazzStudentID = clazzStudentID;
    }

    public Integer getClazzID() {
        return clazzID;
    }

    public void setClazzID(Integer clazzID) {
        this.clazzID = clazzID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public long getClazzYear() {
        return clazzYear;
    }

    public void setClazzYear(long clazzYear) {
        this.clazzYear = clazzYear;
    }

    public ClazzDTO getClazz() {
        return clazz;
    }

    public void setClazz(ClazzDTO clazz) {
        this.clazz = clazz;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}
