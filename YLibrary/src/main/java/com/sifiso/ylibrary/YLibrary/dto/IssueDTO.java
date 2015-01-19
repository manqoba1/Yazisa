package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;

/**
 * Created by CodeTribe1 on 2014-12-02.
 */
public class IssueDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer issueID, studentID, teacherID;
    private String message;
    private StudentDTO student;
    private TeacherDTO teacher;
    private long issueDate;

    public IssueDTO() {
    }

    public Integer getIssueID() {
        return issueID;
    }

    public void setIssueID(Integer issueID) {
        this.issueID = issueID;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }
}
