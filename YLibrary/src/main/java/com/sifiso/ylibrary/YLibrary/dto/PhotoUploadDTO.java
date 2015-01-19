package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-14.
 */
public class PhotoUploadDTO implements Serializable {
    public static final int SCHOOL_IMAGE = 1, CLASS_IMAGE = 2, PARENT_IMAGE = 3, STUDENT_IMAGE = 4, TEACHER_IMAGE = 5;
    private Integer schoolID, classID, studentID, parentID, teacherID, pictureType;
    private List<String> tags;
    private boolean isFullPicture;

    public Integer getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(Integer schoolID) {
        this.schoolID = schoolID;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getPictureType() {
        return pictureType;
    }

    public void setPictureType(Integer pictureType) {
        this.pictureType = pictureType;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isFullPicture() {
        return isFullPicture;
    }

    public void setFullPicture(boolean isFullPicture) {
        this.isFullPicture = isFullPicture;
    }
}
