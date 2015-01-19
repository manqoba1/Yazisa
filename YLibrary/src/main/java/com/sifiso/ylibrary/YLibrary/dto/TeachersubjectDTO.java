package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class TeachersubjectDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer teacherSubjectID, subjectID, teacherID;
    private TeacherDTO teacher;
    private SubjectDTO subject;

    public TeachersubjectDTO() {
    }

    public Integer getTeacherSubjectID() {
        return teacherSubjectID;
    }

    public void setTeacherSubjectID(Integer teacherSubjectID) {
        this.teacherSubjectID = teacherSubjectID;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }
}
