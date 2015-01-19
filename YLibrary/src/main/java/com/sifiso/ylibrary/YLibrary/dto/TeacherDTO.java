package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class TeacherDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer teacherID;
    private String name;
    private String surname;
    private String idnumber;
    private String email;
    private String cell;
    private String password;
    private String sessionID;
    private List<IssueDTO> issueList = new ArrayList<IssueDTO>();
    private List<GcmdeviceDTO> gcmdeviceList = new ArrayList<GcmdeviceDTO>();
    private List<ClazzteacherDTO> clazzteacherList = new ArrayList<ClazzteacherDTO>();
    private List<AttendenceDTO> attendenceList = new ArrayList<AttendenceDTO>();
    private List<TeachersubjectDTO> teachersubjectList = new ArrayList<TeachersubjectDTO>();

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public List<IssueDTO> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<IssueDTO> issueList) {
        this.issueList = issueList;
    }

    public List<GcmdeviceDTO> getGcmdeviceList() {
        return gcmdeviceList;
    }

    public void setGcmdeviceList(List<GcmdeviceDTO> gcmdeviceList) {
        this.gcmdeviceList = gcmdeviceList;
    }

    public List<ClazzteacherDTO> getClazzteacherList() {
        return clazzteacherList;
    }

    public void setClazzteacherList(List<ClazzteacherDTO> clazzteacherList) {
        this.clazzteacherList = clazzteacherList;
    }

    public List<AttendenceDTO> getAttendenceList() {
        return attendenceList;
    }

    public void setAttendenceList(List<AttendenceDTO> attendenceList) {
        this.attendenceList = attendenceList;
    }

    public List<TeachersubjectDTO> getTeachersubjectList() {
        return teachersubjectList;
    }

    public void setTeachersubjectList(List<TeachersubjectDTO> teachersubjectList) {
        this.teachersubjectList = teachersubjectList;
    }
}
