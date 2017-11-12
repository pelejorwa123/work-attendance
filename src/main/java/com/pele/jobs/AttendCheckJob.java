package com.pele.jobs;

import com.pele.common.utils.DateUtils;
import com.pele.mapper.AttendMapper;
import com.pele.pojo.Attend;
import com.pele.pojo.User;
import com.pele.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/*
*@author: pele
*@time: 2017/10/19 16:17
*@project: work-attendance
*@description:使用quartz框架进行定时任务，在每晚固定的时间进行数据库打卡记录的扫描，标记有问题的打卡记录
*/
@Transactional
public class AttendCheckJob {
    //缺勤全天的分钟数
    private static final int ABSENCE_DAY = 480;
    //允许工作时间误差五分钟，设置检验标准时间
    private static final int WORK_ATTEND_TIME = 475;
    //考勤状态为正常，值为1
    private static final Byte NORMAL_ATTEND_STATUS = 1;
    //考勤状态为异常，值为2
    private static final Byte ABNORMAL_ATTEND_STATUS = 2;

    @Autowired
    AttendService attendService;

    @Autowired
    JavaMailSender mailSender;


    /**
     *@author: pele
     *@time: 2017/10/19 21:05
     *@package: com.pele.jobs
     *@descroption:每天晚上对打卡记录进行审核的函数
     */
    public void attendCheck(){
        Date date = new Date();//拿到当天的日期和时间
        //第一步，对今天正常打卡，但是时间不满足要求的记录进行统计，并更新缺席时间
        List<Attend> allAttendList = attendService.getAllAttendByDate(date);
        for (Attend attend : allAttendList){
            //如果两次记录都存在，计算打卡的两次记录的时间差，如果小于480分钟，就更新缺席时间
            if(attend.getAttendMorning()!=null&&attend.getAttendEvening()!=null){
                Date attendMorning = attend.getAttendMorning();
                Date attendEvening = attend.getAttendEvening();
                long time = attendEvening.getTime()-attendMorning.getTime();//拿到毫秒差
                time = time/(1000*60);//转化成分钟数
                //允许有五分钟以内的误差
                if(time<WORK_ATTEND_TIME){
                    attend.setAbsence((int)(ABSENCE_DAY-time));
                    attend.setAttendStatus(ABNORMAL_ATTEND_STATUS);
                }else{
                    //把没有出现异常的打卡记录全部设置为打卡状态正常
                    attend.setAttendStatus(NORMAL_ATTEND_STATUS);
                }
            }
            attendService.updateAttend(attend);
        }
        //第二步，一定要在插入记录前面进行，如果用户有今天的打卡记录，但是打卡记录不完整(上午没有打卡，或者下午没有打卡），设置为异常，并设置缺席时间480分钟
        List<Attend> abNormalAttendList = findAbnormalAttendList(date);
        for (Attend attend : abNormalAttendList){
            attend.setAttendStatus((byte) ABNORMAL_ATTEND_STATUS);
            attend.setAbsence(ABSENCE_DAY);
            attendService.updateAttend(attend);
        }
        //然后，查找出今天在数据库中不存在打卡记录的人，插入记录，并设置考勤状态为2,缺勤时长为480分钟
        List<User> userList = findUnattendedUsers(date);
        //循环遍历这个list，插入数据库，并给用户发一封邮件
        for(User user : userList){
            sendMessage(user,date);
            Attend attend = new Attend();
            attend.setUserId(user.getId());
            attend.setAttendDate(date);
            attend.setAttendWeek((byte) DateUtils.getWeekday(date));
            attend.setAbsence(ABSENCE_DAY);
            attend.setAttendStatus(ABNORMAL_ATTEND_STATUS);
            attendService.attendBySystem(attend);
        }

    }


    /**
     *@author: pele
     *@time: 2017/10/19 21:06
     *@package: com.pele.jobs
     *@descroption:找出今天数据库里面没有打卡记录的人
     */
    private List<User> findUnattendedUsers(Date date){
        List<User> userList = attendService.getUnCheckedUserList(date);
        return userList;
    }

    private List<Attend> findAbnormalAttendList(Date date){
        List<Attend> abNormalAttendList = attendService.findAbnormalAttendList(date);
        return  abNormalAttendList;
    }


    /**
     *@author: pele
     *@time: 2017/11/7 15:29
     *@package: com.pele.jobs
     *@descroption:给当天打卡状态不正常的员工发送邮件
     */
    private void sendMessage(User user,Date date){
        String username = user.getUsername();
        String email = user.getEmail();
        String subject = "考勤异常通知";
        String content = "<div>" +
                            "<p>xx,你在xxx打卡记录异常,请登录系统后查看</p>" +
                            "<a href=\"http://localhost:8080/login/index\">去登陆</a>" +
                         "</div>";

        MimeMessage message = mailSender.createMimeMessage();
        try {
            InternetAddress fromAddress = new InternetAddress("1461775766@qq.com");
            InternetAddress toAddress = new InternetAddress(email);
            message.setSubject(subject);
            message.setFrom(fromAddress);
            message.addRecipient(MimeMessage.RecipientType.TO,toAddress);
            message.setContent(content,"text/html;charset=utf-8");
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
