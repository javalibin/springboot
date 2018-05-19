package com.school.manage.model;

public class Subject {
    private Integer subjectId;

    private String subjectName;

    private Integer datesId;

    private String other;

    private String cloun;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public Integer getDatesId() {
        return datesId;
    }

    public void setDatesId(Integer datesId) {
        this.datesId = datesId;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getCloun() {
        return cloun;
    }

    public void setCloun(String cloun) {
        this.cloun = cloun == null ? null : cloun.trim();
    }
}