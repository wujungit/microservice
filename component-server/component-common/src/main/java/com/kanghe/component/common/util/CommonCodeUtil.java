package com.kanghe.component.common.util;

import java.util.Random;

/**
 * @Author： Jaince
 * @Date： 2018/06/13
 * @Description：通用工具类
 */
public class CommonCodeUtil {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public static final int LENGTH = 8;


    /**
     * 生成固定长度的随机数字
     *
     * @param length
     * @return
     */
    public static String randomNumber(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 生成带前缀的随机编码
     *
     * @param prefix
     * @return
     */
    public static String generateRandomCoded(String prefix) {
        return prefix + generateRandomCode();
    }

    /**
     * 生成带前缀的随机编码
     *
     * @return
     */
    public static String generateRandomCode() {
        return System.currentTimeMillis() + randomNumber(4);
    }


}
