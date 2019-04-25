package com.ky.gps.util;

import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.entity.SbGps;
import com.ky.gps.sys.ParseGPS;
import com.ky.gps.util.SbBusPositionServiceUtil;

public class ParseGPSUtil {
	
	private SbBusPosition sbBusPosition;
	private SbBusPositionServiceUtil sbpsu;
	private SbGps sbGps;
		
	public ParseGPSUtil() {
		super();
	}

	public void init() {
		sbBusPosition = new SbBusPosition();
		sbpsu = new SbBusPositionServiceUtil();
		sbGps = new SbGps();
	}

	public void setSbGPS(String id) {
		sbGps.setId(id);
	}

	public void parse(String sign) {
		ParseGPS parseGPS = new ParseGPS();
		parseGPS.setSbBusPosition(sbBusPosition);
//		String sign = "$GPRMC,024813.640,A,3011.034344544,N,12048.852860598,E,10.05,324.27,150706,A*50";
		switch (parseGPS.parse(sign)) {
		case 0:
			System.out.println("定位成功！");
			sbBusPosition.setSbGps(sbGps);
			sbpsu.init();
			sbpsu.save(sbBusPosition);
			break;
		case -1:
			System.err.println("错误信息");
			break;
		case 2:
			System.err.println("非GPRMC信息");
			break;
		case 3:
			System.err.println("无效定位");
			break;
		case 4:
			System.err.println("格式错误");
			break;
		case 5:
			System.err.println("校验错误");
			break;
		case 404:
			System.err.println("数据丢失");
			break;
		default:
			System.err.println("定位信息无效");
			break;
		}
	}

}