package com.ky.gps.util;

import com.ky.gps.entity.SbRoute;

/**
 * 路线工具类
 *
 * @author Darren
 */
public class SbRouteUtil {

    /**
     * 判断路线信息是否完整
     *
     * @param sbRoute 待判断的路线对象
     * @return 返回true/false
     */
    public static boolean veritySbRouteExcludeId(SbRoute sbRoute) {
        return sbRoute != null
                && StringUtil.isNotEmpty(sbRoute.getSbrRouteName())
                && StringUtil.isNotEmpty(sbRoute.getSbrEndStation())
                && StringUtil.isNotEmpty(sbRoute.getSbrDepartTime());
    }

    /**
     * 判断路线信息是否完整
     *
     * @param sbRoute 待判断的路线对象
     * @return 返回true/false
     */
    public static boolean veritySbRoute(SbRoute sbRoute) {
        return sbRoute != null
                && IntegerUtil.isValid(sbRoute.getId())
                && StringUtil.isNotEmpty(sbRoute.getSbrRouteName())
                && StringUtil.isNotEmpty(sbRoute.getSbrEndStation())
                && StringUtil.isNotEmpty(sbRoute.getSbrDepartTime());
    }
}
