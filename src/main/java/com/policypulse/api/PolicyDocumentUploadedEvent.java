package com.policypulse.api;

public class PolicyDocumentUploadedEvent {

    private String eventType;
    private Long policyId;
    private String policyNumber;
    private String documentKey;
    private String uploadedAt;

    public PolicyDocumentUploadedEvent() {
    }

    public PolicyDocumentUploadedEvent(
            String eventType,
            Long policyId,
            String policyNumber,
            String documentKey,
            String uploadedAt
    ) {
        this.eventType = eventType;
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.documentKey = documentKey;
        this.uploadedAt = uploadedAt;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}