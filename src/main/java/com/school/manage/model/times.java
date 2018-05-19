package com.school.manage.model;

public class times {
    private Integer datasId;

    private String datesName;

    public Integer getDatasId() {
        return datasId;
    }

    public void setDatasId(Integer datasId) {
        this.datasId = datasId;
    }

    public String getDatesName() {
        return datesName;
    }

    public void setDatesName(String datesName) {
        this.datesName = datesName == null ? null : datesName.trim();
    }
}