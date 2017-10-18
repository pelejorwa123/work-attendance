package com.pele.service;

import com.pele.common.pojo.PageParam;
import com.pele.common.pojo.PageResult;
import com.pele.pojo.Attend;

public interface AttendService {
    public void attend(Attend attend);
    public PageResult getAttendListByPageParam(PageParam pageParam);
}
