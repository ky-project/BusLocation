package com.ky.gps.sys;

import com.ky.gps.entity.SbBusPosition;
import com.ky.gps.entity.SbGps;
import com.ky.gps.entity.SbTerminal;
import com.ky.gps.service.inter.SbBusPositionService;
import com.ky.gps.util.CheckValidUtil;
import com.ky.gps.util.ParseGPSUtil;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseGPS {
	
	private SbTerminal sbTerminal;
	private ParseGPSUtil parseGPSUtil;
	private final Logger LOGGER = LoggerFactory.getLogger(ParseGPS.class);
	
	public ParseGPS() {
	}

	public void init() {
		sbTerminal = new SbTerminal("", new SbBusPosition(), "");
	}

	public String parse(String sign) {
		parseGPSUtil = new ParseGPSUtil(sbTerminal);
		sbTerminal = parseGPSUtil.parse(sign);
//		String sign = "$GPRMC,024813.640,A,3011.034344544,N,12048.852860598,E,10.05,324.27,150706,A*50";
		if (sbTerminal.getSbBusPosition().getValid()) {
			LOGGER.info("GPS:"+sbTerminal.getSbBusPosition().getSbGps().getId()+" 信息接收");
			return sbTerminal.getAgreement();
		}
		else {
			if (CheckValidUtil.isValid(sbTerminal)) {
				LOGGER.info("GPS:"+sbTerminal.getSbBusPosition().getSbGps().getId()+" 无效信息");
			}
			else {
				LOGGER.info("GPS:null 无效信息");
			}
			return sbTerminal.getAgreement();
		}
	}

}
