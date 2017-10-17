package com.pele.service.impl;

import com.pele.mapper.AttendMapper;
import com.pele.pojo.Attend;
import com.pele.service.AttendService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AttendServiceImpl implements AttendService{

    Log log= LogFactory.getLog(AttendServiceImpl.class);


    @Autowired
    AttendMapper attendMapper;

    @Override
    public void attend(Attend attend) {
        try{
            attendMapper.insertSelective(attend);
        }catch (Exception e){
            log.error(e);
            throw e;
        }

    }
}
