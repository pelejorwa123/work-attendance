package com.pele.common.utils;

import java.util.Random;

/**
 * @author 14617
 *生成商品ID的工具类
 */
public class IDUtils {
	public static long creatID() {
		//取当前时间
		long time=System.currentTimeMillis();
		//取两位随机数
		Random random=new  Random();
		Integer randomInt=random.nextInt(99);
		String temp=time+String.format("%02d", randomInt);
		long id=new Long(temp);		
		return id;
	}
}
