package com.sifiso.ylibrary.YLibrary.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeTribe1 on 2014-11-15.
 */
public class StudentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer studentID, parentID,countAbsent,countAttendant,countLate;
    private String name;
    private String surname;
    private String idNumber;
    private long dateOfBirth;
    private String email;
    private String cell;
    private String password;
    private ParentDTO parent;
    private String sessionID;
    private List<IssueDTO> issueList = new ArrayList<IssueDTO>();
    private List<GcmdeviceDTO> gcmdeviceList = new ArrayList<GcmdeviceDTO>();
    private List<ClazzstudentDTO> clazzstudentList = new ArrayList<ClazzstudentDTO>();
    private List<AttendenceDTO> attendenceList = new ArrayList<AttendenceDTO>();

    public Integer getCountAbsent() {
        return countAbsent;
    }

    public void setCountAbsent(Integer countAbsent) {
        this.countAbsent = countAbsent;
    }

    public Integer getCountAttendant() {
        return countAttendant;
    }

    public void setCountAttendant(Integer countAttendant) {
        this.countAttendant = countAttendant;
    }

    public Integer getCountLate() {
        return countLate;
    }

    public void setCountLate(Integer countLate) {
        this.countLate = countLate;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public ParentDTO getParent() {
        return parent;
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
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

    public List<ClazzstudentDTO> getClazzstudentList() {
        return clazzstudentList;
    }

    public void setClazzstudentList(List<ClazzstudentDTO> clazzstudentList) {
        this.clazzstudentList = clazzstudentList;
    }

    public List<AttendenceDTO> getAttendenceList() {
        return attendenceList;
    }

    public void setAttendenceList(List<AttendenceDTO> attendenceList) {
        this.attendenceList = attendenceList;
    }
}
