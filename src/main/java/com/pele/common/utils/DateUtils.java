package com.pele.common.utils;

import java.util.Calendar;
import java.util.Date;

/*
*@author: pele
*@time: 2017/10/18 12:58
*@project: work-attendance
*@description:时间处理工具类
*/
public class DateUtils {

    /**
     *@author: pele
     *@time: 2017/10/18 12:58
     *@package: com.pele.common.utils
     *@descroption:得到当前时间的星期几
     */
    public static int getWeekday(){
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(new Date());
        //换算成中国的表示习惯
        int weekday=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (weekday==0){
            weekday=7;
        }
        return weekday;
    }

    /**
     *@author: pele
     *@time: 2017/10/18 13:29
     *@package: com.pele.common.utils
     *@descroption:根据给定的Hour和Minute生成一个Date
     */
    public static Date getDateByHourAndMinute(int hour,int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        return  calendar.getTime();
    }
    /**
     *@author: pele
     *@time: 2017/10/18 13:28
     *@package: com.pele.common.utils
     *@descroption:判断Date是上午，还是下午,如果为上午，则返回0，如为下午，则返回1
     */
    public static int getTimeState(Date date,Date standard){
        if(date.before(standard)){
            return 0;
        }else {
            return 1;
        }
    }

}
