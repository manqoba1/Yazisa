package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class ClazzteacherDTO implements Serializable{

    private Integer clazzTeacherID, clazzID, teacherID;
    private ClazzDTO clazz;
    private TeacherDTO teacher;


    public ClazzteacherDTO() {
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getClazzID() {
        return clazzID;
    }

    public void setClazzID(Integer clazzID) {
        this.clazzID = clazzID;
    }

    public Integer getClazzTeacherID() {
        return clazzTeacherID;
    }

    public void setClazzTeacherID(Integer clazzTeacherID) {
        this.clazzTeacherID = clazzTeacherID;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public ClazzDTO getClazz() {
        return clazz;
    }

    public void setClazz(ClazzDTO clazz) {
        this.clazz = clazz;
    }

}
