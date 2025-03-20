package model;

import java.util.Date;

public class LeaveRequest {
    private int requestId;
    private String title;
    private Date fromDate;
    private Date toDate;
    private String reason;
    private int createdBy;
    private String status;
    private Integer processedBy;

    public LeaveRequest() {}

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Date getFromDate() { return fromDate; }
    public void setFromDate(Date fromDate) { this.fromDate = fromDate; }
    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getProcessedBy() { return processedBy; }
    public void setProcessedBy(Integer processedBy) { this.processedBy = processedBy; }
}