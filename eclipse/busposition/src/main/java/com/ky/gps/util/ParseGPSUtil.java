package com.ky.gps.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ky.gps.entity.SbGps;
import com.ky.gps.entity.SbTerminal;
import com.ky.gps.service.inter.SbBusPositionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import org.junit.Test;

/**
 * 
 * @author Rocky
 *
 */
public class ParseGPSUtil {
	
	private SbTerminal sbTerminal;
	private final static Logger LOGGER = LoggerFactory.getLogger(ParseGPSUtil.class);
	private SbBusPositionService sbBusPositionService;
	
	
	public ParseGPSUtil(SbTerminal sbTerminal) {
		ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        sbBusPositionService = applicationContext.getBean(SbBusPositionService.class);
		this.sbTerminal = sbTerminal;
	}

	/**
	 *  
	 * @param 返回值 1 正确 0校验失败 2非TRV信息 3无效定位 4格式错误
	 * @return
	 */
	public SbTerminal parse(String by) {
		
		try {
			
			{
				if (by == null || by.isEmpty()) {// 判断非空
					sbTerminal.getSbBusPosition().setValid(false);
					LOGGER.info("信息为空，无效");
					return sbTerminal;
				}
			}
			// if (checksum(by.getBytes()) == false)// 计算校验和并与语句中的校验和比较
			// return 5;
			
			/*
			 * 确定是TRV
			 */
			{
				if (!by.startsWith("TRV")) {
					sbTerminal.getSbBusPosition().setValid(false);
					LOGGER.info("非GPS终端信息，无效");
					return sbTerminal;
				}
			}
			
			{
				if (!by.endsWith("#")) {
					sbTerminal.getSbBusPosition().setValid(false);
					LOGGER.info("GPS信息无中断，无效");
					return sbTerminal;
				}
			}
			
			String gpsProtocol = by.substring(3, 7);
			String str = by.substring(7,by.length()-1);
			if (str.charAt(0) == ',') {
				str = str.replaceFirst(",", "");
			}
			
			/*
			 * 协议选择器
			 */
			switch (gpsProtocol){
			
				case "AP00":
					return getAP00(str);
			
				case "AP01":
					sbTerminal = getAP01(str);
					if (sbTerminal.getSbBusPosition().getValid()) {
						sbBusPositionService.savePosition(sbTerminal.getSbBusPosition());
					}
					return sbTerminal;
				
				case "CP01":
					return getCP01(str);
				
				case "YP02":
					return getYP02();
					
				default:
					LOGGER.info("协议未开通-"+gpsProtocol);
					break;
			}
			return sbTerminal;
		} catch (Exception e) {
			LOGGER.error("",e);
			sbTerminal.getSbBusPosition().setValid(false);
			return sbTerminal;
		}
		
	}

	private SbTerminal getAP00(String str) {
		if (str.length() != 15) {
			sbTerminal.getSbBusPosition().setValid(false);
			LOGGER.info("无效协议号 "+str);
			return sbTerminal;
		}
		else {
			sbTerminal.getSbBusPosition().setSbGps(new SbGps(str));
			sbTerminal.getSbBusPosition().setValid(true);
			LOGGER.info("GPS:"+sbTerminal.getSbBusPosition().getSbGps().getId()+" 登录成功");
			sbTerminal.setAgreement("TRVBP00#");
			return sbTerminal;
		}
	}
	
	private SbTerminal getAP01(String str) {
		if (str.length() < 45 || !CheckValidUtil.isValid(sbTerminal)) {
			sbTerminal.getSbBusPosition().setValid(false);
			return sbTerminal;
		}
		ArrayList<String> info = new ArrayList<>();
		info.add(str.toString().substring(0,6));
		info.add(str.toString().substring(7,16));
		info.add(str.toString().substring(17,27));
		info.add(str.toString().substring(28,33));
		info.add(str.toString().substring(33,39));
		info.add(str.toString().substring(39,45));
		
		/*
		 * 为A则有效 为V则无效
		 */
		{
			if (str.charAt(6) == 'A') {
				sbTerminal.getSbBusPosition().setValid(true);
			}
			else if (str.charAt(6) == 'V') {
				sbTerminal.getSbBusPosition().setValid(false);
				return sbTerminal;
			}
		}
		
		/*
		 * 获取时间
		 */
		{
			String hour = info.get(4).substring(0, 2);
			String minute = info.get(4).substring(2, 4);
			String second = info.get(4).substring(4);
			String hms = hour + ":" + minute + ":" + second;
			// 设置当前时间格式
			String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			sbTerminal.getSbBusPosition().setSbpRecodeTime(Timestamp.valueOf(strNow + " " + hms));
		}

		// 获取纬度
		{
			Double lat = Double.parseDouble(info.get(1).substring(0, 2));// 纬度-度
			lat += Double.parseDouble(info.get(1).substring(2)) / 60;// 纬度-分
			sbTerminal.getSbBusPosition().setSbpLatitude(lat);
		}
		
		// 纬度半球
		{
			
			if (str.charAt(16) != 'N' && str.charAt(16) != 'S') {
				sbTerminal.getSbBusPosition().setValid(false);
				return sbTerminal;
			}
			else {
				char lats = str.charAt(16);
			}
		}
		
		// 获取经度
		{
			Double lnt = Double.parseDouble(info.get(2).substring(0, 3));
			lnt += Double.parseDouble(info.get(2).substring(3)) / 60;
			sbTerminal.getSbBusPosition().setSbpLongitude(lnt);
		}
		
		// 经度半球
		{
			if (str.charAt(27) != 'E' && str.charAt(27) != 'W') {
				sbTerminal.getSbBusPosition().setValid(false);
				return sbTerminal;
			}
			else {
				char lngs = str.charAt(27);
			}
		}
		
		TransformUtil.wgs84tobd09(sbTerminal);
		
		// 地面速率
		{
			Double speed = 0D;
			if (!info.get(3).isEmpty()) {
				speed = Double.parseDouble(info.get(3));// 速度单位转换为千米每小时
			}
			sbTerminal.getSbBusPosition().setSbpVelocity(speed);
		}
		
		// 方向角
		{
			Double dir = Double.parseDouble(info.get(5));
			sbTerminal.getSbBusPosition().setSbpDirection(dir);
		}
		sbTerminal.getSbBusPosition().setValid(true);
		LOGGER.info("GPS终端id：" + sbTerminal.getSbBusPosition().getSbGps().getId());
		LOGGER.info("时间：" + sbTerminal.getSbBusPosition().getSbpRecodeTime());
		LOGGER.info("定位：" + sbTerminal.getSbBusPosition().getSbpLatitude() + "," + sbTerminal.getSbBusPosition().getSbpLongitude());
		LOGGER.info("速度：" + sbTerminal.getSbBusPosition().getSbpVelocity() + "km/h");
		LOGGER.info("方向角：" + sbTerminal.getSbBusPosition().getSbpDirection() + "度");
		sbTerminal.setAgreement("TRVBP01#");
		return sbTerminal;
	}
	
	private SbTerminal getCP01(String str) {
		if (str.length() != 28 || !CheckValidUtil.isValid(sbTerminal)) {
			sbTerminal.getSbBusPosition().setValid(false);
			return sbTerminal;
		}
		else {
			LOGGER.info("GPS:"+sbTerminal.getSbBusPosition().getSbGps().getId()+" 心跳检测");
			sbTerminal.getSbBusPosition().setValid(true);
			sbTerminal.setAgreement("TRVDP01#");
			return sbTerminal;
		}
	}
	
	private SbTerminal getYP02() {
		if (!CheckValidUtil.isValid(sbTerminal)) {
			sbTerminal.getSbBusPosition().setValid(false);
			return sbTerminal;
		}else {
			LOGGER.info("GPS:"+sbTerminal.getSbBusPosition().getSbGps().getId()+" IMSI号及ICCID号码响应");
			sbTerminal.getSbBusPosition().setValid(true);
			sbTerminal.setAgreement("TRVZP02#");
			return sbTerminal;
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