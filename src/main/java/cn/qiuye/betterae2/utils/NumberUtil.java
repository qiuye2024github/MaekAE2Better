package cn.qiuye.betterae2.utils;

import cn.qiuye.betterae2.config.MAE2BConfig;

import com.glodblock.github.extendedae.config.EPPConfig;

public class NumberUtil {

    private NumberUtil() {}

    public static int ConvertLongToIntSaturating(long value) {
        int EAEConfig = EPPConfig.busSpeed;
        int BAE2Config = MAE2BConfig.INSTANCE.speed;
        long result = value * BAE2Config * EAEConfig;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }
}
