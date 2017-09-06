package com.myloe.attend.vo;

import com.myloe.commons.page.PageQueryBean;

public class PageCondition extends PageQueryBean {

    private Long userId;
    private String startDate;
    private String endDate;
    private String rangeDate;
    private Byte attendStatus;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRangeDate() {
        return rangeDate;
    }

    public void setRangeDate(String rangeDate) {
        this.rangeDate = rangeDate;
    }

    public Byte getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(Byte attendStatus) {
        this.attendStatus = attendStatus;
    }
}
