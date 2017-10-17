package com.pele.pojo;

import java.util.Date;

public class Attend {
    private Integer id;

    private Long userId;

    private Date attendDate;

    private Byte attendWeek;

    private Date attendMorning;

    private Date attendEvening;

    private Integer absence;

    private Byte attendStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(Date attendDate) { this.attendDate = attendDate; }

    public Byte getAttendWeek() {
        return attendWeek;
    }

    public void setAttendWeek(Byte attendWeek) {
        this.attendWeek = attendWeek;
    }

    public Date getAttendMorning() {
        return attendMorning;
    }

    public void setAttendMorning(Date attendMorning) {
        this.attendMorning = attendMorning;
    }

    public Date getAttendEvening() {
        return attendEvening;
    }

    public void setAttendEvening(Date attendEvening) {
        this.attendEvening = attendEvening;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public Byte getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(Byte attendStatus) {
        this.attendStatus = attendStatus;
    }
}