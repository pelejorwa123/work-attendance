package com.pele.service;

import com.pele.common.pojo.PageParam;
import com.pele.common.pojo.PageResult;
import com.pele.pojo.Attend;
import com.pele.pojo.User;

import java.util.Date;
import java.util.List;

public interface AttendService {
    public void attend(Attend attend);
    public PageResult getAttendListByPageParam(PageParam pageParam);
    public List<User> getUnCheckedUserList(Date date);
    public void attendBySystem(Attend attend);
    public List<Attend> findAbnormalAttendList(Date date);
    public void updateAttend(Attend attend);
    public List<Attend> getAllAttendByDate(Date date);
}
