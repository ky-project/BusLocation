package com.ky.gps.util;

import com.ky.gps.entity.SbStation;

/**
 * 站点对象相关工具类
 *
 * @author Daye
 */
public class SbStationUtil {

    /**
     * 对象校验，校验其重要属性是否为null
     *
     * @param sbStation 站点对象
     * @return 返回true/false
     */
    public static Boolean verityContainId(SbStation sbStation) {
        return IntegerUtil.isValid(sbStation.getId())
                && StringUtil.isNotEmpty(sbStation.getSbsStation())
                && DoubleUtil.isValid(sbStation.getSbsLongitude())
                && DoubleUtil.isValid(sbStation.getSbsLatitude());
    }

    /**
     * 对象校验，校验其重要属性是否为null
     *
     * @param sbStation 站点对象
     * @return 返回true/false
     */
    public static Boolean verity(SbStation sbStation) {
        return StringUtil.isNotEmpty(sbStation.getSbsStation())
                && DoubleUtil.isValid(sbStation.getSbsLongitude())
                && DoubleUtil.isValid(sbStation.getSbsLatitude());
    }
}
