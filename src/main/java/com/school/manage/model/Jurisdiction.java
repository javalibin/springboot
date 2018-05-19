package com.school.manage.model;

public class Jurisdiction {
    private String jurisdictionId;

    private String subject;

    private String message;

    private String usermanage;

    public String getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(String jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getUsermanage() {
        return usermanage;
    }

    public void setUsermanage(String usermanage) {
        this.usermanage = usermanage == null ? null : usermanage.trim();
    }
}