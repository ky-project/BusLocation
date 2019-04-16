package com.ky.gps.sys;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.ky.gps.entity.SbBusPosition;

//import org.junit.Test;

/**
 * 
 * @author Alienware
 *
 */

public class ParseGPS {
	
	private boolean valid;

	private SbBusPosition sbBusPosition;

	public ParseGPS() {
	}
	
	
	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public SbBusPosition getSbBusPosition() {
		return sbBusPosition;
	}


	public void setSbBusPosition(SbBusPosition sbBusPosition) {
		this.sbBusPosition = sbBusPosition;
	}


	// 返回值 0 正确 1校验失败 2非GPRMC信息 3无效定位 4格式错误 5校验错误
	public int parse(String by) {
		
		try {
			char lats;
			char lngs;
			if (by == null || by.isEmpty()) {// 判断非空
				return 4;
			}
			// if (checksum(by.getBytes()) == false)// 计算校验和并与语句中的校验和比较
			// return 5;
			StringTokenizer str = new StringTokenizer(new String(by), ",");
			String temp = null;
			
			// 确定是$GPRMC
			{
				temp = str.nextToken();
				if (!temp.equals("$GPRMC")) {
					return 2;
				}
			}

			// 获取时间
			{
				temp = str.nextToken();
				short hour = (short) Integer.parseInt(temp.substring(0, 2));
				short minute = (short) Integer.parseInt(temp.substring(2, 4));
				short second = (short) Float.parseFloat(temp.substring(4));
				String hms = hour + ":" + minute + ":" + second;
				// 设置当前时间格式
				String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				
				sbBusPosition.setSbpRecodeTime(Timestamp.valueOf(strNow + " " + hms));
			}

			// 为A则有效 为V则无效
			{
				temp = str.nextToken();
				if (temp.length() != 1) {
					return 404;
				}
				else if (temp.charAt(0) == 'V') {
					return 3;
				}
			}

			// 获取纬度
			{
				temp = str.nextToken();// 纬度
				Double lat = Double.parseDouble(temp.substring(0, 2));// 纬度-度
				lat += Double.parseDouble(temp.substring(2)) / 60;// 纬度-分
				sbBusPosition.setSbpLatitude(lat);
			}
			
			// 纬度半球
			{
				temp = str.nextToken();
				if (temp.length() != 1) {
					return 404;
				}
				else if (temp.charAt(0) == 'N') {
					lats = 'N';
				}
				else if (temp.charAt(0) == 'S') {
					lats = 'S';
				}
				else { // 错误信息
					return -1;
				}
			}
			
			// 获取经度
			{
				temp = str.nextToken();
				Double lnt = Double.parseDouble(temp.substring(0, 3));
				lnt += Double.parseDouble(temp.substring(3)) / 60;
				sbBusPosition.setSbpLongitude(lnt);
			}
			
			// 经度半球
			{
				temp = str.nextToken();// 经度半球
				 if (temp.length() != 1) {
					 return 404;
				 }
				 else if (temp.charAt(0) == 'E') {
					 lngs = 'E';
				 }
				 else if (temp.charAt(0) == 'W') {
					 lngs = 'W';
				 }
				 else {
					 return -1;
				 }
			}
			
			// 地面速率
			{
				temp = str.nextToken();// 地面速率
				Double speed = 0D;
				if (!temp.isEmpty()) {
					speed = Double.parseDouble(temp) * 1.852;// 速度单位转换为千米每小时
				}
				sbBusPosition.setSbpVelocity(speed);
			}
			
			// 方向角
			{
				temp = str.nextToken();
				Double dir = Double.parseDouble(temp);
				sbBusPosition.setSbpDirection(dir);
			}
			
			this.valid = true;// 转换成功，产生的数据有效
			sbBusPosition.setValid(valid);
			System.out.println("时间：" + sbBusPosition.getSbpRecodeTime());
			System.out.println("定位：" + sbBusPosition.getSbpLatitude() + "," + sbBusPosition.getSbpLongitude());
			System.out.println("速度：" + sbBusPosition.getSbpVelocity() + "km/h");
			System.out.println("方向角：" + sbBusPosition.getSbpDirection() + "度");
			return 0;
		} catch (Exception e) {
			return -1;
		}
		
	}

	private boolean checksum(byte[] b) {
		byte chk = 0;// 校验和
		byte cb = b[1];// 当前字节
		for (byte c : b) {
			System.out.print((char) c);
		}
		System.out.println();
		int i = 0;
		if (b[0] != '$')
			return false;
		for (i = 2; i < b.length; i++)// 计算校验和
		{
			if (b[i] == '*')
				break;
			cb = (byte) (cb ^ b[i]);
			System.out.print(cb + " ");
		}
		if (i != b.length - 3)// 校验位不正常
			return false;
		i++;
		byte[] bb = new byte[2];// 用于存放语句后两位
		bb[0] = b[i++];
		bb[1] = b[i];
		try {
			chk = (byte) Integer.parseInt(new String(bb), 16);// 后两位转换为一个字节
		} catch (Exception e)// 后两位无法转换为一个字节，格式错误
		{
			return false;
		}
		System.out.println("校验信息");
		System.out.println(" 原文：" + chk);
		System.out.println(" 计算：" + cb);
		return chk == cb;// 计算出的校验和与语句的数据是否一致
	}
}