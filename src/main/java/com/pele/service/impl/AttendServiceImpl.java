package com.pele.service.impl;

import com.pele.common.utils.DateUtils;
import com.pele.mapper.AttendMapper;
import com.pele.pojo.Attend;
import com.pele.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class AttendServiceImpl implements AttendService{

    @Value("${STANDARD_HOUR}")
    int STANDARD_HOUR;

    @Value("${STANDARD_MINUTE}")
    int STANDARD_MINUTE;

    @Autowired
    AttendMapper attendMapper;

    /**
     *@author: pele
     *@time: 2017/10/18 12:06
     *@package: com.pele.service.impl
     *@descroption:
     */
    @Override
    public void attend(Attend attend) {
        //首先，判断今天是否已经打过卡
        Date now = new Date();
        Date standard = DateUtils.getDateByHourAndMinute(STANDARD_HOUR,STANDARD_MINUTE);
        attend.setAttendDate(now);
        Attend attendResult = attendMapper.selectByUserIdAndDate(attend);
        //如果没有打卡，则进行插入打卡记录操作
        if (attendResult==null){
            //如果是上午，则进行上午打卡操作
            //如果是下午，则进行下午打卡操作
            if(DateUtils.getTimeState(now,standard)==0){
                attend.setAttendDate(now);
                attend.setAttendMorning(now);
                attend.setAttendWeek((byte) DateUtils.getWeekday());
            }else{
                attend.setAttendDate(now);
                attend.setAttendEvening(now);
                attend.setAttendWeek((byte) DateUtils.getWeekday());
            }
            //插入到数据库
            attendMapper.insertSelective(attend);
        }else{
            //如果已经打卡，则进行更新操作
            //如果是上午，如果已经打卡，则不更新，防止重复打卡
            if(DateUtils.getTimeState(now,standard)==0){
                //没有打卡，要进行更新
                if(attendResult.getAttendMorning()==null){
                    attend.setAttendDate(now);
                    attend.setAttendMorning(now);
                    attend.setAttendWeek((byte) DateUtils.getWeekday());
                    attend.setId(attendResult.getId());
                    attendMapper.updateByPrimaryKey(attend);
                }
            }else{
                //下午打卡
                //不管有没有下午打卡的记录，都要进行更新
                attend.setId(attendResult.getId());
                attend.setAttendWeek((byte) DateUtils.getWeekday());
                attend.setAttendDate(now);
                attend.setAttendEvening(now);
                attendMapper.updateByPrimaryKey(attend);
            }
        }
    }
}
