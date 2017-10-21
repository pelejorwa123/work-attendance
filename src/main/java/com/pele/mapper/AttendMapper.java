package com.pele.mapper;

import com.pele.common.pojo.PageParam;
import com.pele.pojo.Attend;
import com.pele.pojo.AttendExample;
import com.pele.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendMapper {
    long countByExample(AttendExample example);

    int deleteByExample(AttendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attend record);

    int insertSelective(Attend record);

    List<Attend> selectByExample(AttendExample example);

    Attend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByExample(@Param("record") Attend record, @Param("example") AttendExample example);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectByUserIdAndDate(Attend attend);
    //先根据筛选条件去查看是否有符合条件的记录
    int countAttendByPageParam(PageParam pageParam);
    //根据筛选条件和分页参数进行查询
    List<Attend> selectAttendByPageParam(PageParam pageParam);
    //找出当天所有不存在打卡记录的用户
    List<User> selectUnattendedUsers(Date date);
    //找出异常的打卡记录
    List<Attend> selectAbnormalAttendList(Date date);
    //根据日期找出当天所有的打卡记录
    List<Attend> selectAllAttendByDate(Date date);
}