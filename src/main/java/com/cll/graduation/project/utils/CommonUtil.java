package com.cll.graduation.project.utils;

public class CommonUtil {
    public static String getRandom6() {
        return (int)((Math.random()*9+1)*100000) + "";
    }
}
