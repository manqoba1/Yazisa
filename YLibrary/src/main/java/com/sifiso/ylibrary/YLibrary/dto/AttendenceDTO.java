package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;

/**
 * Created by CodeTribe1 on 2014-11-22.
 */
public class AttendenceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer attendenceID, subjectID, studentID, teacherID;
    private long dateAttended;
    private StudentDTO student;
    private SubjectDTO subject;
    private Integer attendenceFlag;
    private String message;
    private TeacherDTO teacher;

    public AttendenceDTO() {
    }


    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public Integer getAttendenceID() {
        return attendenceID;
    }

    public void setAttendenceID(Integer attendenceID) {
        this.attendenceID = attendenceID;
    }

    public long getDateAttended() {
        return dateAttended;
    }

    public void setDateAttended(long dateAttended) {
        this.dateAttended = dateAttended;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectDTO subject) {
        this.subject = subject;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getAttendenceFlag() {
        return attendenceFlag;
    }

    public void setAttendenceFlag(Integer attendenceFlag) {
        this.attendenceFlag = attendenceFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attendenceID != null ? attendenceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AttendenceDTO)) {
            return false;
        }
        AttendenceDTO other = (AttendenceDTO) object;
        if ((this.attendenceID == null && other.attendenceID != null) || (this.attendenceID != null && !this.attendenceID.equals(other.attendenceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sifiso.yazisa.data.Attendence[ attendenceID=" + attendenceID + " ]";
    }

}
